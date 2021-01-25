package ec.ups.edu.appdis.g1.sistemaTransaccional.servicios;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Transaccion;

public class ClientePOJO {
	public String cedula;
	public String nombres;
	public String apellidos;
	public String correo;
	public String direccion;
	public String telefono;
	public Cuenta cuenta[];
	public Transaccion transaccion[];
	
	/*@JsonbTransient
	public boolean legendary = true;

	public ClientePOJO() {

    }

	@JsonbCreator
    public ClientePOJO(
      @JsonbProperty("name") String name,
      @JsonbProperty("albums") Album albums[]) {

      this.name = name;
      this.albums = albums;
    }

	@Override
	public String toString() {
		return name + " wrote " + albums.length + " albums";
	}*/
}
