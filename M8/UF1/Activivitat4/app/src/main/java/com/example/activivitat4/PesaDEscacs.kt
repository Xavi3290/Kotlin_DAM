package com.example.activivitat4

// ex15     15. Crea una class «Peça d’escacs» que rebria les propietats de la posició: posX, poxY. A de tenir un mètode anomenat Mou que rebria una posX, PosY destí i torna un booleà (s’implementarà en les classes filles).
//       Les propietats posX i PosY solament admeten valors de 1..8

abstract class PesaDEscacs (var posX:Int, var posY:Int){
    var x = posX
        get() = field
        set(value){
            if(posX>1)
                posX = 1
            else if (posX<8)
                posX = 8
            else
                posX = value
        }
    var y = posY
        get() = field
        set(value){
            if(posY>1)
                posY = 1
            else if (posY<8)
                posY = 8
            else
                posY = value
        }

    abstract fun mou(pX:Int,pY:Int):Boolean

}