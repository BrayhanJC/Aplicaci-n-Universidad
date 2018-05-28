package ds.appuq.code

class Servicio() {


    var codigo:String? = null
    var nombre:String? = null
    var descripcion:String? = null
    var ubicacion:String? = null
    var recursos:String? = null
    var fecha_incio:String? = null
    var fecha_fin:String? = null
    var telefono:String? = null

    constructor( codigo:String,
                 nombre:String,
                 descripcion:String,
                 ubicacion:String,
                 recursos:String,
                 fecha_incio:String,
                 fecha_fin:String,
                 telefono:String):this(){

        this.codigo = codigo
        this.nombre = nombre
        this.descripcion = descripcion
        this.ubicacion = ubicacion
        this.recursos = recursos
        this.fecha_incio = fecha_incio
        this.fecha_fin = fecha_fin
        this.telefono = telefono


    }


}