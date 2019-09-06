package com.example.fourtitudetask1.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.adapter.DummyAdapter
import com.example.fourtitudetask1.model.Dummy


class DummyMainActivity : AppCompatActivity(), View.OnClickListener {

    val dummyList: ArrayList<Dummy> = arrayListOf()

    override fun onClick(v: View) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_main)

        loadDummyData()

        rv_dummy.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_dummy.adapter = DummyAdapter(this@DummyMainActivity, rv_dummy, cv_nothingFound,dummyList)
    }

    private fun loadDummyData() {
        val listOfUrls = listOf(
                "https://upload.wikimedia.org/wikipedia/commons/3/3a/Cat03.jpg",
                "https://mymodernmet.com/wp/wp-content/uploads/2019/07/russian-blue-cats-17-1024x1024.jpg",
                "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/barbara.png",
                "https://homepages.cae.wisc.edu/~ece533/images/girl.png",
                "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
                "https://homepages.cae.wisc.edu/~ece533/images/frymire.png",
                "https://homepages.cae.wisc.edu/~ece533/images/goldhill.png",
                "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",
                "https://homepages.cae.wisc.edu/~ece533/images/mountain.png",
                "https://homepages.cae.wisc.edu/~ece533/images/zelda.png",
                "https://homepages.cae.wisc.edu/~ece533/images/peppers.png",
                "https://homepages.cae.wisc.edu/~ece533/images/pool.png",
                "https://homepages.cae.wisc.edu/~ece533/images/watch.png",
                "https://homepages.cae.wisc.edu/~ece533/images/tulips.png",
                "https://homepages.cae.wisc.edu/~ece533/images/serrano.png",
                "https://homepages.cae.wisc.edu/~ece533/images/sails.png",
                "https://homepages.cae.wisc.edu/~ece533/images/lena.png"
                )

        for (i in listOfUrls.indices) {
            val currentNumber = i + 1

            val dummy = Dummy(
                    "Title $currentNumber",
                    "Subtite $currentNumber Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley ",
                    "Description $currentNumber Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Wwhen an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    listOfUrls[i])

            dummyList.add(dummy)
        }
    }
}
