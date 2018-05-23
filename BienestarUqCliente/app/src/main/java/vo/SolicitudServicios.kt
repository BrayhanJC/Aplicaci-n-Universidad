package vo

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by PC on 20/05/2018.
 */
class SolicitudServicios : Parcelable {


    var codigo:String? = null
    var nombre:String? = null
    var descripcion:String? = null
    var recursosDisponibles:String? = null
    var ubicacion:String? = null
    var tipoServicio:String? = null
    var fecha: Date? = null


    constructor(codigo: String,
                nombre: String,
                descripcion: String,
                recursosDisponibles: String,
                ubicacion: String,
                tipoServicio: String,
                fecha : Date){

        this.codigo = codigo
        this.nombre = nombre
        this.descripcion = descripcion
        this.recursosDisponibles= recursosDisponibles
        this.ubicacion = ubicacion
        this.tipoServicio = tipoServicio
        this.fecha = fecha

    }


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as Date) {
    }






    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codigo)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeString(recursosDisponibles)
        parcel.writeString(ubicacion)
        parcel.writeString(tipoServicio)
        parcel.writeSerializable(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SolicitudServicios> {
        override fun createFromParcel(parcel: Parcel): SolicitudServicios {
            return SolicitudServicios(parcel)
        }

        override fun newArray(size: Int): Array<SolicitudServicios?> {
            return arrayOfNulls(size)
        }
    }

}