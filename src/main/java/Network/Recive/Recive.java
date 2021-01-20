/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Recive;

/**
 * Tato třída se stará o zpracování všech příchozích zprav a spuštění příkazu který odpovídá příchozí zprávě.
 * @author Miloslav Fico
 */

import Logic.Game;
import Network.NetworkThreadName;
import UI.UI;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Recive implements Runnable {
     private final Logger logger = LoggerFactory.getLogger(Recive.class);
    private final NetworkThreadName NAME;
    private BufferedReader reader;
    private final String ADRESS = "localhost";
    private final int PORT = 5010;
    private boolean isConnected = true;
    private Socket socket;
    private CommandMapRecive mapOfCommands;
    private final Game game;
    private final UI UI;

    public Recive(NetworkThreadName name,Game game,UI UI) {
    this.NAME=name;
    this.game=game;
    this.UI=UI;
    }

    
    public void start() {
        mapOfCommands = new CommandMapRecive();
        mapOfCommands.AddCommandToMap(new CommandRecMsg());
        mapOfCommands.AddCommandToMap(new CommandEndConnections());
        mapOfCommands.AddCommandToMap(new CommandLogin());
        mapOfCommands.AddCommandToMap(new CommandRegistration());
        mapOfCommands.AddCommandToMap(new CommandStartGame());
        mapOfCommands.AddCommandToMap(new CommandReciveField());
        mapOfCommands.AddCommandToMap(new CommandUpdate());
        mapOfCommands.AddCommandToMap(new CommandError());
        logger.debug("Všechny příkazy uloženy pro Recive.");
        try {
            socket = new Socket(ADRESS, PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            
        } catch (IOException e) {
            logger.error("Nemůžu se připojit k serveru od Recive: "+e);
        }

    }

    @Override
    public void run() {
        
        start();
        String message = "";
        
           
            
            try {
                while (isConnected) {
                
                message = reader.readLine();
                    System.out.println("PZ: "+message);
                if (!message.isEmpty()) {
                    String[] split_message;
                   
                    split_message = message.split("/");
                    if (mapOfCommands.isCommand(split_message[0])) {

                        mapOfCommands.getCommandClass(split_message[0]).doCommand(game,this,UI,getValuesFromMessage(split_message));
                    }
                    
                }
                }
            } catch (Exception e) {
                //Chyba pri prijmu
                logger.error("Nemůžu se pripojit k serveru od Recive: "+e);
                UI.failedConnectionToServer();
                closeConnectionRecive();
            }

        }
       

    

    private String[] getValuesFromMessage(String[] message) {
        message[message.length-1]=message[message.length-1].replace(";", ""); // oseka o ";"
        return message;
    }
    
    public void closeConnectionRecive(){
        try {
            socket.close();
            reader.close();
        } catch (IOException e) {
            logger.error("Nemůžu ukončit spojení: "+e);        
        }
        
    }

    public NetworkThreadName getNAME() {
        return NAME;
    }
    
    
    

}
