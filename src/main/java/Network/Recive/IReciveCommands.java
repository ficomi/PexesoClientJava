/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.Recive;

import Logic.Game;
import UI.UI;

/**
 * Tato třída slouži jako interface ke všem příchozím zprávám.
 * @author Miloslav Fico
 */
public interface IReciveCommands {
      /**
     *
     * @param game Třída hry
     * @param recive Třída Recive
     * @param ui Třída UI
     * @param values Hodnoty získané ze zprávy
     */

    public void doCommand(Game game,Recive recive,UI ui,String[] values);
    
    /**
     *
     * @return název příkazu
     */
    public String getName();
}
