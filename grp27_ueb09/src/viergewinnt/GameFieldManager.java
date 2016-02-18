package viergewinnt;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Verwaltet das Spielfeld. Ein Spielfeld besteht in erster Linie aus einem
 * GridPane. Die einzelnen Zellen des GridPanes werden separat durch einen
 * Zell-Manager verwaltet, um den Zugriff auf die einzelnen Zellen zu
 * vereinfachen. Ein Spielfeld hat in einer Spalte immer gleich viele Reihen.
 * Genauso besitzt jede Reihe gleich viele Spalten.
 *
 * @author Jan Riemer
 */
public class GameFieldManager {

    /**
     * Das Spielfeld als GridPane.
     */
    private final GridPane gameField;

    /**
     * Der Zell-Manager, welcher für die Verwaltung der einzelnen Zellen
     * innerhalb des Spielfeldes verantwortlich ist.
     */
    private final CellManager cellManager;

    /**
     * Initialisiert einen GameFieldManager mit einem GridPane-Objekt. Das
     * GridPane-Objekt symbolisiert das Spielfeld.
     *
     * @param gameField Ein GridPane als Spielfeld.
     */
    public GameFieldManager(GridPane gameField) {
        assert (gameField != null);
        this.gameField = gameField;
        this.cellManager = new CellManager(this.getColumnCount(), this.getRowCount());
        this.addCellsToGameField();
    }

    /**
     * Gibt den Zell-Manager zurück.
     *
     * @return Der Zell-Manager
     */
    public CellManager getCellManager() {
        return this.cellManager;
    }

    /**
     * Fügt dem Zell-Manager so viele Zellen hinzu, wie das Spielfeld Zellen
     * besitzt. Gleichzeitig werden die nun vorhandenen Pane-Objekte einer Zelle
     * im Zell-Manager dem Spielfeld bzw. dessen Zellen hinzugefügt. Die Indizes
     * der Zellen des Spielfeldes korrespondieren dabei mit den Indizes der
     * Zellen des Zell-Managers.
     */
    private void addCellsToGameField() {
        int numOfColumns = this.getColumnCount();
        int numOfRows = this.getRowCount();
        for (int row = 0; row < numOfRows; row++) {
            for (int column = 0; column < numOfColumns; column++) {
                Pane paneToAdd = this.cellManager
                        .addCell(column, row) // wir fügen eine Zelle hinzu...
                        .getCell(column, row) // ...holen uns diese Zelle...
                        .getPane(); // ...und von dieser Zelle das Pane-Objekt
                this.cellManager.getCell(column, row).colorize(Color.WHITE);
                this.gameField.add(paneToAdd, column, row);
            }
        }
    }

    /**
     * Lässt einen Stein in einen Slot des Spielfeldes fallen, sofern dies
     * möglich ist und färbt die entsprechende Zelle gemäß der Farbe des
     * Spielsteines. Gewinnt der im aktuellen Zug befindliche Spieler durch
     * Setzen des Spiel-Steines das Spiel, werden die Zellen der Reihe, die zum
     * Sieg geführt haben, mit einem schwarzen Rahmen markiert.
     *
     * @param paneOfSlot Ein Pane-Objekt des Slots, in der ein Stein fallen
     * gelassen werden soll.
     * @param gameLogic Die Verwaltung der Spiellogik, die bestimmt, in welcher
     * Zelle in dem Slot der Spielstein landet.
     * @return True, falls der Spielstein in den Slot eingefügt werden konnte,
     * ansonsten false.
     */
    public boolean dropStoneIntoSlot(Pane paneOfSlot, GameLogic gameLogic) {
        assert (paneOfSlot != null && gameLogic != null);

        CellCoordinates clickedCoordinates = cellManager.findCellCoords(paneOfSlot);
        CellCoordinates currentCoordinates = cellManager.getCoordsForLowestFirstFreeCell(clickedCoordinates.getColumnIdx());

        if (gameLogic.dropStoneIntoSlot(currentCoordinates)) {
            Cell validCell = this.cellManager.getCell(currentCoordinates);
            validCell.colorize(gameLogic.getColorOfCurrentPlayer());

            if (gameLogic.hasCurrentPlayerWon()) {
                markWinningRow(currentCoordinates);
            }

            return true;
        }
        return false;
    }

    /**
     * Markiert die Reihe, die zum Sieg geführt hat.
     *
     * @param origin Zell-Koordinaten, von denen ausgegangen werden soll
     */
    private void markWinningRow(CellCoordinates origin) {
        if (origin != null) {
            CellCoordinates[] winningRow = this.cellManager
                    .getLongestRowOfPlayer(origin);
            for (CellCoordinates cellCoords : winningRow) {
                this.cellManager.getCell(cellCoords).colorizeBorder(Color.BLACK);
            }
        }
    }

    /**
     * Gibt die Anzahl Spalten auf dem Spielfeld zurück.
     *
     * @return Die Anzahl Spalten auf dem Spielfeld.
     */
    private int getColumnCount() {
        return this.gameField.getColumnConstraints().size();
    }

    /**
     * Gibt die Anzahl Zeilen auf dem Spielfeld zurück.
     *
     * @return Die Anzahl Zeilen auf dem Spielfeld.
     */
    private int getRowCount() {
        return this.gameField.getRowConstraints().size();
    }

}
