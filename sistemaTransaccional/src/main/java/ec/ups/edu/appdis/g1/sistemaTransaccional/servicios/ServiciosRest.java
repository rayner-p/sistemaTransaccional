package ec.ups.edu.appdis.g1.sistemaTransaccional.servicios;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cliente;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.Mensaje;
@Path("transacciones")
public class ServiciosRest {
	@Inject
	private GestionSistemLocal on;
	private Transaccion tran;
	private Cliente cli;
	private Cuenta c;
	private double saldoNuevo;
	private Date fechaA;
	
	public ServiciosRest() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Metodo a enviar para registrar las restricciones
	 * @param transac
	 * @return
	 * @throws Exception 
	 */
	@POST
	@Path("/registroTransaccion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  void  realizarTransaccion(@QueryParam("cedulaCliente") String cedula,
									  @QueryParam("cuenta") String cuenta, 
									  @QueryParam("transaccion") String tipoTransaccion,
									  @QueryParam("monto") double monto) throws Exception {	
		tran = new Transaccion();
		
		try {
			fechaA = new Date();
			System.out.println("fecha Actual"+fechaA);
			System.out.println("entras?");
		
			tran.setTipoTransaccion(tipoTransaccion);
			tran.setMonto(monto);
			tran.setFechaHora(fechaA);
			c = new Cuenta();
			c = on.obtenerCuentaPorNumero(cuenta);
			tran.setCuenta(c);
			System.out.println("que trae la cuenta" + c);
			on.insertarTransaccion(tran);
			
			System.out.println("transaccion creada"+" "
			+tran);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}
	@GET
	@Path("/listaTransaccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaccion> obtenerTransacciones() throws Exception{
		try {
		return on.obtenerTransaccion();
		}catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}
	@GET
	@Path("/cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente obtenerTransacciones(@QueryParam("cedula")String cedula) throws Exception{
		try {
		return on.buscarCliente(cedula);
		}catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}
	
	@GET
	@Path("/cuenta")
	@Produces(MediaType.APPLICATION_JSON)
	public Cuenta obtenerCuenta(@QueryParam("cuenta")String cuenta) throws Exception{
		try {
		return on.obtenerCuentaPorNumero(cuenta);
		}catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}
	
	@GET
	@Path("/clienteCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	public Cuenta obtenerCuentaCliente(@QueryParam("cedula")String cedula) throws Exception{
		try {
		return on.buscarCuentaD(cedula);
		}catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}

	@PUT
	@Path("/actualizarCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	public String actualziarC(@QueryParam("cuenta")String cuenta, @QueryParam("saldo")double saldo) throws Exception{
		try {
		return on.actaulizarCuentaCliente(cuenta, saldo);
		}catch (Exception e) {
			throw new Exception("Se ah producido un error"+e.getMessage());

		}
	}	
}
