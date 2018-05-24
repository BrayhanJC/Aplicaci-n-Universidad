package util



import activity.DetallePersonaje
import activity.SimpsonActivity
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import ds.simpson.R
import fragments.DetalleDePersonajeFragment
import kotlinx.android.synthetic.main.resumen_personaje.view.*
import vo.Personaje


/**
 * Created by PC on 7/04/2018.
 */

class AdaptadorDePersonaje(var personajes : ArrayList<Personaje>, var fragmento : Fragment ) : RecyclerView.Adapter<AdaptadorDePersonaje.PersonajeViewHolder>() {


    private lateinit var listener:OnClickAdaptadorDePersonaje

    init {
        listener = fragmento as OnClickAdaptadorDePersonaje
    }

    interface OnClickAdaptadorDePersonaje {
        fun onClickPosition(pos: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PersonajeViewHolder {
        val v = LayoutInflater.from( parent!!.context )
                .inflate(R.layout.resumen_personaje, parent, false)
        return PersonajeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return personajes.size
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder?, position: Int) {
        holder!!.bindPersonaje(personajes.get(position))
    }



    inner class PersonajeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            //val intent = Intent(context, DetallePersonaje::class.java)
            //intent.putExtra(Personaje,personajes)
            //startActivity(intent)
            listener.onClickPosition(adapterPosition)
        }

        val nombre : TextView = itemView.nombre
        val fechaNacimiento : TextView = itemView.fecha_nacimiento

        fun bindPersonaje(personaje: Personaje){
            nombre.text = personaje.nombre
            fechaNacimiento.text = personaje.fecha.toString()
        }

    }


}