package be.r2l.xboxc.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public final class SocketClient {
	
	private static SocketClient INSTANCE;
	
	private static final String IP = "127.0.0.1";
	private static final int PORT = 8099;
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	private SocketClient() {
		
	}
	
	public static SocketClient getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SocketClient();
		}
		return INSTANCE;
	}
	

	public void startConnection() {
		try {
			clientSocket = new Socket(IP, PORT);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String sendMessage(String msg) {
		out.println(msg);
		String response = "";
		try {
			response = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
