package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "cuenta")
public class Cuenta {
	@Id
	@Column(name= "numero_cuenta", length =12)
	private String numeroCuenta;
	@Column(name= "fecha_apertura")
	private Date fechaApertura;
	@Column(length =8)
	private double saldo;
	@Column(name= "tipo_cuenta", length=20)
	private String tipoCuenta;
	
	private String cuenta_fk;
	
	
	/**  
	 * Metodo que permite obtener el atributo numeroCuenta 
	 * @return El atributo numeroCuenta de esta clase
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	/**  
	 * Metodo que permite asignar el atributo numeroCuenta 
	 * @param El atributo numeroCuenta de esta clase
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/**  
	 * Metodo que permite obtener el atributo fechaApertura 
	 * @return El atributo fechaApertura de esta clase
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}
	/**  
	 * Metodo que permite asignar el atributo fechaApertura 
	 * @param El atributo fechaApertura de esta clase
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	/**  
	 * Metodo que permite obtener el atributo saldo 
	 * @return El atributo saldo de esta clase
	 */
	public double getSaldo() {
		return saldo;
	}
	/**  
	 * Metodo que permite asignar el atributo saldo 
	 * @param El atributo saldo de esta clase
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	/**  
	 * Metodo que permite asignar el atributo cuenta_fk 
	 * @return El atributo cuenta_fk de esta clase
	 */
	public String getCuenta_fk() {
		return cuenta_fk;
	}
	/**  
	 * Metodo que permite asignar el atributo cuenta_fk 
	 * @param El atributo cuenta_fk de esta clase
	 */
	public void setCuenta_fk(String cuenta_fk) {
		this.cuenta_fk = cuenta_fk;
	}
	/**  
	 * Metodo que permite obtener el atributo tipoCuenta 
	 * @return El atributo tipoCuenta de esta clase
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	/**  
	 * Metodo que permite asignar el atributo tipoCuenta 
	 * @param El atributo tipoCuenta de esta clase
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo + ", tipoCuenta=" + tipoCuenta + "]";
	}
	
}
