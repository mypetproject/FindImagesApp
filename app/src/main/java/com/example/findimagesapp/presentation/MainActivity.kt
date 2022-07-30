package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.ActivityMainBinding
import com.example.findimagesapp.presentation.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var menu: Menu? = null
    private val model: MainActivityViewModel by viewModels()
    private val gridFragment = GridFragment()
    private lateinit var binding: ActivityMainBinding
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                setSupportActionBar(toolbar)
            }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, gridFragment, GridFragment::class.simpleName)
            .commit()
    }

    fun startPagerFragment() {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(
                R.id.fragment_container, PagerFragment(), PagerFragment::class.java
                    .simpleName
            )
            .detach(gridFragment)
            .addToBackStack(null)
            .commit()
    }

    fun attachGridFragment() {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .attach(gridFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        this.menu = menu

        menu.findItem(R.id.search_menu_item).run {
            (actionView as SearchView).setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    this@MainActivity.query = query.orEmpty()
                    model.downloadData(query.orEmpty())
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    (p0?.actionView as SearchView).let {
                        it.postOnAnimationDelayed(
                            { it.setQuery(query, false) },
                            SEARCH_VIEW_SET_TEXT_DELAY
                        )
                    }
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    return true
                }
            })
        }

        if (supportFragmentManager.findFragmentByTag(PagerFragment::class.java.simpleName) != null) {
            showInfoButton()
        }

        binding.toolbar.title = query.ifBlank { getString(R.string.default_toolbar_title) }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.info -> {
                infoMenuItemClicked()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun infoMenuItemClicked() {
        DetailsBottomSheet().show(supportFragmentManager, DetailsBottomSheet.TAG)
    }


    fun fullImageShown() {
        invalidateOptionsMenu()
    }

    fun showInfoButton() {
        menu?.findItem(R.id.info)?.isVisible = true
        menu?.findItem(R.id.search_menu_item)?.isVisible = false
    }

    companion object {
        var currentPosition = 0
        const val SEARCH_VIEW_SET_TEXT_DELAY = 50L
    }
}