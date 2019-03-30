/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pexesoclient;

import Logic.Game;
import Network.Network;
import UI.UI;


/**
 *  Main třída.
 * @author Miloslav Fico
 */
public class PexesoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game(16);
        Network network = new Network(game);
        UI ui = new UI(game,network);
        network.setUI(ui);
        
    }
    
    
    
}
