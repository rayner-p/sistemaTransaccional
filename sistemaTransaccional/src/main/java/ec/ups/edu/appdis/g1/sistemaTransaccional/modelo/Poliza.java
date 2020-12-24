package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "poliza")
public class Poliza implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length=8, nullable=false)
	private int codigo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEmision;
	@Column(length=15)
	private String sucursal;
	private double monto;
	@Column(name ="tiempo_plazo")
	private int tiempoPlazo;
	@Column(name ="cargo_transaccion")
	private double cargoTransaccion;
	private double interes;
	
	@OneToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL })
	@JoinColumn(name="empleado_Fk")
	private Empleado empleadoCaptacion;
	

}
