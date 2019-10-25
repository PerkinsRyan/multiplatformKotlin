package com.rperkins.nationalparks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    private val presenter = MainPresenter(ParksCloud())
    private lateinit var contactsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        contactsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        listView.adapter = contactsAdapter

        presenter.bindView(this)

        searchButton.setOnClickListener {
            presenter.filterParks(filterTextView.text.toString())
        }
    }

    override fun showParks(parks: List<Park>) {
        contactsAdapter.clear()
        contactsAdapter.addAll(parks.map { it.name })
    }

    override fun showVisitedCount(count: Int) {
        supportActionBar?.let { toolbar ->
            toolbar.title = getString(R.string.main_count, count)
        }
    }

}