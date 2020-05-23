package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shared.navigation.NavigationProvider
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: NavigationProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToExCoroutinesActivity.setOnClickListener {
            navigator.providerExCorountineNavigation().toExCorountineMainActivity()
        }
    }
}
