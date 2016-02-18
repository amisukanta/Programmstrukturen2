package viergewinnt;

import javafx.scene.layout.Pane;

/**
 * Verwaltet einzelne Zellen in einem zweidimensionalen Array. Das
 * Koordinatensystem beginnt dabei links oben: ___________________ |0,0 1,0 ...
 * |0,1 ... |...
 *
 * @author Jan Riemer
 */
public class CellManager {

    /**
     * Die zu verwaltenden Zellen.
     */
    private final Cell[][] cells;

    /**
     * Initialisiert einen Zell-Manager.
     *
     * @param columnCount Die Anzahl der Spalten in jeder Reihe.
     * @param rowCount Die Anzahl der Zeilen in jeder Spalte.
     */
    public CellManager(int columnCount, int rowCount) {
        this.cells = new Cell[rowCount][columnCount];
    }

    /**
     * Fügt diesem Zell-Manager eine Zelle hinzu und gibt den veränderten
     * Zell-Manager zurück.
     *
     * @param columnIdx Die Spalte, in der die neue Zelle hinzugefügt werden
     * soll
     * @param rowIdx Die Zeile, in der die neue Zelle hinzugefügt werden soll
     * @return Dieser Zell-Manager mit der hinzugefügten Zelle.
     */
    public CellManager addCell(int columnIdx, int rowIdx) {
        Cell newCell = new Cell();
        this.cells[rowIdx][columnIdx] = newCell;
        return this;
    }

    /**
     * Gibt die Zelle zurück, welche sich in einer bestimmten Spalte und Zeile
     * befindet.
     *
     * @param col Die Spalte der zurückzugebenden Zelle
     * @param row Die Zeile der zurückzugebenden Zelle
     * @return Die Zelle, welche sich in der angegebenen Spalte und Zeile
     * befindet.
     */
    public Cell getCell(int col, int row) {
        return this.cells[row][col];
    }

    /**
     * Gibt die Zelle anhand ihrer Zell-Koordinaten zurück.
     *
     * @param coords Die Koordinaten der zurückzugebenden Zelle
     * @return Die Zelle auf den angegebenen Koordinaten.
     */
    public Cell getCell(CellCoordinates coords) {
        assert (coords != null);
        return this.getCell(coords.getColumnIdx(), coords.getRowIdx());
    }

    /**
     * Gibt die Koordinaten der untersten freien Zelle aus einer Spalte zurück.
     *
     * @param columnIdx Die Spalte, aus der die unterste freie Zelle
     * zurückgegeben werden soll.
     * @return Die Koordinaten der untersten freien Zelle aus der Spalte.
     */
    public CellCoordinates getCoordsForLowestFirstFreeCell(int columnIdx) {
        int lastRow = -1;

        while (lastRow < cells.length - 1 && this.cells[lastRow + 1][columnIdx].getPlayerOwner() == null) {

            lastRow++;

        }

        if (lastRow == -1) {
            return null;
        } else {
            return new CellCoordinates(columnIdx, lastRow);
        }
    }

    /**
     * Überprüft, ob alle Zellen in diesem Zell-Manager belegt sind.
     *
     * @return true, wenn alle Zellen in diesem Zell-Manager belegt sind,
     * ansonsten false
     */
    public boolean areAllCellsReserved() {

        boolean allReserved = true;

        for (int i = 0; i < this.cells.length; i++) {

            for (int j = 0; j < this.cells[i].length; j++) {

                if (this.cells[i][j].getPlayerOwner() == null) {
                    allReserved = false;
                }
            }

        }

        return allReserved;
    }

    /**
     * Setzt für alle Zellen in diesem Zell-Manager den Event-Handler für das
     * Mouse-Click Event.
     *
     * @param clickHandler Der zu setzende Click-Handler.
     */
    public void setOnMouseClickedForCells(ClickHandler clickHandler) {
        for (Cell[] rows : this.cells) {
            for (Cell cell : rows) {
                cell.setOnMouseClicked(clickHandler);
            }
        }
    }

    /**
     * Entfernt für alle Zellen in diesem Zell-Manager den Event-Handler für das
     * Mouse-Click Event.
     */
    public void removeOnMouseClickedForCells() {
        for (Cell[] rows : this.cells) {
            for (Cell cell : rows) {
                cell.removeOnMouseClicked();
            }
        }
    }

    /**
     * Überprüft, ob die angegebenen Zell-Koordinaten innerhalb dieses
     * Zell-Managers vorhanden sind.
     *
     * @param coords Die zu überprüfenden Zell-Koordinaten
     * @return true, wenn die angegebenen Zell-Koordinaten innerhalb dieses
     * Zell-Managers vorhanden sind, ansonsten false.
     */
    public boolean areCoordsInBounds(CellCoordinates coords) {
        assert (coords != null);

        return this.cells.length - 1 >= coords.getRowIdx()
                && this.cells[0].length - 1 >= coords.getColumnIdx()
                && coords.getRowIdx() >= 0 && coords.getColumnIdx() >= 0;

    }

