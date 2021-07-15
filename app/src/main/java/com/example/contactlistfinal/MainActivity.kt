package com.example.contactlistfinal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlistfinal.databinding.ActivityMainBinding
import com.example.contactlistfinal.itemAdapter.ItemAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.save.setImageResource(R.drawable.add)
        binding.save.setOnClickListener {
            navigateToAddContact()
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        val data = sharedPreferences.getString("List", null)
        val type = object : TypeToken<List<Contact>>() {}.type
        val contactData: MutableList<Contact> = if (data != null)
            gson.fromJson(data, type)
        else mutableListOf()
        val contactList = mutableListOf<Contact>()
        contactList += contactData
        if (contactList.size > 0) {
            binding.tv1.visibility = View.INVISIBLE
        }
        binding.recyclerView.adapter = ItemAdapter(contactList)
    }

    private fun navigateToAddContact() {
        val intent = Intent(this, AddContact::class.java)
        startActivity(intent)
    }
}