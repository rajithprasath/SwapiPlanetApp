package com.rajith.swapiplanetapp.presentation


import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.rajith.swapiplanetapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryColor)

        (supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }
}