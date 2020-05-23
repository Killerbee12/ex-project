package com.example.excoroutines.presentation

import android.os.Bundle
import com.example.excoroutines.R
import com.example.excoroutines.presentation.flowerlist.FlowerListFragment
import com.example.shared.common.BaseActivity
import com.example.shared.navigation.NavigationProvider
import org.koin.android.ext.android.inject

class ExCoroutinesMainActivity : BaseActivity() {
    private val navigator: NavigationProvider by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex_coroutines)
        supportFragmentManager.beginTransaction().replace(R.id.contentLayout, FlowerListFragment())
            .commit()
    }

    override fun onBackPressed() {
        navigator.toMainActivity()
    }
}