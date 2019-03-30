/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Recive;

import Logic.Game;
import UI.UI;

/**
 * Tato třída reprezentuje co se stane když příjde příkaz ENDALL.
 * @author Miloslav Fico
 */
public class CommandEndConnections implements IReciveCommands{
    private static final String NAME_OF_COMMAND = StringCommandsRecive.ENDALL.toString().toUpperCase();
    
    @Override
    public void doCommand(Game game,Recive recive,UI ui,String[] values){
    recive.closeConnectionRecive();
    } 
    
    @Override
    public String getName(){return NAME_OF_COMMAND;}
}
