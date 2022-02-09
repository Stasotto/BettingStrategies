package com.example.bettingstrategies.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.ActivityMainBinding
import com.example.bettingstrategies.presentation.fragments.StrategiesFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openStrategiesFragment()
    }

    private fun openStrategiesFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, StrategiesFragment.newInstance(), StrategiesFragment.TAG)
            .commit()
    }
}