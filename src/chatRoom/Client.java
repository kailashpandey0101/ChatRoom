package chatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client{
	
	Scanner scan=new Scanner(System.in);
	ClientConnection cc;
	
	public static void main(String[] args){
		new Client();
	}
	
	public Client(){
		try {
			String name=JOptionPane.showInputDialog(null,"Enter your Name","LOGIN",JOptionPane.QUESTION_MESSAGE);
			//Select chatroom and store ip or port accordingly
			Socket s=new Socket("localhost",3333);
			cc=new ClientConnection(s, this);
			DataOutputStream dout= new DataOutputStream(s.getOutputStream());
			String announce="!!! "+name+" has joined the room !!!";
			dout.writeUTF(announce);
			//dout.close();
			cc.start();
			
			listenForInput(name);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void listenForInput(String name){
		Scanner console =new Scanner(System.in);
		
		while (true){
			while(!console.hasNextLine()){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}  
			}
			
			String input = console.nextLine();
			
			if(input.toLowerCase().equals("quit")){
				break;
			}
			cc.sendStringToServer(name+":"+input);
		}
		cc.close();
	}
}