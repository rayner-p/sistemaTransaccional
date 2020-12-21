package ec.ups.edu.appdis.g1.sistemaTransaccional.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;

public class ventanaParametrizarPoliza {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField tasaInteres;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaParametrizarPoliza window = new ventanaParametrizarPoliza();
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
	public ventanaParametrizarPoliza() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 464, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 153));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblParametrizar = new JLabel("Parametrizar");
		lblParametrizar.setEnabled(false);
		lblParametrizar.setBounds(200, 20, 94, 16);
		panel.add(lblParametrizar);
		
		JLabel lblTasaDeInteres = new JLabel("TASA DE INTERES");
		lblTasaDeInteres.setBounds(33, 149, 123, 16);
		panel.add(lblTasaDeInteres);
		
		JLabel lblTiempo_1 = new JLabel("TIEMPO DIAS");
		lblTiempo_1.setBounds(33, 77, 94, 16);
		panel.add(lblTiempo_1);
		
		JLabel lblMinimo = new JLabel("MIN");
		lblMinimo.setBounds(126, 108, 40, 16);
		panel.add(lblMinimo);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(161, 103, 53, 26);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(288, 103, 53, 26);
		panel.add(textField_1);
		
		JLabel lblMax = new JLabel("MAX");
		lblMax.setBounds(247, 108, 44, 16);
		panel.add(lblMax);
		
		JButton btnAniador = new JButton("AÑADIR");
		btnAniador.setBackground(Color.ORANGE);
		btnAniador.setBounds(288, 144, 85, 29);
		panel.add(btnAniador);
		
		tasaInteres = new JTextField();
		tasaInteres.setColumns(10);
		tasaInteres.setBounds(166, 144, 53, 26);
		panel.add(tasaInteres);
		
		table = new JTable();
		table.setBounds(111, 228, 235, 104);
		panel.add(table);
	}
}