    /**
     * Gibt die Zell-Koordinaten eines Panes zurück, welches sich möglicherweise
     * innerhalb einer Zelle befindet, die von diesem Zell-Manager verwaltet
     * wird.
     *
     * @param pane das Pane, dessen Koordinaten ermittelt werden sollen.
     * @return Die Koordinaten des Panes, falls es innerhalb dieses
     * Zell-Managers gefunden wurde, ansonsten die null-Referenz.
     */
    public CellCoordinates findCellCoords(Pane pane) {
        int rowIdx = 0;
        boolean found = false;
        CellCoordinates result = null;
        while (rowIdx < this.cells.length && !found) {
            int colIdx = 0;
            while (colIdx < this.cells[rowIdx].length && !found) {
                if (this.getCell(colIdx, rowIdx).getPane().equals(pane)) {
                    result = new CellCoordinates(colIdx, rowIdx);
                    found = true;
                }
                colIdx++;
            }
            rowIdx++;
        }
        return result;
    }

    /**
     * Gibt, ausgehend von einem Koordinatenursprung, die Anzahl belegter Zellen
     * der längsten Reihe desjenigen Spielers zurück, welcher Inhaber der Zelle
     * ist, die auf dem spezifizierten Koordinaten- ursprung liegt.
     *
     * @param origin Der Koordinatenursprung, von dem ausgegangen werden soll
     * @return Die Anzahl belegter Zellen der längsten Reihe desjenigen
     * Spielers, welcher Inhaber der Zelle ist, die auf dem spezifizierten
     * Koordinatenursprung liegt.
     */
    public int getCountLongestRowOfPlayer(CellCoordinates origin) {
        return this.getLongestRowOfPlayer(origin).length;
    }

