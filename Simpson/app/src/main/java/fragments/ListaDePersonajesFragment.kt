package fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast

import ds.simpson.R
import util.*
import vo.Personaje
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListaDePersonajesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class ListaDePersonajesFragment : Fragment(), AdaptadorDePersonaje.OnClickAdaptadorDePersonaje, ManagerFireBase.onActualizarAdaptador {


    lateinit var personajes : ArrayList<Personaje>
    lateinit var adaptador : AdaptadorDePersonaje
    private lateinit var listener:OnPersonajeSeleccionadoListener
    var managerFB : ManagerFireBase? = null

    interface OnPersonajeSeleccionadoListener{
        fun onPersonajeSeleccionado(pos:Int)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_lista_de_personajes, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        personajes = ArrayList<Personaje>()
        adaptador = AdaptadorDePersonaje(personajes, this)
        ManagerFireBase.instanciar(this)
        managerFB = ManagerFireBase.instant
        //managerFB!!.insertarPersonajes(Personaje("Bart", Date(), "prueba", "www.google.com"))

//        listaPersonajes.adapter = adaptador
//        listaPersonajes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        managerFB?.escucharEventoFireBase()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId){
            R.id.menu_agregar -> {
                Toast.makeText(context, "Me oprime agregar", Toast.LENGTH_SHORT).show()
                mostrarAgregarPersona()

            }
            R.id.menu_eliminar -> {
                personajes.removeAt(0 )
                adaptador.notifyItemRemoved(0)
            }


            R.id.menu_modificar -> {
                Toast.makeText(context, "Me oprime Modificar", Toast.LENGTH_SHORT).show()

            }


            R.id.menu_cambiarIdioma -> {
                selecionarIdioma(context)
                val intent = activity.intent
                intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.finish()
                activity.startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //se activa cuando hay conexion entre el fragmento y la actividad
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if( context is Activity){
            try {
                listener = context as OnPersonajeSeleccionadoListener
            }catch (e:ClassCastException){
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnPersonajeSeleccionadoListener")
            }
        }
    }

    override fun onClickPosition(pos: Int) {
        listener.onPersonajeSeleccionado(pos)
    }


    fun agregarPersonaje(personaje: Personaje){
        managerFB!!.insertarPersonajes(personaje)
    }


    fun mostrarAgregarPersona(){
        val agregarPersonaje = AgregarPersona()
        agregarPersonaje.setStyle(DialogFragment.STYLE_NORMAL,
                R.style.DialogoTitulo)
        agregarPersonaje.show(fragmentManager,"AgregarPersonaje")
    }

    override fun actualizarAdaptador(personaje: Personaje) {
        personajes.add(personaje)
        adaptador.notifyItemInserted(personajes.size-1)
    }



}// Required empty public constructor
