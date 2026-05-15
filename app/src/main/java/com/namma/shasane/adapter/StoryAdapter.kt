package com.namma.shasane.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.namma.shasane.R
import com.namma.shasane.model.Story

class StoryAdapter(
    private val stories: List<Story>,
    private val onItemClick: (Story) -> Unit = {}
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emojiView: TextView = itemView.findViewById(R.id.story_emoji)
        val titleView: TextView = itemView.findViewById(R.id.story_title)
        val dynastyView: TextView = itemView.findViewById(R.id.story_dynasty)
        val excerptView: TextView = itemView.findViewById(R.id.story_excerpt)
        val locationView: TextView = itemView.findViewById(R.id.story_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.emojiView.text = story.emoji
        holder.titleView.text = story.title
        holder.dynastyView.text = story.dynasty
        holder.excerptView.text = story.excerpt
        holder.locationView.text = story.location
        holder.itemView.setOnClickListener { onItemClick(story) }
    }

    override fun getItemCount(): Int = stories.size
}
