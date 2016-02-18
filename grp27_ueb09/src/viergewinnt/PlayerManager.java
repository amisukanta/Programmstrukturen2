package viergewinnt;

import javafx.scene.paint.Color;

/**
 * Verwaltet die Spieler in dem Spiel.
 * @author Jan Riemer 
 */
public class PlayerManager {
    
    /**
     * Die Spieler-Id des Spielers, welcher sich
     * aktuell am Spielzug befindet.
     */
    private int currentPlayer;
    
    /**
     * Die maximal zulässige Anzahl an Spielern.
     */
    private final int MAX_PLAYERS = 2;
    
    /**
     * Die Farben für die Spieler.
     */
    private final Color[] PLAYER_COLORS = {Color.RED, Color.GOLD};
    
    /**
     * Initialisiert den Spiel-Manager.
     * Die Spieler-Id des Spielers, welcher sich
     * als erstes am Spielzug befindet, wird auf die
     * Id 0 gesetzt.
     */
    public PlayerManager() {
        this.currentPlayer = 0;
    }
    
    /**
     * Gibt den Spielzug an den nächsten Spieler weiter.
     */
    public void nextPlayer() {
        this.currentPlayer = (this.currentPlayer + 1) % MAX_PLAYERS;
    }
    
    /**
     * Gibt die Id des sich aktuell an der Reihe befindlichen Spielers
     * zurück.
     * @return Die Id des sich aktuell an der Reihe befindlichen Spielers.
     */
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    /**
     * Gibt die Farbe des aktuellen Spielers zurück.
     * @return Die Farbe des aktuellen Spielers.
     */
    public Color getColorOfCurrentPlayer() {
        return this.PLAYER_COLORS[this.currentPlayer];
    }
}
