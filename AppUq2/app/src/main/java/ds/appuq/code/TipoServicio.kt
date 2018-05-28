package ds.appuq.code

class TipoServicio() {

    var codigo: String? = null
    var nombre: String? = null

    constructor(codigo: String,
                nombre: String) : this() {
        this.codigo = codigo
        this.nombre = nombre
    }

}