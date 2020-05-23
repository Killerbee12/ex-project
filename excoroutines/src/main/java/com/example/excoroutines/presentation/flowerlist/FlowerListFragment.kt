package com.example.excoroutines.presentation.flowerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.excoroutines.R
import com.example.shared.common.Error
import com.example.shared.common.HideLoading
import com.example.shared.common.ShowLoading
import com.example.shared.extenstions.gone
import com.example.shared.extenstions.visible
import kotlinx.android.synthetic.main.fragment_flower_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FlowerListFragment : Fragment() {

    private val flowerListAdapter: FlowerListAdapter = FlowerListAdapter(arrayListOf())
    private val flowerListViewModel: FlowerListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_flower_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        flowerListViewModel.getFlowerList()
    }

    private fun initRecyclerView() {
        rvFlowerList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = flowerListAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        flowerListViewModel.getFlowerListLiveData().observe(this, Observer {
            flowerListAdapter.updateFlowerList(it)
        })

        flowerListViewModel.viewState.observe(this, Observer {
            when (it) {
                is ShowLoading -> progressBar.visible()
                is HideLoading -> progressBar.gone()
                is Error -> {
                    progressBar.gone()
                    Toast.makeText(activity, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}