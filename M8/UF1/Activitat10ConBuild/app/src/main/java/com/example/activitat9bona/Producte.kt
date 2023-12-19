package com.example.activitat9bona

import com.example.activitat9bona.BD.Carritos
import com.example.activitat9bona.BD.Usuari

public var listPortada: java.util.ArrayList<Portada> = arrayListOf()
public var listProducte: java.util.ArrayList<Producte> = arrayListOf()
public var listCarrito: java.util.ArrayList<Producte> = arrayListOf()
var userBD: List<Usuari>? = arrayListOf()
var listCarritoBD:ArrayList<Carritos>? = arrayListOf()
//public lateinit var listCarrito: MutableList<Producte>




data class Producte(
    val codi:Int,
    val imatge:String,
    val descripcio:String,
    val descripcioCompleta:String,
    val preu:Double,
    val tipusProducte:Int,
    val tipusCardview:Int

)

data class Portada(
    val imatge:String,
    val descripcio: String,
    val tipusProducte: Int  //al final no sa fet serbir
)

data class ProducteCarret(
    val codi:Int,
    val imatge:String,
    val descripcio:String
)



fun omplirPortada(){
    listPortada = arrayListOf()
    listPortada.addAll(
        listOf(
            Portada("https://cms-images.mmst.eu/osyynfyvlyjc/2bVLOFX3OyfEXHuSd7PFF4/f7eb33ed4108608df2c1e71084febc3a/tele1.png?q=80&w=264","Smartphone",1),
            Portada("https://cms-images.mmst.eu/osyynfyvlyjc/hRsXNZM0LKfKKdTssQBVq/3ff9fe69641bfff508a47cf58706f754/portatil1.png?q=80&w=264","Ordinadors",2),
            Portada("https://cms-images.mmst.eu/osyynfyvlyjc/7eiGGhEkYZBBjuZtnx1wkj/437e943b38c502667feffd4c210da7ca/tele1.png?q=80&w=264","Televisions",3),
            Portada("https://cms-images.mmst.eu/osyynfyvlyjc/6RI4ZRlFJsAeg3uaiMjFM9/4879d28922b1e795e1c8357fc4bb99d9/patiente1.png?q=80&w=264","Movilitat",4)
        )
    )
}

