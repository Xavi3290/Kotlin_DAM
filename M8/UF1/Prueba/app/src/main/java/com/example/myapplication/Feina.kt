package com.example.myapplication

open class Feina(var empleat: Empleat, var feina: String) {    // open devant per que no sigi final i pugui heretar
    var sou: Double = empleat.sou
        get() = field
        set(value) {
            if (feina == "Informatic"){
                empleat.sou = value*1.15
                field = value*1.15
            }else{
                field= value
            }
        }
    constructor(empleat:Empleat, feina:String, sou:Double): this(empleat, feina){
        this.sou = sou
    }

    open fun mostraFeina(): String{
        return "${empleat.nomcognoms} treballa de $feina"
    }


}