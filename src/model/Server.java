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
	private ArrayList<Client> clients;

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

	private void addClient(Client client) {
		clients.add(client);
	}

	public static String getServerIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "NOT FOUND";
		}
	}

	public Client getClientFromClientsList(String name) {
		if (clients == null)
			return null;

		for (Client cc : clients) {
			if (cc.getName().equals(name)) {
				return cc;
			}
		}
		return null;
	}

	public ArrayList<Client> getAliveClientsOnly() {
		Iterator<Client> iterator = clients.iterator();
		while (iterator.hasNext()) {
			try {
				Client c = iterator.next();
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
		private boolean cholbeKina;

		@Override
		public void run() {
			try {
				cholbeKina = true;
				serverSocket = new ServerSocket(ServerMain.SERVER_PORT);
				while (cholbeKina) {
					Socket socket = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					String name = (String) in.readObject();

					boolean alreadyHave = false;
					for (Client client : clients) {
						String s = socket.getInetAddress().toString();
						s = s.substring(1, s.length());
						if (client.getIp().equals(s)) {
							alreadyHave = true;
						}
					}

					if (alreadyHave == false) {
						addClient(new Client(socket, name));
						ServerMain.serverFrame.addClients(name);
					} else {
						System.out.println("second connection");
					}
					socket.close();
				}
			} catch (Exception e) {

			}
		}

		public void setcholbeKina(boolean tof) {
			cholbeKina = tof;
		}

	}
}
