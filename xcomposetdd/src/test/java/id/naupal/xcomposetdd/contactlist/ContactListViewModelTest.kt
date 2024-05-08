package id.naupal.xcomposetdd.contactlist

import app.cash.turbine.test
import com.google.common.truth.Truth
import id.naupal.xcomposetdd.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactListViewModelTest {
    private val contactsRepository: ContactRepository = mock()

    private lateinit var underTest: ContactListViewModel

    @Before
    fun init() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterAll
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @BeforeEach
    fun setup() {
        reset(contactsRepository)
    }

    private fun initTestClass() {
        underTest = ContactListViewModel(contactsRepository)
    }

    @Test
    fun `test that loading indicator visibility state is true when ViewModel first load`() =
        runTest {
            // Arrange

            // Act
            initTestClass()

            // Assert
            underTest.uiState.test {
                val state = awaitItem()
                Truth.assertThat(state.isLoading).isTrue()
            }
        }

    @Test
    fun `test that loading indicator visibility state is false when fetch contacts finished successful`() =
        runTest {
            val contacts: List<Contact> = mock()
            Mockito.`when`(contactsRepository.getContacts()).thenReturn(Response.success(contacts))

            // Act
            initTestClass()

            // Assert
            underTest.uiState.test {
                val state = awaitItem()
                Truth.assertThat(state.isLoading).isFalse()
            }
        }

    @Test
    fun `test that loading indicator visibility state is false when fetch contacts returns error`() =
        runTest {

            // Assert
            underTest.uiState.test {
                val state = awaitItem()
                Truth.assertThat(state.isLoading).isFalse()
            }

        }

    @Test
    fun `test that contacts data should be updated when fetch contacts successful`() = runTest {

        // Assert
        underTest.uiState.test {
            val state = awaitItem()
            Truth.assertThat(state.isLoading).isFalse()
            Truth.assertThat(state.contacts).isNotEmpty()
        }
    }

    @Test
    fun `test that error layout visibility is true when fetch contacts throws error`() = runTest {

        // Assert
        underTest.uiState.test {
            val state = awaitItem()
            Truth.assertThat(state.isError).isTrue()
        }
    }

}