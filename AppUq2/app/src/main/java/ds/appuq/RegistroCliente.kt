package ds.appuq

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import ds.appuq.code.LoadImage

class RegistroCliente : AppCompatActivity() {

    lateinit var cargando: LoadImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_cliente)

        val imageview = findViewById<View>(R.id.lblImageRegistrarCliente) as ImageView
        cargando=LoadImage(imageview, this)
        imageview!!.setOnClickListener{cargando.showPictureDialog()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cargando.onActivityResult(requestCode, resultCode, data)
    }


}
