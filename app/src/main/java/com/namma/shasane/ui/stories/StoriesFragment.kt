package com.namma.shasane.ui.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.namma.shasane.adapter.StoryAdapter
import com.namma.shasane.databinding.FragmentStoriesBinding
import com.namma.shasane.model.InscriptionRepository

class StoriesFragment : Fragment() {

    private var _binding: FragmentStoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stories = InscriptionRepository.getSampleStories()

        val adapter = StoryAdapter(stories) { story ->
            Toast.makeText(
                requireContext(),
                story.title,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.storiesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
