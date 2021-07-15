package com.example.contactlistfinal

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactlistfinal.databinding.ActivityAddContactBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddContact : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE)
        binding.imageView.setImageResource(R.drawable.ic_baseline_account_circle_24)

        binding.save.setOnClickListener {
            saveData()
        }
    }
    private fun saveData(){
        val saveList = sharedPreferences.getString("List", null)
        val type = object : TypeToken<List<Contact>>() {}.type
        val autoFill: MutableList<Contact> = if (saveList != null)
            gson.fromJson(saveList, type)
        else mutableListOf()
        val mainList: MutableList<Contact> = autoFill
        val name = binding.etName.text.toString()
        val number = binding.etNumber.text.toString()
        if (number.length > 5) {
            val numberInt = number.toInt()
            val contact = Contact(name, numberInt)
            val list = mutableListOf<Contact>()
            list += contact
            mainList += list
            val json = gson.toJson(mainList)
            val edit = sharedPreferences.edit()
            edit.putString("List", json)
            edit.apply()
            finish()
        } else
            Toast.makeText(this, "Enter Correct Number", Toast.LENGTH_SHORT).show()
    }
}