package com.learning.thegitrepoapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.learning.thegitrepoapp.R
import com.learning.thegitrepoapp.databinding.ActivityAddItemBinding
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import com.learning.thegitrepoapp.jetpack.module.isApiFailed
import com.learning.thegitrepoapp.jetpack.module.isApiSuccessful
import com.learning.thegitrepoapp.jetpack.usecase.observe
import com.learning.thegitrepoapp.jetpack.viewmodels.HomeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddItemBinding
    private val viewModels by viewModels<HomeVM>()
    private lateinit var model: ItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clickListener = this@AddItemActivity
        setUpObserver()

    }

    private fun setUpObserver() {
        observe(viewModels.liveData) {
            if (isApiSuccessful(it)){
                Log.d("jamun123", it.toString())
                val itemModel = (it as ResourceState.Success).body as ItemModel
                if (itemModel.url.isNotEmpty()) {
                    this.model = itemModel
                    Toast.makeText(
                        this@AddItemActivity,
                        "Repository Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }else if (isApiFailed(it)){
                Toast.makeText(
                    this@AddItemActivity,
                    "Please check the names Again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.checkMark -> if (validate()) {

                viewModels.getProduct(
                    RepoRequest(
                        userName = binding.idUserName.text.toString().trim(),
                        repoName = binding.idRepoName.text.toString().trim()
                    )
                )
            }
            R.id.backArrow -> super.onBackPressed()

        }

    }

    private fun validate(): Boolean {
        val userName = binding.idUserName.text.toString().trim()
        val repoName = binding.idRepoName.text.toString().trim()

        if (userName.isNotEmpty() && repoName.isNotEmpty()) {
            return true
        } else {
            Toast.makeText(this@AddItemActivity, "Please enter some text", Toast.LENGTH_SHORT)
                .show()
        }
        return false
    }
}