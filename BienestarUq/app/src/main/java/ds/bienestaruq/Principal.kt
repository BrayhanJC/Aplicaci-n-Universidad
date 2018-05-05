package ds.bienestaruq

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.app_bar_principal.*
import recursos.AdaptadorServiciosBienestar
import recursos.ServiciosBienestar
import java.util.ArrayList

class Principal : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        setSupportActionBar(toolbar)

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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
                val recyclerView:RecyclerView=findViewById(R.id.recycler)
                recyclerView.layoutManager=LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                val x=ArrayList<ServiciosBienestar>()
                x.add(ServiciosBienestar(R.drawable.user_logo, "Tenis de mesa", "lugar donde se entrena", "cancha"))
                x.add(ServiciosBienestar(R.drawable.user_logo, "Ajedrez", "lugar donde se entrena", "cancha"))
                x.add(ServiciosBienestar(R.drawable.user_logo, "Natacion", "lugar donde se entrena", "cancha"))
                val adapter=AdaptadorServiciosBienestar(x)
                recyclerView.adapter=adapter
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {
                val transaction = supportFragmentManager.beginTransaction()
                val fragment = FragmentRegistrarEncargado()
                transaction.replace(R.id.content_principal, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.nav_share -> {

                val transaction = supportFragmentManager.beginTransaction()
                val fragment = FragmentRegistroServicios()
                transaction.replace(R.id.content_principal, fragment)
                transaction.addToBackStack(null)
                transaction.commit()


            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
