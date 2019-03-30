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
 * Tato třída reprezentuje co se stane když příjde příkaz REG.
 *  Vrací zprávu jestli se registrace clienta povedla.
 * @author Miloslav Fico
 */
public class CommandRegistration implements IReciveCommands{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String NAME = StringCommandsRecive.REG.toString().toUpperCase();
    
    @Override
    public void doCommand(Game game, Recive recive, UI ui, String[] values) {
       
        if (values[1].equals("OK")) {
         ui.succsefullReg();
         logger.debug("Úspěšná registrace");
        }else{
         ui.failedRegistration();
        logger.debug("Registrace se nepovedla");
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
    
    
    
}
