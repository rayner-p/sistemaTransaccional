package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

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

	private Date fechaActual;
	private String cuenta;
	private String tipoCuenta;
	private String tipoTransacción;
	private double saldoAhorro = 5.0;
	private double saldoCredito = 500.00;
	private String nombrEmpleado;
	// private Cliente cliente;
	private double saldoNuevo;
	private String cuentaObtenida;

	public String getCuentaObtenida() {
		return cuentaObtenida;
	}

	public void setCuentaObtenida(String cuentaObtenida) {
		this.cuentaObtenida = cuentaObtenida;
	}

	private List<Cuenta> listaCuenta;

	private Cuenta newCuenta;

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

	@PostConstruct
	public void init() {
		newCuenta = new Cuenta();
		listaCuenta = new ArrayList<Cuenta>();
	}

	/**
	 * metodo que genera un número de cuenta para los clientes automáticamente
	 * 
	 * @return cuenta con los datos generados
	 */

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

	public boolean validarDatosingresados() {
		boolean bandera = true;

		if (newCuenta.getSaldo() == 0.0 & newCuenta.getNumeroCuenta().isEmpty()) {
			bandera = false;
		} else {

		}
		return bandera;
	}

	/**
	 * Metodo que me permite validar el ingreso correcto de los valores para crear
	 * una cuenta
	 * 
	 * @return
	 */

	public boolean validarIngreso() {
		boolean bandera = true;

		if (newCuenta.getSaldo() < saldoAhorro) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Para la apertura de cuenta de ahorro el saldo minimo es de $5");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			bandera = false;
			System.out.println("Para la apertura de cuenta ahorro se necesita un valor mínimo de $5 dólares");
		} else {
			saldoAhorro = newCuenta.getSaldo();
			System.out.println(saldoAhorro);
		}

		if (newCuenta.getSaldo() < saldoCredito) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
					"Para la apertura de cuenta de crédito el saldo minimo es de $500");

			PrimeFaces.current().dialog().showMessageDynamic(message);
			bandera = false;
			System.out.println("Para la apertura de cuenta crédito se necesita un valor mínimo de $500 dólares");
		} else {
			System.out.println("Se ha ingresado más");
			saldoCredito = newCuenta.getSaldo();
			System.out.println(saldoCredito);
		}
		return bandera;
	}

	/**
	 * metodo que me permite registrar una nueva cuenta para el cliente
	 * 
	 * @return
	 */
	public String doAperturaCuenta() {
		fechaActual = new Date();
		try {
			listaCuenta.add(newCuenta);
			// ystem.out.println("LISTA DE CUENTA -->" + listaCuenta);
			if (tipoCuenta.equalsIgnoreCase("Ahorro")) {
				System.out.println(tipoCuenta);
				System.out.println("ENTRA AL IF PARA CREAR CUENTA");
				// System.out.println("cuenta dentro if" + newCuenta.getNumeroCuenta());
				// System.out.println("Cedula CLiente CUENTA" +
				// clienteB.getNewCliente().getCedula());
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
					} else if (validarDatosingresados() == false) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Ingrese todos los datos"));

					} else if (validarIngreso() == false) {
						System.err.println("valores menores a lo especificado");
					} else {
						on.guardarCuentaDeAhorros(newCuenta);
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Cuenta creada correctamente");

						PrimeFaces.current().dialog().showMessageDynamic(message);

					}
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Info", "Error en la apertura de cuenta de ahorro"));
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
					} else if (validarDatosingresados() == false) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Message",
								"Ingrese todos los datos");

						PrimeFaces.current().dialog().showMessageDynamic(message);

					} else if (validarIngreso() == false) {
						
						System.err.println("valores menores a lo especificado");
					} else {
						on.guardarCuentaDeAhorros(newCuenta);
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message",
								"Cuenta creada correctamente");

						PrimeFaces.current().dialog().showMessageDynamic(message);
					}

				} catch (Exception e) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message",
							"No se pudo crear la cuenta correctamente");

					PrimeFaces.current().dialog().showMessageDynamic(message);
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Error en la creación de cuentas"));
			System.out.println("error al guardar cuenta" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
