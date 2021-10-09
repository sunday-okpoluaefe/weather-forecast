package com.sunday.weatherapplication.ui.forecast_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunday.weatherapplication.databinding.FragmentForecastBinding
import com.sunday.weatherapplication.ui.factory.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null

    @Inject
    lateinit var factory: MainViewModelFactory

    lateinit var vm: ForecastViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.vm = ViewModelProvider(this, factory).get(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val city = it.getString("CITY")
            requireActivity().toolbar.title = city
            this.vm.getForecast(city = city ?: "")
        }

        this.vm.loading.observe(this, Observer {
            this.binding.swipeContainer.isRefreshing = it
        })

        this.vm.forecast.observe(this, Observer {
            println(it.base)
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}