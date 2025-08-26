package com.example.clock

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.clock.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var clockJob: Job? = null
    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var selectedZoneId: ZoneId = ZoneId.systemDefault()

    private val cities = listOf(
        "Amsterdam" to "Europe/Amsterdam",
        "Auckland" to "Pacific/Auckland",
        "Bangkok" to "Asia/Bangkok",
        "Berlin" to "Europe/Berlin",
        "Brussels" to "Europe/Brussels",
        "Cairo" to "Africa/Cairo",
        "Chicago" to "America/Chicago",
        "Denver" to "America/Denver",
        "Dubai" to "Asia/Dubai",
        "Hong Kong" to "Asia/Hong_Kong",
        "Jakarta" to "Asia/Jakarta",
        "Johannesburg" to "Africa/Johannesburg",
        "Kolkata" to "Asia/Kolkata",
        "Kuala Lumpur" to "Asia/Kuala_Lumpur",
        "Kuwait" to "Asia/Kuwait",
        "London" to "Europe/London",
        "Los Angeles" to "America/Los_Angeles",
        "Madrid" to "Europe/Madrid",
        "Manila" to "Asia/Manila",
        "Melbourne" to "Australia/Melbourne",
        "Mexico City" to "America/Mexico_City",
        "Moscow" to "Europe/Moscow",
        "New York" to "America/New_York",
        "Paris" to "Europe/Paris",
        "Riyadh" to "Asia/Riyadh",
        "Rome" to "Europe/Rome",
        "Sao Paulo" to "America/Sao_Paulo",
        "Seoul" to "Asia/Seoul",
        "Shanghai" to "Asia/Shanghai",
        "Singapore" to "Asia/Singapore",
        "Stockholm" to "Europe/Stockholm",
        "Tehran" to "Asia/Tehran",
        "Tokyo" to "Asia/Tokyo",
        "Toronto" to "America/Toronto",
        "Vancouver" to "America/Vancouver",
        "Zurich" to "Europe/Zurich"
    ).sortedBy { it.first }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCityAutoComplete()
        updateTimeDisplay()
    }

    private fun setupCityAutoComplete() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cities.map { it.first }
        )
        binding.cityAutoComplete.setAdapter(adapter)

        // انتخاب پیش‌فرض بر اساس منطقهٔ زمانی دستگاه
        val currentZone = ZoneId.systemDefault().id
        val defaultCity = cities.find { it.second == currentZone }?.first ?: "Tehran"
        binding.cityAutoComplete.setText(defaultCity, false)
        selectedZoneId = ZoneId.of(cities.find { it.first == defaultCity }!!.second)

        binding.cityAutoComplete.setOnItemClickListener { _, _, position, _ ->
            val selectedCity = adapter.getItem(position)
            val zone = cities.find { it.first == selectedCity }?.second
            if (zone != null) {
                selectedZoneId = ZoneId.of(zone)
                updateTimeDisplay()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        startClockUpdates()
    }

    override fun onStop() {
        super.onStop()
        stopClockUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }

    private fun startClockUpdates() {
        clockJob = uiScope.launch {
            while (isActive) {
                updateTimeDisplay()
                delay(1000)
            }
        }
    }

    private fun stopClockUpdates() {
        clockJob?.cancel()
        clockJob = null
    }

    private fun updateTimeDisplay() {
        try {
            val zonedDateTime = ZonedDateTime.now(selectedZoneId)
            val now = zonedDateTime.toLocalDateTime()
            val time = zonedDateTime.toLocalTime()

            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")

            binding.timeTextView.text = now.format(timeFormatter)
            binding.dateTextView.text = now.format(dateFormatter)
            binding.cityTimeZoneTextView.text = "${binding.cityAutoComplete.text} (${selectedZoneId.id})"

            binding.analogClock.setTime(time)

        } catch (e: Exception) {
            binding.timeTextView.text = "--:--:--"
            binding.dateTextView.text = "Error loading date"
            binding.cityTimeZoneTextView.text = "Error"
        }
    }
}
