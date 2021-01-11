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
@Table(name="transferencia_local")
public class TranferenciaLocal implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(length=4)
	private int idTransferencia;
	@OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL })
	@JoinColumn(name="cuenta_origen")
	private Cuenta cuentaOrigen;
	@Column(length=5)
	private double monto;
	@Column(length=250, name="concepto_transferencia")
	private String conceptoTransferencia;
	@Column(length=4, name="tarifa_transaccion")
	private double tarifaTransaccion = 0.0;
	@OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL })
	@JoinColumn(name="cuenta_destino")
	private Cuenta cuenta;
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public int getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public Cuenta getClienteLocal() {
		return cuentaOrigen;
	}
	public void setClienteLocal(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getConceptoTransferencia() {
		return conceptoTransferencia;
	}
	public void setConceptoTransferencia(String conceptoTransferencia) {
		this.conceptoTransferencia = conceptoTransferencia;
	}
	public double getTarifaTransaccion() {
		return tarifaTransaccion;
	}
	public void setTarifaTransaccion(double tarifaTransaccion) {
		this.tarifaTransaccion = tarifaTransaccion;
	}
	@Override
	public String toString() {
		return "TranferenciaLocal [idTransferencia=" + idTransferencia + ", cuentaOrigen=" + cuentaOrigen + ", monto="
				+ monto + ", conceptoTransferencia=" + conceptoTransferencia + ", tarifaTransaccion="
				+ tarifaTransaccion + ", cuenta=" + cuenta + "]";
	}

	
	
}
