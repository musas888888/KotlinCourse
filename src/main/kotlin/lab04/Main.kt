package lab04

data class WeatherData(
    val temperature: Float,
    val humidity: Float,
    val pressure: Float
)

interface Observer {
    fun update(data: WeatherData)
}

interface Subject {
    fun registerObserver(o: Observer)
    fun removeObserver(o: Observer)
    fun notifyObservers()
}

class WeatherStation : Subject {

    private val observers = mutableListOf<Observer>()
    private var currentData: WeatherData? = null

    override fun registerObserver(o: Observer) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObservers() {
        val data = currentData ?: return
        for (o in observers) {
            o.update(data)
        }
    }

    fun measurementsChanged(newData: WeatherData) {
        currentData = newData
        println("WeatherStation: Got new data -> $newData")
        notifyObservers()
    }
}

class CurrentConditionsDisplay : Observer {

    private var temperature = 0f
    private var humidity = 0f

    override fun update(data: WeatherData) {
        temperature = data.temperature
        humidity = data.humidity
        println("CurrentConditionsDisplay: ${temperature}C and ${humidity}% humidity")
    }
}

class StatisticsDisplay : Observer {

    private val temps = mutableListOf<Float>()

    override fun update(data: WeatherData) {
        temps.add(data.temperature)
        val avg = temps.average()
        println("StatisticsDisplay: Avg temperature: ${avg}C")
    }
}

fun main() {
    val station = WeatherStation()

    val current = CurrentConditionsDisplay()
    val stats = StatisticsDisplay()

    station.registerObserver(current)
    station.registerObserver(stats)

    station.measurementsChanged(WeatherData(25.0f, 65f, 1012f))
    station.measurementsChanged(WeatherData(27.5f, 70f, 1011f))

    station.removeObserver(stats)

    station.measurementsChanged(WeatherData(26.0f, 90f, 1013f))
}
