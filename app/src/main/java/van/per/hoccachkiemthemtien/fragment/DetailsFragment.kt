package van.per.hoccachkiemthemtien.fragment

import amp.er.kiemtientainha.data.DataModel
import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import van.per.hoccachkiemthemtien.R
import van.per.hoccachkiemthemtien.base.BaseFragment
import van.per.hoccachkiemthemtien.binding.viewBinding
import van.per.hoccachkiemthemtien.databinding.FragmentDetailsBinding


class DetailsFragment(
    private val data: DataModel,
    private val fm: FragmentManager
): BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    override fun getTheme(): Int = android.R.style.Theme_Material_Dialog

    override fun setupViews() {
        with(binding){
            title.text = data.title
            description.text = data.content
            imageBg.setBackgroundResource(data.resource)

            exitButton.setOnClickListener {
                dismiss()
            }
        }
    }

    override fun viewModelObservers() {

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { d ->
            val bottomSheetDialog = d as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun show(){
        show(fm, data.title)
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet) ?: return
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}