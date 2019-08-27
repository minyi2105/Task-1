package com.example.fourtitudetask1.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fourtitudetask1.R

class DummyMainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_main)
    }
}
