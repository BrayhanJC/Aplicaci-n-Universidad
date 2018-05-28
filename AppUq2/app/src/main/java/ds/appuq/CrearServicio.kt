package ds.appuq

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.widget.ShareDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ds.appuq.code.Cliente
import ds.appuq.code.LoadImage
import ds.appuq.code.compartirContenidoFacebook
import ds.appuq.code.mostrarMensaje
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class CrearServicio : AppCompatActivity() {



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

    lateinit var btn: Button
    lateinit var btnCompartir: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_servicio)

        //btn = findViewById<View>(R.id.btnGuardarRegistrarServicio) as Button


        //val imageview = findViewById<View>(R.id.imagenCrearServicio) as ImageView
        //cargando=LoadImage(imageview, this)
        //imageview!!.setOnClickListener{cargando.showPictureDialog()}


        val imageview = findViewById<View>(R.id.imagenCrearServicio) as ImageView
        btn = findViewById<View>(R.id.btnGuardarRegistrarServicio) as Button
        btnCompartir = findViewById<View>(R.id.btnCompartir) as ImageButton
        cargando = LoadImage(imageview, this)
        imageview!!.setOnClickListener { cargando.showPictureDialog() }

        cedula=  findViewById<View>(R.id.txtCedulaRegistroCliente) as EditText
        codigo=  findViewById<View>(R.id.txtCedulaRegistroCliente) as EditText
        nombres=  findViewById<View>(R.id.txtNombresRegistroCliente) as EditText
        apellidos=  findViewById<View>(R.id.txtApellidosRegistroCliente) as EditText
        contrasena=  findViewById<View>(R.id.txtContrasenaRegistroCliente) as EditText
        repetirContrasena=  findViewById<View>(R.id.txtRepetirContrasenaRegistroCliente) as EditText
        dependencia=  findViewById<View>(R.id.txtDependenciaRegistroCliente) as EditText
        telefono=  findViewById<View>(R.id.txtTelefonoRegistroCliente) as EditText

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

    fun compartirContenido(view: View) {
        compartirContenidoFacebook(shareDialog, "Registro de Cliente", "#Vamos a seguir creciendo")
    }

    fun compartirImageFacebook(view: View) {
        compartirContenido(view)
    }

    fun guardarServicio(view: View) {
        obtenerDatos()
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

        } else {
            mostrarMensaje(this, "Debe de llenar todos los campos")
        }
    }


}
