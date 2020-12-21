package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="parametrizar")
public class Parametrizar implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length=4)
	private String minimo;
	@Column(length=4)
	private String maximo;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cedula_empleado")
	private Empleado empleado;
	

	@Column(name = "tasa_interes")
	private double tasaInteres;
	
	public String getMinimo() {
		return minimo;
	}
	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}
	public String getMaximo() {
		return maximo;
	}
	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}
	public double getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(int tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	
}
