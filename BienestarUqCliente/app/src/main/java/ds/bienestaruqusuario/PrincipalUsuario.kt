package ds.bienestaruqusuario

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import fragments.FragmentListaSolicitudes
import fragments.FragmentPerfil
import fragments.FragmentSolicitudServicios
import kotlinx.android.synthetic.main.app_bar_principal_usuario.*
import utilidades.SolicitudServicios
import vo.SolicitudServicios
import java.util.*

class PrincipalUsuario : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FragmentListaSolicitudes.OnSolicitudServicioSeleccionadoListener {


    lateinit var listaServiciosSolicitados : ArrayList<SolicitudServicios>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_usuario)

        listaServiciosSolicitados = ArrayList()
        listaServiciosSolicitados.add(SolicitudServicios("1", "Masaje","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.", "Bienestar", "Salud", Date()))
        listaServiciosSolicitados.add(SolicitudServicios("2", "Piscina","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.","Bienestar","Deporte",Date()))
        listaServiciosSolicitados.add(SolicitudServicios("3", "Futbol","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.","Cancha","Deporte",Date()))
        listaServiciosSolicitados.add(SolicitudServicios("4", "Tenis de Mesa","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.","Coliseo","Deporte",Date()))
        listaServiciosSolicitados.add(SolicitudServicios("5", "Futbol Sala","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris luctus erat massa, non convallis turpis porttitor sagittis. Maecenas dapibus sapien in cursus ornare.","Coliseo","Deporte", Date()))
        val fragmentLista = supportFragmentManager.findFragmentById(R.id.fragmentoSolicitudServicios) as FragmentListaSolicitudes
        fragmentLista.solictudServicios = listaServiciosSolicitados

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)



        fab_crear_solicitud.setOnClickListener { view ->
            val intent = Intent(this, FragmentSolicitudServicios::class.java)
            startActivity(intent)
        }


        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal_usuario, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        val fragmentManager: FragmentManager = supportFragmentManager


        if (id == R.id.miPerfil) {
            fragmentManager.beginTransaction().replace(R.id.Principal, FragmentPerfil()).commit()
        }else if (id == R.id.historialServicioSolicitado){
            fragmentManager.beginTransaction().replace(R.id.Principal, FragmentSolicitudServicios()).commit()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onSolicitudServicioSeleccionado(pos: Int) {
        val intent: Intent = Intent(this,DetalleSolicitudServicio::class.java)
        intent.putExtra(SolicitudServicios, listaServiciosSolicitados[pos])
        startActivity(intent)
    }
}
