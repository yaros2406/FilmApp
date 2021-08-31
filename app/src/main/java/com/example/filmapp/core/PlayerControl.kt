package com.example.filmapp.core

import com.google.android.exoplayer2.SimpleExoPlayer

interface PlayerControl{
    var player: SimpleExoPlayer

    fun playerPause(){
        player.pause()
    }

    fun releasePlayer() {
        player.release()
    }

}