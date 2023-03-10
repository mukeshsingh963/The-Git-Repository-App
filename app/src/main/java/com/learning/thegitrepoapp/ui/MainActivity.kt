package com.learning.thegitrepoapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.thegitrepoapp.R
import com.learning.thegitrepoapp.databinding.ActivityMainBinding
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.usecase.observe
import com.learning.thegitrepoapp.jetpack.viewmodels.HomeVM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HomeVM>()
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var adapter: ReposAdapter

    private var listener: (Int, ItemModel, Int) -> Unit = { adappterPosition, model, action ->

        Log.d("jamun", model.toString())
        when (action) {
            ReposAdapter.ACTION_DELETE -> viewModel.deleteItem(model)
            ReposAdapter.ACTION_OPEN ->  adapter.openBrowser(model.url,this)
            ReposAdapter.ACTION_SHARE -> share(model.url)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        binding.clickListener = this
        observer()
    }

    private fun initUI() {
        binding.idRecyclerView.hasFixedSize()
        binding.idRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = ReposAdapter(this, listener)
        binding.idRecyclerView.adapter = adapter

    }

    private fun observer() {
        observe(viewModel.dbLiveData) {
            Log.d("jamun", "aya hahi ${it.size}")
            adapter.updateList(it)
        }
    }

    override fun onClick(v: View?) {
    if (v!!.id == R.id.fb_add_note)
        handler.postDelayed({
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

    fun share(url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, url)
        startActivity(Intent.createChooser(intent, "Share Link Using"))
    }

}

