package model;

import java.io.ObjectOutputStream;
import java.net.Socket;

import main.ClientMain;

public class ClientMittente {

	private static ClientMittente client;

	private MittenteFoto fotoMittente;
	private ConnessioneAttiva connessioneAttiva;

	public static void inizializzazione() {
		client = new ClientMittente();
		client.fotoMittente = new MittenteFoto();
		client.connessioneAttiva = new ConnessioneAttiva();
	}

	public static void iniziaServizio() {
		if (client == null)
			inizializzazione();
		new Thread(client.fotoMittente).start();
		new Thread(client.connessioneAttiva).start();
	}

	public static void fermaServizio() {
		if (client == null)
			return;
		client = null;
	}

	public static boolean mandaMessaggio(String serverIp, String messaggio) {
		try {
			Socket socket = new Socket(serverIp, ClientMain.SERVER_PORT);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			out.writeObject(messaggio);
			socket.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}