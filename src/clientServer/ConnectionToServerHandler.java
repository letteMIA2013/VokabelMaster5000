package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by e2_parkhomenko on 18.06.14.
 * extends Thread ist damit man die Methode broadcast() aufrufen kann
 * ohne das in der Klasse VokabelTrainerServer die while() unterbrochen wird.
 *
 */
public class ConnectionToServerHandler extends Thread {

    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    String message;
    ClientData data;

    public ConnectionToServerHandler(Socket socketToClient, VokabelTrainerServer server){


        try {
            inputStream = new ObjectInputStream(socketToClient.getInputStream());
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        try {
            outputStream = new ObjectOutputStream(socketToClient.getOutputStream());
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        start();

    }

    @Override
    public void run() {

        while(true){
            try {
                readMessages();
            }

            catch ( IOException e ) {
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();
            }
        }
    }

    public  void readMessages() throws IOException, ClassNotFoundException {

        ClientData message = (ClientData)inputStream.readObject();
        System.out.println("Nachricht vom Client: "+message.getMessage());

    }
    public String getMessage() {
        return message;
    }


    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }


}
