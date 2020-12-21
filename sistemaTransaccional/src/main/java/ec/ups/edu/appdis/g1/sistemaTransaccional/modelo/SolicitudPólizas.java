package ec.ups.edu.appdis.g1.sistemaTransaccional.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="solicitudes")
public class SolicitudPólizas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Lob 
	@Column(length=16777216)
    private byte[] archivoCedula; 
	@Lob 
	@Column(length=16777216)
    private byte[] archivoPlanillaServicios; 
}
