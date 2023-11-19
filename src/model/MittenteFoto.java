package model;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

import main.ClientMain;

public class MittenteFoto implements Runnable {

	private boolean fotoMittenteStatoAttivo = true;

	@Override
	public void run() {
		while (fotoMittenteStatoAttivo) {
			try {
				ServerSocket serverSocket = new ServerSocket(ClientMain.CLIENT_PHOTO_SENDING_PORT);
				Socket sock = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
				out.flush();
				BufferedImage screenshot = new Robot()
						.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(screenshot, "jpg", out);

				sock.close();
				serverSocket.close();
			} catch (Exception e) {
			}
		}
	}

	public void setFotoMittenteStatoAttivo(boolean fotoMittenteStatoAttivo) {
		this.fotoMittenteStatoAttivo = fotoMittenteStatoAttivo;
	}

}