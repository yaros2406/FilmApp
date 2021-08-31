package com.example.filmapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.filmapp.core.MainRouter
import com.example.filmapp.filmlistscreen.ui.fragment.FilmListFragment
import com.example.filmapp.filmlistscreen.ui.model.FilmUi
import com.example.filmapp.filmplayer.ui.fragment.FilmPlayerFragment
import com.example.filmapp.onefilmscreen.ui.fragment.OneFilmFragment

class MainActivity : AppCompatActivity(), MainRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            showFragment(FilmListFragment())
        }
    }

    override fun showFilmInfo(film: FilmUi) {
        showFragment(OneFilmFragment.newInstance(film))
    }

    override fun showFilmPlayer(film: FilmUi) {

        showFragment(FilmPlayerFragment.newInstance(film))

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}