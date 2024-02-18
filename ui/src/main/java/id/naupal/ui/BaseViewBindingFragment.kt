package id.naupal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import id.naupal.utils.extension.Event
import id.naupal.utils.extension.observe
import id.naupal.utils.extension.observeEvent

/**
 * A base fragment class used throughout application
 * Don't change this file.
 */
abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment() {

    companion object {
        const val FRAGMENT_NAME = "FRAGMENT_NAME"
    }

    private var _binding: VB? = null
    abstract val _vbInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (isUsingCustomStyleTheme()) {
            requireActivity().setTheme(setCustomStyleTheme())
        }
        _binding = _vbInflater.invoke(inflater, container, false)
        binding = requireNotNull(_binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Log class name to make it easier in the future to find from logcat where is this fragment located
         */

        setupSubscriber(viewLifecycleOwner)
        setupToolbar()
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun isUsingCustomStyleTheme(): Boolean = false
    protected open fun setCustomStyleTheme(): Int = R.style.AppThemeMaterial

    /**
     * Method to setup Toolbar in this fragment
     */
    open fun setupToolbar(){}

    /**
     * Method to setup UI related stuff such as setting default text and click listener
     */
    abstract fun setupUi()

    /**
     * Method to setup subscription related stuff such as subscribing to LiveData or Flow. Will be
     * automatically called in onViewCreated so safeViewLifecycleOwner param will never throws
     */
    abstract fun setupSubscriber(safeViewLifecycleOwner: LifecycleOwner)

    /**
     * Method to render data based on model or state, either from ViewModel or local private variable
     */
    protected fun render() {}

    /**
     * Method to check the binding already initialized or inflate with layout
     */
    protected fun isBindingInitialized(): Boolean = this::binding.isInitialized

    /**
     * Fragment's replacement for [observe], utilising [getViewLifecycleOwner] by default
     */
    fun <T> safeObserve(
        liveData: LiveData<T>,
        action: (t: T) -> Unit
    ) {
        viewLifecycleOwner.observe(liveData, action)
    }

    /**
     * Fragment's replacement for [observeEvent], utilising [getViewLifecycleOwner] by default
     */
    fun <T> safeObserveEvent(
        liveData: LiveData<Event<T>>,
        action: (t: T) -> Unit
    ) {
        viewLifecycleOwner.observeEvent(liveData, action)
    }
}