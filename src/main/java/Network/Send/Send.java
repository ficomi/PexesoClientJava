/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Send;

/**
 * Třída která se stará o odesílaní příkazu na server.   
 * @author Miloslav Fico
 */
import Logic.Game;
import Network.NetworkThreadName;
import java.io.BufferedWriter;
import java.net.Socket;
import java.io.OutputStreamWriter;
import UI.UI;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Send implements Runnable {

    
     private final Logger logger = LoggerFactory.getLogger(getClass());
    private final NetworkThreadName NAME;
    private BufferedWriter writer;
    private final String ADRESS = "localhost";
    private final int PORT = 5010;
    private Socket socket;
    private CommandMapSend mapOfCommands;
    private final Game game;
    private final UI UI;
    
    private boolean isConnected = true;

    public Send(NetworkThreadName name, Game game, UI UI) {
        this.NAME = name;
        this.game = game;
        this.UI = UI;
         
         //vytvoreni mapy prikazu
        mapOfCommands = new CommandMapSend();
        //plneni mapy prikazu
        mapOfCommands.AddCommandToMap(new CommandSendField());
        mapOfCommands.AddCommandToMap(new CommandSendMsg());
        mapOfCommands.AddCommandToMap(new CommandStartCommunationWithServer());
        mapOfCommands.AddCommandToMap(new CommandAddUser());
        mapOfCommands.AddCommandToMap(new CommandStartSearchingGame());
        mapOfCommands.AddCommandToMap(new CommandEndGame());
         
        //nastaven jako prvni command "navaz spojeni se serverem"
        try {
           
            socket = new Socket(ADRESS, PORT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
          
            //prvni komuinikace se serverem
        } catch (IOException e) {
         logger.error("Nepodařilo se připojit k serveru od SENDER: "+e);
            UI.failedConnectionToServer();
           closeConnectionSend();
//            try {
////                writer.flush();
////                writer.close();
////                socket.close();
//            } catch (IOException ex) {
//                
//            }
 
        }
    }

    

    @Override
    public void run() {
       
        try {
           
            writer.flush();
            while (isConnected) {
               
                 Thread.currentThread().sleep(100);
                
            }
           
        } catch (InterruptedException e) {
           
             mapOfCommands.getCommandClass(CommandMapSend.getRequiredCommand()).doCommand(writer, game, UI);
             if(isConnected){run();}
             
        }catch(IOException i){
          logger.error("Nepodařilo se připojit k serveru od SENDER: "+i);
        }
            
  
    }

    
    public NetworkThreadName getNAME() {
        return NAME;
    }
    
     private void closeConnectionSend(){
        try {
            socket.close();
            writer.close();
        } catch (IOException e) {
           logger.error("Nepodařilo se ukončit spojení k serveru od SENDER: "+e);
            
        }
        
    }

}
