package main;

import view.ServerFrame;

public class ServerMain {

	public static final int SERVER_PORT = 55555;
	public static final int CLIENT_ALIVE_PORT = 55556;
	public static final int CLIENT_PHOTO_SENDING_PORT = 55557;

	public static ServerFrame serverFrame;

	public static void main(String[] args) {
		serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
	}

}
