package com.ecodex.production;

import com.ecodex.production.cancela.*;
import com.ecodex.production.repositorio.RepositorioCancelaComprobanteFallaServicioFaultFaultMessage;
import com.ecodex.production.repositorio.RepositorioCancelaComprobanteFallaSesionFaultFaultMessage;
import com.ecodex.production.repositorio.RepositorioCancelaComprobanteFallaValidacionFaultFaultMessage;
import com.ecodex.production.seguridad.SeguridadObtenerTokenFallaServicioFaultFaultMessage;
import com.ecodex.production.seguridad.SeguridadObtenerTokenFallaSesionFaultFaultMessage;
import com.ecodex.production.timbrado.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by bennsandoval@gmail.com on 4/15/15.
 */
public class Timbrar {

    public RespuestaTimbraXML timbraXML(String token, String rfc, String comprobanteXML) throws TimbradoTimbraXMLFallaSesionFaultFaultMessage, TimbradoTimbraXMLFallaServicioFaultFaultMessage, TimbradoTimbraXMLFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Timbrado_Service servicioX = new Timbrado_Service();
        Timbrado puertoX = servicioX.getPuertoTimbrado();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);
        ComprobanteXML comprobante =new ComprobanteXML();
        JAXBElement xml=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","DatosXML"),JAXBElement.class,comprobanteXML);
        comprobante.setDatosXML(xml);
        JAXBElement<ComprobanteXML> xml2=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","ComprobanteXML"),JAXBElement.class,comprobante);

        SolicitudTimbraXML solicitud = new SolicitudTimbraXML();
        solicitud.setRFC(Rfc);
        solicitud.setToken(Token);
        solicitud.setTransaccionID(transactionID);
        solicitud.setComprobanteXML(xml2);
        RespuestaTimbraXML respuesta = puertoX.timbraXML(solicitud);

        return respuesta;
    }

    public RespuestaEstatusTimbrado estatus(String token, String rfc, Long trsIdOriginal, String uuid) throws TimbradoEstatusTimbradoFallaServicioFaultFaultMessage, TimbradoEstatusTimbradoFallaSesionFaultFaultMessage, TimbradoEstatusTimbradoFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Timbrado_Service servicioX = new Timbrado_Service();
        Timbrado puertoX = servicioX.getPuertoTimbrado();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> Uuid = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);
        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudEstatusTimbrado solicitud = new SolicitudEstatusTimbrado();
        solicitud.setRFC(Rfc);
        solicitud.setToken(Token);
        solicitud.setTransaccionID(transactionID);
        solicitud.setTransaccionOriginal(trsIdOriginal);
        solicitud.setUUID(Uuid);

        RespuestaEstatusTimbrado respuesta = puertoX.estatusTimbrado(solicitud);

        return respuesta;

    }

    public RespuestaCancelaTimbrado cancela(String token, String rfc, String uuid) throws TimbradoCancelaTimbradoFallaValidacionFaultFaultMessage, TimbradoCancelaTimbradoFallaServicioFaultFaultMessage, TimbradoCancelaTimbradoFallaSesionFaultFaultMessage, RepositorioCancelaComprobanteFallaServicioFaultFaultMessage, RepositorioCancelaComprobanteFallaSesionFaultFaultMessage, RepositorioCancelaComprobanteFallaValidacionFaultFaultMessage, CancelacionesCancelaMultipleFallaServicioFaultFaultMessage, CancelacionesCancelaMultipleFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        //Repositorio_Service servicioX = new Repositorio_Service();
        //Repositorio puertoX = servicioX.getPuertoRepositorio();

        //Timbrado_Service servicioX = new Timbrado_Service();
        //Timbrado puertoX = servicioX.getPuertoTimbrado();

        Cancelaciones_Service servicioX = new Cancelaciones_Service();
        Cancelaciones puertoX = servicioX.getPuertoCancelacion();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        ListaCancelar lista= new ListaCancelar();
        lista.getGuid().add(uuid);

        //JAXBElement<String> UUID = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);
        JAXBElement<ListaCancelar> UUIDS= new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","ListaCancelar"), JAXBElement.class,lista);
        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        //SolicitudCancelaTimbrado solicitud = new SolicitudCancelaTimbrado();
        //SolicitudCancelaComprobante solicitud = new SolicitudCancelaComprobante();

        SolicitudCancelaMultiple solicitud = new SolicitudCancelaMultiple();

        solicitud.setRFC(Rfc);
        solicitud.setToken(Token);
        solicitud.setListaCancelar(UUIDS);
        //solicitud.setUUID(UUID);
        solicitud.setTransaccionID(transactionID);

        //RespuestaCancelaTimbrado respuesta = puertoX.cancelaTimbrado(solicitud);

        //RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        //RespuestaCancelaComprobante respuestaX = puertoX.cancelaComprobante(solicitud);
        //respuesta.setCancelada(respuestaX.isCancelada());
        //respuesta.setTransaccionID(respuestaX.getTransaccionID());

        RespuestaCancelaMultiple respuestaX = puertoX.cancelaMultiple(solicitud);
        RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        if(respuestaX.getResultado().getValue().getResultadoCancelacion().get(0).getEstatus().getValue().equals("Cancelado")){
            respuesta.setCancelada(true);
        } else {
            respuesta.setCancelada(false);
        }
        respuesta.setTransaccionID(Long.valueOf(respuestaX.getTransaccionID().getValue()));

        return respuesta;
    }

    public RespuestaCancelaTimbrado cancelaOtros(String token, String rfcEmisor, String rfcReceptor, String UUID) throws CancelacionesCancelaOtrosFallaSesionFaultFaultMessage, CancelacionesCancelaOtrosFallaServicioFaultFaultMessage, CancelacionesCancelaOtrosFallaValidacionFaultFaultMessage {
        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        JAXBElement<String> RfcEmisor = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFCEmisor"), JAXBElement.class, rfcEmisor);
        JAXBElement<String> RfcReceptor = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFCReceptor"), JAXBElement.class, rfcReceptor);

        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudCancelaOtros solicitud = new SolicitudCancelaOtros();
        solicitud.setRFCEmisor(RfcEmisor);
        solicitud.setRFCReceptor(RfcReceptor);
        solicitud.setToken(Token);
        solicitud.setTransaccionID(transactionID);
        solicitud.setUUID(UUID);

        Cancelaciones_Service servicioX = new Cancelaciones_Service();
        Cancelaciones puertoX = servicioX.getPuertoCancelacion();
        RespuestaCancelaOtros respuestaX = puertoX.cancelaOtros(solicitud);

        RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        if(respuestaX.getResultado().getValue().getResultadoCancelacion().get(0).getEstatus().getValue().equals("Cancelado")){
            respuesta.setCancelada(true);
        } else {
            respuesta.setCancelada(false);
        }
        respuesta.setTransaccionID(respuestaX.getTransaccionID());
        return respuesta;
    }

    public RespuestaObtenerTimbrado obtenerTimbre(String token, String rfc, Long trsIdOriginal, String uuid) throws TimbradoObtenerTimbradoFallaValidacionFaultFaultMessage, TimbradoObtenerTimbradoFallaSesionFaultFaultMessage, TimbradoObtenerTimbradoFallaServicioFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        String comprobante = new String();
        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Timbrado_Service servicioX = new Timbrado_Service();
        Timbrado puertoX = servicioX.getPuertoTimbrado();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> Uuid = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);
        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudObtenerTimbrado solicitud = new SolicitudObtenerTimbrado();
        solicitud.setToken(Token);
        solicitud.setRFC(Rfc);
        solicitud.setTransaccionID(transactionID);
        solicitud.setTransaccionOriginal(trsIdOriginal);
        solicitud.setUUID(Uuid);

        RespuestaObtenerTimbrado respuesta = puertoX.obtenerTimbrado(solicitud);

        return respuesta;
    }

    public RespuestaObtenerQRTimbrado obtenerQR(String token, String rfc, String uuid) throws TimbradoObtenerQRTimbradoFallaSesionFaultFaultMessage, TimbradoObtenerQRTimbradoFallaServicioFaultFaultMessage, TimbradoObtenerQRTimbradoFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Timbrado_Service servicioX = new Timbrado_Service();
        Timbrado puertoX = servicioX.getPuertoTimbrado();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);
        JAXBElement<String> Uuid = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);

        SolicitudObtenerQRTimbrado solicitudqr = new SolicitudObtenerQRTimbrado();
        solicitudqr.setTransaccionID(transactionID);
        solicitudqr.setRFC(Rfc);
        solicitudqr.setToken(Token);
        solicitudqr.setUUID(Uuid);

        RespuestaObtenerQRTimbrado respuestaqr = puertoX.obtenerQRTimbrado(solicitudqr);
        return respuestaqr;
    }

}
