package data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.data.model.CurrentWeather

@Serializable
data class WeatherResponse(
    @SerialName("current")val currentWeather: CurrentWeather
)