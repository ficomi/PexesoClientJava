/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Recive;

import Logic.Game;
import UI.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 *Tato třída reprezentuje co se stane když příjde příkaz RECMSG.
 *  Týká se to hlavnš chatujících zpráv.
 * @author Miloslav Fico
 */
public class CommandRecMsg implements IReciveCommands{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String NAME_OF_COMMAND = StringCommandsRecive.RECMSG.toString().toUpperCase();
    
    @Override
    public void doCommand(Game game,Recive recive,UI ui,String[] values){
         ui.recChat(values[1]+": "+values[2]);
         logger.debug("Prijata zprava: "+values[1]+": "+values[2]);
    
    }
    
    @Override
    public String getName(){return NAME_OF_COMMAND;}
}
