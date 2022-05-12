package com.example.art_2.ArtInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.art_2.R
import com.example.art_2.data.ArtObj
import com.example.art_2.databinding.ActivityArtInfoBinding
import com.example.art_2.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class ActivityArtInfo : AppCompatActivity() {
    lateinit var binding: ActivityArtInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NEW ACTIVITY", "YES")
        binding = ActivityArtInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() = with(binding) {
        val art = intent.getSerializableExtra("art") as ArtObj

        if (art.urlPrimaryImage != "") {
            Picasso.get().load(art.urlPrimaryImage).into(image)
            Picasso.get().load(art.urlPrimaryImage).into(imageMain)
        } else {
            Picasso.get().load(art.urlSmallImage).into(image)
            Picasso.get().load(art.urlSmallImage).into(imageMain)
        }

        name.text = art.name
        authorName.text = art.author
        countryName.text = art.country
        yearNumber.text = art.year
    }
}