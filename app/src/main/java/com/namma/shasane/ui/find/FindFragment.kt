package com.namma.shasane.ui.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.namma.shasane.adapter.InscriptionAdapter
import com.namma.shasane.databinding.FragmentFindBinding
import com.namma.shasane.model.InscriptionRepository

class FindFragment : Fragment() {

    private var _binding: FragmentFindBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inscriptions = InscriptionRepository.getSampleInscriptions()

        val adapter = InscriptionAdapter(inscriptions) { inscription ->
            Toast.makeText(
                requireContext(),
                "${inscription.title}\n${inscription.dynasty} · ${inscription.century}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.inscriptionsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            isNestedScrollingEnabled = false
        }

        binding.countBadge.text = "${inscriptions.size} FOUND"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
