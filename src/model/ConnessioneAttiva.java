package model;

import java.net.ServerSocket;
import java.net.Socket;

import main.ClientMain;

public class ConnessioneAttiva implements Runnable {

	private boolean statoAttivo = true;

	@Override
	public void run() {

		try {
			ServerSocket serverSocket = new ServerSocket(ClientMain.CLIENT_ALIVE_PORT);
			while (statoAttivo) {
				Socket sock = serverSocket.accept();
				sock.close();
			}
			serverSocket.close();
		} catch (Exception e) {
		}
	}

	public void setAttivo(boolean statoAttivo) {
		this.statoAttivo = statoAttivo;
	}

}