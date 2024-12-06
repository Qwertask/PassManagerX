package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.PassItemModelFactory
import com.example.fragment.PassViewModel
import com.example.fragment.PasswordManagerApplication
import com.example.fragment.adapter.PassItemAdapter
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.password.NewPassItemSheet
import com.example.fragment.password.PassItem

class MainActivity : AppCompatActivity(), PassItemListener {
    private lateinit var binding: ActivityMainBinding
    private val passViewModel: PassViewModel by viewModels {
        PassItemModelFactory((application as PasswordManagerApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addButton.setOnClickListener {
            NewPassItemSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val mainActivity = this
        passViewModel.passItems.observe(this){
            binding.eventsRecycler.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = PassItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editPassItem(passItem: PassItem) {
        NewPassItemSheet(passItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun deletePassItem(passItem: PassItem) {
        passViewModel.updatePassItem(passItem)
    }
}