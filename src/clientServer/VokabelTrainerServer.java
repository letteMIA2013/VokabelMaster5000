package clientServer;

import java.io.IOException;
import java.lang.String;import java.lang.System;import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by e2_parkhomenko on 18.06.14.
 */
public class VokabelTrainerServer extends ServerSocket {


    ArrayList<ConnectionToServerHandler> connectionHandler;

    public VokabelTrainerServer() throws IOException {

        super( 5555 );

        System.out.println( "Server gestartet mit dem Port: 5555" );

        connectionHandler = new ArrayList<ConnectionToServerHandler>();

    }

    /**
     * Methodenaufruf startet das "horchen" auf dem Port 5555
     */
    public void onConnection() {

        try {

            while ( true ) {

                Socket socketToClient = this.accept();
                System.out.println( "Jemand hat sich Verbunden" );
                connectionHandler.add( new ConnectionToServerHandler( socketToClient, this ) );
                connectionHandler.get( 0 ).start();

            }

        } catch ( IOException ioex ) {
            ioex.printStackTrace();
            System.out.println( "Ein Problem ist aufgetreten als jemand sich versucht hat zu verbinden" );
        }

    }

    public void broadcast( ClientData data ) {

        for ( ConnectionToServerHandler currentClient : connectionHandler ) {

            try {

                currentClient.getOutputStream().writeObject( data );

            } catch ( IOException ioexc ) {

            }

        }

    }

    public static void main( String[] args ) throws IOException{

        VokabelTrainerServer server = new VokabelTrainerServer();
        server.onConnection();
        System.out.println("Server heruntergefahren!");
        server.close();

    }
}
