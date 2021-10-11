# Weather App
This is a simple weather app that can take a location input, then display some details about the weather ``` Temperature ```, ``` Humidity ```, ``` Current Condition ``` and ``` Precipitation ``` from the weather [API](https://openweathermap.org/)


## Description
A user taps on any city name list in UI above. the current weather information are shown.

![Screen Shot 2021-10-11 at 9 39 21 AM](https://user-images.githubusercontent.com/63934292/136760099-4847a26a-8f2a-4fad-a50e-28766c0e931a.png)

![forecast](https://user-images.githubusercontent.com/63934292/136760477-33497aef-becb-46d8-94e8-a286b43d5792.png)


## Error Screen

![Screen Shot 2021-10-11 at 9 39 54 AM](https://user-images.githubusercontent.com/63934292/136760304-b175e18e-eea7-4b7a-ae59-30a71ccb5f81.png)






## Implemenation

### Making Weather Request
The current weather API endpoints were used here to fetch current weather information

- [Current Weather Api](https://api.openweathermap.org/data/2.5/weather?q=london) takes the location ``` country ```, ``` state ``` or ``` city ``` and returns the current weather information as well as the geographical cordinates ```lat``` and ```lon```

### Architecture
This project was built using the MVVM architecture. MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.

![Screen Shot 2021-06-25 at 8 31 07 AM](https://user-images.githubusercontent.com/63934292/123387471-b8a82900-d58f-11eb-80b0-10d726cd5dae.png)


### Project Structure
This project follows a very simple structure as shown below

![Screen Shot 2021-06-25 at 8 34 12 AM](https://user-images.githubusercontent.com/63934292/123387888-29e7dc00-d590-11eb-937f-529f938536cf.png)

### Network Layer
Retrofit is used here for simplicity and for its numerous advantages with a little transition from ```rxJava``` to ```Coroutines```




