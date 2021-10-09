# Weather App
This is a simple weather app that can take a location input, then display some details about the weather ``` Temperature ```, ``` Humidity ```, ``` Current Condition ``` and ``` Precipitation ``` from the weather [API](https://openweathermap.org/)

![cities](https://user-images.githubusercontent.com/63934292/136660901-f9c697d2-aaa4-4514-8f29-0e84d4064a36.png)


## Description
A user taps on any city name list in UI above. the current weather information are shown.

![forecast](https://user-images.githubusercontent.com/63934292/136660916-34e83752-f415-45f8-a370-97c115113dfc.png)




## Implemenation

### Making Weather Request
Two endpoints were used here to make both current weather and future weather information

- [Current Weather Api](https://api.openweathermap.org/data/2.5/weather?q=london) takes the location ``` country ```, ``` state ``` or ``` city ``` and returns the current weather information as well as the geographical cordinates ```lat``` and ```lon```

### Architecture
This project was built using the MVVM architecture. MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

![Screen Shot 2021-06-25 at 8 31 07 AM](https://user-images.githubusercontent.com/63934292/123387471-b8a82900-d58f-11eb-80b0-10d726cd5dae.png)


### Project Structure
This project follows a very simple structure as shown below

![Screen Shot 2021-06-25 at 8 34 12 AM](https://user-images.githubusercontent.com/63934292/123387888-29e7dc00-d590-11eb-937f-529f938536cf.png)

### Network Layer
Retrofit is used here for simplicity and for its numerous advantages with a little transition from ```rxJava``` to ```Coroutines```




