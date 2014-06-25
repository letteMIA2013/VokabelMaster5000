package clientserver;/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: croussel
 *
 *  $Id$  
 */

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ClassNotFoundException;import java.lang.String;import java.lang.System;import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class VokabelClient {

    private String name;
    private int clientID;

    public static void main( String[] args ) throws IOException {
        new VokabelClient();
    }

    public VokabelClient() throws IOException {

        name = JOptionPane.showInputDialog( "Name des Spielers?" );
        clientID = new Random().nextInt( 1000 );

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            kkSocket = new Socket( "192.168.1.105", 4444 );
            out = new PrintWriter( kkSocket.getOutputStream(), true );
            in = new BufferedReader( new InputStreamReader( kkSocket.getInputStream() ) );
        } catch ( UnknownHostException e ) {
            System.err.println( "Don't know about host: localhost." );
            System.exit( 1 );
        } catch ( IOException e ) {
            System.err
                    .println( "Couldn't get I/O for the connection to: localhost." );
            System.exit( 1 );
        }

        ClientData client = new ClientData( name );
        OutputStream os = kkSocket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( os );
        oos.writeObject( client );
        oos.flush();

        while ( true ) {
            InputStream is = kkSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream( is );
            String serverData = null;
            try {
                serverData = (String) ois.readObject();
            } catch ( ClassNotFoundException e ) {

            }
            if ( serverData != null ) {
                System.out.println( "Server info: " );
                System.out.println( "Spieler am Tisch: " + serverData );


                ClientData clientInfo = new ClientData( name );

                oos.writeObject( clientInfo );
                oos.flush();
            } else {
                System.out.println( "Ich muss warten" );
            }

        }
    }

    // out.close();
    // in.close();
    // stdIn.close();
    // kkSocket.close();
}

