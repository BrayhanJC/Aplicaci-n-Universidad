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
import ds.appuq.code.*
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



    lateinit var codigo: EditText
    lateinit var nombre: EditText
    lateinit var descripcion: EditText
    lateinit var ubicacion: EditText
    lateinit var recursos: EditText
    lateinit var fecha_incio: EditText
    lateinit var fecha_fin: EditText
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


        codigo=  findViewById<View>(R.id.txtCodigoRegistrarServicio) as EditText
        nombre=  findViewById<View>(R.id.txtNombreRegistrarServicio) as EditText
        descripcion=  findViewById<View>(R.id.txtDescripcionRegistrarServicio) as EditText
        ubicacion=  findViewById<View>(R.id.txtUbicacionRegistrarServicio) as EditText
        recursos=  findViewById<View>(R.id.txtRecursosDisponiblesRegistrarServicio) as EditText
        fecha_incio=  findViewById<View>(R.id.txtFechaInicialRegistrarServicio) as EditText
        fecha_fin=  findViewById<View>(R.id.txtFechaFinalRegistrarServicio) as EditText
        telefono=  findViewById<View>(R.id.txtTelefonoRegistrarServicio) as EditText

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


        val code:String = codigo.text.toString()
        val name:String = nombre.text.toString()
        val description:String = descripcion.text.toString()
        val ubication:String = ubicacion.text.toString()
        val resource:String = recursos.text.toString()
        val date_begin:String = fecha_incio.text.toString()
        val date_end:String = fecha_fin.text.toString()
        val phone:String= telefono.text.toString()


        if (!TextUtils.isEmpty(code) &&
                !TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(description) &&
                !TextUtils.isEmpty(resource) &&
                !TextUtils.isEmpty(date_begin) &&
                !TextUtils.isEmpty(date_end) &&
                !TextUtils.isEmpty(phone)&&
                !TextUtils.isEmpty(ubication)) {

            mDatabase!!.child("Servicio" + code).setValue(Servicio(code, name, description, ubication, resource, date_begin, date_end, phone))
            Toast.makeText(this, "Se ha guardado \n el cliente", Toast.LENGTH_LONG).show()
            btn.setVisibility(View.INVISIBLE);
            btnCompartir.setVisibility(View.VISIBLE);

        } else {
            mostrarMensaje(this, "Debe de llenar todos los campos")
        }
    }


}
