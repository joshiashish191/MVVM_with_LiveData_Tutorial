package net.softglobe.mvvmarchitecturetutorial.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.softglobe.mvvmarchitecturetutorial.R
import net.softglobe.mvvmarchitecturetutorial.databinding.ActivityMainBinding
import net.softglobe.mvvmarchitecturetutorial.model.User
import net.softglobe.mvvmarchitecturetutorial.viewmodel.MainModelFactory
import net.softglobe.mvvmarchitecturetutorial.viewmodel.MainViewModel
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainModelFactory(applicationContext)).get(MainViewModel::class.java)


        binding.button.setOnClickListener {
            viewModel.insertUser(User(binding.etName.text.toString()))
            binding.etName.setText("")
        }

        viewModel.getUsers().observe(this, Observer {
            val namesList = StringBuilder()
            it.forEach { user ->
                namesList.append(user.name).append("\n")
            }
            binding.userNames.text = namesList
        })
    }
}