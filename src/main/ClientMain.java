package main;

import view.ClientFrame;

public class ClientMain {

	public static final int SERVER_PORT = 55555;
	public static final int CLIENT_ALIVE_PORT = 55556;
	public static final int CLIENT_PHOTO_SENDING_PORT = 55557;

	public static void main(String[] args) {
		ClientFrame.initialize();
	}
}
