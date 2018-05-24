package util

import android.support.v4.app.Fragment
import android.util.Log
import com.google.firebase.database.*
import vo.Personaje

/**
 * Created by PC on 16/05/2018.
 */
object ManagerFireBase {

    //con esto nos podemos conectar a la base de datos
    var database: FirebaseDatabase? = null
    var dataRef: DatabaseReference? = null
    var instant: ManagerFireBase? = null
    //fragmento que se conecta con manager fire base
    var fragment: Fragment? = null
    //escuchador que debe de tener el fragmento
    var listener: onActualizarAdaptador?= null


    private fun inicializar(fragment: Fragment):ManagerFireBase{
        val instant = ManagerFireBase
        instant!!.database= FirebaseDatabase.getInstance()
        instant!!.dataRef = database!!.reference
        instant!!.fragment = fragment
        listener= fragment as onActualizarAdaptador
        return instant!!
    }
    fun instanciar(fragment: Fragment){
        if( instant == null ){
            instant = inicializar(fragment)
        }
    }

    fun insertarPersonajes(personaje:Personaje){
        dataRef!!.push().setValue(personaje)
    }


    fun escucharEventoFireBase(){
        dataRef!!.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.v("ManagerFire", "onCancelled")
            }
            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildMoved")
            }
            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildChanged")
            }
            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildAdded")
                val personaje:Personaje = p0!!.getValue(Personaje::class.java)!!
                personaje.id = p0!!.key
                listener!!.actualizarAdaptador(personaje)
            }
            override fun onChildRemoved(p0: DataSnapshot?) {
                Log.v("ManagerFire", "onChildRemoved")
            }
        })
    }

    interface onActualizarAdaptador{
        fun actualizarAdaptador(personaje: Personaje)
    }


}