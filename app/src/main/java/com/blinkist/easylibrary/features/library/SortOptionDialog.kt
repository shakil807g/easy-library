package com.blinkist.easylibrary.features.library

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.blinkist.easylibrary.R
import com.blinkist.easylibrary.databinding.BottomSheetSortOptionsBinding
import com.blinkist.easylibrary.databinding.inflateBinding
import com.blinkist.easylibrary.ktx.unsyncLazy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortOptionDialog : BottomSheetDialogFragment() {

  companion object {
    val TAG: String = SortOptionDialog::class.java.name

    fun newInstance() = SortOptionDialog()
  }

  private val viewModel by unsyncLazy {
    ViewModelProviders.of(requireActivity()).get(LibraryViewModel::class.java)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState)
    val binding = inflateBinding<BottomSheetSortOptionsBinding>(R.layout.bottom_sheet_sort_options)
    dialog.setContentView(binding.root)

    setupUi(binding)

    return dialog
  }

  private fun setupUi(binding: BottomSheetSortOptionsBinding) {
    binding.viewModel = viewModel
    binding.ascendingTextView.setOnClickListener { onAscendingClick() }
    binding.descendingTextView.setOnClickListener { onDescendingClick() }
  }

  private fun onAscendingClick() {
    viewModel.rearrangeBooks(sortByDescending = false)
    dismiss()
  }

  private fun onDescendingClick() {
    viewModel.rearrangeBooks(sortByDescending = true)
    dismiss()
  }
}