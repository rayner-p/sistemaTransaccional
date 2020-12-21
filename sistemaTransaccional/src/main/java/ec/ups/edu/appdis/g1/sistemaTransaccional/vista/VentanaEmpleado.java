package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEmpleado {

	private JFrame frame;
	private JTextField txtTipoDocumento;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private JTextField txtUsuario;
	private JTextField txtContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpleado window = new VentanaEmpleado();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTIpoDocumento = new JLabel("Cédula");
		lblTIpoDocumento.setBounds(16, 61, 130, 16);
		panel.add(lblTIpoDocumento);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(16, 101, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Apellidos");
		lblCorreo.setBounds(16, 147, 61, 16);
		panel.add(lblCorreo);
		
		txtTipoDocumento = new JTextField();
		txtTipoDocumento.setColumns(10);
		txtTipoDocumento.setBounds(179, 56, 182, 26);
		panel.add(txtTipoDocumento);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(179, 96, 182, 26);
		panel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(179, 142, 182, 26);
		panel.add(txtApellidos);
		
		JButton btnGuardar = new JButton("REGISTRAR");
		btnGuardar.setBackground(Color.ORANGE);
		btnGuardar.setBounds(299, 464, 145, 29);
		panel.add(btnGuardar);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 26, -100, 16);
		panel.add(textPane);
		
		JLabel lblCrearUsuarioAdministrativo = new JLabel("Crear Usuario Administrativo");
		lblCrearUsuarioAdministrativo.setEnabled(false);
		lblCrearUsuarioAdministrativo.setBounds(138, 6, 182, 16);
		panel.add(lblCrearUsuarioAdministrativo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(16, 192, 154, 16);
		panel.add(lblFechaDeNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(179, 187, 182, 26);
		panel.add(txtFechaNacimiento);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(16, 237, 154, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(179, 232, 182, 26);
		panel.add(txtDireccion);
		
		JLabel lblCorreo_1 = new JLabel("Correo");
		lblCorreo_1.setBounds(16, 276, 154, 16);
		panel.add(lblCorreo_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(179, 271, 182, 26);
		panel.add(txtCorreo);
		
		JLabel lblCorreo_1_1 = new JLabel("Teléfono");
		lblCorreo_1_1.setBounds(16, 319, 154, 16);
		panel.add(lblCorreo_1_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(179, 314, 182, 26);
		panel.add(txtTelefono);
		
		JLabel lblCorreo_1_2 = new JLabel("Rol");
		lblCorreo_1_2.setBounds(16, 356, 154, 16);
		panel.add(lblCorreo_1_2);
		
		JComboBox cmbRol = new JComboBox();
		cmbRol.setBackground(new Color(192, 192, 192));
		cmbRol.setBounds(179, 352, 172, 27);
		panel.add(cmbRol);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(16, 395, 307, 16);
		panel.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(16, 436, 307, 16);
		panel.add(lblContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setEditable(false);
		txtUsuario.setBounds(179, 390, 144, 26);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setEnabled(false);
		txtContrasenia.setEditable(false);
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(179, 431, 144, 26);
		panel.add(txtContrasenia);
	}
}
