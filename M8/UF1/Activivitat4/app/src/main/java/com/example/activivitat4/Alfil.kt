package com.example.activivitat4

// Ex17     17. Crea la class Alfil heredera de Peça. Implementa el mètode mou per a que torni true si:
//        1. La PosX origen / PosY origen  = PosX destí / PosY destí
//        2. (PosX origen – PosX desti) / (PosY origen – PosY desti) = 1
//           En cas contrari torni un false

open class Alfil(posX:Int, posY:Int):PesaDEscacs(posX,posY) {
    override fun mou(pX: Int, pY: Int): Boolean {
        var moure = false
        if((posX-pX)==(posY-pY)) {
            posX = pX
            posY = pY
            moure = true
            return moure
        }else if((posX-pX)==(pY-posY)){
            posX = pX
            posY = pY
            moure = true
            return moure
        }else
            return moure
    }

}