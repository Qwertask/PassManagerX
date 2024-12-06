package com.example.fragment

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.adapter.PassItemAdapter
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.password.NewPassItemSheet
import com.example.fragment.password.PassItem

class MainActivity : AppCompatActivity(), PassItemListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PassItemAdapter
    private val passViewModel: PassViewModel by viewModels {
        PassItemModelFactory((application as PasswordManagerApplication).repository)
    }
    private val passItems = mutableListOf<PassItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация RecyclerView
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        adapter = PassItemAdapter(passItems, this)
        binding.recycleView.adapter = adapter

        // Пример добавления данных
        loadDummyData()

        // Кнопка добавления элемента
        binding.addButton.setOnClickListener {
            addNewPassItem()
        }
    }

    private fun loadDummyData() {
        passItems.addAll(
            listOf(
                PassItem("Google", "user@gmail.com", "password123"),
                PassItem("Facebook", "user@facebook.com", "securePass")
            )
        )
        adapter.notifyDataSetChanged()
    }

    private fun addNewPassItem() {
        // Инициализация диалогового окна
        val dialogView = layoutInflater.inflate(R.layout.add_password, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
            .setTitle("Добавить пароль")
            .setPositiveButton("Сохранить") { _, _ ->
                // Получение данных из полей ввода
                val service = dialogView.findViewById<EditText>(R.id.titleEditText).text.toString()
                val email = dialogView.findViewById<EditText>(R.id.loginEditText).text.toString()
                val password = dialogView.findViewById<EditText>(R.id.passwordEditText).text.toString()

                // Создание нового PassItem
                val newItem = PassItem(service, email, password)

                // Добавление в базу данных
                passViewModel.insertPassItem(newItem)
            }
            .setNegativeButton("Отмена", null)
            .create()
            .show()
    }


    override fun editPassItem(passItem: PassItem) {
        passViewModel.insertPassItem(passItem) // Сохранение в базу
    }

    override fun deletePassItem(passItem: PassItem) {
        passViewModel.updatePassItem(passItem)
    }
}
