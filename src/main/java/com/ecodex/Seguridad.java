package com.ecodex;

import com.ecodex.seguridad.wsdl.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * Created by bennsandoval@gmail.com on 4/15/15.
 */
public class Seguridad {

    public String construirToken(String rfcCliente, Long transactionID, String integradorKey) throws SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage, UnsupportedEncodingException {

        String tokenService = obtenerTokenServicio(transactionID, rfcCliente);
        String toHash = String.format("%s|%s", integradorKey, tokenService);
        byte[] as = toHash.getBytes("UTF-8");
        String toHash2 = new String(as,"UTF-8");
        SHA1 sha1 = new SHA1();
        try {
            return sha1.getHash(toHash2);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }

    }

    public String construirTokenAlta(String integrador, String integradorRFC, String integradorAlta, Long trsID) throws SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage, UnsupportedEncodingException {

        String tokenServicio = obtenerTokenServicio(trsID, integradorRFC);
        String toHash = String.format("%s|%s|%s", integrador, integradorAlta, tokenServicio);
        byte[] as = toHash.getBytes("UTF-8");
        String toHash2 = new String(as,"UTF-8");

        SHA1 sha1 = new SHA1();
        try {
            return sha1.getHash(toHash2);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }

    }

    public String obtenerTokenServicio(Long trsID, String rfc) throws SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage {

        SolicitudObtenerToken tokenSolicitud = new SolicitudObtenerToken();

        tokenSolicitud.setTransaccionID(trsID);
        tokenSolicitud.setRFC(new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class, rfc));

        Seguridad_Service servicio = new Seguridad_Service();
        com.ecodex.seguridad.wsdl.Seguridad puertoX = servicio.getPuertoSeguridad();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaObtenerToken tokenServicio = puertoX.obtenerToken(tokenSolicitud);
        String token = tokenServicio.getToken().getValue();

        return token;

    }

}
