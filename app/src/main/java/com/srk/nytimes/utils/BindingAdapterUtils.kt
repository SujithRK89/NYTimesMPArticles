package com.srk.nytimes.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.srk.nytimes.R
import com.srk.nytimes.data.remote.model.MostPopular

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter(value = ["app:text"])
    fun TextView.setText(item: MostPopular?) {

        item?.let { mostPopular ->
            mostPopular.media?.let { mediaList ->
                if (mediaList.isNotEmpty()) {
                    mediaList.first().let {
                        this.text = it.caption
                    }
                }

            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:item", "app:type"], requireAll = true)
    fun ImageView.loadImage(item: MostPopular?, type: ImageType) {

        item?.let { mostPopular ->
            mostPopular.media?.let { mediaList ->
                if (mediaList.isNotEmpty()) {
                    mediaList.first().mediaMetadata?.let { metadataList ->

                        val requestOption = RequestOptions().apply {
                            this.placeholder(R.drawable.ic_placeholder_small)
                            this.error(R.drawable.ic_placeholder_small)
                        }

                        val imageUrl = if (type == ImageType.THUMB) {
                            requestOption.apply {
                                this.circleCrop()
                            }
                            metadataList.first().url
                        } else {
                            metadataList.last().url
                        }

                        imageUrl?.let { url ->
                            Glide.with(this.context)
                                .load(url)
                                .apply(requestOption)
                                .into(this)
                        }

                    }
                }
            }
        }
    }
}