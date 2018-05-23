package ds.appuq

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


class Login : AppCompatActivity() {


    lateinit var editTextName:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    /***
     * Funcion que permite pasar a la actividad principal de la aplicacion vista administrador o cliente
     */
    fun  ventanaPrincipal(view: View){

        editTextName =findViewById(R.id.txtUsuario) as EditText
        val name=editTextName.text.toString()

        //vista administrador
        if (name.equals("1")){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }


    }


    /***
     * funcion que permite dirigirsea la ventana para recuperar la contraseña
     */
    fun  ventanaRecuperarContrasena(view: View){

        val intent = Intent(this, RecuperarContrasena::class.java)
        startActivity(intent)
    }


    /***
     * funcion que permite dirigirsea la ventana para registrarse como cliente o administrador
     */
    fun  ventanaRegistrarse(view: View){

        val intent = Intent(this, RegistroCliente::class.java)
        startActivity(intent)

    }





}
