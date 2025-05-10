package Application;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EventManagementClient {

    public void run() throws IOException {
        int port = 8080;
        InetAddress client= InetAddress.getByName("localhost");

        try (Socket clientSocket = new Socket(client, port);
             PrintWriter sendDataServer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader buff = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            Scanner enterVal = new Scanner(System.in);

            while(true){
                System.out.println("Send Next Request to Server");
                sendDataServer.println(enterVal.nextLine());
                //String readline = buff.readLine();
                //System.out.println("Server response: " + readline);
            }
        }
    }
}