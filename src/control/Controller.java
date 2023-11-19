package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Server;
import view.ClientFrame;
import view.ServerFrame;


public class Controller implements ActionListener{

	private ServerFrame serverFrame;
	private Server server;
	private ClientFrame clientFrame;
	
	
	public Controller(ServerFrame serverFrame,Server server) {
		super();
		this.server=server;
		this.serverFrame=serverFrame;
		//this.clientDisplay = clientDisplay;
		serverFrame.registraEvento(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == serverFrame.getBtnRefresh())
			this.serverFrame.gettIpField().setText(Server.getIndirizzoIpServer());
		
		if(e.getSource() == serverFrame.getBtnStart())
			server.startServer();
		
		if(e.getSource() == serverFrame.getBtnStop())
			server.stopServer();
		
		if(e.getSource() == serverFrame.getBtnWatch())
			serverFrame.getClientDisplay().startDisplay(server.getClienteListaClienti(serverFrame.nomeSelezionato()));
		
		
		if(e.getSource() == serverFrame.getBtnChiudi())
			serverFrame.getClientDisplay().stopDisplay();
		
		
	}

}
