package com.shubham.shayariapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shubham.shayariapp.Adapter.CategoryAdapter
import com.shubham.shayariapp.Models.CatModels
import com.shubham.shayariapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db:FirebaseFirestore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val  list= arrayListOf<String>("Love Shayari","Romantic Shayari","Sad Shayari","Love Shayari","Romantic Shayari","Sad Shayari")

        db = FirebaseFirestore.getInstance()
        db.collection("Shayari").addSnapshotListener { value, error ->


            val  list= arrayListOf<CatModels>()
            val data = value?.toObjects(CatModels::class.java)
            list.addAll(data!!)
            binding.rcvCategory.layoutManager=LinearLayoutManager(this)
            binding.rcvCategory.adapter=CategoryAdapter(this,list)

        }


        binding.btnMenu.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.share -> {
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nInstall this application for New Shayari 2023\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    true;

                }
                R.id.rate -> {
                    var uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkMarket = Intent(Intent.ACTION_VIEW,uri)

                    try {
                        startActivity(myAppLinkMarket)

                    }catch (e: ActivityNotFoundException){
                        Toast.makeText(this,"unable to find market app", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                R.id.more -> {
                    try {
                        startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                        )
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }

                    true
                }else-> false


            }
        }

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed()

        }
    }
}