package icedo.hector.examen2_icedohector

import java.io.Serializable

data class Contact(var nombre:String, var apellidos:String, var compañia:String, var telefono:String, var email: String, var color: Int):
    Serializable
