package com.example.dh3teste.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dh3teste.adapter.MarvelAdapter
import com.example.dh3teste.viewModel.MarvelViewModel
import com.example.dh3teste.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MarvelViewModel
    var name: String? = null
    var imageUri: Uri? = null

    private val marvelAdapter by lazy {
        MarvelAdapter {result ->
            result?.let {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("comic", it)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MarvelViewModel::class.java)


        supportActionBar?.setTitle("Marvel")



        setUpRecyclerView()
        loadComic()
    }

    private fun setUpRecyclerView() {
        binding.rvMenu.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = marvelAdapter
        }
    }

    private fun loadComic() {
        viewModel.marvelPagedList?.observe(this) {pagedList ->
            marvelAdapter.submitList(pagedList)
        }
    }


}