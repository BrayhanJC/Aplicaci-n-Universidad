package util

import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by PC on 21/05/2018.
 */
fun getKeyHash(context: Context) {
    try {
        val info =
                context.getPackageManager().getPackageInfo(context.getPackageName(),
                        PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            val sign = Base64.encodeToString(md.digest(), Base64.DEFAULT)
            Log.e("Mi clave HASH:", sign)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        Log.d("prueba", "1 KeyHash Error: " + e.message)

    } catch (e: NoSuchAlgorithmException) {
        Log.d("prueba", "2 KeyHash Error: " + e.message)
    }
}