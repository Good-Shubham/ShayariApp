package com.shubham.shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shubham.shayariapp.Adapter.AllShayariAdapter
import com.shubham.shayariapp.Models.ShayariModels
import com.shubham.shayariapp.databinding.ActivityAllShayariBinding

class All_Shayari_Activity : AppCompatActivity() {
    lateinit var binding: ActivityAllShayariBinding
    lateinit var db:FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        binding.catName.text=name.toString()

        db.collection("Shayari").document(id!!).collection("all").addSnapshotListener { value, error ->
            val  shayariList = arrayListOf<ShayariModels>()
            val data = value?.toObjects(ShayariModels::class.java)
            shayariList.addAll(data!!)
            binding.rcvallShayari.layoutManager = LinearLayoutManager(this)
            binding.rcvallShayari.adapter =AllShayariAdapter(this,shayariList)
        }


    }
}