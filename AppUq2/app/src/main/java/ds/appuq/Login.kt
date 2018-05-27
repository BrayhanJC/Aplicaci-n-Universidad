package ds.appuq

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.EditText
import java.security.MessageDigest


class Login : AppCompatActivity() {


    lateinit var editTextName:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun getKeyHash (context: Context) {
        try {
            val info =
                    context.getPackageManager().getPackageInfo(context.getPackageName() ,
                            PackageManager. GET_SIGNATURES )
            for (signature in info. signatures ) {
                val md = MessageDigest.getInstance( "SHA" )
                md.update(signature.toByteArray())
                val sign = Base64.encodeToString(md.digest() , Base64. DEFAULT )
                Log.e( "Mi clave HASH:" , sign)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.d("prueba", "1 KeyHash Error: " + e.message)
        }}
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
            getKeyHash(this)
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
