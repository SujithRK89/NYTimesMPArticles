package com.srk.nytimes.ui.mostpopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.srk.nytimes.R
import com.srk.nytimes.data.remote.model.MostPopular
import com.srk.nytimes.databinding.MostPopularFragmentBinding
import com.srk.nytimes.handler.MPItemHandler
import com.srk.nytimes.ui.adapter.MPAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularFragment : Fragment() {

    companion object {
        fun newInstance() = MostPopularFragment()
    }

    private lateinit var adapter: MPAdapter
    private lateinit var binding: MostPopularFragmentBinding
    private val viewModel: MostPopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MostPopularFragmentBinding.inflate(inflater).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        setUpRecyclerView()

        addObserver()
    }

    private fun addObserver() {

        viewModel.mostPopularList.observe(viewLifecycleOwner, {

            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.message.observeForever {
            it.getContentIfNotHandled()?.let { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry), View.OnClickListener {
                    viewModel.invokeMostPopular()
                }).show()
            }
        }
    }

    private fun setUpRecyclerView() {

        adapter = MPAdapter(object : MPItemHandler {
            override fun onItemClick(item: MostPopular) {
                navigate(item)
            }
        })

        binding.rvPopular.adapter = adapter
    }

    private fun navigate(item: MostPopular) {
        findNavController().navigate(MostPopularFragmentDirections.actionHomeToDetail(item))
    }

}