package com.shubham.shayariapp.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.shayariapp.All_Shayari_Activity
import com.shubham.shayariapp.MainActivity
import com.shubham.shayariapp.Models.CatModels
import com.shubham.shayariapp.databinding.ItemCategoeyBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CatModels>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorsList = arrayListOf<String>(
        "#FFC312",
        "#ED4C67",
        "#B53471",
        "#EA2027",
        "#6F1E51",
        "#A3CB38",
        "#EE5A24",
        "#5758BB",
        "#ffa801"
    )

    class CatViewHolder(val binding: ItemCategoeyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoeyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        if (position % 9 == 0) {

            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[0]))
        }else if (position % 9 == 1){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[1]))

        }else if (position % 9 == 2){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[2]))

        }else if (position % 9 == 3){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[3]))
        }else if (position % 9 == 4){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[4]))
        }else if (position % 9 == 5){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[5]))
        }else if (position % 9 == 6){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[6]))
        }else if (position % 9 == 7){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[7]))
        }else if (position % 9 == 8){
            holder.binding.itemText.setBackgroundColor(Color.parseColor(colorsList[8]))
        }

        holder.binding.itemText.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity, All_Shayari_Activity::class.java)

            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size


}