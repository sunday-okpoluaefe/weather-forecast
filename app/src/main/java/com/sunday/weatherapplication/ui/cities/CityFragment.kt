package com.sunday.weatherapplication.ui.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sunday.weatherapplication.R
import com.sunday.weatherapplication.data.model.City
import com.sunday.weatherapplication.databinding.FragmentCityBinding
import com.sunday.weatherapplication.ui.cities.adapter.CityAdapter
import com.sunday.weatherapplication.ui.cities.adapter.CityViewHolder
import com.sunday.weatherapplication.ui.factory.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CityFragment : Fragment(), CityViewHolder.Delegate {

    private var _binding: FragmentCityBinding? = null

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var vm: CityViewModel

    private lateinit var adapter: CityAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCityBinding.inflate(inflater, container, false)

        this.vm = ViewModelProvider(this, factory).get(CityViewModel::class.java)
        this.vm.cities.observe(this, {
            this.adapter.load(it)
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        this.adapter = CityAdapter(this)
        this.binding.recyclerView.adapter = this.adapter
    }

    override fun onClick(city: City) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, Bundle().apply {
            putString("CITY", city.name)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}