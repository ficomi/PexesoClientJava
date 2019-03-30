/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Send;

import Logic.Game;
import UI.UI;
import java.io.BufferedWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Tato třída reprezentuje co se stane když se odešle příkaz STARTCOM.
 *  Snaho o login na server.
 * @author Miloslav Fico
 */
public class CommandStartCommunationWithServer implements ISendCommands {
     private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String NAME_OF_COMMAND = StringCommandsSend.STARTCOM.toString().toUpperCase();
    
    @Override
    public void doCommand(BufferedWriter writer,Game game,UI ui) {
        
        try {
            logger.debug("Snaha se přihlásit na server uživatelem: "+ui.getTempName());
            writer.write(NAME_OF_COMMAND+"/"+ui.getTempName()+"/"+ui.getTempPasswd()+";\r\n");          
            writer.flush();
        } catch (IOException e) {
            //nepodarilo se 
            logger.error("Nepodarilo se odeslat "+e);
        }
      
    }

    @Override
    public String getName() {
        return NAME_OF_COMMAND;
    }
    
}
