package com.example.room.ui.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.room.R
import com.example.room.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    private lateinit var navCon: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navCon = navHostFragment.navController

        when (PreferenceHelper.isShow) {
            false -> {
                navCon.navigate(R.id.onBoardFragment)
            }
            else -> {
                navCon.navigate(R.id.homeFragment)
            }
        }
    }
}