package model;

import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.imageio.ImageIO;

import main.ServerMain;

public class ClientDestinatario {

	private String nome;
	private String ip;

	public ClientDestinatario(Socket socket, String nome) {
		this.nome = nome;
		String s = socket.getInetAddress().toString();
		this.ip = s.substring(1, s.length());
	}

	public String getNome() {
		return nome;
	}

	public String getIp() {
		return ip;
	}

	public BufferedImage getRemoteFoto() {
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
