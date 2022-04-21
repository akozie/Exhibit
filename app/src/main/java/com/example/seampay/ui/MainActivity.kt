package com.example.seampay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seampay.R
import com.example.seampay.databinding.ActivityMainBinding
import com.example.seampay.db.Exhibits
import com.example.seampay.model.Exhibit
import com.example.seampay.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // DaggerHilt will inject the view-model for us
    private val viewModel: ExhibitViewModel by viewModels()
    private lateinit var recyclerviewItem: RecyclerView
    private lateinit var list: List<Exhibits>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The bellow segment would instantiate the exhibit layout
        // and will create a property for different views
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewItem = binding.recyclerView
        val layoutManager = LinearLayoutManager(this@MainActivity)



        viewModel.exhibit.observe(this) { result ->
           // list = result.
            val itemAdapter = ExhibitAdapter()
            itemAdapter.submitList(result.data)
            recyclerviewItem.adapter = itemAdapter
            recyclerviewItem.layoutManager = layoutManager

            binding.progressBar.isVisible = result is Resource.Loading<*> && result.data.isNullOrEmpty()
            binding.textViewError.isVisible = result is Resource.Error<*> && result.data.isNullOrEmpty()
            binding.textViewError.text = "No Internet"

        }
    }

}