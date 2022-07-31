package com.example.findimagesapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.findimagesapp.R
import com.example.findimagesapp.databinding.ActivityMainBinding
import com.example.findimagesapp.presentation.viewModels.MainActivityViewModel
import com.example.findimagesapp.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Activity with a fragments container
 *
 * @author S. Kishkar
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var menu: Menu? = null
    private val model: MainActivityViewModel by viewModels()
    private val gridFragment = GridFragment()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                setSupportActionBar(toolbar)
            }

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_container, gridFragment, GridFragment::class.simpleName)
            .addToBackStack(null)
            .commit()

        lifecycleScope.launch {
            model.failureResponse.collect {
                applicationContext.toast(it)
            }
        }
    }

    fun startPagerFragment() {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_container, PagerFragment(), PagerFragment::class.java.simpleName)
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

        setSearchMenuItemListeners(menu)

        if (supportFragmentManager.findFragmentByTag(PagerFragment::class.java.simpleName) != null) {
            showInfoButton()
        }

        binding.toolbar.title = model.query.ifBlank { getString(R.string.default_toolbar_title) }

        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearchMenuItemListeners(menu: Menu) {
        menu.findItem(R.id.search_menu_item).run {
            (actionView as SearchView).setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) binding.toolbar.title = query
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
                            { it.setQuery(model.query, false) },
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

    private fun showInfoButton() {
        menu?.findItem(R.id.info)?.isVisible = true
        menu?.findItem(R.id.search_menu_item)?.isVisible = false
    }

    companion object {
        var currentPosition = 0
        const val SEARCH_VIEW_SET_TEXT_DELAY = 50L
    }
}