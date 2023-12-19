package com.example.activivitat4

// ex!6      Crea la class Torre heredera de Peça. Implementa el mètode mou per a que torni true si:
//        1. Les PosX origen i destí són iguals o
//        2. Les PosY origen i destí són iguals.
//           En cas contrari torni un false.

open class Torre(posX:Int,posY:Int):PesaDEscacs(posX,posY) {
    override fun mou(pX: Int, pY: Int): Boolean {
        var moure = false
        if(pX == posX && pY != posY) {
            posY = pY
            moure = true
            return moure
        }else if (pY == posY && pX != posX) {
            posX = pX
            moure = true
            return moure
        }else
            return moure


    }

}
