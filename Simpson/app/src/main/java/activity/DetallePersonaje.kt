package activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ds.simpson.R
import fragments.DetalleDePersonajeFragment
import util.Personaje
import vo.Personaje

class DetallePersonaje : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_personaje)
        val personaje: Personaje = intent.getParcelableExtra<Personaje>(Personaje)
        val fragmentoDetalle = supportFragmentManager.findFragmentById(R.id.fragmentoDetallePersonaje) as DetalleDePersonajeFragment
        Log.v("ManagerFire", personaje.nombre)
        fragmentoDetalle.darDetalle(personaje)
    }
}
