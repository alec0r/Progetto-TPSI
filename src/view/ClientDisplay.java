package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import model.Client;

public class ClientDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private DisplayRefresher displayRefresher;
	private Client client;

	private static int COUNT = 0;

	public ClientDisplay() {
		displayRefresher = new DisplayRefresher();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Graphics2D g2 = (Graphics2D) g;
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            g2.drawImage(client.getRemotePhoto(), 0, 0, panelWidth, panelHeight, null);
		} catch (Exception e) {
			displayRefresher.setWorkingStatus(false);
		}
		g.drawString(String.valueOf(COUNT++), 10, 20);
	}

	public void startDisplay(Client client) {
		this.client = client;
		displayRefresher.setWorkingStatus(true);
		new Thread(displayRefresher).start();
	}

	public void stopDisplay() {
		client = null;
		displayRefresher.setWorkingStatus(false);
	}

	class DisplayRefresher implements Runnable {

		private boolean workingStatus;

		public DisplayRefresher() {
		}

		@Override
		public void run() {
			workingStatus = true;
			while (workingStatus) {
				repaint();
			}
		}

		public void setWorkingStatus(boolean tof) {
			workingStatus = tof;
		}

	}

}