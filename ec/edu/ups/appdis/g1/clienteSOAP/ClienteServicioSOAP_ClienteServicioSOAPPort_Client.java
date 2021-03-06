
package ec.edu.ups.appdis.g1.clienteSOAP;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2021-01-06T18:17:20.281-05:00
 * Generated source version: 2.7.11
 * 
 */
public final class ClienteServicioSOAP_ClienteServicioSOAPPort_Client {

    private static final QName SERVICE_NAME = new QName("http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", "ClienteServicioSOAPService");

    private ClienteServicioSOAP_ClienteServicioSOAPPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ClienteServicioSOAPService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ClienteServicioSOAPService ss = new ClienteServicioSOAPService(wsdlURL, SERVICE_NAME);
        ClienteServicioSOAP port = ss.getClienteServicioSOAPPort();  
        
        {
        System.out.println("Invoking prueba...");
        java.lang.String _prueba_arg0 = "";
        java.lang.String _prueba__return = port.prueba(_prueba_arg0);
        System.out.println("prueba.result=" + _prueba__return);


        }
        {
        System.out.println("Invoking realizarTransaccion...");
        ec.edu.ups.appdis.g1.clienteSOAP.Transaccion _realizarTransaccion_arg0 = null;
        java.lang.String _realizarTransaccion__return = port.realizarTransaccion(_realizarTransaccion_arg0);
        System.out.println("realizarTransaccion.result=" + _realizarTransaccion__return);


        }

        System.exit(0);
    }

}
