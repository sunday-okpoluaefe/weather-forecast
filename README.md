# Weather App
This is a simple weather app that can take a location input, then display some details about the weather ``` Temperature ```, ``` Humidity ```, ``` Current Condition ``` and ``` Precipitation ``` from the weather [API](https://openweathermap.org/)

![weather_ui 1](https://user-images.githubusercontent.com/63934292/123390454-d75bef00-d592-11eb-9d67-8ee75deacec8.png)


## Description
A user enters a locaton and hit ``` Show ```. the current weather information as well as future information for the next 7 days are shown.

![search 1](https://user-images.githubusercontent.com/63934292/123390472-dcb93980-d592-11eb-8386-d5c845c2d53c.png)


## Implemenation

### Making Weather Request
Two endpoints were used here to make both current weather and future weather information

- [Current Weather Api](https://api.openweathermap.org/data/2.5/weather?q=london) takes the location ``` country ```, ``` state ``` or ``` city ``` and returns the current weather information as well as the geographical cordinates ```lat``` and ```lon```

- [Future Weather Api](https://api.openweathermap.org/data/2.5/onecall?lat=&lon=) takes the ```lat``` and ```lon``` from [Current Weather Api](https://api.openweathermap.org/data/2.5/weather?q=london) to get future weather information for the next 7 days

### Architecture
This project was built using the MVVM architecture. MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

![Screen Shot 2021-06-25 at 8 31 07 AM](https://user-images.githubusercontent.com/63934292/123387471-b8a82900-d58f-11eb-80b0-10d726cd5dae.png)


### Project Structure
This project follows a very simple structure as shown below

![Screen Shot 2021-06-25 at 8 34 12 AM](https://user-images.githubusercontent.com/63934292/123387888-29e7dc00-d590-11eb-937f-529f938536cf.png)

### Network Layer
Retrofit is used here for simplicity and for its numerous advantages with a little transition from ```rxJava``` to ```Coroutines```

#### Retrofit Weather Service
```
package com.sunday.okpoluaefe.weather_app.services

import com.sunday.okpoluaefe.weather_app.data.response.CurrentWeatherResponse
import com.sunday.okpoluaefe.weather_app.data.response.FutureWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(value = "onecall")
    suspend fun getFutureForeCast(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double,
        @Query(value = "units") unit: String = "imperial",
        @Query(value = "exclude") exclude: String = "minutely,hourly,alerts"
    ) : FutureWeatherResponse

    @GET(value = "weather")
    suspend fun getCurrentForeCast(
        @Query(value = "q") location: String,
        @Query(value = "units") unit: String = "imperial"
    ) : CurrentWeatherResponse
}

```

#### Retrofit Weather Client

```
package com.sunday.okpoluaefe.weather_app.services

import com.sunday.okpoluaefe.weather_app.utils.WEATHER_API_KEY
import com.sunday.okpoluaefe.weather_app.utils.WEATHER_BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit(): Retrofit {

        val requestInterceptor = Interceptor {
            val url = it.request()
                .url()
                .newBuilder()
                .addQueryParameter("appid", WEATHER_API_KEY)
                .build()

            val request = it.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor it.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: WeatherApiService = getRetrofit().create(WeatherApiService::class.java)
}

```


## The ViewModel
The viewmodel uses constructor dipendency indjection for simplicity. 

```
package com.sunday.okpoluaefe.weather_app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sunday.okpoluaefe.weather_app.data.repository.WeatherRepository
import com.sunday.okpoluaefe.weather_app.services.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun getCurrentForeCast(location: String) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading(data = null))
        try {
            emit(ResponseWrapper.success(data = repository.getCurrentForeCast(location)))
        } catch (exception: Exception) {
            emit(ResponseWrapper.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getFutureForeCast(latitude: Double, longitude: Double) = liveData(Dispatchers.IO) {
        emit(ResponseWrapper.loading(data = null))
        try {
            emit(ResponseWrapper.success(data = repository.getFutureForeCast(latitude= latitude, longitude= longitude)))
        } catch (exception: Exception) {
            emit(ResponseWrapper.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}

```

## Localization
``` English ```, ``` French ```, ``` German ``` , ``` Chinese ```

![french_](https://user-images.githubusercontent.com/63934292/123426584-7eed1780-d5bb-11eb-86d3-11395ee53271.png)

## Testing the Weather Viewmodel
There are unit tests for four use cases as shown below
```

package com.sunday.okpoluaefe.weather_app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sunday.okpoluaefe.weather_app.data.mock.repository.MockWeatherRepository
import com.sunday.okpoluaefe.weather_app.data.response.CurrentWeatherResponse
import com.sunday.okpoluaefe.weather_app.data.response.FutureWeatherResponse
import com.sunday.okpoluaefe.weather_app.ui.WeatherViewModel
import com.sunday.okpoluaefe.weather_app.utils.Status
import com.sunday.okpoluaefe.weather_app.utils.extensions.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun testGetCurrentForeCast() {
        viewModel = WeatherViewModel(MockWeatherRepository())
        val result = viewModel.getCurrentForeCast(location = "abuja").getOrAwaitValue()
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun testGetFutureForeCast() {
        viewModel = WeatherViewModel(MockWeatherRepository())
        val result = viewModel.getFutureForeCast(latitude = 7.34345, longitude = 3.5434).getOrAwaitValue()
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun testFailedFutureWeatherRequest(){
        viewModel = WeatherViewModel(object : MockWeatherRepository(){
            override suspend fun getFutureForeCast(
                latitude: Double,
                longitude: Double
            ): FutureWeatherResponse {
                throw Exception()
            }
        })

        val result = viewModel.getFutureForeCast(latitude = 7.34345, longitude = 3.5434).getOrAwaitValue()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun testFailedCurrentWeatherRequest(){
        viewModel = WeatherViewModel(object : MockWeatherRepository(){
            override suspend fun getCurrentForeCast(
                location: String
            ): CurrentWeatherResponse {
               throw Exception()
            }
        })

        val result = viewModel.getCurrentForeCast(location = "abuja").getOrAwaitValue()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }
}

```



