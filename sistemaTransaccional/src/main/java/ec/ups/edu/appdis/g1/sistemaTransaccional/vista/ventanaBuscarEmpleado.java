package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.Color;

public class ventanaBuscarEmpleado {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaBuscarEmpleado window = new ventanaBuscarEmpleado();
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
	public ventanaBuscarEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 427, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar Usuario Administrativo");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(140, 6, 201, 16);
		panel.add(lblNewLabel);
		
		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setBounds(46, 31, 61, 16);
		panel.add(lblCedula);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(168, 72, 61, 16);
		panel.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(168, 117, 61, 16);
		panel.add(lblApellidos);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(46, 168, 123, 16);
		panel.add(lblFechaNacimiento);
		
		JLabel lblDirección = new JLabel("Dirección");
		lblDirección.setBounds(46, 204, 61, 16);
		panel.add(lblDirección);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(46, 69, 101, 64);
		panel.add(editorPane);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(46, 244, 61, 16);
		panel.add(lblCorreo);
		
		JLabel lblTeléfono = new JLabel("Teléfono");
		lblTeléfono.setBounds(46, 279, 61, 16);
		panel.add(lblTeléfono);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(46, 312, 61, 16);
		panel.add(lblRol);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setEditable(false);
		editorPane_1.setEnabled(false);
		editorPane_1.setBounds(241, 72, 152, 16);
		panel.add(editorPane_1);
		
		JEditorPane editorPane_1_1 = new JEditorPane();
		editorPane_1_1.setEnabled(false);
		editorPane_1_1.setEditable(false);
		editorPane_1_1.setBounds(241, 117, 152, 16);
		panel.add(editorPane_1_1);
		
		JEditorPane editorPane_1_2 = new JEditorPane();
		editorPane_1_2.setEnabled(false);
		editorPane_1_2.setEditable(false);
		editorPane_1_2.setBounds(189, 168, 152, 16);
		panel.add(editorPane_1_2);
		
		JEditorPane editorPane_1_3 = new JEditorPane();
		editorPane_1_3.setEnabled(false);
		editorPane_1_3.setEditable(false);
		editorPane_1_3.setBounds(189, 204, 152, 16);
		panel.add(editorPane_1_3);
		
		JEditorPane editorPane_1_4 = new JEditorPane();
		editorPane_1_4.setEnabled(false);
		editorPane_1_4.setEditable(false);
		editorPane_1_4.setBounds(189, 244, 152, 16);
		panel.add(editorPane_1_4);
		
		JEditorPane editorPane_1_5 = new JEditorPane();
		editorPane_1_5.setEnabled(false);
		editorPane_1_5.setEditable(false);
		editorPane_1_5.setBounds(189, 279, 152, 16);
		panel.add(editorPane_1_5);
		
		JEditorPane editorPane_1_6 = new JEditorPane();
		editorPane_1_6.setEnabled(false);
		editorPane_1_6.setEditable(false);
		editorPane_1_6.setBounds(189, 312, 152, 16);
		panel.add(editorPane_1_6);
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(276, 26, 117, 29);
		panel.add(btnNewButton);
		
		JEditorPane editorPane_1_7 = new JEditorPane();
		editorPane_1_7.setBounds(112, 31, 152, 16);
		panel.add(editorPane_1_7);
	}
	
	
}
