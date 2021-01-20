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
 * Tato třída reprezentuje co se stane když se odešle příkaz ADDUSER.
 *  Žádá o registraci usera.
 * @author Miloslav Fico
 */
public class CommandAddUser  implements ISendCommands{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String NAME_OF_COMMAND = StringCommandsSend.ADDUSER.toString().toUpperCase();
    
    @Override
    public void doCommand(BufferedWriter writer,Game game,UI ui){
        
        try {
            writer.write(NAME_OF_COMMAND+"/"+ui.getName()+"/"+new String(ui.getPasswd())+";\r\n");
            logger.debug("Snaha o registraci klienta:" +ui.getName());
            writer.flush();
        } catch (IOException e) {
            logger.error("Nepodarilo se odeslat "+e);
        }
      
      
    }
    
    
    @Override
    public String getName(){
    return NAME_OF_COMMAND;
    }
}


