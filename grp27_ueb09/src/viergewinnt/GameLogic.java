package viergewinnt;

import javafx.scene.paint.Color;

/**
 * Stellt zentrale Funktionalitäten für das Einhalten der Spielregeln bereit.
 * @author Jan Riemer 
 */
public class GameLogic {
    
    /**
     * Der Zell-Manager, welcher die Zellen
     * des Spielfeldes verwaltet.
     */
    private final CellManager cellManager;
    
    /**
     * Der Spieler-Manager, welcher die Spieler in dem Spiel
     * verwaltet.
     */
    private final PlayerManager playerManager;
     
    /**
     * Ein Flag, welches anzeigt, ob das Spiel durch den aktuell im Spielzug
     * befindlichen Spieler gewonnen wurde.
     */
    private boolean hasCurrentPlayerWon;
    
    /**
     * Gibt an, wie viele Spiel-Steine eines Spielers in
     * einer Reihe erforderlich sind, damit der Spieler das Spiel gewinnt.
     */
    private final int MIN_NUM_STONES_WINNING = 4;
    
    /**
     * Initialisiert ein Spiellogik-Objekt mit einem
     * zugehörigen Zell-Manager.
     * @param cellManager Der Zell-Manager zur Verwaltung und dem
     * einfachen Zugriff auf Zellen.
     */
    public GameLogic(CellManager cellManager) {
        assert (cellManager != null);
        this.cellManager = cellManager;
        this.playerManager = new PlayerManager();
        this.hasCurrentPlayerWon = false;
    }
    
    /**
     * Gibt den Spielzug an den nächsten Spieler weiter.
     */
    public void nextPlayer() {
        this.playerManager.nextPlayer();
    }
    
    /**
     * Gibt die Id des sich aktuell an der Reihe befindlichen Spielers
     * zurück.
     * @return Die Id des sich aktuell an der Reihe befindlichen Spielers.
     */
    public int getCurrentPlayer() {
        return this.playerManager.getCurrentPlayer();
    }
    
    /**
     * Prüft, ob alle Slots auf dem Spielfeld belegt sind.
     * @return true, wenn alle Slots auf dem Spielfeld belegt sind,
     * ansonsten false
     */
    public boolean areAllSlotsReserved() {
        return this.cellManager.areAllCellsReserved();
    }
    
    /**
     * Prüft, ob der aktuell an der Reihe befindliche Spieler gewonnen hat.
     * Ein Spieler hat gewonnen, sobald er mind. 4 Spielsteine
     * in eine Reihe gelegt hat.
     * @return True, wenn der aktuell an der Reihe befindliche Spieler gewonnen
     * hat, ansonsten false
     */
    public boolean hasCurrentPlayerWon() {
        return this.hasCurrentPlayerWon;
    }
    
    /**
     * Gibt die Farbe des aktuellen Spielers zurück.
     * @return Die Farbe des aktuellen Spielers.
     */
    public Color getColorOfCurrentPlayer() {
        return this.playerManager.getColorOfCurrentPlayer();
    }
    
    /**
     * Lässt einen Spielstein in einen Slot des Spielfeldes fallen.
     * @param coords Einer der Zell-Koordinaten des Slots, in dem ein Spielstein
     * fallen gelassen werden soll.
     * @return true, falls der Spielstein fallen gelassen werden konnte,
     * ansonsten false.
     */
    public boolean dropStoneIntoSlot(CellCoordinates coords) {
        boolean wasDropped = false;
            
        if (coords != null) {
            Cell targetCell = this.cellManager.getCell(coords);
            // das Fallenlassen des Spielsteines simulieren wir, indem wir
            // dem Spieler, der aktuell am Zug ist, zum neuen
            // Eigentümer der Zelle machen
            targetCell.setPlayerOwner(this.playerManager.getCurrentPlayer());
            this.updateHasCurrentPlayerWon(coords);
            wasDropped = true;
        }
        
        return wasDropped;
    }
    
    /**
     * Aktualisiert das Flag, welches anzeigt, ob der
     * Spieler, welcher Inhaber der Zelle auf dem angegebenen
     * Koordinatenursprung ist, gewonnen hat.
     * Ein Spieler hat gewonnen, sobald er mind. 4 Spielsteine
     * in eine Reihe gelegt hat.
     * @param origin Die Zell-Koordinaten, von denen ausgegangen werden soll.
     */
    private void updateHasCurrentPlayerWon(CellCoordinates origin) {
        assert (origin != null);
        this.hasCurrentPlayerWon = this.cellManager
                .getCountLongestRowOfPlayer(origin) >= MIN_NUM_STONES_WINNING;
    }
    
}
