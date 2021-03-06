package com.sunday.weatherapplication.ui.forecast

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunday.weatherapplication.databinding.FragmentForecastBinding
import com.sunday.weatherapplication.ui.factory.MainViewModelFactory
import com.sunday.weatherapplication.util.extensions.loadImage
import com.sunday.weatherapplication.util.extensions.toVisibility
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

    private var city: String = ""

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
            city = it.getString("CITY") ?: ""
            requireActivity().toolbar.title = city
            this.vm.getForecast(city = city)
        }

        setObservers()

        binding.swipeContainer.setOnRefreshListener {
            this.vm.getForecast(city = city)
        }
    }

    private fun setError(isError: Boolean){
        binding.apply {
            currentLayout.visibility = (!isError).toVisibility()
            error.visibility = isError.toVisibility()
        }
    }

    private fun setObservers(){
        this.vm.loading.observe(this, Observer {
            this.binding.swipeContainer.isRefreshing = it
        })

        this.vm.error.observe(this, Observer {
            setError(true)
        })

        this.vm.forecast.observe(this, Observer {
            binding.apply {
                icon.loadImage(it.weather?.first()?.iconUrl())
                temperature.text = it.main?.temp.toString()
                weather.text = it.weather?.first()?.main
                "${it.main?.humidity.toString()}%".also { binding.humidity.text = it }
                "${it.clouds?.all}%".also { binding.precipitation.text = it }
                "${it.wind?.speed} m/h".also { binding.wind.text = it }
                setError(false)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}