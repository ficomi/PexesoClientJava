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
 * Tato třída reprezentuje co se stane když se odešle příkaz SSEARCH. Snaha o
 * požádání serveru o přidání do fronty hráčů kteří hledají hru.
 *
 * @author Miloslav Fico
 */
public class CommandStartSearchingGame implements ISendCommands {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String NAME_OF_COMMAND = StringCommandsSend.SSEARCH.toString().toUpperCase();

    @Override
    public void doCommand(BufferedWriter writer, Game game, UI ui) {
        String name = game.getLoclalPlayer().getName();
        String passwd = game.getLoclalPlayer().getPassword();
      
        try {
            if ((name != null || name != "") && (passwd != null || passwd != "")) {
               
                if (!game.isIsSearching()) {
                    
                    writer.write(NAME_OF_COMMAND + "/" + name + "/" + passwd + "/true/" + ";\r\n");
                    writer.flush();
                       System.out.println(name+" t "+passwd);
                    game.setIsSearching(true);
                } else {
                    
                    writer.write(NAME_OF_COMMAND + "/" + name + "/" + passwd + "/false/" + ";\r\n");
                    writer.flush();
                    System.out.println(name+" f "+passwd);
                    game.setIsSearching(false);
                }

            }

        } catch (IOException e) {
            //nepodarilo se 
            logger.error("Nepodarilo se odeslat " + e);
        }

    }

    @Override
    public String getName() {
        return NAME_OF_COMMAND;
    }

}
