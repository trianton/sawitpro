package id.naupal.xweighbridge.presentation.inputticket

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import id.naupal.xweighbridge.CoroutineTestRule
import id.naupal.xweighbridge.model.Ticket
import id.naupal.xweighbridge.model.UiState
import id.naupal.xweighbridge.usecase.InsertTicketUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class InputTicketViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    lateinit var insertTicketUseCase: InsertTicketUseCase

    private lateinit var viewModel: InputTicketViewModel

    private val insertTicketsStateObserver: Observer<UiState> = mock()

    @Before
    fun before() {
        MockKAnnotations.init(this)
        viewModel = InputTicketViewModel(insertTicketUseCase)
        viewModel.insertTicketState.observeForever(insertTicketsStateObserver)
    }

    @Test
    fun `when insert ticket return success data, insertTicket should be return valid value`() {

        val dummyTicket = Ticket(
            id = "1234",
            dateTime = 12000897,
            licenceNumber = "BG 1234 LL",
            driverName = "Driver Name",
            inboundWeight = 100,
            outboundWeight = 10,
            netWeight = 90
        )

        coEvery { insertTicketUseCase.invoke(any()) } coAnswers {
            UiState.Success.InputTicket(dummyTicket)
        }

        viewModel.insertTicket(dummyTicket)

        val captor = ArgumentCaptor.forClass(UiState::class.java)

        captor.run {
            Mockito.verify(insertTicketsStateObserver, Mockito.times(2)).onChanged(capture())
            TestCase.assertEquals(UiState.Success.InputTicket(dummyTicket), captor.value)
        }
    }

    @Test
    fun `when insert ticket return error data, insertTicket should be return error value`() {
        val dummyTicket = Ticket(
            id = "1234",
            dateTime = 12000897,
            licenceNumber = "BG 1234 LL",
            driverName = "Driver Name",
            inboundWeight = 100,
            outboundWeight = 10,
            netWeight = 90
        )

        coEvery { insertTicketUseCase.invoke(any())} coAnswers {
            UiState.Error(error = "error!!")
        }

        viewModel.insertTicket(dummyTicket)

        val captor = ArgumentCaptor.forClass(UiState::class.java)

        captor.run {
            Mockito.verify(insertTicketsStateObserver, Mockito.times(2)).onChanged(capture())
            TestCase.assertEquals(UiState.Error(error = "error!!"), captor.value)
        }
    }
}