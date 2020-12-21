package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name= "transaccion")
public class Transaccion {
	@Id
	@GeneratedValue
	@Column(name ="codigo")
	private int codigoTransaccion;	
	//private Cliente cliente;
	@Column(name = "fecha_hora")
	private Date fechaHora;
	private String sucursal;

	private double monto;
	@Column(name = "tipo_transaccion")
	private String tipoTransaccion;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "empleado_fk")
	private Empleado empleado;

	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}

	public void setCodigoTransaccion(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return "Transaccion [codigoTransaccion=" + codigoTransaccion + ", fechaHora=" + fechaHora + ", sucursal="
				+ sucursal + ", monto=" + monto + ", tipoTransaccion=" + tipoTransaccion + ", empleado=" + empleado
				+ "]";
	}
	
}
