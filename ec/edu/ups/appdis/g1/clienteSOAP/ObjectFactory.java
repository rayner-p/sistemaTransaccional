
package ec.edu.ups.appdis.g1.clienteSOAP;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.edu.ups.appdis.g1.clienteSOAP package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Prueba_QNAME = new QName("http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", "prueba");
    private final static QName _PruebaResponse_QNAME = new QName("http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", "pruebaResponse");
    private final static QName _RealizarTransaccionResponse_QNAME = new QName("http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", "realizarTransaccionResponse");
    private final static QName _RealizarTransaccion_QNAME = new QName("http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", "realizarTransaccion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.edu.ups.appdis.g1.clienteSOAP
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RealizarTransaccionResponse }
     * 
     */
    public RealizarTransaccionResponse createRealizarTransaccionResponse() {
        return new RealizarTransaccionResponse();
    }

    /**
     * Create an instance of {@link RealizarTransaccion }
     * 
     */
    public RealizarTransaccion createRealizarTransaccion() {
        return new RealizarTransaccion();
    }

    /**
     * Create an instance of {@link Prueba }
     * 
     */
    public Prueba createPrueba() {
        return new Prueba();
    }

    /**
     * Create an instance of {@link PruebaResponse }
     * 
     */
    public PruebaResponse createPruebaResponse() {
        return new PruebaResponse();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Empleado }
     * 
     */
    public Empleado createEmpleado() {
        return new Empleado();
    }

    /**
     * Create an instance of {@link Transaccion }
     * 
     */
    public Transaccion createTransaccion() {
        return new Transaccion();
    }

    /**
     * Create an instance of {@link Cuenta }
     * 
     */
    public Cuenta createCuenta() {
        return new Cuenta();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Prueba }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", name = "prueba")
    public JAXBElement<Prueba> createPrueba(Prueba value) {
        return new JAXBElement<Prueba>(_Prueba_QNAME, Prueba.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PruebaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", name = "pruebaResponse")
    public JAXBElement<PruebaResponse> createPruebaResponse(PruebaResponse value) {
        return new JAXBElement<PruebaResponse>(_PruebaResponse_QNAME, PruebaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarTransaccionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", name = "realizarTransaccionResponse")
    public JAXBElement<RealizarTransaccionResponse> createRealizarTransaccionResponse(RealizarTransaccionResponse value) {
        return new JAXBElement<RealizarTransaccionResponse>(_RealizarTransaccionResponse_QNAME, RealizarTransaccionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarTransaccion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/", name = "realizarTransaccion")
    public JAXBElement<RealizarTransaccion> createRealizarTransaccion(RealizarTransaccion value) {
        return new JAXBElement<RealizarTransaccion>(_RealizarTransaccion_QNAME, RealizarTransaccion.class, null, value);
    }

}
