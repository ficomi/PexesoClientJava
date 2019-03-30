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
 * @author pix
 */
public class CommandError implements IReciveCommands{
      private final String  NAME = StringCommandsRecive.ERROR.toString().toUpperCase();
      private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    public void doCommand(Game game,Recive recive,UI ui,String[] values) {
        
     
            UI.failedConnectionToServer();
      
        
    }

    @Override
    public String getName() {
        return NAME;
    }
}
