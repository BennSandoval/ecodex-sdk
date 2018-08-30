package com.ecodex.production;

import com.ecodex.Utils;
import com.ecodex.production.cliente.*;
import com.ecodex.production.seguridad.SeguridadObtenerTokenFallaServicioFaultFaultMessage;
import com.ecodex.production.seguridad.SeguridadObtenerTokenFallaSesionFaultFaultMessage;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by bennsandoval@gmail.com on 4/16/15.
 */
public class Cliente {

    public RespuestaRegistro registrar(String clienteRFC, String razonSocial, String email, String integradorKey, String integradorRFC, String integradorAltaKey) throws UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage, ClientesRegistrarFallaServicioFaultFaultMessage, ClientesRegistrarFallaValidacionFaultFaultMessage, ClientesRegistrarFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        JAXBElement<String> rfcClienteElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,clienteRFC);
        JAXBElement<String> razonSocialElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RazonSocial"),JAXBElement.class,razonSocial);
        JAXBElement<String> emailElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","CorreoElectronico"),JAXBElement.class,email);

        AltaEmisor emisor= new AltaEmisor();
        emisor.setRFC(rfcClienteElement);
        emisor.setRazonSocial(razonSocialElement);
        emisor.setCorreoElectronico(emailElement);

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, integradorRFC);
        String token = Utils.construirTokenAlta(tokenServicio, integradorKey, integradorAltaKey);

        JAXBElement<AltaEmisor> altaEmisorElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Emisor"),JAXBElement.class,emisor);
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RfcIntegrador"),JAXBElement.class, integradorRFC);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        SolicitudRegistroCliente registroSolicitud = new SolicitudRegistroCliente();
        registroSolicitud.setToken(tokenElement);
        registroSolicitud.setEmisor(altaEmisorElement);
        registroSolicitud.setRfcIntegrador(rfcElement);
        registroSolicitud.setTransaccionID(transactionID);

        Clientes_Service servicio = new Clientes_Service();
        Clientes clientes = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaRegistro respuesta = clientes.registrar(registroSolicitud);
        return respuesta;

    }

    public RespuestaEstatusCuenta estatus(String rfc, String integradorKey) throws ClientesEstatusCuentaFallaServicioFaultFaultMessage, ClientesEstatusCuentaFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        SolicitudEstatusCuenta estatusSolicitud = new SolicitudEstatusCuenta();
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,rfc);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        estatusSolicitud.setToken(tokenElement);
        estatusSolicitud.setRFC(rfcElement);
        estatusSolicitud.setTransaccionID(transactionID);

        Clientes_Service servicio = new Clientes_Service();
        Clientes clientes = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaEstatusCuenta respuesta = clientes.estatusCuenta(estatusSolicitud);
        return respuesta;
    }

    public RespuestaAsignacionTimbres asigna(String rfc, int folios, String integradorKey) throws ClientesAsignacionTimbresFallaServicioFaultFaultMessage, ClientesAsignacionTimbresFallaValidacionFaultFaultMessage, ClientesAsignacionTimbresFallaSesionFaultFaultMessage, UnsupportedEncodingException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        SolicitudAsignacionTimbres asignacionSolicitud = new SolicitudAsignacionTimbres();
        JAXBElement<String> rfcElement=new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","RFC"),JAXBElement.class,rfc);
        JAXBElement<String> tokenElement = new JAXBElement(new QName("http://Ecodex.WS.Model/2011/CFDI","Token"),JAXBElement.class,token);

        asignacionSolicitud.setRFC(rfcElement);
        asignacionSolicitud.setToken(tokenElement);
        asignacionSolicitud.setTransaccionId(transactionID);
        asignacionSolicitud.setTimbresAsignar(folios);

        Clientes_Service servicio = new Clientes_Service();
        Clientes clientes = servicio.getPuertoClientes();

        //((BindingProvider)puertoX).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 60000);

        RespuestaAsignacionTimbres respuesta = clientes.asignacionTimbres(asignacionSolicitud);
        return respuesta;

    }

    public String getClaveAltaCertificados(String rfc, String integradorKey) throws IOException, SeguridadObtenerTokenFallaServicioFaultFaultMessage, SeguridadObtenerTokenFallaSesionFaultFaultMessage {

        Random random = new Random();
        Integer trsInt = random.nextInt();
        Long transactionID = Long.parseLong(String.valueOf(trsInt));

        Seguridad seguridad = new Seguridad();
        String tokenServicio = seguridad.obtenerTokenServicio(transactionID, rfc);
        String token = Utils.construirToken(tokenServicio, integradorKey);

        String accessToken = Utils.getAccesToken("https://apiservicios.ecodex.com.mx", rfc);
        String clave =  Utils.getClaveAlta("https://apiservicios.ecodex.com.mx", token, accessToken);
        return clave;

    }

}
