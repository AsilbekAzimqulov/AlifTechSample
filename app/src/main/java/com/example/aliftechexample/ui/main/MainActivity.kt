package com.example.aliftechexample.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aliftechexample.R
import com.example.aliftechexample.ui.webscreen.WebScreenFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var guideAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getGuide()
        guideAdapter = MainAdapter()
        rvGuide.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = guideAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    viewModel.onScroll(dy)
                }
            })
        }



        guideAdapter.listener = object : MainAdapter.Listener {
            override fun onClick(url: String) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.flContent, WebScreenFragment.getInstance(url))
                    .addToBackStack("").commit()
            }

        }

        viewModel.guide.observe(this, Observer {
            guideAdapter.items = it
        })

        viewModel.showLoader.observe(this, Observer {
            pbLoading.isVisible = it
        })
    }

    override fun onResume() {
        super.onResume()
        //avval 3 ta keyin qoganini chiqarish zadachasini togri tushungan bolsam shunaqa qildim
        // aslida scrool listener bilan qilish kerak item kam bogani uchun scrool bomaydi shunga buyoqda  qilib quydim
        //zadachada shu nazarda tutilgan bolsa agar yoki zadachani yaxshi tushunmadim pagination demoqci bolinganmi
        viewModel.onScroll(1)
    }
}