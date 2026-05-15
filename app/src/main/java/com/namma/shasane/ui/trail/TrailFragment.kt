package com.namma.shasane.ui.trail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.namma.shasane.R
import com.namma.shasane.databinding.FragmentTrailBinding

data class TrailStop(
    val order: Int,
    val name: String,
    val location: String,
    val distanceFromPrev: String,
    val duration: String,
    val emoji: String
)

class TrailFragment : Fragment() {

    private var _binding: FragmentTrailBinding? = null
    private val binding get() = _binding!!

    private val trailStops = listOf(
        TrailStop(1, "Hampi — Rashtrakuta Victory Slab", "Hampi, Ballari District", "Start", "45 min", "🏛"),
        TrailStop(2, "Aihole — Chalukya Boundary Marker", "Aihole, Bagalkot District", "+38 km", "1 hr", "🪨"),
        TrailStop(3, "Belur — Vishnu Grant Stone", "Belur, Hassan District", "+112 km", "2 hr 30 min", "⛩"),
        TrailStop(4, "Shravanabelagola — Copper Plate", "Hassan District", "+51 km", "1 hr 15 min", "🧘"),
        TrailStop(5, "Sringeri — Temple Prashasti", "Chikkamagaluru District", "+97 km", "2 hr", "📜")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildTrailStops()

        binding.btnStartTrail.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Opening navigation to Hampi...\n(Maps integration ready)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun buildTrailStops() {
        val container = binding.trailStopsContainer
        container.removeAllViews()

        for ((index, stop) in trailStops.withIndex()) {
            val stopView = createTrailStopView(stop, index == trailStops.size - 1)
            container.addView(stopView)
        }
    }

    private fun createTrailStopView(stop: TrailStop, isLast: Boolean): View {
        val outerLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.bottomMargin = 0 }
        }

        // Left column: connector line + circle
        val connectorLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER_HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(32, LinearLayout.LayoutParams.WRAP_CONTENT)
        }

        // Stop circle
        val circle = TextView(requireContext()).apply {
            text = stop.order.toString()
            setTextColor(Color.parseColor("#1A0F00"))
            textSize = 11f
            gravity = android.view.Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
            background = ContextCompat.getDrawable(context, R.drawable.bg_marker_verified)
            layoutParams = LinearLayout.LayoutParams(28, 28)
        }
        connectorLayout.addView(circle)

        // Connector line (not for last)
        if (!isLast) {
            val line = View(requireContext()).apply {
                setBackgroundColor(Color.parseColor("#4A3728"))
                layoutParams = LinearLayout.LayoutParams(2, 60).also {
                    it.gravity = android.view.Gravity.CENTER_HORIZONTAL
                }
            }
            connectorLayout.addView(line)
        }

        outerLayout.addView(connectorLayout)

        // Right content
        val contentLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            ).also {
                it.marginStart = dpToPx(12)
                it.bottomMargin = if (!isLast) dpToPx(4) else 0
            }
            setPadding(0, 0, 0, dpToPx(16))
        }

        val titleRow = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = android.view.Gravity.CENTER_VERTICAL
        }

        val emojiView = TextView(requireContext()).apply {
            text = stop.emoji
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.marginEnd = dpToPx(8) }
        }
        titleRow.addView(emojiView)

        val stopTitle = TextView(requireContext()).apply {
            text = stop.name
            setTextColor(Color.parseColor("#F5DEB3"))
            textSize = 13f
            setTypeface(null, android.graphics.Typeface.BOLD)
            layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            )
        }
        titleRow.addView(stopTitle)

        val distanceBadge = TextView(requireContext()).apply {
            text = stop.distanceFromPrev
            setTextColor(Color.parseColor("#C8A86B"))
            textSize = 11f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        titleRow.addView(distanceBadge)

        contentLayout.addView(titleRow)

        val locationText = TextView(requireContext()).apply {
            text = "${stop.location}  ·  ${stop.duration} drive"
            setTextColor(Color.parseColor("#8B7355"))
            textSize = 11f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.topMargin = dpToPx(2) }
        }
        contentLayout.addView(locationText)

        outerLayout.addView(contentLayout)

        return outerLayout
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