fun omplirProductes(){

    listProducte = arrayListOf()
    listProducte.addAll(
        listOf(
            Producte(1,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91289781/mobile_200_200_png","Apple Iphone 13","Apple iPhone 13, Medianoche, 128 GB, 5G, 6.1\" OLED Super Retina XDR, Chip A15 Bionic, iOS",865.00,1,1),
            Producte(2,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91289781/mobile_200_200_png","Apple Iphone 13","Apple iPhone 13, Medianoche, 128 GB, 5G, 6.1\" OLED Super Retina XDR, Chip A15 Bionic, iOS",865.00,1,1),
            Producte(3,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91289781/mobile_200_200_png","Apple Iphone 13","Apple iPhone 13, Medianoche, 128 GB, 5G, 6.1\" OLED Super Retina XDR, Chip A15 Bionic, iOS",865.00,1,1),
            Producte(4,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png","Samsung Galaxy S22 Ultra 5G","Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",999.00,1,2),
            Producte(5,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91289781/mobile_200_200_png","Apple Iphone 13","Apple iPhone 13, Medianoche, 128 GB, 5G, 6.1\" OLED Super Retina XDR, Chip A15 Bionic, iOS",865.00,1,1),
            Producte(6,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91289781/mobile_200_200_png","Apple Iphone 13","Apple iPhone 13, Medianoche, 128 GB, 5G, 6.1\" OLED Super Retina XDR, Chip A15 Bionic, iOS",865.00,1,1),
            Producte(7,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_91248603/mobile_200_200_png","Samsung Galaxy S22 Ultra 5G","Samsung Galaxy S22 Ultra 5G, Black, 256GB, 12GB RAM, 6.8\" QHD+, Exynos 2200, 5000mAh, Android 12",999.00,1,2),
            Producte(8,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967352/mobile_200_200_png","ASUS F515JA-EJ2883W"," ASUS F515JA-EJ2883W, 15.6\" Full HD, Intel® Core™ i7-1065G7, 16GB RAM, 512GB SSD, Iris® Plus Graphics, Windows 11 Home",599.00,2,1),
            Producte(9,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967352/mobile_200_200_png","ASUS F515JA-EJ2883W"," ASUS F515JA-EJ2883W, 15.6\" Full HD, Intel® Core™ i7-1065G7, 16GB RAM, 512GB SSD, Iris® Plus Graphics, Windows 11 Home",599.00,2,1),
            Producte(10,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967352/mobile_200_200_png","ASUS F515JA-EJ2883W"," ASUS F515JA-EJ2883W, 15.6\" Full HD, Intel® Core™ i7-1065G7, 16GB RAM, 512GB SSD, Iris® Plus Graphics, Windows 11 Home",599.00,2,1),
            Producte(11,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967352/mobile_200_200_png","ASUS F515JA-EJ2883W"," ASUS F515JA-EJ2883W, 15.6\" Full HD, Intel® Core™ i7-1065G7, 16GB RAM, 512GB SSD, Iris® Plus Graphics, Windows 11 Home",599.00,2,1),
            Producte(12,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_96958432/mobile_200_200_png","Lenovo IdeaPad 1 15ADA7","Lenovo IdeaPad 1 15ADA7, 15.6\" Full HD, AMD Ryzen™ 5 3500U, 8GB RAM, 512GB SSD, Radeon™ Vega 8 Graphics, Windows 11 Home",399.00,2,2),
            Producte(13,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98967352/mobile_200_200_png","ASUS F515JA-EJ2883W"," ASUS F515JA-EJ2883W, 15.6\" Full HD, Intel® Core™ i7-1065G7, 16GB RAM, 512GB SSD, Iris® Plus Graphics, Windows 11 Home",599.00,2,1),
            Producte(14,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_96958432/mobile_200_200_png","Lenovo IdeaPad 1 15ADA7","Lenovo IdeaPad 1 15ADA7, 15.6\" Full HD, AMD Ryzen™ 5 3500U, 8GB RAM, 512GB SSD, Radeon™ Vega 8 Graphics, Windows 11 Home",399.00,2,2),
            Producte(15,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png","TV OLED 65\" - Samsung QE65S95BATXXC","TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",1599.00,3,1),
            Producte(16,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png","TV OLED 65\" - Samsung QE65S95BATXXC","TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",1599.00,3,1),
            Producte(17,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png","TV OLED 65\" - Samsung QE65S95BATXXC","TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",1599.00,3,1),
            Producte(18,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98942215/mobile_200_200_png","TV OLED 65\" - LG OLED65CS6LA","TV OLED 65\" - LG OLED65CS6LA, UHD 4K, α9 Gen5 AI Processor 4K, Smart TV, DVB-T2 (H.265), Plata",1379.00,3,2),
            Producte(19,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_99017469/mobile_200_200_png","TV OLED 65\" - Samsung QE65S95BATXXC","TV OLED 65\" - Samsung QE65S95BATXXC, UHD 4K, Procesador Quantum 4K con IA, Smart TV, DVB-T2 (H.265), Plata",1599.00,3,1),
            Producte(20,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98942215/mobile_200_200_png","TV OLED 65\" - LG OLED65CS6LA","TV OLED 65\" - LG OLED65CS6LA, UHD 4K, α9 Gen5 AI Processor 4K, Smart TV, DVB-T2 (H.265), Plata",1379.00,3,2),
            Producte(21,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_98942215/mobile_200_200_png","TV OLED 65\" - LG OLED65CS6LA","TV OLED 65\" - LG OLED65CS6LA, UHD 4K, α9 Gen5 AI Processor 4K, Smart TV, DVB-T2 (H.265), Plata",1379.00,3,2),
            Producte(22,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_94422064/mobile_200_200_png","Patinete eléctrico - Segway Ninebot KickScooter D18E","Patinete eléctrico - Segway Ninebot KickScooter D18E, Hasta 100 kg, Velocidad 25 Km/h, Batería 183 Wh, Negro",249.00,4,1),
            Producte(23,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_94422064/mobile_200_200_png","Patinete eléctrico - Segway Ninebot KickScooter D18E","Patinete eléctrico - Segway Ninebot KickScooter D18E, Hasta 100 kg, Velocidad 25 Km/h, Batería 183 Wh, Negro",249.00,4,1),
            Producte(24,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_94422064/mobile_200_200_png","Patinete eléctrico - Segway Ninebot KickScooter D18E","Patinete eléctrico - Segway Ninebot KickScooter D18E, Hasta 100 kg, Velocidad 25 Km/h, Batería 183 Wh, Negro",249.00,4,1),
            Producte(25,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_94422064/mobile_200_200_png","Patinete eléctrico - Segway Ninebot KickScooter D18E","Patinete eléctrico - Segway Ninebot KickScooter D18E, Hasta 100 kg, Velocidad 25 Km/h, Batería 183 Wh, Negro",249.00,4,1),
            Producte(26,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_89197291/mobile_200_200_png","Patinete eléctrico - SmartGyro Rockway","Patinete eléctrico - SmartGyro Rockway, 500 W, Hasta 120 Kg, 45 km, 3 velocidades, Negro",555.00,4,2),
            Producte(27,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_89197291/mobile_200_200_png","Patinete eléctrico - SmartGyro Rockway","Patinete eléctrico - SmartGyro Rockway, 500 W, Hasta 120 Kg, 45 km, 3 velocidades, Negro",555.00,4,2),
            Producte(28,"https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_94422064/mobile_200_200_png","Patinete eléctrico - Segway Ninebot KickScooter D18E","Patinete eléctrico - Segway Ninebot KickScooter D18E, Hasta 100 kg, Velocidad 25 Km/h, Batería 183 Wh, Negro",249.00,4,1)

        )
    )


}

/*fun iniciarCarrito(){
    listCarrito = arrayListOf()
//listCarrito = mutableListOf()
}*/

