package control;

import java.io.ObjectOutputStream;
import java.net.Socket;

import main.ClientMain;
import model.AliveConnection;
import model.PhotoSender;

public class Client {

	private static Client client;

	private PhotoSender photoSender;
	private AliveConnection aliveConnection;

	public static void initialize() {
		client = new Client();
		client.photoSender = new PhotoSender();
		client.aliveConnection = new AliveConnection();
	}

	public static void startService() {
		if (client == null)
			initialize();
		new Thread(client.photoSender).start();
		new Thread(client.aliveConnection).start();
	}

	public static void stopService() {
		if (client == null)
			return;
		client = null;
	}

	public static boolean sendMessage(String serverIp, String message) {
		try {
			Socket socket = new Socket(serverIp, ClientMain.SERVER_PORT);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			out.writeObject(message);
			socket.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}