package id.naupal.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.naupal.ui.databinding.CustomBaseDialogFragmentBinding

/**
 * Created by Naupal T. on 16/07/22.
 */

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    abstract fun layoutId(): Int

    protected var baseBinding: CustomBaseDialogFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = CustomBaseDialogFragmentBinding.inflate(layoutInflater, container, false)
        return baseBinding?.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheetLayout =
                dialog.findViewById<CoordinatorLayout>(R.id.clBottomSheet)
            bottomSheetLayout?.let { container ->
                val bottomSheetWrapper =
                    dialog.findViewById<View>(R.id.clWrapper)
                bottomSheetWrapper?.let { wrapper ->
                    val behavior = BottomSheetBehavior.from(wrapper)
                    bottomSheetLayout.layoutParams.height = setPeekHeight() ?: wrapper.height
                    BottomSheetBehavior.from(container.parent as View).peekHeight =
                        setPeekHeight() ?: wrapper.height
                    behavior.peekHeight = setPeekHeight() ?: wrapper.height
                    container.parent.requestLayout()
                }
            }
        }
        return dialog
    }

    open fun setPeekHeight(): Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding()
        setupUi(layoutId())
        setupData()
        setupListener()
        setupLayoutContent()
        baseBinding?.ivClose?.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        baseBinding = null
        super.onDestroyView()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (this.isAdded || manager.findFragmentByTag(tag) != null) return

        super.show(manager, tag)
    }

    /**
     * use this to handle the args
     * **/
    protected open fun setupData() {}

    abstract fun setupLayoutContent()

    private fun setupUi(layoutId: Int) {
        baseBinding?.apply {
            llContainer.apply {
                layoutResource = layoutId
                inflate()
            }
        }
    }

    /**
     * use this to handle the listener like onclick, etc
     * **/
    protected open fun setupListener() {}

    protected open fun onContentViewInflated(contentView: View) {}

    private fun setupBinding() {
        baseBinding?.llContainer?.let {
            it.setOnInflateListener { _, inflated ->
                onContentViewInflated(inflated)
            }
        }
    }

}