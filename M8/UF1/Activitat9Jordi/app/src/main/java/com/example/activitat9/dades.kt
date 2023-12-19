package com.example.activitat9

import androidx.navigation.NavController
import com.google.android.material.navigation.NavigationView

data class portada(
    val imatge: String,
    val descripcio: String
)

data class producte(
    val codi: Int,
    val imatge: String,
    val descripcio: String,
    val descripcioCompleta: String,
    val preu: Double,
    val tipusProducte: Int,
    val tipusCarview: Int
)

data class producteCarret(
    val codiproducte: Int,
    val qtt: Int
)

lateinit var dadesPortada : ArrayList<portada>
lateinit var llistaProductes: ArrayList<producte>
var llistaCarret: ArrayList<producteCarret> = arrayListOf()

lateinit var navView: NavigationView
lateinit var navController : NavController
fun omplirDadesPortada() {
    dadesPortada = arrayListOf()
    dadesPortada.addAll(
        listOf(
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/2Bf1asULGycgHN9oP5cOI5/d9f769907663aae42b34cae95b6e194e/test_1.png", "Smartphones"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/3cIeQiIHcJJUk2s2yPFP2x/158640ac43949b00e877e240c9938e2f/portatil1.png", "Ordinadors"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/3SiL1Mo8WNRMMTwADtoHJH/54312a5006894bda0e14f4c207025a99/tele1.png", "Televisions"),
            portada("https://cms-images.mmst.eu/osyynfyvlyjc/4bril4wujHiHuI7ugmjyEH/80866060e6e05242018fc6f993a8477c/patinete.png", "Movilitat")
        )
    )

}

