package com.example.myapplication

import android.database.CursorIndexOutOfBoundsException
import java.time.LocalDate

fun main(){
    println("Hola mon")
    var num = 5
    println("El numero val " + num)
    num++
    println("El numero val " + num)

    var num2 : Int
    num2 = num + 1
    println("El numero2 val " + num2)

    var num3 : Byte = 15

    num = 500_000_000

    var lletra : Char = 'A'
    var texte = "Hola mundo " + '\u2140'
    println(texte)

    var notes = arrayOf(4.3,9.8,7.68,9.5,8.9,9.45,8.6)
    for (nota in notes)
        println("La nota és " + nota)
    println("La segona nota és " + notes[1])
    println("La tercera nota és " + notes.get(2))

    for (i in 0..notes.size - 1){           // en el for se puede utilizar step (ir de 2 en 2 o de 3 en 3) o down to(para ir bajando de 5 a 1)
        println("La nota $i és " + notes[i])
    }
    println()
    for (i in 3..5){
        println("La nota $i és " + notes[i])    // va del element 3 al 5
    }
    println()
    notes.forEach { nota -> println("La nota és " + nota) }     // un altre tipus de for per escriure-ho tot en una linea
    println()
    var notes2 : ArrayList<Double> = arrayListOf()
    notes2.add(9.8)
    notes2.add(8.5)
    notes2.add(6.5)

    for (nota in notes2)
        println("La nota és "+ nota)

    println("L'IVA d'un producte que val 100euros és " + calculaIVA( 100.0)+"euro")
    mostraIVA(50.0)
    mostraIVA2(60.0)

    println()

    var iva2 = calculaIVA2(300.0)
    if(iva2 < 50.0){
        println("El iva es mes gran que 50")
    }else if(iva2 == 50.0){
        println("Es igual a 50")
    }else{
        println("Es menor a 50")
    }
    println()

    var notaText = if(notes[1]<5)               // tipus de if
                    "Insuficient"
                    else if (notes[1]<6)
                     "Suficient"
                    else if (notes[1]<7)
                     "Be"
                    else if (notes[1]<9)
                     "Notable"
                    else
                     "Exelent"

    println("La nota es " + notes[1] +": " + notaText)
    println()

    when{                                   // el switch en kotlin
        notes[0]>=0 && notes[0]<5 -> notaText = "Insuficient"
        notes[0]>=5 && notes[0]<6 -> notaText = "Suficient"
        notes[0]>=6 && notes[0]<7 -> notaText = "Be"
        notes[0]>=7 && notes[0]<9 -> notaText = "Notable"
        else -> notaText = "Excelent"
    }
    println("La nota es " + notes[0] +": " + notaText)
    println()

    notaText = when{                                   // el switch en kotlin de una altre manera
        notes[0]>=0 && notes[0]<5 -> "Insuficient"
        notes[0]>=5 && notes[0]<6 -> "Suficient"
        notes[0]>=6 && notes[0]<7 -> "Be"
        notes[0]>=7 && notes[0]<9 -> "Notable"
        else -> "Excelent"
    }
    println("La nota es " + notes[0] +": " + notaText)
    println()

    println("La mitjana de notes és " + calculaMitjanaNotes(notes2))
    println()

    var variablenula:String?   // ? aquesta variable admet null
    variablenula = null

    if (variablenula == null)
        println("La variable es null")
    else
        println("la variable val $variablenula")

   // var varialbe2: String = variablenula!!      // Per que es puguin igualar ,  que si peta dona igual
        println()

    // Creació d'un objecta DataClas

    var empleat = Empleat(1, "Jordi Soto", 5000.0, LocalDate.now())   //o empleat: Empleat
    println(empleat.toString())
    println()
    println("L'empleat ${empleat.nomcognoms} cobra ${empleat.sou}")
    println()
    var empleats : ArrayList<Empleat> = arrayListOf()
    empleats.add(Empleat(2, "Pere", 6000.0, LocalDate.of(2020, 9, 1)))
    println("L'empleat ${empleats[0]} cobra ${empleats[0].sou}")
    println()
    println(empleats[0].toString())
    println()

    // creacio d'un objecte de la class Feina

    var feina = Feina(Empleat(3,"Adria", 7000.0, LocalDate.now()),"Informatic")
    println(feina.empleat.toString())
    println()
    println("L'empleat ${feina.empleat.nomcognoms} cobra ${feina.sou}")
    println()

    var feina2 = Feina(Empleat(4,"Albert", 1200.0, LocalDate.now()), "Administratiu")
    println("L'empleat ${feina2.empleat.nomcognoms} cobra ${feina2.sou}")
    println()
    println(feina2.empleat.toString())
    println()
    println(feina2.mostraFeina())
    println()

    var tipusFeina = TipusFeina(Empleat(5,"Pere", 2000.0, LocalDate.now()),"Informatic","Programador")
    println(tipusFeina.mostraFeina())
    println()


}

fun calculaIVA(preu: Double): Double{
    return preu*0.21
}

fun mostraIVA(preu: Double){
    println("L'IVA de " + preu + " es " + preu*0.21)
}

fun calculaIVA2(preu: Double) = preu * 0.21         // si solo tiene una linea lo puedes poner asi

fun mostraIVA2(preu: Double){
    println("L'IVA de " + preu + " es " + calculaIVA(preu))
}

fun mostraIVA3(preu: Double){
    println("L'IVA de $preu  és ${calculaIVA(preu)}")    // poner las variables dentro del string
}

//while  mira condicio de "permanencia"!!

fun calculaMitjanaNotes(notes: ArrayList<Double>):Double{
    var suma = 0.0
   var i= 0
    while(i<notes.size-1){
        suma+=notes[i]
        i++
    }
    return arrodoneix2Decimals(suma/ notes.size)
}

fun arrodoneix2Decimals(numero:Double) = (Math.round(numero*100)/100.0)






