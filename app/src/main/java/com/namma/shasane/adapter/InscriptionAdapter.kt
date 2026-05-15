package com.namma.shasane.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.namma.shasane.R
import com.namma.shasane.model.Inscription
import com.namma.shasane.model.InscriptionStatus
import com.namma.shasane.model.ThumbnailType

class InscriptionAdapter(
    private val inscriptions: List<Inscription>,
    private val onItemClick: (Inscription) -> Unit = {}
) : RecyclerView.Adapter<InscriptionAdapter.InscriptionViewHolder>() {

    inner class InscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbContainer: FrameLayout = itemView.findViewById(R.id.thumb_container)
        val thumbIcon: ImageView = itemView.findViewById(R.id.thumb_icon)
        val titleText: TextView = itemView.findViewById(R.id.inscription_title)
        val locationText: TextView = itemView.findViewById(R.id.inscription_location)
        val chipCentury: TextView = itemView.findViewById(R.id.chip_century)
        val chipType: TextView = itemView.findViewById(R.id.chip_type)
        val chipStatus: TextView = itemView.findViewById(R.id.chip_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InscriptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inscription, parent, false)
        return InscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InscriptionViewHolder, position: Int) {
        val inscription = inscriptions[position]
        val context = holder.itemView.context

        // Title
        holder.titleText.text = inscription.title

        // Location + distance
        val distanceStr = if (inscription.distanceKm == inscription.distanceKm.toLong().toDouble()) {
            "${inscription.distanceKm.toLong()} km"
        } else {
            "${inscription.distanceKm} km"
        }
        holder.locationText.text = "${inscription.location}, ${inscription.district} · $distanceStr"

        // Century chip
        holder.chipCentury.text = inscription.century

        // Type chip
        holder.chipType.text = inscription.type.displayName

        // Status chip with correct background and color
        holder.chipStatus.text = inscription.status.displayName
        applyStatusChip(context, holder.chipStatus, inscription.status)

        // Thumbnail icon
        val iconRes = when (inscription.thumbnailType) {
            ThumbnailType.STONE -> R.drawable.ic_stone
            ThumbnailType.TEMPLE -> R.drawable.ic_temple
            ThumbnailType.GATE -> R.drawable.ic_gate
            ThumbnailType.WARNING -> R.drawable.ic_warning_stone
        }
        holder.thumbIcon.setImageResource(iconRes)

        // Click listener
        holder.itemView.setOnClickListener { onItemClick(inscription) }
    }

    private fun applyStatusChip(context: Context, chip: TextView, status: InscriptionStatus) {
        when (status) {
            InscriptionStatus.PRESERVED, InscriptionStatus.RESTORED -> {
                chip.background = ContextCompat.getDrawable(context, R.drawable.bg_chip_preserved)
                chip.setTextColor(ContextCompat.getColor(context, R.color.chip_preserved_text))
            }
            InscriptionStatus.AT_RISK -> {
                chip.background = ContextCompat.getDrawable(context, R.drawable.bg_chip_atrisk)
                chip.setTextColor(ContextCompat.getColor(context, R.color.chip_atrisk_text))
            }
            InscriptionStatus.MUSEUM -> {
                chip.background = ContextCompat.getDrawable(context, R.drawable.bg_chip_museum)
                chip.setTextColor(ContextCompat.getColor(context, R.color.chip_museum_text))
            }
        }
    }

    override fun getItemCount(): Int = inscriptions.size
}
