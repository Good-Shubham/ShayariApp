package com.shubham.shayariapp.Adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shubham.shayariapp.All_Shayari_Activity
import com.shubham.shayariapp.BuildConfig
import com.shubham.shayariapp.Models.ShayariModels
import com.shubham.shayariapp.R
import com.shubham.shayariapp.databinding.ItermShayariBinding


class AllShayariAdapter(
    val allShayariActivity: All_Shayari_Activity,
    val shayariList: ArrayList<ShayariModels>
) : RecyclerView.Adapter<AllShayariAdapter.ShayariViewHolder>() {
    class ShayariViewHolder(val binding: ItermShayariBinding) :
        RecyclerView.ViewHolder(binding.root)

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {


        return ShayariViewHolder(
            ItermShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {

        if (position % 9 == 0) {

            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_2)
        } else if (position % 9 == 1) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_4)

        } else if (position % 9 == 2) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_5)

        } else if (position % 9 == 3) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_1)
        } else if (position % 9 == 4) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_6)
        } else if (position % 9 == 5) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_3)
        } else if (position % 9 == 6) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_7)
        } else if (position % 9 == 7) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_8)
        } else if (position % 9 == 8) {
            holder.binding.mainBackground.setBackgroundResource(R.drawable.gradint_9)
        }

        holder.binding.itemShayari.text = shayariList[position].data.toString()

        //copy shayari
        holder.binding.btnCopy.setOnClickListener() {
            val clipboard: ClipboardManager? =
                allShayariActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", shayariList[position].data.toString())
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(allShayariActivity, "Shayari Copy...", Toast.LENGTH_LONG).show()


        }
        holder.binding.btnWhatsapp.setOnClickListener() {

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, shayariList[position].data.toString())
            try {
                allShayariActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {

            }
            Toast.makeText(allShayariActivity, "Share in Whatsapp", Toast.LENGTH_LONG).show()
        }
        //Share btn click share
        holder.binding.btnShare.setOnClickListener() {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n ${shayariList[position].data}\n\n"
                shareMessage =
                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allShayariActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
            Toast.makeText(allShayariActivity, "Share Shayari", Toast.LENGTH_LONG).show()

        }
    }

    override fun getItemCount() = shayariList.size
}