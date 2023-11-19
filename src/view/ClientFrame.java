package view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Controller;
import model.ClientMittente;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame {

	private static ClientFrame clientFrame;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tIP;
	private JTextField tNameField;
	private JButton btnNewButton_1 = new JButton("CLR");
	private JLabel connectionStatus = null;

	public ClientFrame() {
		setTitle("CLIENT");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 267, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("INDIRIZZO IP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 51, 230, 31);
		contentPane.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 82, 230, 31);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		tIP = new JTextField();
		tIP.setFont(new Font("Dialog", Font.PLAIN, 15));
		tIP.setText("127.0.0.1");
		panel_1.add(tIP);
		tIP.setColumns(10);

		JButton btnNewButton_1 = new JButton("CLR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tIP.setText("");
			}
		});
		
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(btnNewButton_1);

		JButton btnDisconnect = new JButton("Disconnettiti");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDisconnect.setBounds(10, 282, 230, 37);
		contentPane.add(btnDisconnect);

		JButton btnConnect = new JButton("Connettiti");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String serverIp = tIP.getText().trim();
				String myName = tNameField.getText();

				if (serverIp.length() == 0 && myName.length() == 0) {
					JOptionPane.showMessageDialog(null, "Inserisci sia il nome che l'indirizzo ip!!!");
					return;
				}
				if (myName.length() == 0) {
					JOptionPane.showMessageDialog(null, "Inserisci il tuo nome!!!");
					return;
				}
				if (serverIp.length() == 0) {
					JOptionPane.showMessageDialog(null, "Inseriresi l'indirizzo ip!!!");
					return;
				}
				
				

				ClientMittente.inizializzazione();
				if (ClientMittente.mandaMessaggio(serverIp, myName)) {
					ClientMittente.iniziaServizio();
					connectionStatus.setText("Sei connesso");
				} else {
					connectionStatus.setText("Non ti sei connesso!!");
				}
			}
		});
		btnConnect.setBounds(10, 237, 230, 37);
		contentPane.add(btnConnect);

		connectionStatus = new JLabel("Non sei connesso");
		connectionStatus.setHorizontalAlignment(SwingConstants.CENTER);
		connectionStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		connectionStatus.setBounds(10, 11, 230, 19);
		contentPane.add(connectionStatus);

		JLabel lblSetYourName = new JLabel("INSERIRE IL NOME");
		lblSetYourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetYourName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSetYourName.setBounds(10, 138, 230, 31);
		contentPane.add(lblSetYourName);

		tNameField = new JTextField();
		tNameField.setFont(new Font("Dialog", Font.PLAIN, 15));
		tNameField.setText("ROSSI");
		tNameField.setColumns(10);
		tNameField.setBounds(10, 170, 164, 31);
		contentPane.add(tNameField);

		JButton btnClr_1 = new JButton("CLR");
		btnClr_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tNameField.setText("");
			}
		});
		btnClr_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnClr_1.setBounds(174, 169, 66, 31);
		contentPane.add(btnClr_1);

		setLocationRelativeTo(null);
	}

	public JTextField gettIP() {
		return tIP;
	}

	public void settIP(JTextField tIP) {
		this.tIP = tIP;
	}

	public JTextField gettNameField() {
		return tNameField;
	}

	public void settNameField(JTextField tNameField) {
		this.tNameField = tNameField;
	}



	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

	public static void initialize() {
		clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
	}

	
}
