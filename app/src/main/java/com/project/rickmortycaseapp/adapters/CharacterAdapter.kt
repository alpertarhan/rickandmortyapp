package com.project.rickmortycaseapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.project.rickmortycaseapp.ui.CharacterDetailActivity
import com.project.rickmortycaseapp.adapters.CharacterAdapter.*
import com.project.rickmortycaseapp.databinding.CharacterRowBinding
import com.project.rickmortycaseapp.models.Character

class CharacterAdapter:PagingDataAdapter<Character, CharacterViewHolder>(diffCallback) {

    class CharacterViewHolder(val binding: CharacterRowBinding):RecyclerView.ViewHolder(binding.root) {

       /* init {
            itemView.setOnClickListener { v: View ->
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context,"Clicked ${position}",Toast.LENGTH_SHORT).show()
            }
        }*/

    }
        //check page && items
    companion object{
        val diffCallback = object :DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {

            characterName.text = "${currentItem?.name}"

            val image = currentItem?.image
            characterImage.load(image){
                crossfade(true)
                crossfade(500)
            }
        }

        holder.binding.characterImage.setOnClickListener {

            ImageClicked(holder,currentItem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
            return CharacterViewHolder(CharacterRowBinding.inflate(LayoutInflater
                .from(parent.context),
                parent, false))
    }

    private fun ImageClicked(holder: CharacterViewHolder, currentItem: Character?) {

        var detailIntent = Intent(holder.itemView.context, CharacterDetailActivity::class.java)

        //senddatas to intent

        detailIntent.putExtra("character_detail_name", currentItem?.name)
        detailIntent.putExtra("status_textView", currentItem?.status)
        detailIntent.putExtra("species_textView", currentItem?.species)
        detailIntent.putExtra("gender_textView", currentItem?.gender)
        detailIntent.putExtra("character_detail_image", currentItem?.image)
        detailIntent.putStringArrayListExtra("episode_name_list",currentItem?.episode)

        holder.itemView.context.startActivity(detailIntent)
    }

}

