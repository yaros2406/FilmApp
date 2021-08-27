package com.example.filmapp.onefilmscreen.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.core.MainRouter
import com.example.filmapp.R
import com.example.filmapp.databinding.FragmentOneFilmBinding
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.example.filmapp.util.loadImage


class OneFilmFragment : Fragment(R.layout.fragment_one_film) {

    private val binding by viewBinding(FragmentOneFilmBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbAppBar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        arguments?.getParcelable<FilmUi>(FILM_ARG)?.let {
            setupFilmInfo(it)
        } ?: showError()
    }


    private fun setupFilmInfo(film: FilmUi) {
        binding.ivPoster.loadImage(film.posterUrl)
        binding.bWatch.setOnClickListener {
            (requireActivity() as? MainRouter)?.showFilmPlayer(film)
        }
        with(binding) {
            with(layoutFilmDetail) {
                tvFilmName.text = film.title
                tvRating.text = film.rating
                tvGenres.text = film.genres.toString()
                tvReleaseDate.text = film.releaseDate
                tvOverview.text = film.overview
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireActivity(), R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val FILM_ARG = "film_arg"

        fun newInstance(film: FilmUi): OneFilmFragment {
            return OneFilmFragment().apply {
                arguments = Bundle().apply { putParcelable(FILM_ARG, film) }
            }
        }
    }
}