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
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import ds.appuq.code.LoadImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class CrearServicio : AppCompatActivity() {

   // private var btn: Button? = null

    lateinit var cargando: LoadImage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_servicio)

        //btn = findViewById<View>(R.id.btnGuardarRegistrarServicio) as Button


        val imageview = findViewById<View>(R.id.imagenCrearServicio) as ImageView
        cargando=LoadImage(imageview, this)
        imageview!!.setOnClickListener{cargando.showPictureDialog()}
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cargando.onActivityResult(requestCode, resultCode, data)
    }

}
