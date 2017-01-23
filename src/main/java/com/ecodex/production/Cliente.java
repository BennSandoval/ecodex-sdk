package com.ecodex.production;

import com.ecodex.cliente.wsdl.*;
import com.ecodex.seguridad.wsdl.SeguridadObtenerTokenFallaServicioFaultFaultMessage;
import com.ecodex.seguridad.wsdl.SeguridadObtenerTokenFallaSesionFaultFaultMessage;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by bennsandoval@gmail.com on 4/16/15.
 */
public class Cliente {

    public RespuestaRegistro registrar(String rfcCliente, String razonSocial, String email, String integrador, String integradorRFC, String integradorAlta) throws UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage, ClientesRegistrarFallaServicioFaultFaultMessage, ClientesRegistrarFallaValidacionFaultFaultMessage, ClientesRegistrarFallaSesionFaultFaultMessage {

        Seguridad seguridad = new Seguridad();
        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        JAXBElement<String> rfcClienteElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,rfcCliente);
        JAXBElement<String> razonSocialElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RazonSocial"),JAXBElement.class,razonSocial);
        JAXBElement<String> emailElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","CorreoElectronico"),JAXBElement.class,email);

        AltaEmisor emisor= new AltaEmisor();
        emisor.setRFC(rfcClienteElement);
        emisor.setRazonSocial(razonSocialElement);
        emisor.setCorreoElectronico(emailElement);

        SolicitudRegistroCliente registroSolicitud = new SolicitudRegistroCliente();
        String token = seguridad.construirTokenAlta(integrador, integradorRFC, integradorAlta, transactionID);

        JAXBElement<AltaEmisor> altaEmisorElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Emisor"),JAXBElement.class,emisor);
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RfcIntegrador"),JAXBElement.class, integradorRFC);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        registroSolicitud.setToken(tokenElement);
        registroSolicitud.setEmisor(altaEmisorElement);
        registroSolicitud.setRfcIntegrador(rfcElement);
        registroSolicitud.setTransaccionID(transactionID);

        Clientes_Service servicio = new Clientes_Service();
        Clientes puertoX = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaRegistro respuesta = puertoX.registrar(registroSolicitud);
        return respuesta;

    }

    public RespuestaEstatusCuenta estatus(String rfc, String integradorKey) throws ClientesEstatusCuentaFallaServicioFaultFaultMessage, ClientesEstatusCuentaFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Seguridad seguridad = new Seguridad();
        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        String token = seguridad.construirToken(rfc, transactionID, integradorKey);

        SolicitudEstatusCuenta estatusSolicitud = new SolicitudEstatusCuenta();
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,rfc);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        estatusSolicitud.setToken(tokenElement);
        estatusSolicitud.setRFC(rfcElement);
        estatusSolicitud.setTransaccionID(transactionID);

        Clientes_Service servicio = new Clientes_Service();
        Clientes puertoX = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaEstatusCuenta respuesta = puertoX.estatusCuenta(estatusSolicitud);
        return respuesta;
    }

    public RespuestaAsignacionTimbres asigna(String rfc, int folios, String integradorKey) throws ClientesAsignacionTimbresFallaServicioFaultFaultMessage, ClientesAsignacionTimbresFallaValidacionFaultFaultMessage, ClientesAsignacionTimbresFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Seguridad seguridad = new Seguridad();
        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        String token = seguridad.construirToken(rfc,transactionID, integradorKey);

        SolicitudAsignacionTimbres asignacionSolicitud = new SolicitudAsignacionTimbres();
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,rfc);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        asignacionSolicitud.setRFC(rfcElement);
        asignacionSolicitud.setToken(tokenElement);
        asignacionSolicitud.setTransaccionId(transactionID);
        asignacionSolicitud.setTimbresAsignar(folios);

        Clientes_Service servicio = new Clientes_Service();
        Clientes puertoX = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaAsignacionTimbres respuesta = puertoX.asignacionTimbres(asignacionSolicitud);
        return respuesta;

    }

}
