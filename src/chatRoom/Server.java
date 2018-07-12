package chatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
	
	ServerSocket ss;
	ArrayList<ServerConnection> connections=new ArrayList<ServerConnection>();
	boolean go=true;
	
	public static void main(String[] args){
		new Server();
	}
	
	public Server(){
		try {
			ss=new ServerSocket(3333);
			while(go){
				Socket s=ss.accept();
				ServerConnection sc= new ServerConnection(s,this);
				sc.start();
				connections.add(sc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}