package com.mgm.kiliaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kiliaro.R
import kiliaro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val itemList: Array<String>
        get() = arrayOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7",
            "Item 8",
            "Item 9",
            "Item 10",
            "Item 11",
            "Item 12",
            "Item 13",
            "Item 14",
            "Item 15",
            "Item 16",
            "Item 17",
            "Item 18",
            "Item 19",
            "Item 20",
            "Item 21",
            "Item 22"
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupGridView()
    }

    private fun setupGridView() {
        val adapter = ImageListAdapter(this, R.layout.list_item, itemList)
        binding.gridview.adapter = adapter

        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    this@MainActivity, " Clicked Position: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}