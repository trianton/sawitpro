package id.naupal.xcomposetdd.contactlist

import app.cash.turbine.test
import com.google.common.truth.Truth
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

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactListViewModelTest {
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
        underTest = ContactListViewModel()
    }

    private fun initTestClass() {
        underTest = ContactListViewModel()
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