package utilidades

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ds.bienestaruqusuario.R
import kotlinx.android.synthetic.main.resumen_solicitud_servicio.view.*
import org.w3c.dom.Text
import vo.SolicitudServicios
/**
 * Created by PC on 20/05/2018.
 */
class AdaptadorSolicitudes(var solicitudesServicios : ArrayList<SolicitudServicios>, var fragmento : Fragment) : RecyclerView.Adapter<AdaptadorSolicitudes.SolicitudServicioViewHolder>(){


    private lateinit var listener:OnClickAdaptadorDeSolicitudServicio

    init {
        listener = fragmento as OnClickAdaptadorDeSolicitudServicio
    }

    interface OnClickAdaptadorDeSolicitudServicio {
        fun onClickPosition(pos: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SolicitudServicioViewHolder {
        val v = LayoutInflater.from( parent!!.context )
                .inflate(R.layout.resumen_solicitud_servicio, parent, false)
        return SolicitudServicioViewHolder(v)
    }

    override fun getItemCount(): Int {
        return solicitudesServicios.size
    }

    override fun onBindViewHolder(holder: SolicitudServicioViewHolder?, position: Int) {
        holder!!.bindSolicitudServicios(solicitudesServicios.get(position))
    }



    inner class SolicitudServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            //val intent = Intent(context, DetalleSolicitudServicios::class.java)
            //intent.putExtra(SolicitudServicios,SolicitudServicioss)
            //startActivity(intent)
            listener.onClickPosition(adapterPosition)
        }

        val nombre : TextView = itemView.txtNombreServicioResumen
        val descripcion : TextView = itemView.txtDescripcionServicioResumen
        val ubicacion : TextView = itemView.txtUbicacionResumen
        val tipoServicio: TextView = itemView.txtTipoServicioResumen
        val fechaSolicitud : TextView = itemView.txtFechaSolicitudResumen

        fun bindSolicitudServicios(SolicitudServicios: SolicitudServicios){
            nombre.text = SolicitudServicios.nombre
            descripcion.text = SolicitudServicios.descripcion
            ubicacion.text = SolicitudServicios.ubicacion
            tipoServicio.text = SolicitudServicios.tipoServicio
            fechaSolicitud.text = SolicitudServicios.fecha.toString()
        }

    }







}



