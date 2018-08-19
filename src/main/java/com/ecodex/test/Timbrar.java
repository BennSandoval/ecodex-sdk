package com.ecodex.test;

import com.ecodex.Utils;
import com.ecodex.test.cancela.*;
import com.ecodex.test.repositorio.RepositorioCancelaComprobanteFallaServicioFaultFaultMessage;
import com.ecodex.test.repositorio.RepositorioCancelaComprobanteFallaSesionFaultFaultMessage;
import com.ecodex.test.repositorio.RepositorioCancelaComprobanteFallaValidacionFaultFaultMessage;
import com.ecodex.test.seguridad.SeguridadObtenerTokenFallaServicioFaultFaultMessage;
import com.ecodex.test.seguridad.SeguridadObtenerTokenFallaSesionFaultFaultMessage;
import com.ecodex.test.timbrado.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * Created by bennsandoval@gmail.com on 4/15/15.
 */
public class Timbrar {

    public RespuestaTimbraXML timbraXML(String rfc, String comprobanteXML, String integradorKey) throws TimbradoTimbraXMLFallaSesionFaultFaultMessage, TimbradoTimbraXMLFallaServicioFaultFaultMessage, TimbradoTimbraXMLFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        ComprobanteXML comprobante =new ComprobanteXML();
        JAXBElement xml=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","DatosXML"),JAXBElement.class,comprobanteXML);
        comprobante.setDatosXML(xml);

        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class, token);
        JAXBElement<ComprobanteXML> xml2=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","ComprobanteXML"),JAXBElement.class, comprobante);

        SolicitudTimbraXML solicitud = new SolicitudTimbraXML();
        solicitud.setRFC(Rfc);
        solicitud.setToken(Token);
        solicitud.setTransaccionID(transactionID);
        solicitud.setComprobanteXML(xml2);

        Timbrado_Service service = new Timbrado_Service();
        Timbrado timbrado = service.getPuertoTimbrado();
        RespuestaTimbraXML respuesta = timbrado.timbraXML(solicitud);

        return respuesta;
    }

    public RespuestaEstatusTimbrado estatus(String rfc, Long trsIdOriginal, String uuid, String integradorKey) throws TimbradoEstatusTimbradoFallaServicioFaultFaultMessage, TimbradoEstatusTimbradoFallaSesionFaultFaultMessage, TimbradoEstatusTimbradoFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

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

        Timbrado_Service servicioX = new Timbrado_Service();
        Timbrado timbrado = servicioX.getPuertoTimbrado();
        RespuestaEstatusTimbrado respuesta = timbrado.estatusTimbrado(solicitud);

        return respuesta;

    }

    public RespuestaCancelaTimbrado cancela(String rfc, String uuid, String integradorKey) throws TimbradoCancelaTimbradoFallaValidacionFaultFaultMessage, TimbradoCancelaTimbradoFallaServicioFaultFaultMessage, TimbradoCancelaTimbradoFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage, UnsupportedEncodingException {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);
        JAXBElement<String> UUID = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);

        SolicitudCancelaTimbrado solicitudCancelaTimbrado = new SolicitudCancelaTimbrado();
        solicitudCancelaTimbrado.setRFC(Rfc);
        solicitudCancelaTimbrado.setToken(Token);
        solicitudCancelaTimbrado.setTransaccionID(transactionID);
        solicitudCancelaTimbrado.setUUID(UUID);

        Timbrado_Service service = new Timbrado_Service();
        Timbrado timbrado = service.getPuertoTimbrado();
        RespuestaCancelaTimbrado respuestaCancelaTimbrado = timbrado.cancelaTimbrado(solicitudCancelaTimbrado);

        RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        if(respuestaCancelaTimbrado.isCancelada()){
            respuesta.setCancelada(true);
        } else {
            respuesta.setCancelada(false);
        }
        respuestaCancelaTimbrado.getTransaccionID();
        return respuesta;
    }

    public RespuestaCancelaTimbrado cancelaMasiva(String rfc, List<String> uuids, String integradorKey) throws TimbradoCancelaTimbradoFallaValidacionFaultFaultMessage, TimbradoCancelaTimbradoFallaServicioFaultFaultMessage, TimbradoCancelaTimbradoFallaSesionFaultFaultMessage, RepositorioCancelaComprobanteFallaServicioFaultFaultMessage, RepositorioCancelaComprobanteFallaSesionFaultFaultMessage, RepositorioCancelaComprobanteFallaValidacionFaultFaultMessage, CancelacionesCancelaMultipleFallaServicioFaultFaultMessage, CancelacionesCancelaMultipleFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        ListaCancelar lista= new ListaCancelar();
        for(String uuid: uuids) {
            lista.getGuid().add(uuid);
        }

        JAXBElement<ListaCancelar> UUIDS= new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","ListaCancelar"), JAXBElement.class,lista);
        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudCancelaMultiple solicitud = new SolicitudCancelaMultiple();
        solicitud.setRFC(Rfc);
        solicitud.setToken(Token);
        solicitud.setListaCancelar(UUIDS);
        solicitud.setTransaccionID(transactionID);

        Cancelaciones_Service service = new Cancelaciones_Service();
        Cancelaciones cancelaciones = service.getPuertoCancelacion();
        RespuestaCancelaMultiple respuestaX = cancelaciones.cancelaMultiple(solicitud);
        RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        if(respuestaX.getResultado().getValue().getResultadoCancelacion().get(0).getEstatus().getValue().equals("Cancelado")){
            respuesta.setCancelada(true);
        } else {
            respuesta.setCancelada(false);
        }
        respuesta.setTransaccionID(Long.valueOf(respuestaX.getTransaccionID().getValue()));

        return respuesta;
    }

    public RespuestaCancelaTimbrado cancelaOtros(String rfc, String rfcReceptor, String UUID, String integradorKey) throws CancelacionesCancelaOtrosFallaSesionFaultFaultMessage, CancelacionesCancelaOtrosFallaServicioFaultFaultMessage, CancelacionesCancelaOtrosFallaValidacionFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage, SeguridadObtenerTokenFallaServicioFaultFaultMessage, UnsupportedEncodingException {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        JAXBElement<String> RfcEmisor = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFCEmisor"), JAXBElement.class, rfc);
        JAXBElement<String> RfcReceptor = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFCReceptor"), JAXBElement.class, rfcReceptor);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudCancelaOtros solicitud = new SolicitudCancelaOtros();
        solicitud.setRFCEmisor(RfcEmisor);
        solicitud.setRFCReceptor(RfcReceptor);
        solicitud.setToken(Token);
        solicitud.setTransaccionID(transactionID);
        solicitud.setUUID(UUID);

        Cancelaciones_Service service = new Cancelaciones_Service();
        Cancelaciones cancelaciones = service.getPuertoCancelacion();
        RespuestaCancelaOtros respuestaX = cancelaciones.cancelaOtros(solicitud);
        RespuestaCancelaTimbrado respuesta = new RespuestaCancelaTimbrado();
        if(respuestaX.getResultado().getValue().getResultadoCancelacion().get(0).getEstatus().getValue().equals("Cancelado")){
            respuesta.setCancelada(true);
        } else {
            respuesta.setCancelada(false);
        }
        respuesta.setTransaccionID(respuestaX.getTransaccionID());
        return respuesta;

    }

    public RespuestaObtenerTimbrado obtenerTimbre(String rfc, Long trsIdOriginal, String uuid, String integradorKey) throws TimbradoObtenerTimbradoFallaValidacionFaultFaultMessage, TimbradoObtenerTimbradoFallaSesionFaultFaultMessage, TimbradoObtenerTimbradoFallaServicioFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> UUID = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);
        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudObtenerTimbrado solicitud = new SolicitudObtenerTimbrado();
        solicitud.setToken(Token);
        solicitud.setRFC(Rfc);
        solicitud.setTransaccionID(transactionID);
        solicitud.setTransaccionOriginal(trsIdOriginal);
        solicitud.setUUID(UUID);

        Timbrado_Service service = new Timbrado_Service();
        Timbrado timbrado = service.getPuertoTimbrado();
        RespuestaObtenerTimbrado respuesta = timbrado.obtenerTimbrado(solicitud);

        return respuesta;
    }

    public RespuestaObtenerQRTimbrado obtenerQR(String rfc, String uuid, String integradorKey) throws TimbradoObtenerQRTimbradoFallaSesionFaultFaultMessage, TimbradoObtenerQRTimbradoFallaServicioFaultFaultMessage, TimbradoObtenerQRTimbradoFallaValidacionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        JAXBElement<String> Rfc = new JAXBElement (new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"), JAXBElement.class, rfc);
        JAXBElement<String> Token = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);
        JAXBElement<String> UUID = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","UUID"),JAXBElement.class,uuid);

        SolicitudObtenerQRTimbrado solicitudqr = new SolicitudObtenerQRTimbrado();
        solicitudqr.setTransaccionID(transactionID);
        solicitudqr.setRFC(Rfc);
        solicitudqr.setToken(Token);
        solicitudqr.setUUID(UUID);

        Timbrado_Service service = new Timbrado_Service();
        Timbrado timbrado = service.getPuertoTimbrado();
        RespuestaObtenerQRTimbrado respuestaqr = timbrado.obtenerQRTimbrado(solicitudqr);
        return respuestaqr;

    }

}
