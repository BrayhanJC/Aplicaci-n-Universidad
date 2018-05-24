package fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast

import ds.simpson.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_personajes.*
import kotlinx.android.synthetic.main.fragment_agregar_persona.*
import kotlinx.android.synthetic.main.fragment_lista_de_personajes.view.*
import util.AdaptadorDePersonaje
import util.ManagerFireBase
import vo.Personaje
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AgregarPersona.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class AgregarPersona : DialogFragment() {



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dialog.setTitle("Agregar Personaje")
        ManagerFireBase.instanciar(this)
        var managerFB : ManagerFireBase = ManagerFireBase.instant!!

        val view:View = inflater!!.inflate(R.layout.fragment_agregar_persona, container, false)

        val btn_click_me = view.findViewById(R.id.btnAgregarPersonaje) as Button
        val simpleDatePicker =  view.findViewById(R.id.sDPFechaNacimiento) as DatePicker
        val fecha_nacimiento : String = (simpleDatePicker.year.toString()+"/"+(simpleDatePicker.month+1).toString()+"/"+simpleDatePicker.dayOfMonth.toString())

        // set on-click listener
        btn_click_me.setOnClickListener {
           // val personaje:Personaje = Personaje("1", txtNombrePersonaje.text.toString(), fecha_nacimiento as Date, txtHistoria.text.toString(), txtUrl.text.toString())
            //se agrega el personaje

            val personaje:Personaje = Personaje(txtNombrePersonaje.text.toString(), Date(), txtHistoria.text.toString(), txtUrl.text.toString() )
            managerFB.insertarPersonajes(personaje)

        }
        return view
    }




}// Required empty public constructor
