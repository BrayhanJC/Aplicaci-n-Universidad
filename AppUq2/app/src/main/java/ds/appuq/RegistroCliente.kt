package ds.appuq

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.share.model.ShareHashtag
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import ds.appuq.code.LoadImage

class RegistroCliente : AppCompatActivity() {

    lateinit var cargando: LoadImage
    var shareDialog: ShareDialog? = null
    var callbackManager: CallbackManager? = null


    lateinit var btn: Button
    lateinit var btnCompartir: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_cliente)

        val imageview = findViewById<View>(R.id.lblImageRegistrarCliente) as ImageView
        btn = findViewById<View>(R.id.btnGuardarRegistroCliente) as Button
        btnCompartir = findViewById<View>(R.id.btnCompartir) as ImageButton
        cargando = LoadImage(imageview, this)
        imageview!!.setOnClickListener { cargando.showPictureDialog() }





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
        if (ShareDialog.canShow(ShareLinkContent::class.java)) {
            val content = ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=Zq8_XppDXdk"))
                    .setQuote("Agregnado registro")
                    .setShareHashtag(ShareHashtag.Builder()
                            .setHashtag("#programando ando")
                            .build()).build()

            shareDialog?.show(content)
        }
    }

    fun compartirImageFacebook(view: View) {
        compartirContenido(view)
    }

    fun guardarCliente(view: View) {
        Toast.makeText(this, "Se ha guardado \n el cliente", Toast.LENGTH_LONG).show()
       btn.setVisibility(View.INVISIBLE);
        btnCompartir.setVisibility(View.VISIBLE);
    }


}
