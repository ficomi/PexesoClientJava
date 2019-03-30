/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Logic.Game;
import Network.Network;
import Network.NetworkThreadName;
import Network.Send.CommandMapSend;
import Network.Send.StringCommandsSend;

/**
 * Tato třída se stará o propojeni "síťové časti" s graficou částí.
 *
 * @author Miloslav Fico
 */
public final class UI {

    private PanelLogin login;
    private PanelRegistration registration;
    private PanelSuccesRegistration regSuc;

    private PanelGameMenu gameMenu;
    private PanelGame gamePanel;

    private PanelLoginFailed failedLogin;
    private PanelFailedRegistration failedReg;

    private String tempName = "";
    private String tempPasswd = "";
    private String chatMsg = "";
    private boolean playerTurn;
    private final Game game;
    private final Network network;

    private String name = "";
    private char[] passwd;

    public UI(Game game, Network network) {
        this.game = game;
        this.network = network;
        startLogin();
    }

    public synchronized void startLogin() {
        login = new PanelLogin(this);
        login.setVisible(true);
    }

    public synchronized void startRegistration() {
        registration = new PanelRegistration(this);
        registration.setVisible(true);
    }

    public synchronized void login(String name, char[] passwd) {

        tempName = name;
        tempPasswd = new String(passwd);

        CommandMapSend.setRequiredCommand(StringCommandsSend.STARTCOM);
        network.getNetworkThread(NetworkThreadName.SENDER).interrupt();

    }

    public static synchronized void failedConnectionToServer() {
        PanelFailedConnectionToServer filedCon = new PanelFailedConnectionToServer();
        filedCon.setVisible(true);
    }

    public synchronized void setUser(String name, char[] passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized char[] getPasswd() {
        return passwd;
    }

    public synchronized String getTempName() {
        return tempName;
    }

    public synchronized String getTempPasswd() {
        return tempPasswd;
    }

    public synchronized void succsefullReg() {
        regSuc = new PanelSuccesRegistration();
        regSuc.setVisible(true);
    }

    public void sentRegistration() {
        CommandMapSend.setRequiredCommand(StringCommandsSend.ADDUSER);
        network.getNetworkThread(NetworkThreadName.SENDER).interrupt();
    }

    public synchronized void failedLogin() {
        failedLogin = new PanelLoginFailed();
        failedLogin.setVisible(true);
    }

    public synchronized void failedRegistration() {
        failedReg = new PanelFailedRegistration();
        failedReg.setVisible(true);
    }

    public synchronized void startGameMenu() {
        if (gamePanel == null) {
            gameMenu = new PanelGameMenu(this, game);
            gameMenu.setVisible(true);
            login.setVisible(false);
        } else {
            gamePanel.setVisible(false);
            gameMenu = new PanelGameMenu(this, game);
            gameMenu.setVisible(true);
            refreshGameMenu();
        }

    }

    public void isGameWon() {
        if (game.getLoclalPlayer().getScore() > 0) {
            CommandMapSend.setRequiredCommand(StringCommandsSend.EGAME);
            network.getNetworkThread(NetworkThreadName.SENDER).interrupt();

        }

    }

    public void sentChat(String text) {
        chatMsg = text;
        CommandMapSend.setRequiredCommand(StringCommandsSend.SENDMSG);
        network.getNetworkThread(NetworkThreadName.SENDER).interrupt();

    }

    public synchronized void recChat(String text) {
        if (gameMenu != null) {
            gameMenu.addMessageToMsgChat(text);
        }
    }

    public synchronized String getChatMsg() {
        return chatMsg;
    }

    public synchronized void startSearchingGame() {

        CommandMapSend.setRequiredCommand(StringCommandsSend.SSEARCH);
        network.getNetworkThread(NetworkThreadName.SENDER).interrupt();
    }

    public synchronized void startGame(boolean bool) {
        setPlayerTurn(bool);
        startGamePanel();

    }

    public synchronized void startGamePanel() {

        gamePanel = new PanelGame(game, this);

        gamePanel.setVisible(true);
        gameMenu.setVisible(false);
        gamePanel.setButtonsForFirstTime();

    }

    public void nextTurn() {
        setButtons();
        CommandMapSend.setRequiredCommand(StringCommandsSend.FIELD);
        network.getNetworkThread(NetworkThreadName.SENDER).interrupt();
    }

    public synchronized void getActualField() {
        gamePanel.setButtonsValue();
    }

    public synchronized void setCardsBackside() {
        gamePanel.setAllCardsToBackside();
    }

    public synchronized void setButtons() {
        gamePanel.setAllButtonsAvailability();
    }

    public synchronized boolean isPlayerTurn() {
        return playerTurn;
    }

    public synchronized void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public synchronized void setScoresOnGamePanel() {
        gamePanel.setScores();
    }

    public synchronized void refreshGameMenu() {
        gameMenu.refreshGamemenu();
    }

}
