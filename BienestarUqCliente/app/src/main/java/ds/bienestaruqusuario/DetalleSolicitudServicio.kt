package ds.bienestaruqusuario

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fragments.FragmentDetalleServicioSolicitado
import utilidades.SolicitudServicios
import vo.SolicitudServicios


class DetalleSolicitudServicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_solicitud_servicio)
        val solicitudServicios: SolicitudServicios = intent.getParcelableExtra<SolicitudServicios>(SolicitudServicios)
        val fragmentoDetalle = supportFragmentManager.findFragmentById(R.id.fragmentoDetalleSolicitudServicio) as FragmentDetalleServicioSolicitado
        fragmentoDetalle.darDetalle(solicitudServicios)
    }
}
