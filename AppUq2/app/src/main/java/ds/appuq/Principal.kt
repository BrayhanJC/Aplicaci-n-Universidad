package ds.appuq

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import ds.appuq.R.id.action_settings
import ds.appuq.code.selecionarIdioma
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var callbackManager: CallbackManager?=null
    var shareDialog : ShareDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbar)
        shareDialog = ShareDialog( this )
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback( callbackManager ,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess (loginResult: LoginResult) {
// App code
                    }
                    override fun onCancel () {
// App code
                    }
                    override fun onError (exception: FacebookException) {
// App code
                    }
                })



        fab_crear_encargado.setOnClickListener { view ->
            val intent = Intent(this, CrearEncargado::class.java)
            startActivity(intent)
        }

        fab_crear_servicio.setOnClickListener { view ->
            val intent = Intent(this, CrearServicio::class.java)
            startActivity(intent)
        }


        fab_crear_tipo_servicio.setOnClickListener { view ->
            val intent = Intent(this, CrearTipoServicio::class.java)
            startActivity(intent)
        }



/*        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->{                selecionarIdioma(this)
                val intent = this.intent
                intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                this.finish()
                this.startActivity(intent)
                return true}

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.nav_solicitud_servicios -> {
                selecionarIdioma(this)
                val intent = this.intent
                intent.flags = (Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                this.finish()
                this.startActivity(intent)


            }
            R.id.nav_lista_servicios -> {

               // remplazarFragmento( FragmentRegistroServicios())
            }

            R.id.nav_lista_encargados -> {

            }
            R.id.nav_tipo_servicio -> {

               // remplazarFragmento(FragmentTipoServicio())

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /***
     * Funcion que reemplaza un fragmento
     */
    fun remplazarFragmento(fragmento: Fragment)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_principal, fragmento)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun compartirContenido(view: View){
        if (ShareDialog.canShow(ShareLinkContent:: class . java )) {
            val content = ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse( "https://www.youtube.com/watch?v=Zq8_XppDXdk" ))
                    .setQuote( "Agregnado registro" )
                    .setShareHashtag(ShareHashtag.Builder()
                            .setHashtag( "#programando ando" )
                            .build()).build()

            shareDialog?.show(content)
        }
    }
}
