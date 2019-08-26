package com.example.fourtitudetask1.item

import android.view.View
import com.example.fourtitudetask1.R
import com.example.fourtitudetask1.databinding.ItemFooterMovieBinding
import com.xwray.groupie.databinding.BindableItem

class MovieFooterItem() : BindableItem<ItemFooterMovieBinding>() {

    private var show: Boolean = false

    override fun getLayout(): Int {
        return R.layout.item_footer_movie
    }

    override fun bind(viewBinding: ItemFooterMovieBinding, position: Int) {

        if (show) {
            viewBinding.progressBar.visibility = View.VISIBLE
        } else {
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    fun show() {
        this.show = true
    }

    fun hide() {
        this.show = false
    }
}
