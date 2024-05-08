package id.naupal.xweighbridge.presentation.listticket

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import id.naupal.firebase.model.TicketFiler
import id.naupal.xweighbridge.CoroutineTestRule
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import id.naupal.xweighbridge.usecase.GetTicketsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class ListOfTicketViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    lateinit var getTicketsUseCase: GetTicketsUseCase

    private lateinit var viewModel: ListOfTicketViewModel

    private val getTicketsStateObserver: Observer<UiState> = mock()

    @Before
    fun before() {
        MockKAnnotations.init(this)
        viewModel = ListOfTicketViewModel(getTicketsUseCase)
        viewModel.getTicketsState.observeForever(getTicketsStateObserver)
    }

    @Test
    fun `when get ticket return success data, getTickets should be return valid value`() {

        val dummyTicket: List<Ticket> = mutableListOf()

        coEvery { getTicketsUseCase.invoke(any(), any()) } coAnswers {
            UiState.Success.GetTickets(dummyTicket)
        }

        viewModel.getTickets("", TicketFiler())

        val captor = ArgumentCaptor.forClass(UiState::class.java)

        captor.run {
            Mockito.verify(getTicketsStateObserver, Mockito.times(2)).onChanged(capture())
            TestCase.assertEquals(UiState.Success.GetTickets(dummyTicket), captor.value)
        }
    }

    @Test
    fun `when get ticket return error data, getTickets should be return error value`() {
        coEvery { getTicketsUseCase.invoke(any(), any())} coAnswers {
            UiState.Error(error = "error!!")
        }

        viewModel.getTickets("", TicketFiler())

        val captor = ArgumentCaptor.forClass(UiState::class.java)

        captor.run {
            Mockito.verify(getTicketsStateObserver, Mockito.times(2)).onChanged(capture())
            TestCase.assertEquals(UiState.Error(error = "error!!"), captor.value)
        }
    }

    @Test
    fun when_collected_flow_multiple_time_then_return_same_values() = runBlocking {
        val coldStream = flow {
            for (i in 1..5) {
                delay(100L)
                emit(i)
            }
        }
        val collect1 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")
        val collect2 = buildString {
            coldStream.collect { append(it).append(", ") }
        }.removeSuffix(", ")

        assertEquals("1, 2, 3, 4, 5", collect1)
        assertEquals("1, 2, 3, 4, 5", collect2)
    }
}