package com.leodeleon.onflick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leodeleon.onflick.feed.FeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.content, FeedFragment())
                .commit()
    }
}