    /**
     * Gibt, ausgehend von einem Koordinatenursprung, die Zell-Koordinaten
     * zurück, welche die längste Reihe desjenigen Spielers bilden, welcher
     * Inhaber der Zelle ist, die auf dem spezifizierten Koordinatenursprung
     * liegt.
     *
     * @param origin Der Koordinatenursprung, von dem ausgegangen werden soll
     * @return Die Zell-Koordinaten, welche die längste Reihe desjenigen
     * Spielers bilden, welcher Inhaber der Zelle ist, die auf dem
     * spezifizierten Koordinatenursprung liegt. @param origin @return
     */
    public CellCoordinates[] getLongestRowOfPlayer(CellCoordinates origin) {
        assert (origin != null);

        CellCoordinates[] intermediateResult1;
        CellCoordinates[] intermediateResult2;
        CellCoordinates[] intermediateResult3;

        Integer size1 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN).length;
        Integer size2 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN_LEFT).length;
        Integer size3 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN_RIGHT).length;
        Integer size4 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.LEFT).length;

        CellCoordinates[] choice1 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN);
        CellCoordinates[] choice2 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN_LEFT);
        CellCoordinates[] choice3 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.DOWN_RIGHT);
        CellCoordinates[] choice4 = getLongestRowOfPlayerInDir(origin, CellCoordinates.Direction.LEFT);

        intermediateResult1 = size1 > size2 ? choice1 : choice2;
        intermediateResult2 = intermediateResult1.length > size3 ? intermediateResult1 : choice3;
        intermediateResult3 = intermediateResult2.length > size4 ? intermediateResult2 : choice4;

        return intermediateResult3;

    }

    /**
     * Gibt, ausgehend von einem Koordinatenursprung, die Zell-Koordinaten
     * zurück, welche die längste Reihe desjenigen Spielers in eine bestimmte
     * Richtung bilden, welcher Inhaber der Zelle ist, die auf dem
     * spezifizierten Koordinatenursprung liegt. Die entgegengesetzte Richtung
     * wird dabei mit berücksichtigt.
     *
     * @param origin Der Koordinatenursprung, von dem ausgegangen werden soll
     * @param dir Die Richtung, in der die längste Reihe ermittelt werden soll.
     * Die entgegengesetzte Richtung wird automatisch berücksichtigt und braucht
     * nicht separat überprüft zu werden.
     * @return Die Zell-Koordinaten, welche die längste Reihe desjenigen
     * Spielers in eine bestimmte Richtung bilden, welcher Inhaber der Zelle
     * ist, die auf dem spezifizierten Koordinatenursprung liegt.
     */
    private CellCoordinates[] getLongestRowOfPlayerInDir(
            CellCoordinates origin,
            CellCoordinates.Direction dir) {

        CellCoordinates[] firstDirArray = getLongestRowOfPlayerInOneDir(origin, dir);
        CellCoordinates[] secondDirArray = getLongestRowOfPlayerInOneDir(origin, CellCoordinates.getOppositeDirOf(dir));
        CellCoordinates[] returnCoordinatesArray;

        if ((firstDirArray.length + secondDirArray.length) > 0) {
            returnCoordinatesArray = new CellCoordinates[firstDirArray.length + secondDirArray.length - 1];
            System.arraycopy(firstDirArray, 0, returnCoordinatesArray, 0, firstDirArray.length);
            System.arraycopy(secondDirArray, 1, returnCoordinatesArray, firstDirArray.length, secondDirArray.length - 1);
        } else {
            returnCoordinatesArray = new CellCoordinates[0];
        }

        return returnCoordinatesArray;
    }

    /**
     * Gibt, ausgehend von einem Koordinatenursprung, die Zell-Koordinaten
     * zurück, welche die längste Reihe desjenigen Spielers in eine bestimmte
     * Richtung bilden, welcher Inhaber der Zelle ist, die auf dem
     * spezifizierten Koordinatenursprung liegt. Die entgegengesetzte Richtung
     * wird NICHT mit berücksichtigt.
     *
     * @param origin Der Koordinatenursprung, von dem ausgegangen werden soll
     * @param dir Die Richtung, in der die längste Reihe ermittelt werden soll.
     * @return Die Zell-Koordinaten, welche die längste Reihe desjenigen
     * Spielers in eine bestimmte Richtung bilden, welcher Inhaber der Zelle
     * ist, die auf dem spezifizierten Koordinatenursprung liegt.
     */
    private CellCoordinates[] getLongestRowOfPlayerInOneDir(
            CellCoordinates origin,
            CellCoordinates.Direction dir) {

        // Initialisierung mit "origin" Werten
        int currentRow = origin.getRowIdx();
        int currentCol = origin.getColumnIdx();
        CellCoordinates currentCellCoordinates = origin;

        // Initialisierung Return Variable
        CellCoordinates[] returnCoordinatesArray = new CellCoordinates[0];

        // Initialisierung Hilfsvariablen
        int nextRow, nextCol;
        Integer cellPlayerID;
        boolean belongsToSamePlayer = true;

        for (int i = 0; areCoordsInBounds(currentCellCoordinates)
                && this.cells[currentRow][currentCol].getPlayerOwner() != null
                && belongsToSamePlayer; i++) {

            returnCoordinatesArray = addCoordinatesToArray(i, returnCoordinatesArray, currentCellCoordinates);

            cellPlayerID = this.cells[currentRow][currentCol].getPlayerOwner();

            CellCoordinates nextCellCoordinates = CellCoordinates.getCoordsAtDir(dir, origin, i + 1);
            nextRow = nextCellCoordinates.getRowIdx();
            nextCol = nextCellCoordinates.getColumnIdx();

            if (areCoordsInBounds(nextCellCoordinates) && cellPlayerID != this.cells[nextRow][nextCol].getPlayerOwner()) {
                belongsToSamePlayer = false;
            }

            currentRow = nextRow;
            currentCol = nextCol;
            currentCellCoordinates = nextCellCoordinates;

        }

        return returnCoordinatesArray;
    }

    /**
     * Fügt dem übergegebenem Array an der Position pos die übergegebene
     * CellCoordinates hinzu
     *
     * @param pos Stelle für den neuen Wert
     * @param returnCoordinatesArray Array, dem der Wert hinzugefügt werden soll
     * @param currentCellCoordinates CellCoordinates die hinzu gefügt werden
     * sollen
     * @return Array mit hinzugefügten Wert
     */
    private CellCoordinates[] addCoordinatesToArray(int pos, CellCoordinates[] returnCoordinatesArray, CellCoordinates currentCellCoordinates) {

        CellCoordinates[] tempArray = new CellCoordinates[pos + 1];
        System.arraycopy(returnCoordinatesArray, 0, tempArray, 0, returnCoordinatesArray.length);
        tempArray[pos] = currentCellCoordinates;
        returnCoordinatesArray = new CellCoordinates[pos + 1];
        System.arraycopy(tempArray, 0, returnCoordinatesArray, 0, tempArray.length);

        return returnCoordinatesArray;
    }

    /**
     * ****************************** Methoden zum Testen ********************
     */
    public static void startTestSuite() {
        CellManager.testGetCoordsForLowestFirstFreeCell();
        CellManager.testAreAllCellsReserved();
        CellManager.testAreCoordsInBounds();
        CellManager.testGetLongestRowOfPlayer();
    }

    private CellManager(Cell[][] cellsForTest) {
        assert (cellsForTest != null);
        this.cells = cellsForTest;
    }

    private static void testGetCoordsForLowestFirstFreeCell() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Teste Methode CellManager.getCoordsForLowestFirstFreeCell");
        CellManager TestObjekt1 = new CellManager(6, 7);;
        int numOfColumns = 6;
        int numOfRows = 7;
        for (int row = 0; row < numOfRows; row++) {
            for (int column = 0; column < numOfColumns; column++) {
                TestObjekt1.addCell(column, row);
            }
        }

        System.out.println((TestObjekt1.getCoordsForLowestFirstFreeCell(0)).toString());
        TestObjekt1.getCell(0, 6).setPlayerOwner(1);
        System.out.println((TestObjekt1.getCoordsForLowestFirstFreeCell(0)).toString());
        TestObjekt1.getCell(0, 5).setPlayerOwner(1);
        TestObjekt1.getCell(0, 4).setPlayerOwner(1);
        System.out.println((TestObjekt1.getCoordsForLowestFirstFreeCell(0)).toString());
        TestObjekt1.getCell(0, 3).setPlayerOwner(0);
        System.out.println((TestObjekt1.getCoordsForLowestFirstFreeCell(0)).toString());
        System.out.println((TestObjekt1.getCoordsForLowestFirstFreeCell(1)).toString());
    }

    private static void testAreAllCellsReserved() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Teste Methode CellManager.areAllCellsReserved");
        CellManager TestObjekt2 = new CellManager(2, 2);;
        int numOfColumns = 2;
        int numOfRows = 2;
        for (int row = 0; row < numOfRows; row++) {
            for (int column = 0; column < numOfColumns; column++) {
                TestObjekt2.addCell(column, row);
            }
        }
        TestObjekt2.getCell(0, 0).setPlayerOwner(1);
        TestObjekt2.getCell(1, 0).setPlayerOwner(1);
        TestObjekt2.getCell(0, 1).setPlayerOwner(1);
        System.out.println(TestObjekt2.areAllCellsReserved());
        TestObjekt2.getCell(1, 1).setPlayerOwner(1);
        System.out.println(TestObjekt2.areAllCellsReserved());
    }

    private static void testAreCoordsInBounds() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Teste Methode CellManager.areCoordsInBounds");

        CellManager TestObjektA = new CellManager(6, 7);

        CellCoordinates TestObjekt3 = new CellCoordinates(0, 0);
        System.out.println(TestObjektA.areCoordsInBounds(TestObjekt3));
        CellCoordinates TestObjekt4 = new CellCoordinates(1, 2);
        System.out.println(TestObjektA.areCoordsInBounds(TestObjekt4));
        CellCoordinates TestObjekt5 = new CellCoordinates(5, 6);
        System.out.println(TestObjektA.areCoordsInBounds(TestObjekt5));
        CellCoordinates TestObjekt6 = new CellCoordinates(6, 5);
        System.out.println(TestObjektA.areCoordsInBounds(TestObjekt6));
        CellCoordinates TestObjekt7 = new CellCoordinates(-2, 0);
        System.out.println(TestObjektA.areCoordsInBounds(TestObjekt7));
    }

    private static void testGetLongestRowOfPlayer() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Teste Methode CellManager.getLongestRowOfPlayer");

        CellManager TestObjektA = new CellManager(6, 7);
        int numOfColumns = 6;
        int numOfRows = 7;
        for (int row = 0; row < numOfRows; row++) {
            for (int column = 0; column < numOfColumns; column++) {
                TestObjektA.addCell(column, row);
            }
        }

        CellCoordinates testCoordinates = new CellCoordinates(1, 1);

        TestObjektA.getCell(1, 0).setPlayerOwner(1);
        TestObjektA.getCell(1, 1).setPlayerOwner(1);
        TestObjektA.getCell(1, 2).setPlayerOwner(1);
        TestObjektA.getCell(0, 1).setPlayerOwner(1);
        TestObjektA.getCell(1, 1).setPlayerOwner(1);
        TestObjektA.getCell(2, 1).setPlayerOwner(1);
        TestObjektA.getCell(3, 1).setPlayerOwner(1);
        TestObjektA.getCell(0, 0).setPlayerOwner(1);
        TestObjektA.getCell(2, 2).setPlayerOwner(1);
        TestObjektA.getCell(3, 3).setPlayerOwner(1);
        TestObjektA.getCell(4, 4).setPlayerOwner(1);

        System.out.println((TestObjektA.getLongestRowOfPlayer(testCoordinates)).length);
    }
}
