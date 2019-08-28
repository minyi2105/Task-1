package com.example.fourtitudetask1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.model.Dummy
import kotlinx.android.synthetic.main.activity_dummy_detail.*

class DummyDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_detail)

        val dummy = intent.getParcelableExtra("dummy") as Dummy

        tv_title.text = dummy.title
        tv_subtitle.text = dummy.subtitle
        tv_description.text = dummy.description

        Glide
                .with(this@DummyDetailActivity)
                .load(dummy.imageUrl)
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(iv_dummy)
    }
}