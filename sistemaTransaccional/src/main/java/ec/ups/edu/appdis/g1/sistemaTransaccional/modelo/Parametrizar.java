package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="parametrizar")
public class Parametrizar implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length=4)
	private int codigo;
	@Column(length=4)
	private String minimo;
	@Column(length=4)
	private String maximo;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cedula_empleado")
	private Empleado empleado;
	
	@Column(name = "tasa_interes")
	private double tasaInteres;
	/**  
	 * Metodo que permite obtener el atributo codigo 
	 * @param El atributo codigo de esta clase
	 */
	public int getCodigo() {
		return codigo;
	}
	/**  
	 * Metodo que permite asignar el atributo codigo 
	 * @param El atributo codigo de esta clase
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**  
	 * Metodo que permite asignar el atributo tasaInteres 
	 * @param El atributo tasaInteres de esta clase
	 */
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	/**  
	 * Metodo que permite obtener el atributo minimo 
	 * @param El atributo minimo de esta clase
	 */
	public String getMinimo() {
		return minimo;
	}
	/**  
	 * Metodo que permite asignar el atributo minimo 
	 * @param El atributo minimo de esta clase
	 */
	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}
	/**  
	 * Metodo que permite codigobtenero el atributo maximo 
	 * @param El atributo maximo de esta clase
	 */
	public String getMaximo() {
		return maximo;
	}
	/**  
	 * Metodo que permite obtener el atributo maxitasaInteresmo 
	 * @param El atributo tasaInteres de esta clase
	 */
	
	public double getTasaInteres() {
		return tasaInteres;
	}
	/**  
	 * Metodo que permite asignar el atributo maximo 
	 * @param El atributo maximo de esta clase
	 */
	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}
	/**  
	 * Metodo que permite obtener el atributo empleado 
	 * @param El atributo empleado de esta clase
	 */

	public Empleado getEmpleado() {
		return empleado;
	}
	/**  
	 * Metodo que permite asignar el atributo empleado 
	 * @param El atributo tipoCuempleadoenta de esta clase
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	
	@Override
	public String toString() {
		return "Parametrizar [codigo=" + codigo + ", minimo=" + minimo + ", maximo=" + maximo + ", empleado=" + empleado
				+ ", tasaInteres=" + tasaInteres + "]";
	}
	
	
}
