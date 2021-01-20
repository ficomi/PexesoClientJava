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
 * Tato třída reprezentuje co se stane když se odešle příkaz FIELD.
 *  Odesílá svoje upravené pole s kartami protihráči.
 * @author Miloslav Fico
 */
public class CommandSendField implements ISendCommands{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String NAME_OF_COMMAND = StringCommandsSend.FIELD.toString().toUpperCase();
    
    @Override
    public void doCommand(BufferedWriter writer,Game game,UI ui){
       
        try {
            logger.debug("Odesláno herní pole");
            writer.write(NAME_OF_COMMAND+"/"+game.getField().getFieldToText()+"/"+game.getLoclalPlayer().getScore()+"/"+!ui.isPlayerTurn()+"/"+game.getSecondPlayer().getName()+";\r\n");
            
         
//            writer.write(NAME_OF_COMMAND+"/"+game.getField().getFieldToText()+"/"+game.getLoclalPlayer().getScore()+"/"+!ui.isPlayerTurn()+";\n\r"); 
            writer.flush();
            
            
//              System.out.println("Odeslano pole "+NAME_OF_COMMAND+"/"+game.getField().getFieldToText()+"/"+game.getLoclalPlayer().getScore()+"/"+!ui.isPlayerTurn()+";");
        } catch (IOException e) {
            logger.error("Nepodařilo se odeslat: "+e);
        }
    }
    
    
    @Override
    public String getName(){
    return NAME_OF_COMMAND;
    }
}
