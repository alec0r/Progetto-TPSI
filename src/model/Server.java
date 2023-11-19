package model;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import main.ServerMain;

public class Server {

	private ServerLife serverLife;
	private ArrayList<ClientDestinatario> clients;

	public Server() {
		serverLife = new ServerLife();
		clients = new ArrayList<>();
	}

	public void startServer() {
		new Thread(serverLife).start();
	}

	public void stopServer() {
		serverLife.setcholbeKina(false);
	}

	private void aggiungiClient(ClientDestinatario client) {
		clients.add(client);
	}

	public static String getIndirizzoIpServer() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "NON TROVATO";
		}
	}

	public ClientDestinatario getClienteListaClienti(String name) {//prende il cliente dalla lista clienti
		
		if (clients == null)
			return null;

		for (ClientDestinatario cc : clients) {
			if (cc.getNome().equals(name)) {
				return cc;
			}
		}
		return null;
	}

	public ArrayList<ClientDestinatario> getClientAttivi() {//prende solamente i client attivi
		Iterator<ClientDestinatario> iterator = clients.iterator();
		while (iterator.hasNext()) {
			try {
				ClientDestinatario c = iterator.next();
				Socket socket = new Socket(c.getIp(), ServerMain.CLIENT_ALIVE_PORT);
				socket.close();
			} catch (Exception e) {
				iterator.remove();
			}
		}
		return clients;
	}

	class ServerLife implements Runnable {

		private ServerSocket serverSocket;
		private boolean flag;

		@Override
		public void run() {
			try {
				flag = true;
				serverSocket = new ServerSocket(ServerMain.SERVER_PORT);
				while (flag) {
					Socket socket = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					String nome = (String) in.readObject();

					boolean presente = false; //se c'è già (alreaadyHave)
					for (ClientDestinatario client : clients) {
						String s = socket.getInetAddress().toString();
						s = s.substring(1, s.length());
						if (client.getIp().equals(s)) {
							presente = true;
						}
					}

					if (presente == false) {
						aggiungiClient(new ClientDestinatario(socket, nome));
						ServerMain.serverFrame.addClients(nome);
					} else {
						System.out.println("second connection");
					}
					socket.close();
				}
			} catch (Exception e) {

			}
		}

		public void setcholbeKina(boolean tof) {
			flag = tof;
		}

	}
}
