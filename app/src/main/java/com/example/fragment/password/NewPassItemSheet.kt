package com.example.fragment.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fragment.PassItemListener
import com.example.fragment.databinding.AddPasswordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewPassItemSheet : BottomSheetDialogFragment() {

    private var _binding: AddPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val login = binding.loginEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (title.isEmpty() || login.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Сохраняем данные через ViewModel
            (activity as? PassItemListener)?.let { listener ->
                listener.editPassItem(PassItem(title, login, password))
            }

            dismiss() // Закрыть BottomSheet
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
