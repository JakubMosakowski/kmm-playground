package com.jakmos.counter.android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jakmos.counter.kmm.shared.cache.Database
import com.jakmos.counter.kmm.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    private val database = Database(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.text_view)
        textView.reloadList()

        button.setOnClickListener {
            mainScope.launch {
                database.insertUser("Random user ${Random.nextInt(1000)}")
                textView.reloadList()
            }
        }
    }

    private fun TextView.reloadList() = mainScope.launch {
        text = database.getAllUsers().toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
