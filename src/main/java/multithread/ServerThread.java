package multithread;

import java.io.*;
import java.net.*;

import static java.lang.Integer.parseInt;

public class ServerThread extends Thread {
    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    int clientX;
    int clientY;
    int sumClient;

    // the constructor argument is an established socket
    public ServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String clientSentence;
        String capitalizedSentence;
        try {
            /*clientSentence = inFromClient.readLine();
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);*/

            // read a line (that terminates with \n) from the client
            String[] splitted = inFromClient.readLine().split("\\s+");
            clientX = parseInt(splitted[0]);
            clientY = parseInt(splitted[1]);

            // wait for 10 seconds
            //Thread.sleep(10000);

            sumClient = clientX + clientY;

            // send the response to the client
            outToClient.writeBytes(Integer.toString(sumClient) + '\n');

            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
