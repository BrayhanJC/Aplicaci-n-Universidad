package ds.bienestaruq

import android.app.Activity
import android.content.Intent
import android.net.Uri

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View

import android.widget.ImageView

class CrearServicio : AppCompatActivity() {


    lateinit var imagenServicio: ImageView
    var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_servicio)
    }

    fun  elegirImagen(view: View){
        imagenServicio =findViewById(R.id.lblImagenServicio) as ImageView
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


}
