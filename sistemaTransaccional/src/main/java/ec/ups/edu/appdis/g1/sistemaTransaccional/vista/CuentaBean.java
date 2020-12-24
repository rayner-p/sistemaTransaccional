package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@Named
@SessionScoped
public class CuentaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GestionSistemLocal on;
	@Inject
	private ClienteBean clienteB;
	@Inject
	private Empleado empleadoB;
	private Date fechaActual;
	private String cuenta;
	private String tipoCuenta;
	private String tipoTransacción;
	private double saldoAhorro = 5.0;
	private double saldoCredito = 500.00;
	private String nombrEmpleado;
	private Cliente cliente;
	private double saldoNuevo;
	private String cuentaObtenida;

	public String getCuentaObtenida() {
		return cuentaObtenida;
	}

	public void setCuentaObtenida(String cuentaObtenida) {
		this.cuentaObtenida = cuentaObtenida;
	}

	private List<Cuenta> listaCuenta;
	private List<Transaccion> listaTransaccion;

	private Cuenta newCuenta;
	private Transaccion newTransaccion;

	public double getSaldoNuevo() {
		return saldoNuevo;
	}

	public void setSaldoNuevo(double saldoNuevo) {
		this.saldoNuevo = saldoNuevo;
	}

	public String getNombrEmpleado() {
		return nombrEmpleado;
	}

	public void setNombrEmpleado(String nombrEmpleado) {
		this.nombrEmpleado = nombrEmpleado;
	}

	public String getTipoTransacción() {
		return tipoTransacción;
	}

	public void setTipoTransacción(String tipoTransacción) {
		this.tipoTransacción = tipoTransacción;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String usuario) {
		this.cuenta = usuario;
	}

	public String gettipoCuenta() {
		return tipoCuenta;
	}

	public void setipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public double getSaldoAhorro() {
		return saldoAhorro;
	}

	public void setSaldoAhorro(double saldoAhorro) {
		this.saldoAhorro = saldoAhorro;
	}

	public double getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cuenta getNewCuenta() {
		return newCuenta;
	}

	public void setNewCuenta(Cuenta newCuenta) {
		this.newCuenta = newCuenta;
	}

	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

	public List<Transaccion> getListaTransaccion() {
		return listaTransaccion;
	}

	public void setListaTransaccion(List<Transaccion> listaTransaccion) {
		this.listaTransaccion = listaTransaccion;
	}

	public Transaccion getNewTransaccion() {
		return newTransaccion;
	}

	public void setNewTransaccion(Transaccion newTransaccion) {
		this.newTransaccion = newTransaccion;
	}

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
		newTransaccion = new Transaccion();
		listaCuenta = new ArrayList<Cuenta>();
		listaTransaccion = new ArrayList<Transaccion>() ;
	}

	public String generarCuenta() {
		if (clienteB.getNewCliente().getCedula() == null) {
			System.out.println("no hya datos del usuario ");
		}
		System.out.println("Ingresa al generar cuenta");
		cuenta = on.generarNumeroDeCuenta();
		newCuenta.setNumeroCuenta(cuenta);
		System.out.println("generado =>" + cuenta);

		return cuenta;
	}

	public String doAperturaCuenta() {
		fechaActual = new Date();
		System.out.println("Obtienes fecha?  ==>" + "" + fechaActual);

		System.err.println("ENTRA A APERUTRA");
		try {
			listaCuenta.add(newCuenta);

			// System.out.println("LISTA DE CUENTA -->" + listaCuenta);
			if (tipoCuenta.equalsIgnoreCase("Ahorro")) {
				System.out.println(tipoCuenta);
				System.out.println("ENTRA AL IF PARA CREAR CUENTA");
				System.out.println("cuenta dentro if" + newCuenta.getNumeroCuenta());
				System.out.println("Cedula CLiente CUENTA" + clienteB.getNewCliente().getCedula());
				if (newCuenta.getSaldo() == 0.0 & newCuenta.getTipoCuenta() == null) {
					System.out.println("Error cedula nula cliente");
				} else {
					newCuenta.setCuenta_fk(clienteB.getNewCliente().getCedula());
				}
				newCuenta.setTipoCuenta("Ahorro");

				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN");
					newCuenta.setFechaApertura(fechaActual);
					newCuenta.setCuenta_fk(clienteB.getNewCliente().getCedula());
					// System.out.println(listaCuenta.get(0));
					// System.out.println("Clave fk cuenta"+clienteB.getNewCliente().);
					if (newCuenta.getSaldo() == 0.0 & newCuenta.getTipoCuenta() == null) {
						System.out.println("cuenta en blaco");
					} else {
						on.guardarCuentaDeAhorros(newCuenta);
					}
				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			} else if (tipoCuenta.equalsIgnoreCase("Credito")) {
				System.out.println("ENTRA AL IF PARA CREAR CLIENTE");
				// newCliente.setTiempoResidencia(tiempo);

				newCuenta.setTipoCuenta("Credito");
				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN credito");
					newCuenta.setFechaApertura(fechaActual);
					// System.out.println("Clave fk
					// cuenta"+clienteB.getNewCliente().getCuentaCliente());
					// cliente.setCuentaCliente();
					// System.err.println(cliente);
					// on.actaulizarCliente(cliente);
					if (newCuenta.getNumeroCuenta().isEmpty()
							&& newCuenta.getSaldo() == 0.0 & newCuenta.getTipoCuenta().isEmpty()) {
						System.out.println("cuenta en blaco");
					} else {
						on.guardarCuentaDeAhorros(newCuenta);
					}

				} catch (Exception e) {
					System.out.println("error guardar Cliente con correo");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println("error al guardar cuenta" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String validarIngreso() {

		if (newCuenta.getSaldo() < saldoAhorro) {
			System.out.println("Para la apertura de cuenta ahorro se necesita un valor mínimo de $5 dólares");
		} else {
			saldoAhorro = newCuenta.getSaldo();
			System.out.println(saldoAhorro);
		}

		if (newCuenta.getSaldo() < saldoCredito) {
			System.out.println("Para la apertura de cuenta crédito se necesita un valor mínimo de $500 dólares");
		} else {
			System.out.println("Se ha ingresado más");
			saldoCredito = newCuenta.getSaldo();
			System.out.println(saldoCredito);
		}
		return null;
	}

	public String validarTransaccion() {

		if (newTransaccion.getTipoTransaccion() == "Retiro" && newTransaccion.getMonto() > newCuenta.getSaldo()) {
			System.out.println("El monto a retirar es mayor que el saldo");

		} else {
			saldoNuevo = newCuenta.getSaldo() - newTransaccion.getMonto();
			System.out.println("saldo nuevo"+saldoNuevo);
			newCuenta.setSaldo(saldoNuevo);
			System.out.println("Puede realziar Retiro");
		}
		if (newTransaccion.getTipoTransaccion() == "Deposito") {
			System.out.println("Puede depositar a la cuenta");
			saldoNuevo = newCuenta.getSaldo() + newTransaccion.getMonto();
			newCuenta.setSaldo(saldoNuevo);
			System.out.println("saldo nuevo"+saldoNuevo);
		}
		return null;
	}

	public String obtenerEmpleado() {
		if (empleadoB.getNombres() == null) {
			System.out.println("No hay empleado");
		} else {
			nombrEmpleado = empleadoB.getNombres();

		}
		return null;

	}

	public String doRegistroTransaccion() {
		fechaActual = new Date();
		System.out.println("Obtienes fecha?  ==>" + "" + fechaActual);
		try {
			listaTransaccion.add(newTransaccion);
			System.out.println("liata tra"+listaTransaccion);
			System.err.println("ENTRA A TRANSACCION");
			// System.out.println("LISTA DE CUENTA -->" + listaCuenta);
			if (tipoCuenta == null) {
				System.err.println("Escoge tipo de cuenta");
			}
			if (tipoTransacción.equalsIgnoreCase("Retiro")) {
				System.out.println(tipoTransacción);
				System.out.println("ENTRA AL IF PARA CREAR TRANSACCION");
				System.out.println("cuenta dentro if" + newCuenta.getNumeroCuenta());
				System.out.println("Cedula CLiente CUENTA" + clienteB.getNewCliente().getCedula());
				cuentaObtenida = new String();
				cuentaObtenida = newCuenta.getNumeroCuenta();
				System.out.println("cuenta obte" + cuentaObtenida);

				if (newTransaccion.getMonto() == 0.0 & newTransaccion.getTipoTransaccion() == null) {
					System.out.println("Error cedula nula cliente");
				} else {
					newTransaccion.setTransaccion_fk(clienteB.getNewCliente().getCedula());

				}
				newTransaccion.setTipoTransaccion("Retiro");

				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN TRANSACC");
					newTransaccion.setFechaHora(fechaActual);
					newTransaccion.setTransaccion_fk(clienteB.getNewCliente().getCedula());

					System.out.println(listaTransaccion.get(0));
					// System.out.println("Clave fk cuenta"+clienteB.getNewCliente().);
					if (newTransaccion.getMonto() == 0.0 & newTransaccion.getTipoTransaccion() == null) {
						System.out.println("cuenta en blaco");
					} else {
						on.insertarTransaccion(newTransaccion);
					}
				} catch (Exception e) {
					System.out.println("error guardar Transaccion con correo");
					e.printStackTrace();
				}
			} else if (tipoTransacción.equalsIgnoreCase("Deposito")) {
				System.out.println("ENTRA AL IF PARA CREAR TRANSAC DEPOSTO");
				// newCliente.setTiempoResidencia(tiempo);

				newTransaccion.setTipoTransaccion("Deposito");
				try {
					System.out.println("DENTRO DEL BUSCAR CLIENTE BEAN DEPO");
					newTransaccion.setFechaHora(fechaActual);
					// System.out.println("Clave fk
					// cuenta"+clienteB.getNewCliente().getCuentaCliente());
					// cliente.setCuentaCliente();
					// System.err.println(cliente);
					// on.actaulizarCliente(cliente);
					if (newTransaccion.getMonto() == 0.0 & newTransaccion.getTipoTransaccion().isEmpty()) {
						System.out.println("cuenta en blaco");
					} else {
						on.insertarTransaccion(newTransaccion);

					}

				} catch (Exception e) {
					System.out.println("error guardar transaccion con correo");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println("error al guardar transaccion" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo para validar el Saldo de la Cuenta de Ahorro
	 * 
	 * @return El saldo disponible en la cuenta de ahorro de acuerdo a la
	 *         transaccion que se esta realizando
	 */
	/*
	 * public String valMonto() { try { if (cliente.getCedula() != null) { Cliente
	 * usuarioRegistrado = on.buscarCliente(cliente.getCedula()); if
	 * (usuarioRegistrado != null) { Cuenta cl = on.bus(cliente.getCedula()); String
	 * l = String.valueOf(cl.getSaldoCuentaDeAhorro()); if
	 * (tipoTransaccion.equalsIgnoreCase("retiro") && monto == null) { return l; }
	 * else if (tipoTransaccion.equalsIgnoreCase("retiro") &&
	 * cl.getSaldoCuentaDeAhorro() < monto) { String ms =
	 * "La cuenta no cuenta con el saldo suficiente, Su saldo es: " +
	 * cl.getSaldoCuentaDeAhorro(); return ms;
	 * 
	 * } else { return l; }
	 * 
	 * } }
	 * 
	 * } catch (Exception e) { // TODO: handle exception }
	 * 
	 * return " "; }
	 */

}
