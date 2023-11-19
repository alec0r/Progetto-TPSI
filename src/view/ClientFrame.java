package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Client;
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

	private JLabel connectionStatus = null;

	public ClientFrame() {
		setTitle("CLIENT");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 258, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SET IP ADDRESS");
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
		tIP.setText("192.168.137.1");
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

		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.stopService();
			}
		});
		btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDisconnect.setBounds(10, 282, 230, 37);
		contentPane.add(btnDisconnect);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String serverIp = tIP.getText().trim();
				String myName = tNameField.getText();

				if (myName.length() == 0) {
					JOptionPane.showMessageDialog(null, "Enter Your Name First!!!");
					return;
				}
				if (serverIp.length() == 0) {
					JOptionPane.showMessageDialog(null, "Enter IP address first!!!");
					return;
				}

				Client.initialize();
				if (Client.sendMessage(serverIp, myName)) {
					Client.startService();
					connectionStatus.setText("you are Connected");
				} else {
					connectionStatus.setText("Can not connect!!");
				}
			}
		});
		btnConnect.setBounds(10, 237, 230, 37);
		contentPane.add(btnConnect);

		connectionStatus = new JLabel("YOU ARE NOT CONNTECTED");
		connectionStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		connectionStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		connectionStatus.setBounds(10, 11, 230, 19);
		contentPane.add(connectionStatus);

		JLabel lblSetYourName = new JLabel("SET YOUR NAME");
		lblSetYourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetYourName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSetYourName.setBounds(10, 138, 230, 31);
		contentPane.add(lblSetYourName);

		tNameField = new JTextField();
		tNameField.setFont(new Font("Dialog", Font.PLAIN, 15));
		tNameField.setText("GOBINDA");
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

	public static void initialize() {
		clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
	}
}
