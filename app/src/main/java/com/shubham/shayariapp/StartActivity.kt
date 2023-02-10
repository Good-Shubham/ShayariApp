package com.shubham.shayariapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.shubham.shayariapp.databinding.ActivityMainBinding
import com.shubham.shayariapp.databinding.ActivityStartBinding

lateinit var binding: ActivityStartBinding

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.btnMore.setOnClickListener{
            try {
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }


        }

        binding.btnRate.setOnClickListener{
         var uri = Uri.parse("market://details?id=$packageName")
            val myAppLinkMarket = Intent(Intent.ACTION_VIEW,uri)

            try {
                startActivity(myAppLinkMarket)

            }catch (e: ActivityNotFoundException){
                Toast.makeText(this,"unable to find market app",Toast.LENGTH_LONG).show()
            }
        }

    }
}