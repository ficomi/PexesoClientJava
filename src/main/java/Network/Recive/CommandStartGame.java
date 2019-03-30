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
 * 
 * Tato třída reprezentuje co se stane když příjde příkaz SGAME.
 *  Spouští se hra.
 * @author Miloslav Fico
 */
public class CommandStartGame implements IReciveCommands {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String NAME = StringCommandsRecive.SGAME.toString().toUpperCase();

    @Override
    public void doCommand(Game game, Recive recive, UI ui, String[] values) {
        
        game.setSecondPlayer(values[1], Integer.parseInt(values[2]));
        game.startGame(Boolean.valueOf(values[3]));
        ui.startGame(Boolean.valueOf(values[3]));
        logger.debug("Hra spuštěna. Protihráč: "+values[1]+" elo:"+Integer.parseInt(values[2]));
    
        
       
            
        
        
      
    }

    @Override
    public String getName() {
        return NAME;
    }

}
