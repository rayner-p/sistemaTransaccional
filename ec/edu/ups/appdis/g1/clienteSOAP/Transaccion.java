
package ec.edu.ups.appdis.g1.clienteSOAP;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for transaccion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cliente" type="{http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/}cliente" minOccurs="0"/>
 *         &lt;element name="codigoTransaccion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="empleado" type="{http://servicios.sistemaTransaccional.g1.appdis.edu.ups.ec/}empleado" minOccurs="0"/>
 *         &lt;element name="fechaHora" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="saldoT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transaccion_fk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaccion", propOrder = {
    "cliente",
    "codigoTransaccion",
    "empleado",
    "fechaHora",
    "monto",
    "saldoT",
    "sucursal",
    "tipoTransaccion",
    "transaccionFk"
})
public class Transaccion {

    protected Cliente cliente;
    protected int codigoTransaccion;
    protected Empleado empleado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaHora;
    protected double monto;
    protected double saldoT;
    protected String sucursal;
    protected String tipoTransaccion;
    @XmlElement(name = "transaccion_fk")
    protected String transaccionFk;

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the codigoTransaccion property.
     * 
     */
    public int getCodigoTransaccion() {
        return codigoTransaccion;
    }

    /**
     * Sets the value of the codigoTransaccion property.
     * 
     */
    public void setCodigoTransaccion(int value) {
        this.codigoTransaccion = value;
    }

    /**
     * Gets the value of the empleado property.
     * 
     * @return
     *     possible object is
     *     {@link Empleado }
     *     
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Sets the value of the empleado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Empleado }
     *     
     */
    public void setEmpleado(Empleado value) {
        this.empleado = value;
    }

    /**
     * Gets the value of the fechaHora property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the value of the fechaHora property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaHora(XMLGregorianCalendar value) {
        this.fechaHora = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
    }

    /**
     * Gets the value of the saldoT property.
     * 
     */
    public double getSaldoT() {
        return saldoT;
    }

    /**
     * Sets the value of the saldoT property.
     * 
     */
    public void setSaldoT(double value) {
        this.saldoT = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursal(String value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the tipoTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * Sets the value of the tipoTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTransaccion(String value) {
        this.tipoTransaccion = value;
    }

    /**
     * Gets the value of the transaccionFk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransaccionFk() {
        return transaccionFk;
    }

    /**
     * Sets the value of the transaccionFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransaccionFk(String value) {
        this.transaccionFk = value;
    }

}
