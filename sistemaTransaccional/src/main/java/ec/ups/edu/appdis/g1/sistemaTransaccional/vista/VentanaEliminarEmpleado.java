package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ec.ups.edu.appdis.g1.sistemaTransaccional.modelo.Empleado;
import ec.ups.edu.appdis.g1.sistemaTransaccional.negocio.GestionSistemRemoto;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class VentanaEliminarEmpleado {

	private JFrame frame;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private File fichero; 
	/**
	 * Launch the application.
	 */
	private GestionSistemRemoto remoto;
	JComboBox cmbRol = new JComboBox();
	JPanel panel = new JPanel();
	static VentanaEliminarEmpleado window ;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new VentanaEliminarEmpleado();
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
	public VentanaEliminarEmpleado() {
	
	
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel.setBackground(new Color(153, 204, 153));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTIpoDocumento = new JLabel("Cédula");
		lblTIpoDocumento.setBounds(66, 46, 80, 16);
		panel.add(lblTIpoDocumento);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(66, 114, 61, 16);
		panel.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Apellidos");
		lblCorreo.setBounds(66, 147, 61, 16);
		panel.add(lblCorreo);
		
		txtCedula = new JTextField();
		txtCedula.setEnabled(false);
		txtCedula.setEditable(false);
		txtCedula.setBounds(148, 41, 182, 26);
		txtCedula.setColumns(10);
		panel.add(txtCedula);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(147, 109, 182, 26);
		txtNombre.setColumns(10);
		panel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setEnabled(false);
		txtApellidos.setBounds(147, 142, 182, 26);
		txtApellidos.setColumns(10);
		panel.add(txtApellidos);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(315, 392, 145, 29);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBackground(new Color(255, 200, 0));
		panel.add(btnEliminar);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 26, -100, 16);
		panel.add(textPane);
		
		JLabel lblBuscarUsuarioAdministrativo = new JLabel("Eliminar Usuario Administrativo");
		lblBuscarUsuarioAdministrativo.setBounds(158, 6, 203, 16);
		lblBuscarUsuarioAdministrativo.setEnabled(false);
		panel.add(lblBuscarUsuarioAdministrativo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaDeNacimiento.setBounds(66, 185, 154, 16);
		panel.add(lblFechaDeNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setEnabled(false);
		txtFechaNacimiento.setBounds(229, 180, 182, 26);
		txtFechaNacimiento.setColumns(10);
		panel.add(txtFechaNacimiento);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(66, 230, 154, 16);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(229, 225, 182, 26);
		txtDireccion.setColumns(10);
		panel.add(txtDireccion);
		
		JLabel lblCorreo_1 = new JLabel("Correo");
		lblCorreo_1.setBounds(66, 264, 154, 16);
		panel.add(lblCorreo_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setEnabled(false);
		txtCorreo.setBounds(229, 259, 182, 26);
		txtCorreo.setColumns(10);
		panel.add(txtCorreo);
		
		JLabel lblCorreo_1_1 = new JLabel("Teléfono");
		lblCorreo_1_1.setBounds(66, 307, 154, 16);
		panel.add(lblCorreo_1_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setBounds(229, 302, 182, 26);
		txtTelefono.setColumns(10);
		panel.add(txtTelefono);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(66, 344, 154, 16);
		panel.add(lblRol);
		cmbRol.setBounds(229, 339, 172, 27);
		
		
		cmbRol.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Cajero", "Asistente Captaciones"}));
		cmbRol.setEditable(true);
		cmbRol.setSelectedIndex(0);
		cmbRol.setBackground(new Color(192, 192, 192));
		panel.add(cmbRol);
		
		final JFileChooser cargar = new JFileChooser();
		cargar.setApproveButtonText("");
		cargar.setToolTipText("");
		cargar.setDialogTitle("Escoger Archivo");
		cargar.setBackground(Color.DARK_GRAY);
		cargar.setBounds(29, 243, 145, 1);
		panel.add(cargar);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBackground(Color.ORANGE);
		btnBuscar.setBounds(328, 41, 145, 29);
		panel.add(btnBuscar);
		
	}
}
