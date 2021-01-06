package ec.ups.edu.appdis.g1.sistemaTransaccional.servicios;



import javax.jws.WebMethod;
import javax.jws.WebService;


import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemLocal;

@WebService
public class ClienteServicioSOAP {
	private GestionSistemLocal on;
	private Transaccion tran;
	@WebMethod
	public  String  realizarTransaccion(Transaccion transac) {
		try {
			on.insertarTransaccion(transac);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "insertado transaccion";
	}
	
	public String prueba (String pru) {
		System.out.println("Esto es una prueba" +pru);
		return "";
	}
}
