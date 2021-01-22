package com.example.dh3teste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dh3teste.Model.Result
import com.example.dh3teste.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private var clicked: Result? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()



        clicked = intent.getParcelableExtra("comic")

        Glide.with(this).load(clicked?.thumbnail?.path)
            .placeholder(R.drawable.ic_b)
            .into(binding.ivCover)


        clicked?.images?.forEach {
        it.path = it.path?.removeSuffix("/portrait_incredible.jpg")
        }
        clicked?.images?.forEach {
            it.path = it.path?.toLandScape()
        }

        clicked?.images?.forEach {
            Glide.with(this).load(it.path)
                .placeholder(R.drawable.ic_b)
                .into(binding.ivDetails)
        }




        binding.tvDetails.text = clicked?.title
       binding.tvDescription.text = clicked?.description
        binding.tvDate.text = clicked?.dates?.get(1)?.date?.removeSuffix("T00:00:00-0400")
        binding.tvPrice.text = clicked?.prices?.get(1)?.price.toString().plus("$")
        binding.tvPages.text = clicked?.pageCount.toString().plus(" pages")


    }
}