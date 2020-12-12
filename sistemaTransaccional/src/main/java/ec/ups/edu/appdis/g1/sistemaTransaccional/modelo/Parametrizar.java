package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(length=4)
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
	
	
}
