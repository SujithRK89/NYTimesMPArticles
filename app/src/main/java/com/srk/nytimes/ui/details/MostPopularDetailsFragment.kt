package com.srk.nytimes.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.srk.nytimes.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MostPopularDetailsFragment()
    }

    private lateinit var binding: DetailsFragmentBinding
    private val detailsViewModel: MostPopularDetailsViewModel by viewModels()
    private val args: MostPopularDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = detailsViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel._mostPopular.postValue(args.mostPopular)
    }

}