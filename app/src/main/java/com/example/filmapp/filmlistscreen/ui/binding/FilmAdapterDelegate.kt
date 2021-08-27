package com.example.filmapp.filmlistscreen.ui.binding

import com.example.filmapp.databinding.FilmCardBinding
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.example.filmapp.filmlistscreen.ui.model.Item
import com.example.filmapp.util.loadImage
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun filmAdapterDelegate(onClickAction: (filmUi: FilmUi) -> Unit) =
    adapterDelegateViewBinding<FilmUi, Item, FilmCardBinding>(
        { layoutInflater, parent -> FilmCardBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            with(binding) {
                tvFilmName.text = item.title
                tvRating.text = item.rating
                ivPoster.loadImage(item.posterUrl)
                itemView.setOnClickListener { onClickAction.invoke(item) }
            }
        }
    }