/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Send;

import Logic.Game;
import UI.UI;
import java.io.BufferedWriter;

/**
 * Tato třída slouží jako interface pro odesílací příkazy.
 * @author Miloslav Fico
 */
public interface ISendCommands {
  
    /**
     *
     * @param writer Writer na odesání
     * @param game Třida hra
     * @param ui třída UI
     */

    public void doCommand(BufferedWriter writer,Game game,UI ui);
    
    /**
     *
     * @return název příkazu
     */
    public String getName();
}
