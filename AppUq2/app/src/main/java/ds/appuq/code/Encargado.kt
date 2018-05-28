package ds.appuq.code

class Encargado() {

    var identificacion:String? = null
    var nombres:String? = null
    var apellidos:String? = null
    var telefono:String? = null


    constructor( identificacion:String,
                 nombres:String,
                 apellidos:String,
                 telefono:String):this(){

        this.identificacion = identificacion
        this.nombres = nombres
        this.apellidos = apellidos
        this.telefono = telefono
    }
}