package ds.appuq

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.google.firebase.database.*
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import ds.appuq.code.*

class RegistroCliente : AppCompatActivity() {

    lateinit var cargando: LoadImage
    var shareDialog: ShareDialog? = null
    var callbackManager: CallbackManager? = null

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null


    lateinit var cedula: EditText
    lateinit var codigo: EditText
    lateinit var nombres: EditText
    lateinit var apellidos: EditText
    lateinit var contrasena: EditText
    lateinit var repetirContrasena: EditText
    lateinit var dependencia: EditText
    lateinit var telefono: EditText


    lateinit var id: String
    lateinit var names: String
    lateinit var lastnames: String
    lateinit var type: String
    lateinit var pass: String
    lateinit var repeatPass: String
    lateinit var dependency: String
    lateinit var phone: String

    lateinit var imageview: ImageView
    lateinit var btn: Button
    lateinit var btnCompartir: ImageButton
    lateinit var btnHacerTuit: ImageButton
    lateinit var btnEliminar: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_cliente)

        imageview = findViewById<View>(R.id.lblImageRegistrarCliente) as ImageView
        btn = findViewById<View>(R.id.btnGuardarRegistroCliente) as Button
        btnCompartir = findViewById<View>(R.id.btnCompartir) as ImageButton
        btnHacerTuit = findViewById<View>(R.id.btnHacerTuit) as ImageButton
        btnEliminar = findViewById<View>(R.id.btnEliminar) as ImageButton
        cargando = LoadImage(imageview, this)
        imageview!!.setOnClickListener { cargando.showPictureDialog() }

        cedula = findViewById<View>(R.id.txtCedulaRegistroCliente) as EditText
        codigo = findViewById<View>(R.id.txtCedulaRegistroCliente) as EditText
        nombres = findViewById<View>(R.id.txtNombresRegistroCliente) as EditText
        apellidos = findViewById<View>(R.id.txtApellidosRegistroCliente) as EditText
        contrasena = findViewById<View>(R.id.txtContrasenaRegistroCliente) as EditText
        repetirContrasena = findViewById<View>(R.id.txtRepetirContrasenaRegistroCliente) as EditText
        dependencia = findViewById<View>(R.id.txtDependenciaRegistroCliente) as EditText
        telefono = findViewById<View>(R.id.txtTelefonoRegistroCliente) as EditText

    inicializarTwitter(this)


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



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cargando.onActivityResult(requestCode, resultCode, data)
    }


    fun eliminarCliente(view: View) {
        val id:String = cedula.text.toString()
        mDatabase!!.child("Cliente" + id).removeValue()
        Toast.makeText(this, "Se ha eliminado \n el cliente", Toast.LENGTH_LONG).show()

    }
    fun compartirContenido(view: View) {
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        compartirContenidoFacebook(shareDialog, "Nuevo Registro de Cliente: " + names + lastnames  , "#Vamosaseguircreciendo")
    }

    fun compartirImageFacebook(view: View) {
        compartirContenido(view)
    }

    fun compartirTweet(view: View) {
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        publicarTweet(view, "Nuevo cliente en la plataforma: "+names+" "+lastnames, this)
    }

    fun guardarCliente(view: View) {


        obtenerDatos()
        // val message = NotificationCompat.MessagingStyle.Message()

    }

    fun obtenerDatos() {
        val id:String = cedula.text.toString()
        val names:String = nombres.text.toString()
        val lastnames:String = apellidos.text.toString()
        val type:String = codigo.text.toString()
        val pass:String = contrasena.text.toString()
        val repeatPass:String = repetirContrasena.text.toString()
        val dependency:String= dependencia.text.toString()
        val phone:String = telefono.text.toString()
         mostrarMensaje(this,id+type+names+ lastnames+ pass+ repeatPass+ dependency+ phone)

        if (!TextUtils.isEmpty(id) &&
                !TextUtils.isEmpty(names) &&
                !TextUtils.isEmpty(lastnames) &&
                !TextUtils.isEmpty(type) &&
                !TextUtils.isEmpty(pass) &&
                !TextUtils.isEmpty(repeatPass) &&
                !TextUtils.isEmpty(dependency) &&
                !TextUtils.isEmpty(phone)) {

            mDatabase!!.child("Cliente" + id).setValue(Cliente(id, type, names, lastnames, pass, repeatPass, dependency, phone))
            Toast.makeText(this, "Se ha guardado \n el cliente", Toast.LENGTH_LONG).show()
            btn.setVisibility(View.INVISIBLE);
            btnCompartir.setVisibility(View.VISIBLE);
            btnHacerTuit.setVisibility(View.VISIBLE);
            btnEliminar.setVisibility(View.VISIBLE);

        } else {
            mostrarMensaje(this, "Debe de llenar todos los campos")
        }
    }




}
