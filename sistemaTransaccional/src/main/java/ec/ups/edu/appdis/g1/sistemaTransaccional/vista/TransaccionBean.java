package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class TransaccionBean implements Serializable{

	/**
	 * 
	 */
	private List<Transaccion> listaTra;
	private String tipoTransaccion;
	private Double monto;
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionSistemLocal on;
	@Inject
	private CuentaBean cuentB;
	@Inject
	private Cliente cliente;
	
	public String valMonto() {
		try {
			if (cliente.getCedula() != null) {
				Cliente usuarioRegistrado = on.buscarCliente(cliente.getCedula());
				if (usuarioRegistrado != null) {
					Cuenta cl = on.buscarCuentaD(cliente.getCedula());
					String l = String.valueOf(cl.getSaldo());
					if (tipoTransaccion.equalsIgnoreCase("retiro") && monto == null) {
						return l;
					} else if (tipoTransaccion.equalsIgnoreCase("retiro") && cl.getSaldo() < monto) {
						String ms = "La cuenta no cuenta con el saldo suficiente, Su saldo es: "
								+ cl.getSaldo();
						return ms;

					} else {
						return l;
					}

				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return " ";
	}
	
	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */
	public String registrar() {
		Cuenta clp = on.buscarCuentaD(cliente.getCedula());
		if (tipoTransaccion.equalsIgnoreCase("deposito")) {
			Double nvmonto = clp.getSaldo() + monto;
			clp.setSaldo(nvmonto);
		//#on.actualizarCuentaDeAhorro(clp);
			Transaccion t = new Transaccion();
			//t.setCliente(clp.);
			t.setMonto(monto);
			t.setFechaHora(new Date());
			t.setTipoTransaccion("deposito");
			t.setSaldoT(nvmonto);
			try {
				//on.guardarTransaccion(t);
				
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("PaginaCajero.xhtml");
			} catch (Exception e) {
			}
		} else if (tipoTransaccion.equalsIgnoreCase("retiro") && monto <= clp.getSaldo()) {
			Double nvmonto2 = clp.getSaldo() - monto;
			clp.setSaldo(nvmonto2);
			//on.actualizarCuentaDeAhorro(clp);
			Transaccion t2 = new Transaccion();
			//t2.setCliente(clp.getCuenta_fk());
			t2.setMonto(monto);
			t2.setFechaHora(new Date());
			t2.setTipoTransaccion("retiro");
			t2.setSaldoT(nvmonto2);
			try {
				
				//on.guardarTransaccion(t2);
				
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("PaginaCajero.xhtml");
			} catch (Exception e) {
			}
		}
		return "PaginaCajero";
	}

}
