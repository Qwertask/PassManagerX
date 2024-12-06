package com.example.fragment.password

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragment.PassViewModel
import com.example.fragment.databinding.AddPasswordBinding
import com.example.fragment.databinding.EventItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NewPassItemSheet(var passItem: PassItem?):BottomSheetDialogFragment() {
    private lateinit var binding: AddPasswordBinding
    private lateinit var passViewModel: PassViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if(passItem!=null){
            val editable = Editable.Factory.getInstance()
            binding.titleEditText.text = editable.newEditable(passItem!!.name)
            binding.loginEditText.text = editable.newEditable(passItem!!.login)
            binding.passwordEditText.text = editable.newEditable(passItem!!.password)
        } else{

        }
        passViewModel = ViewModelProvider(activity).get(PassViewModel::class.java)
        binding.saveButton.setOnClickListener{
            savAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun savAction() {
        val name = binding.titleEditText.text.toString()
        val login = binding.loginEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if(passItem == null){
            val newPassItem = PassItem(name, login, password)
            passViewModel.addPassItem(newPassItem)
        } else{
            passItem!!.name = name
            passItem!!.login = login
            passItem!!.password = password
            passViewModel.updatePassItem(passItem!!)
        }
        binding.titleEditText.setText("")
        binding.loginEditText.setText("")
        binding.passwordEditText.setText("")
        dismiss()
    }

}