package com.namma.shasane.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.namma.shasane.databinding.FragmentAlertBinding

class AlertFragment : Fragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPhoto.setOnClickListener {
            binding.photoPreviewContainer.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Photo picker launching...", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
            validateAndSubmit()
        }
    }

    private fun validateAndSubmit() {
        val location = binding.inputLocation.text?.toString()?.trim() ?: ""
        val damage = binding.inputDamage.text?.toString()?.trim() ?: ""

        if (location.isEmpty()) {
            binding.inputLocationLayout.error = "Please enter the location"
            return
        } else {
            binding.inputLocationLayout.error = null
        }

        if (damage.isEmpty()) {
            binding.inputDamageLayout.error = "Please describe the damage"
            return
        } else {
            binding.inputDamageLayout.error = null
        }

        val urgency = when (binding.urgencyGroup.checkedRadioButtonId) {
            binding.urgencyLow.id -> "Low"
            binding.urgencyMedium.id -> "Medium"
            binding.urgencyHigh.id -> "High"
            else -> "Medium"
        }

        // Show success dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Report Submitted")
            .setMessage(
                "Thank you for helping protect Karnataka's heritage.\n\n" +
                "Location: $location\n" +
                "Urgency: $urgency\n\n" +
                "The Archaeological Survey of India and the Karnataka State Heritage Commission will be notified within 24 hours."
            )
            .setPositiveButton("Done") { dialog, _ ->
                dialog.dismiss()
                clearForm()
            }
            .show()
    }

    private fun clearForm() {
        binding.inputLocation.setText("")
        binding.inputDamage.setText("")
        binding.inputContact.setText("")
        binding.urgencyMedium.isChecked = true
        binding.photoPreviewContainer.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
