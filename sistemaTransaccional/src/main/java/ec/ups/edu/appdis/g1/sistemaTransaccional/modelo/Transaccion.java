package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name= "transaccion")
public class Transaccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name ="codigo")
	private int codigoTransaccion;	
	//private Cliente cliente;
	@Column(name = "fecha_hora")
	private Date fechaHora;
	private String sucursal;

	private double monto;
	@Column(name = "tipo_transaccion")
	private String tipoTransaccion;
	
	private Empleado empleado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cuenta_trans")
	private Cuenta cuenta;
	
	private String transaccion_fk;  //no tocar

	
	/** 
	 * Metodo que permite obtener un valor al atributo transaccion_fk 
	 * @return transaccion_fk Variable que se asigna al atributo de la clase
	 */
	public String getTransaccion_fk() {
		return transaccion_fk;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo transaccion_fk 
	 * @param transaccion_fk Variable que se asigna al atributo de la clase
	 */

	public void setTransaccion_fk(String transaccion_fk) {
		this.transaccion_fk = transaccion_fk;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo codigoTransaccion 
	 * @return codigoTransaccion Variable que se asigna al atributo de la clase
	 */

	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}

	/** 
	 * Metodo que permite obtener un valor al atributo codigoTransaccion 
	 * @param  codigoTransaccion Variable que se asigna al atributo de la clase
	 */
	public void setCodigoTransaccion(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	/** 
	 * Metodo que permite obtener un valor al atributo fechaHora 
	 * @return fechaHora Variable que se asigna al atributo de la clase
	 */
	public Date getFechaHora() {
		return fechaHora;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo fechaHora 
	 * @param  urn fechaHora Variable que se asigna al atributo de la clase
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo sucursal 
	 * @return sucursal Variable que se asigna al atributo de la clase
	 */
	public String getSucursal() {
		return sucursal;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo sucursal 
	 * @param  sucursal Variable que se asigna al atributo de la clase
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo monto 
	 * @return monto Variable que se asigna al atributo de la clase
	 */
	public double getMonto() {
		return monto;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo monto 
	 * @param  monto Variable que se asigna al atributo de la clase
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo tipoTransaccion 
	 * @return tipoTransaccion Variable que se asigna al atributo de la clase
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo tipoTransaccion 
	 * @param  tipoTransaccion Variable que se asigna al atributo de la clase
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo empleado 
	 * @return empleado Variable que se asigna al atributo de la clase
	 */
	public Empleado getEmpleado() {
		return empleado;
	}
	/** 
	 * Metodo que permite obtener un valor al atributo empleado 
	 * @param  empleado Variable que se asigna al atributo de la clase
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	

	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	@Override
	public String toString() {
		return "Transaccion [codigoTransaccion=" + codigoTransaccion + ", fechaHora=" + fechaHora + ", sucursal="
				+ sucursal + ", monto=" + monto + ", tipoTransaccion=" + tipoTransaccion + ", empleado=" + empleado
				+ ", cuenta=" + cuenta + ", transaccion_fk=" + transaccion_fk + "]";
	}
	

}
