package com.example.filmapp.filmplayer.ui.fragment


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.filmapp.R
import com.example.filmapp.databinding.FragmentPlayerBinding
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class FilmPlayerFragment : Fragment(R.layout.fragment_player) {

    private val binding by viewBinding(FragmentPlayerBinding::bind)

    private lateinit var player: SimpleExoPlayer

    private var pausedPosition: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null && savedInstanceState.containsKey(PAUSED_POSITION_ARG)) {
            pausedPosition = savedInstanceState.getLong(PAUSED_POSITION_ARG, 0)
        }

        arguments?.getParcelable<FilmUi>(FILM_ARG)
            ?.takeIf { it.videoUrl.isNotBlank() }
            ?.let { initializePlayer(it.videoUrl) } ?: showError()
    }

    override fun onPause() {
        super.onPause()
        pausedPosition = player.currentPosition
        player.pause()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(PAUSED_POSITION_ARG, player.currentPosition)
    }

    private fun initializePlayer(uri: String) {
        if (!::player.isInitialized) {
            player = SimpleExoPlayer.Builder(requireActivity()).build()
        }

        binding.pvPlayer.player = player
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.seekTo(pausedPosition)
    }

    private fun showError() {
        Toast.makeText(requireActivity(), R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val FILM_ARG = "film_arg"
        private const val PAUSED_POSITION_ARG = "paused_position_arg"

        fun newInstance(film: FilmUi): FilmPlayerFragment {
            return FilmPlayerFragment().apply {
                arguments = Bundle().apply { putParcelable(FILM_ARG, film) }
            }
        }
    }
}