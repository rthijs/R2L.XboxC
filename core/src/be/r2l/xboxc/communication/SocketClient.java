package be.r2l.xboxc.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
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
		//Can't touch this, naaa nana na
	}
	
	public static SocketClient getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SocketClient();
		}
		return INSTANCE;
	}
	
	public void startConnection() {
		clientSocket = getSocket();
		out = getPrintWriter(clientSocket);
		in = getBufferedReader(clientSocket);
	}

	public void sendMessage(String message) {
		if (clientSocket != null) {
			sendMessageSocket(message);
		} else {
			sendMessageConsole(message);
			retryStartConnection();
		}
	}
	
	private void sendMessageSocket(String message) {
		out.println(message);
	}
	
	private void sendMessageConsole(String message) {
		System.out.println(message);
	}
	
	private void retryStartConnection() {
		startConnection();
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Dit moet je toch eens fixen, Roel.");
		}
	}
	
	protected Socket getSocket() {
		Socket socket = null;
		try {
			socket = new Socket(IP, PORT);
		} catch (ConnectException e){
			System.out.println("Not connected to server, using dummy output.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return socket;
	}
	
	private PrintWriter getPrintWriter(Socket socket) {
		PrintWriter printWriter = null;
		if (socket != null) {
			try {
				printWriter = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return printWriter;
	}

	private BufferedReader getBufferedReader(Socket socket) {
		BufferedReader reader = null;
		if (socket != null) {
			try {
				reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reader;
	}
}
