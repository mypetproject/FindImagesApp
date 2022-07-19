package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            setSupportActionBar(toolbar)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, GridFragment(), GridFragment::class.simpleName)
            .commit()

        /*ImagesInteractor().getNote().enqueue(object : Callback<ImagesResults> {
            override fun onResponse(
                call: Call<ImagesResults>,
                response: Response<ImagesResults>
            ) {
                Log.d("myLogs", "onResponse: $response")
                response.body()?.imagesResults?.asFlow()
            }

            override fun onFailure(call: Call<ImagesResults>, t: Throwable) {
                Log.d("myLogs", "onFailure: $t")
            }
        })*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        this.menu = menu

        (menu.findItem(R.id.search_menu_item).actionView as SearchView).setOnQueryTextListener(
            object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(this@MainActivity, "Our word : $query", Toast.LENGTH_SHORT)
                        .show()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        var currentPosition = 0
    }
}