fun omplirComputer() {
    llistaProductes = arrayListOf()
    llistaProductes.addAll(
        listOf(
            producte(
                1,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                2,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                3
            ),
            producte(
                3,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                4,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                5,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                6,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                7,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967244/fee_786_587_png",
                "Portátil - ASUS F1400EA-EK1838W, 14\"",
                "Portátil - ASUS F1400EA-EK1838W, 14\" Full HD, Intel® Core™ i5-1135G7, 8GB RAM, 512GB SSD, Iris® Xe Graphics, Windows 11 Home",
                499.0,
                1,
                2
            ),
            producte(
                8,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,1,
                1
            ),
            producte(
                9,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99019348/mobile_200_200_png",
                "Portátil - Acer Aspire 3 A315-56, 15.6",
                "Portátil - Acer Aspire 3 A315-56, 15.6\" Full HD, Intel® Core™ i3-1005G1, 8GB RAM, 512GB SSD, UHD, Windows 11",
                389.0,
                1,
                1
            ),
            producte(
                10,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967244/fee_786_587_png",
                "Portátil - ASUS F1400EA-EK1838W, 14\"",
                "Portátil - ASUS F1400EA-EK1838W, 14\" Full HD, Intel® Core™ i5-1135G7, 8GB RAM, 512GB SSD, Iris® Xe Graphics, Windows 11 Home",
                499.0,
                1,
                2
            ),
            producte(
                11,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png",
                "Samsung Galaxy S22 negre",
                "Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",
                999.0,
                0,
                1

            ),
            producte(
                12,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98898029/mobile_200_200_png",
                "Xiaomi Redmi Note 10 Pro, Azul Glaciar",
                "Xiaomi Redmi Note 10 Pro, Azul Glaciar, 256 GB, 8 GB RAM, 6.67 \" Full HD+, Snapdragon 732G, 5020 mAh, Android",
                249.0,
                0,
                2

            ),
            producte(
                13,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_95170218/mobile_200_200_png",
                "Xiaomi Redmi 9A, Light Blue",
                "Xiaomi Redmi 9A, Light Blue, 32 GB, 2 GB RAM, 6.53\" HD+, MediaTek Helio G25, 5000 mAh, Android",
                99.0,
                0,
                1

            ),
            producte(
                14,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98489325/mobile_200_200_png",
                "POCO M5, Verde",
                "POCO M5, Verde, 64 GB, 4 GB RAM, 6.58 \" Full HD+, MediaTek Helio G99, 5000 mAh, Android",
                168.0,
                0,
                1

            ),
            producte(
                15,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png",
                "Samsung Galaxy S22 negre",
                "Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",
                999.0,
                0,
                1

            ),
            producte(
                16,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png",
                "Samsung Galaxy S22 negre",
                "Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",
                999.0,
                0,
                3

            ),
            producte(
                17,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png",
                "Samsung Galaxy S22 negre",
                "Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",
                999.0,
                0,
                2

            ),
            producte(
                18,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png",
                "Samsung Galaxy S22 negre",
                "Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",
                999.0,
                0,
                1
            ),
            producte(
                19,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png",
                "TV OLED 65\" - Samsung QE65S95BATXXC",
                "TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",
                1599.0,
                2,
                1
            ),
            producte(
                20,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png",
                "TV OLED 65\" - Samsung QE65S95BATXXC",
                "TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",
                1599.0,
                2,
                1
            ),
            producte(
                21,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_92998834/mobile_200_200_png",
                "TV OLED 65\" - LG OLED65B26LA, OLED 4K",
                "TV OLED 65\" - LG OLED65B26LA, OLED 4K, Procesador α7 Gen5 AI Processor 4K, Smart TV, DVB-T2 (H.265), Negro",
                1399.0,
                2,
                3
            ),
            producte(
                22,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png",
                "TV OLED 65\" - Samsung QE65S95BATXXC",
                "TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",
                1599.0,
                2,
                1
            ),
            producte(
                23,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png",
                "TV OLED 65\" - Samsung QE65S95BATXXC",
                "TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",
                1599.0,
                2,
                1
            ),
            producte(
                24,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_92998834/mobile_200_200_png",
                "TV OLED 65\" - LG OLED65B26LA, OLED 4K",
                "TV OLED 65\" - LG OLED65B26LA, OLED 4K, Procesador α7 Gen5 AI Processor 4K, Smart TV, DVB-T2 (H.265), Negro",
                1399.0,
                2,
                2
            ),
            producte(
                25,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_83178311/fee_786_587_png",
                "Patinete eléctrico - SmartGyro Rockway Pro",
                "Patinete eléctrico - SmartGyro Rockway Pro, 10\", Potencia nominal 800 W, 25 km/h, Hasta 120 kg, 15000 mAh, 45-50 km, Negro",
                829.0,
                3,
                1
            ),
            producte(
                26,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_83178311/fee_786_587_png",
                "Patinete eléctrico - SmartGyro Rockway Pro",
                "Patinete eléctrico - SmartGyro Rockway Pro, 10\", Potencia nominal 800 W, 25 km/h, Hasta 120 kg, 15000 mAh, 45-50 km, Negro",
                829.0,
                3,
                1
            ),
            producte(
                27,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_88736507/fee_786_587_png",
                "Casco - Youin LED",
                "Casco - Youin LED, Para patinete eléctrico o bicicleta, Talla L, Luz trasera, Negro",
                44.9,
                3,
                3
            ),
            producte(
                28,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_83178311/fee_786_587_png",
                "Patinete eléctrico - SmartGyro Rockway Pro",
                "Patinete eléctrico - SmartGyro Rockway Pro, 10\", Potencia nominal 800 W, 25 km/h, Hasta 120 kg, 15000 mAh, 45-50 km, Negro",
                829.0,
                3,
                1
            ),
            producte(
                29,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91655876/fee_786_587_png",
                "Bicicleta eléctrica - Youin You-Ride Los Angeles",
                "Bicicleta eléctrica - Youin You-Ride Los Angeles, 250W, 25km/h, Shimano de 7 vel., 28\", Pantalla, Negro",
                649.0,
                3,
                2
            ),
            producte(
                27,
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_88736507/fee_786_587_png",
                "Casco - Youin LED",
                "Casco - Youin LED, Para patinete eléctrico o bicicleta, Talla L, Luz trasera, Negro",
                44.9,
                3,
                3
            )
        )
    )
}