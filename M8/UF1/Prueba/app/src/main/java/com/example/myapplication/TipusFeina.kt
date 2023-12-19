package com.example.myapplication

class TipusFeina( empleat: Empleat, feina:String, var tipus: String):Feina(empleat, feina) {
    override fun mostraFeina(): String{
        return "${empleat.nomcognoms} treballa de $feina el seu tipus es $tipus"
    }
}