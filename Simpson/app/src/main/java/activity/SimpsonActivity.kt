package activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import ds.simpson.R
import fragments.DetalleDePersonajeFragment
import fragments.ListaDePersonajesFragment
import fragments.ListaDePersonajesFragment.OnPersonajeSeleccionadoListener
import kotlinx.android.synthetic.main.fragment_agregar_persona.*
import util.Personaje
import util.cambiarIdioma
import util.getKeyHash
import vo.Personaje
import java.util.*


class SimpsonActivity : AppCompatActivity(), OnPersonajeSeleccionadoListener {


    lateinit var listaPersonajes : ArrayList<Personaje>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cambiarIdioma(this);
        setContentView(R.layout.activity_simpson)
        //listaPersonajes = ArrayList()
        //listaPersonajes.add(Personaje("1", "Homero", Date(), "", ""))
        //listaPersonajes.add(Personaje("2", "Bart", Date(), "", ""))
        //listaPersonajes.add(Personaje("3", "Lisa", Date(), "", ""))
        //listaPersonajes.add(Personaje("4", "Marge", Date(), "", ""))
        //listaPersonajes.add(Personaje("5", "Milhouse", Date(), "", ""))
        val fragmentLista = supportFragmentManager.findFragmentById(R.id.fragmentoListaPersonajes) as ListaDePersonajesFragment
        //fragmentLista.personajes = listaPersonajes

        val callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
// App code
                    }
                    override fun onCancel() {
// App code
                    }
                    override fun onError(exception: FacebookException) {
// App code
                    }

                })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onPersonajeSeleccionado(pos: Int) {
        val intent:Intent = Intent(this,DetallePersonaje::class.java)
        intent.putExtra("per", listaPersonajes[pos])
        startActivity(intent)
    }



}
