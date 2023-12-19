package com.example.activivitat4

import java.lang.reflect.Array

fun main(){

    // ex1 Crea una variable inmutable de nom descompte i assigna-li el valor 0,1. Podem crear una variable inmutable sense assignar-li un valor? Per què?
    val descompte:Double // S'ha de posarli un valor perque no es pot cambiar el valor es inmutable, pero la primera vegada es pot definir // Val per objecres inmutables
    descompte = 0.1
    // ex2 Crea una variable mutable de nom preu. El seu tipus ha d’admetre decimals.
    var preu:Double
    // ex3 Assigna-li un valor al preu.
    preu = 5.0
    // ex4 Crea una variable de nom preuDescompte que sigui igual al preu – preu * descompte.
    var preuDescompte = preu-preu*descompte
    // ex5 Crea un array mutable que serveixi per emmagatzemar noms de productes. Afegeix 5 elements.
    var nomsProductes:ArrayList<String> = arrayListOf()
    nomsProductes.add("Paper")
    nomsProductes.add("Boli")
    nomsProductes.add("Movil")
    nomsProductes.add("Cola")
    nomsProductes.add("USB")
    nomsProductes.addAll(listOf("p6","p7","p8"))            // una altre manera
    // ex6 Substitueix el 3r producte de l’array per un altre producte.
    nomsProductes.set(2,"Teclat")
    nomsProductes[2] = "teclat1"   // un altre manera
    // ex7 Esborra el 4t producte.
    nomsProductes.removeAt(3)
    nomsProductes.remove("teclat1")   // un altre manera
    // ex8 Afegeix un producte nou.
    nomsProductes.add("ColaNova")
    // ex9 Recorre l’array mostrant tots els valors.
    println()
    println("Primera forma")
    for (nom in nomsProductes)
        println("El producte és $nom")
    println()
    println("Segona manera")
    nomsProductes.forEach{producte -> println(producte)}
    println()
    println("Tercera manera")
    for (i in 0..nomsProductes.size-1){
        println(nomsProductes[i])
    }
    println()

    // ex10 Fes la funció PreuDescompte que rebi un preu i el tipus de descompte. El tipus de descompte ha de poder admetre nuls i torni el preu amb el descompte (preu – preu*descompte).
    println(preuDescompte(10.0,0.5))
    println()
    println(preuDescompte(10.0,null))
    println()

    // ex11 Funcio anonima // Fes una funció anònima que rebi un número de dia de la setmana i torni el seu nom en Català.
    var setmana:(Int) -> String = {
            num -> when{
                num == 1 -> "Dilluns"
                num == 2 -> "Dimarts"
                num == 3 -> "Dimecres"
                num == 4 -> "Dijous"
                num == 5 -> "Divendres"
                num == 6 -> "Disabte"
                num == 7 -> "Diumenge"
                else -> "No hi ha dia de la setmana"

            }
    }
    println(setmana(3))
    println()

    // ex 12 Emprant l’estructura when com expressió, assigna el nom del dia de la setmana a una variable depenent del número de dia emmagatzemat en una variable.


    var dia: Int = 5
    var diaSetmana = when{

        dia == 1 -> "Dilluns"
        dia == 2 -> "Dimarts"
        dia == 3 -> "Dimecres"
        dia == 4 -> "Dijous"
        dia == 5 -> "Divendres"
        dia == 6 -> "Disabte"
        dia == 7 -> "Diumenge"
        else -> "No hi ha dia de la setmana"
    }
    println("El dia $dia és $diaSetmana")
    println()

    // ex13 Crea una classe de dades que contingui la referència d’un producte, el nom, les existències i el preu unitari.

    // ex14 Crea un array de la classe de dades creada en el punt anterior. Afegeix 3 productes. Mostra el segon.

    var dades : ArrayList<Dades> = arrayListOf()
    dades.add(Dades("1A","Dades1",100,10.0))
    dades.add(Dades("2A", "Dades2",50,12.0))
    dades.add(Dades("3A","Dades3", 75,15.0))
    println(dades[1].toString())
    println()
    println("Dades\nreferencia: ${dades[1].ref}\nnom: ${dades[1].nom}\nexistencies: ${dades[1].existencies}\npreu ${dades[1].preu}")
    println()

    // ex18 Crea un array de tipus Peça, afegeix una torre i un alfil i prova els seus mètodes mou.

    var taulell : ArrayList<PesaDEscacs> = arrayListOf()
    taulell.add(Torre(1,1))
    taulell.add(Alfil(1,2))
    println("Torre ${taulell[0].mou(1,4)}")
    println("Torre ${taulell[0].mou(3,6)}")
    println("Torre ${taulell[0].mou(4,4)}")
    if(taulell[0].mou(1,4))        // per fero en dintre de un if perque es boolean
        println("la torre sa mogut a la posicio 1,4")
    else
        println("la torre no sa mogut a la posicio 1,4")
    println()
    println("Alfil ${taulell[1].mou(3,4)}")
    println("Alfil ${taulell[1].mou(2,6)}")
    println("Alfil ${taulell[1].mou(1,1)}")
    println()

    var Torre1 = Torre(1,1)
    var Alfil1 = Alfil(2,2)
    var taulell1 = arrayOf(Torre1, Alfil1)      // en el array no se puede

    println("Torre1 ${Torre1.mou(1,4)}")
    println("Torre1 ${Torre1.mou(2,3)}")
    println("Torre1 ${Torre1.mou(4,4)}")
    println("Torre1Array ${taulell1[0].mou(1,4)}")
    println("Torre1Array ${taulell1[0].mou(3,3)}")
    println("Torre1Array ${taulell1[0].mou(4,6)}")
    println()
    println("Alfil1 ${Alfil1.mou(4,4)}")
    println("Alfil1 ${Alfil1.mou(2,6)}")
    println("Alfil1 ${Alfil1.mou(1,1)}")






}

// ex10
fun preuDescompte(preu:Double, desc:Double?):Double{
    if (desc==null)
        return preu
    else
        return preu-preu*desc
}