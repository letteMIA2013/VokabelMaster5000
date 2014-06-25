package clientServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class VokabelServer {

    private Map<Integer, Socket> allPlayers;

    public static void main(String[] args) throws IOException{
        new VokabelServer();
    }



    public VokabelServer() {


        allPlayers = new HashMap<Integer, Socket>();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server running...");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                InputStream is = clientSocket.getInputStream();
                final ObjectInputStream ois = new ObjectInputStream(is);

                Object readObject = null;
                try {
                    readObject = ois.readObject();
                } catch (java.lang.ClassNotFoundException e) {

                }
                if (readObject == null) {
                    continue;
                }
                System.out.println("Da ist ja jemand...");

                if (readObject instanceof ClientData ) {

                    ClientData data = (ClientData) readObject;
                    int id = data.getId();
                    if (!allPlayers.containsKey(id)) {
                        allPlayers.put(id, clientSocket);

                        createNewPlayerThread(clientSocket, data.getSpielerNamen().get( 0 ));
                    }
                    else{

                    }



                    for (Integer playerID : allPlayers.keySet()) {
                        Socket socket = allPlayers.get(playerID);
                        OutputStream os = socket.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);
                        oos.writeObject("Hallo, Spieler " + playerID);
                        oos.flush();
                    }

                }

            }
            catch(Exception e){

            }
        }

        // is.close();
        // clientSocket.close();
        // serverSocket.close();

    }

    private void createNewPlayerThread(Socket clientSocket, String threadName) throws Exception{

        InputStream is = clientSocket.getInputStream();
        final ObjectInputStream ois = new ObjectInputStream(is);

        new Thread(threadName) {

            public void run() {

                while (true) {
                    Object readObject = null;
                    try {
                        readObject = ois.readObject();
                    } catch (Exception e) {

                    }
                    final ClientData clientInfo = (ClientData) readObject;

                    // update all clients

                    try {
                        for (int playerID : allPlayers.keySet()) {
                            OutputStream os = allPlayers.get(playerID).getOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject("Hallo, Spieler " + playerID);
                            oos.flush();
                        }
                    } catch (Exception ex) {

                    }

                }
            }
        }.start();



    }
}
