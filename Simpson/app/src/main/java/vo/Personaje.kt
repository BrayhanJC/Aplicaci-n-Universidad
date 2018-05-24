package vo


import android.os.Parcel
import android.os.Parcelable
import ds.simpson.R.id.nombre
import java.util.*

/**
 * Created by PC on 4/04/2018.
 */

class Personaje (): Parcelable{


    var id:String? = null
    var nombre:String? = null
    var fecha:Date? = null
    var descripcion:String? = null
    var url:String? = null

    constructor( nombre: String,
                 fecha : Date,  descripcion: String,
                 url: String):this(){

        this.id = id
        this.nombre = nombre
        this.fecha = fecha
        this.descripcion = descripcion
        this.url = url

    }



    constructor( id : String,  nombre: String,
                 fecha : Date,  descripcion: String,
                 url: String):this(){

        this.id = id
        this.nombre = nombre
        this.fecha = fecha
        this.descripcion = descripcion
        this.url = url

    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readSerializable() as Date,
            parcel.readString(),
            parcel.readString()) {
    }






    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeSerializable(fecha)
        parcel.writeString(descripcion)
        parcel.writeString(url)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }


}


