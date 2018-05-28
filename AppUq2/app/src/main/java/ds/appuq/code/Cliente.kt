package ds.appuq.code

class Cliente() {


    var id:String? = null
    var cedula:String? = null
    var tipo:String? = null
    var nombres:String? = null
    var apellidos:String? = null
    var contrasena:String? = null
    var repetirContransena:String? = null
    var dependencia:String? = null
    var telefono:String? = null

    constructor( cedula:String,
                 tipo:String,
                 nombres:String,
                 apellidos:String,
                 contrasena:String,
                 repetirContransena:String,
                 dependencia:String,
                 telefono:String):this(){

        this.cedula = cedula
        this.tipo = tipo
        this.nombres = nombres
        this.apellidos = apellidos
        this.contrasena = contrasena
        this.repetirContransena = repetirContransena
        this.dependencia = dependencia
        this.telefono = telefono


    }

}