package ds.appuq

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.widget.ShareDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ds.appuq.code.*

class CrearEncargado : AppCompatActivity() {
    var shareDialog: ShareDialog? = null
    var callbackManager: CallbackManager? = null

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null


    lateinit var identificacion: EditText
    lateinit var nombres: EditText
    lateinit var apellidos: EditText
    lateinit var telefono: EditText

    lateinit var btnCompartir: ImageButton
    lateinit var btnGuardar: Button
    lateinit var btnHacerTuit: ImageButton
    lateinit var btnEliminar: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_encargado)
inicializarTwitter(this)

        btnCompartir = findViewById<View>(R.id.btnCompartir) as ImageButton
        btnGuardar = findViewById(R.id.btnGuardarEncargado) as Button
        btnHacerTuit = findViewById(R.id.btnHacerTuit) as ImageButton
        btnEliminar = findViewById<View>(R.id.btnEliminar) as ImageButton

        identificacion=  findViewById<View>(R.id.txtCedulaRegistrarEncargado) as EditText
        nombres=  findViewById<View>(R.id.txtNombresRegistrarEncargado) as EditText
        apellidos=  findViewById<View>(R.id.txtApellidoRegistrarEncargado) as EditText
        telefono=  findViewById<View>(R.id.txtTelefonoRegistrarEncargado) as EditText

        mDatabase = FirebaseDatabase.getInstance().reference
        mMessageReference = FirebaseDatabase.getInstance().getReference("Cliente")

        shareDialog = ShareDialog(this)
        callbackManager = CallbackManager.Factory.create()
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

    fun eliminarEncargado(view: View) {
        val code:String = identificacion.text.toString()
        mDatabase!!.child("Encargado" + code).removeValue()
        Toast.makeText(this, "Se ha eliminado \n el Encargado", Toast.LENGTH_LONG).show()

    }


    fun compartirTweet(view: View) {
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        publicarTweet(view, "Nuevo encargado en la plataforma: "+names+" "+lastnames, this)
    }
    fun compartirContenido(view: View) {
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        compartirContenidoFacebook(shareDialog, "Nuevo Registro de Encargado: "+names+ " " +lastnames, "#Vamosaseguircreciendo")
    }

    fun compartirImageFacebook(view: View) {
        compartirContenido(view)
    }

    fun guardarEncargado(view: View) {
        obtenerDatos()
    }
    fun obtenerDatos() {


        val code:String = identificacion.text.toString()
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        val phone:String= telefono.text.toString()


        if (!TextUtils.isEmpty(code) &&
                !TextUtils.isEmpty(code) &&
                !TextUtils.isEmpty(names) &&
                !TextUtils.isEmpty(lastnames) &&
                !TextUtils.isEmpty(phone)) {

            mDatabase!!.child("Encargado" + code).setValue(Encargado(code, names, lastnames, phone))
            Toast.makeText(this, "Se ha guardado \n el encargado", Toast.LENGTH_LONG).show()
            btnGuardar.setVisibility(View.INVISIBLE);
            btnCompartir.setVisibility(View.VISIBLE);
            btnHacerTuit.setVisibility(View.VISIBLE);
            btnEliminar.setVisibility(View.VISIBLE);

        } else {
            mostrarMensaje(this, "Debe de llenar todos los campos")
        }
    }
}
