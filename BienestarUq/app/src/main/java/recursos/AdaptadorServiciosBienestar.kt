package recursos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import ds.bienestaruq.R
import kotlinx.android.synthetic.main.contenedor_servicios.view.*

class AdaptadorServiciosBienestar(var listaServicios: ArrayList<ServiciosBienestar>) : RecyclerView.Adapter<AdaptadorServiciosBienestar.viewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.contenedor_servicios, parent,false)
        return viewHolder(v)
    }

    override fun getItemCount(): Int {
        return listaServicios.size
    }

    override fun onBindViewHolder(holder: AdaptadorServiciosBienestar.viewHolder, position: Int) {
        holder.cargarItems(listaServicios[position])
    }


    //clase anidada donde se maneja la vista y se extiende de reclyclerview
    class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun cargarItems(data: ServiciosBienestar) {
            val imagen: ImageView = itemView.findViewById(R.id.lblImagenServicio)
            val nombreServicio: TextView = itemView.findViewById(R.id.txtNombreServicio)
            val descripcion: TextView = itemView.findViewById(R.id.txtDescripcionServicio)
            val ubicacion: TextView = itemView.findViewById(R.id.txtUbicacion)

            Glide.with(itemView.context).load(data.imagenServicio).into(imagen)
            nombreServicio.text = data.nombreServicio
            descripcion.text = data.descripcionServicio
            ubicacion.text = data.ubicacionServicio

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Nombre servicio: ${data.nombreServicio}", Toast.LENGTH_LONG ).show()
            }

        }
    }

}