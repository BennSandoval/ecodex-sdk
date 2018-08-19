package com.ecodex.test;

import com.ecodex.test.seguridad.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 * Created by bennsandoval@gmail.com on 4/15/15.
 */
public class Seguridad {

    public String obtenerTokenServicio(Long trsID, String rfc) throws SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage {

        SolicitudObtenerToken tokenSolicitud = new SolicitudObtenerToken();

        tokenSolicitud.setTransaccionID(trsID);
        tokenSolicitud.setRFC(new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class, rfc));

        Seguridad_Service servicio = new Seguridad_Service();
        com.ecodex.test.seguridad.Seguridad seguridad = servicio.getPuertoSeguridad();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaObtenerToken tokenServicio = seguridad.obtenerToken(tokenSolicitud);
        String token = tokenServicio.getToken().getValue();

        return token;

    }

}
