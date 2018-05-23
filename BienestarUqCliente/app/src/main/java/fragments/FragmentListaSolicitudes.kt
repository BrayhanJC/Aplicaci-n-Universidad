package fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ds.bienestaruqusuario.R
import kotlinx.android.synthetic.main.fragment_fragment_lista_solicitudes.*


import utilidades.AdaptadorSolicitudes
import utilidades.AdaptadorSolicitudes.OnClickAdaptadorDeSolicitudServicio
import vo.SolicitudServicios
import java.util.ArrayList


class FragmentListaSolicitudes : Fragment(), OnClickAdaptadorDeSolicitudServicio{



    lateinit public var solictudServicios : ArrayList<SolicitudServicios>
    lateinit var adaptador : AdaptadorSolicitudes
    private lateinit var listener:OnSolicitudServicioSeleccionadoListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    interface OnSolicitudServicioSeleccionadoListener{
        fun onSolicitudServicioSeleccionado(pos:Int)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_fragment_lista_solicitudes, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //ManagerFireBase.instanciar(this)
        //managerFB = ManagerFireBase.instant
        //managerFB!!.insertarPersonajes(Personaje("Bart", Date(), "prueba", "www.google.com"))



        adaptador = AdaptadorSolicitudes(solictudServicios, this)
        listaSolicitudesServicios.adapter = adaptador
        listaSolicitudesServicios.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

    }

    //se activa cuando hay conexion entre el fragmento y la actividad
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is Activity){
            try {
                listener = context as OnSolicitudServicioSeleccionadoListener
            }catch (e:ClassCastException){
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnPersonajeSeleccionadoListener")
            }
        }
    }


    override fun onClickPosition(pos: Int) {
        listener.onSolicitudServicioSeleccionado(pos)
    }




}// Required empty public constructor
