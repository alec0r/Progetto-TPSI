package model;

import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

import main.ServerMain;

public class Client {

	private String name;
	private String ip;

	public Client(Socket socket, String name) {
		this.name = name;
		String s = socket.getInetAddress().toString();
		this.ip = s.substring(1, s.length());
	}

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public BufferedImage getRemotePhoto() {
		try {
			Socket socket = new Socket(ip, ServerMain.CLIENT_PHOTO_SENDING_PORT);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			BufferedImage screenshot = ImageIO.read(in);
			socket.close();
			return screenshot;
		} catch (Exception ex) {
		}
		return null;
	}

}
