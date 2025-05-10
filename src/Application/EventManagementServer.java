package Application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EventManagementServer {

    public void run() throws IOException {
        ServerSocket newSocketconnect = new ServerSocket(8080);
        boolean serverStatus = true;
        while (serverStatus){
            try(Socket soc = newSocketconnect.accept();
                PrintWriter pval = new PrintWriter(soc.getOutputStream(), true);  // Auto-flush enabled
                BufferedReader clientContent = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            ) {
                String mydata;
                while((mydata=clientContent.readLine())!=null){
                    System.out.println(mydata);
                    pval.println("Server Say I am connected send this message to client");

                }
                serverStatus=false;


            } catch (IOException e) {
                System.out.println("I/O error occurred: Client closed the connection" );
                break;
            }
        }
        newSocketconnect.close();
        System.out.println("Client is stopped Closing the connection");
    }
}
