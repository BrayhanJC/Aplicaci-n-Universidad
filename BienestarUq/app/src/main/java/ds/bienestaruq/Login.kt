package ds.bienestaruq

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() , View.OnClickListener{


    override fun onClick(v: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    /***
     * Funcion que permite pasar a la actividad principal de la aplicacion
     */
    fun  ventanaPrincipal(view: View){

        val intent = Intent(this, Principal::class.java)
        startActivity(intent)

    }
}
