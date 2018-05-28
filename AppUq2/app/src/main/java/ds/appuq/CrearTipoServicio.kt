package ds.appuq

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import ds.appuq.code.inicializarTwitter
import ds.appuq.code.publicarTweet
import java.net.URL

class CrearTipoServicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tipo_servicio)
        inicializarTwitter(this)
    }



    fun coso(view: View) {

        try {
            val url = URL("https://www.youtube.com/watch?v=VV9IRQSxx6w")
            val builder: TweetComposer.Builder = TweetComposer.Builder(this)
                    .text("saludando").url(url);
            builder.show()
        }
        catch (e:Exception){
            e.printStackTrace()
        }


    }




    fun compartirTwitter(view: View) {

        publicarTweet(view, "otro ejemplo para publicar", this)


    }
}
