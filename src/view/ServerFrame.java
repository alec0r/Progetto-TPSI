package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Controller;
import model.ClientDestinatario;
import model.Server;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tIpField;
	private JList<String> clientList = null;
	public ClientDisplay clientDisplay = null;
	private Server server;
	public JLabel countingPhotos = null;
	private JLabel lblNewLabel;
	public JButton btnRefresh = new JButton("INDIRIZZO IP");
	public JButton btnStart = new JButton("ON");;
	public JButton btnStop = new JButton("OFF");
	public JButton btnWatch = new JButton("Guarda");
	public JButton btnChiudi = new JButton("Chiudi");
	

	
	
	public ServerFrame() {

		super();
		
        server = new Server();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame to the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1920, 868);

        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel optionPanel = new JPanel();
        optionPanel.setBounds(5, 5, 197, 815); // Adjusted height
        contentPane.add(optionPanel);
        optionPanel.setLayout(null);


		tIpField = new JTextField();
		tIpField.setBounds(10, 87, 177, 35);
		optionPanel.add(tIpField);
		tIpField.setEditable(false);
		tIpField.setHorizontalAlignment(SwingConstants.CENTER);
		tIpField.setText("127.0.0.1");
		tIpField.setFont(new Font("David", Font.BOLD, 16));
		tIpField.setColumns(10);

		//JButton btnRefresh = new JButton("INDIRIZZO IP");
		btnRefresh.setBounds(10, 49, 177, 35);
		optionPanel.add(btnRefresh);
		//btnRefresh.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent arg0) {
				//tIpField.setText(Server.getIndirizzoIpServer());
			//}
		//});
		
		btnRefresh.setFont(new Font("David", Font.BOLD, 16));

		//JButton btnStart = new JButton("ON");
		btnStart.setForeground(Color.GREEN);
		btnStart.setBounds(10, 133, 84, 36);
		optionPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.startServer();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));

		//JButton btnStop = new JButton("OFF");
		btnStop.setForeground(Color.RED);
		btnStop.setBounds(104, 133, 83, 36);
		optionPanel.add(btnStop);
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 13));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 275, 179, 391);
		optionPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		clientList = new JList<>();
		clientList.setModel(new DefaultListModel<String>());
		scrollPane.setViewportView(clientList);

		JButton btnNewButton_2 = new JButton("AGGIORNA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshClientsList();
			}
		});
		panel_2.add(btnNewButton_2, BorderLayout.SOUTH);

		//JButton btnWatch = new JButton("Guarda");
		/*btnWatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientDisplay.startDisplay(server.getClienteListaClienti(clientList.getSelectedValue()));
			}
		});*/
		
		btnWatch.setForeground(Color.GREEN);
		btnWatch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnWatch.setBounds(10, 677, 84, 36);
		optionPanel.add(btnWatch);

		//JButton btnChiudi = new JButton("Chiudi");
		//btnChiudi.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent arg0) {
			//	clientDisplay.stopDisplay();
			//}
		//});
		btnChiudi.setForeground(Color.RED);
		btnChiudi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChiudi.setBounds(103, 677, 84, 36);
		optionPanel.add(btnChiudi);
		
		lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(55, 11, 84, 25);
		optionPanel.add(lblNewLabel);
		
		JLabel lblUtenti = new JLabel("UTENTI");
		lblUtenti.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtenti.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUtenti.setBounds(55, 239, 84, 25);
		optionPanel.add(lblUtenti);

		clientDisplay = new ClientDisplay();
		clientDisplay.setBounds(212, 5, 1682, 815);
		contentPane.add(clientDisplay);

		setLocationRelativeTo(null);
	
	}
		
		public JList<String> getClientList() {
		return clientList;
	}

	public void setClientList(JList<String> clientList) {
		this.clientList = clientList;
	}
	
		public void registraEvento(Controller controller) {
		
		this.btnRefresh.addActionListener(controller);
		this.btnStart.addActionListener(controller);
		this.btnStop.addActionListener(controller);
		this.btnWatch.addActionListener(controller);
		this.btnChiudi.addActionListener(controller);
		
		}

	public ClientDisplay getClientDisplay() {
			return clientDisplay;
		}

		public void setClientDisplay(ClientDisplay clientDisplay) {
			this.clientDisplay = clientDisplay;
		}

	public JTextField gettIpField() {
		return tIpField;
	}

	public void settIpField(JTextField tIpField) {
		this.tIpField = tIpField;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public void setBtnStop(JButton btnStop) {
		this.btnStop = btnStop;
	}

	public JButton getBtnWatch() {
		return btnWatch;
	}

	public void setBtnWatch(JButton btnWatch) {
		this.btnWatch = btnWatch;
	}

	public JButton getBtnChiudi() {
		return btnChiudi;
	}

	public void setBtnChiudi(JButton btnChiudi) {
		this.btnChiudi = btnChiudi;
	}

	public void addClients(String name) {
		DefaultListModel<String> dlm = (DefaultListModel<String>) clientList.getModel();
		dlm.addElement(name);
		clientList.setModel(dlm);
	}

	public void refreshClientsList() {
		try {
			clientList.setModel(new DefaultListModel<String>());
			DefaultListModel<String> dml = new DefaultListModel<>();
			ArrayList<ClientDestinatario> cc = server.getClientAttivi();
			for (ClientDestinatario c : cc) {
				dml.addElement(c.getNome());
			}
			clientList.setModel(dml);
		} catch (Exception e) {
		}

	}
	
	public String nomeSelezionato() {
			String nome = clientList.getSelectedValue();
		return nome;
	}
	

}
