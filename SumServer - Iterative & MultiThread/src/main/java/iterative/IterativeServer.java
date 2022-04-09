package iterative;

import java.io.*;
import java.net.*;

import static java.lang.Integer.parseInt;
import static sun.misc.Version.println;

class IterativeServer {
    public static void main(String argv[]) throws Exception {
        int sumClient;

        // create a "listening socket" on the specified port
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("\n SERVER STARTS");

        while(true) {
			/*	accept is a blocking call
				once a new connection arrived, it creates
				a new "established socket"	*/
            Socket connectionSocket = welcomeSocket.accept();

            // input stream from the socket initialization
            BufferedReader inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));

            // output stream to the socket initialization
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            // read a line (that terminates with \n) from the client
            String[] clienNums = inFromClient.readLine().split("\\s+");

            // wait for 10 seconds
            //Thread.sleep(10000);

            sumClient = parseInt(clienNums[0]) + parseInt(clienNums[1]);

            // send the response to the client
            outToClient.writeBytes(Integer.toString(sumClient) + '\n');
        }
    }
}
