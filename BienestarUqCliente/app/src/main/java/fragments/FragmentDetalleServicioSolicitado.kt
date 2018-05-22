package fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup

import ds.bienestaruqusuario.R
import kotlinx.android.synthetic.main.fragment_fragment_detalle_servicio_solicitado.*
import vo.SolicitudServicios

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentDetalleServicioSolicitado.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class FragmentDetalleServicioSolicitado : Fragment(), OnClickListener {


    lateinit var solicitudServicios : SolicitudServicios

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnCancelar.setOnClickListener(this)
    }

    fun darDetalle(solicitudServicios: SolicitudServicios) {
        this.solicitudServicios = solicitudServicios
        txtCodigo.setText(solicitudServicios.codigo)
        txtNombreServicio.setText(solicitudServicios.nombre)
        txtDescripcionServicio.setText(solicitudServicios.descripcion)
        txtRecursosDisponibles.setText(solicitudServicios.recursosDisponibles)
        txtUbicacion.setText(solicitudServicios.ubicacion)
        txtTipoServicio.setText(solicitudServicios.tipoServicio)
        txtFechaSolicitudDetalle.setText(solicitudServicios.fecha.toString())
    }


    override fun onClick(v: View?) {
        var intent: Intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=hP3fmnMuZZU"))
        startActivity(intent)
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_fragment_detalle_servicio_solicitado, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetalleDesolicitudServiciosFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FragmentDetalleServicioSolicitado {
            val fragment = FragmentDetalleServicioSolicitado()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
