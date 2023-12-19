package com.example.activitat8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitat8.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var listTempsDia: java.util.ArrayList<TempsDia>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val dia = LocalDateTime.now()
        val diaEuropeu = dia.format(format)
        binding.tv1.text = diaEuropeu.toString()

        omplirTempsDia()

        llancarListTempsDia()
    }

    private fun llancarListTempsDia() {
        binding.rv1.layoutManager =
            LinearLayoutManager(this)  //el linearlayoutmanager es para que salga de uno en uno
        binding.rv1.adapter = Adaptador(listTempsDia)
    }

    fun omplirTempsDia() {
        listTempsDia = arrayListOf()
        listTempsDia.addAll(
            listOf(
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/partly_cloudy.png",
                    "0:00",
                    "10",
                    "2",
                    "40",
                    "11"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/sunny.png",
                    "2:00",
                    "8",
                    "3",
                    "42",
                    "12"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/cloudy.png",
                    "4:00",
                    "9",
                    "4",
                    "41",
                    "14"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/rain_s_cloudy.png",
                    "6:00",
                    "11",
                    "6",
                    "43",
                    "15"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/cloudy_s_rain.png",
                    "8:00",
                    "13",
                    "8",
                    "48",
                    "13"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/sunny_s_rain.png",
                    "10:00",
                    "16",
                    "10",
                    "44",
                    "12"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/rain_light.png",
                    "12:00",
                    "20",
                    "10",
                    "42",
                    "16"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/rain.png",
                    "14:00",
                    "24",
                    "8",
                    "44",
                    "14"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/snow_s_rain.png",
                    "16:00",
                    "24",
                    "7",
                    "43",
                    "3"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/snow_s_sunny.png",
                    "18:00",
                    "25",
                    "9",
                    "42",
                    "12"
                ),
                TempsDia(
                    "https://ssl.gstatic.com/onebox/weather/64/snow.png",
                    "20:00",
                    "20",
                    "11",
                    "41",
                    "11"
                ),


                )
        )
    }
}