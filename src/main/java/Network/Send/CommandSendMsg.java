/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Send;

import Logic.Game;
import UI.UI;
import java.io.BufferedWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tato třída reprezentuje co se stane když se odešle příkaz SENDMSG.
 *  Odesílá chatzující zprávu na server.
 * @author Miloslav Fico
 */
public class CommandSendMsg implements ISendCommands{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String NAME_OF_COMMAND = StringCommandsSend.SENDMSG.toString().toUpperCase();
    
    
    @Override
    public void doCommand(BufferedWriter writer,Game game,UI ui){
        try {
            logger.debug("Odeslána zpráva: "+ui.getChatMsg());
             writer.write(NAME_OF_COMMAND+"/"+game.getLoclalPlayer().getName()+"/"+ui.getChatMsg()+"/;\r\n");
             writer.flush();
        } catch (Exception e) {
            logger.error("Nepodařilo se odeslat: "+e);
        }
     
    
    }
    
    @Override
    public String getName(){return NAME_OF_COMMAND;}
}
