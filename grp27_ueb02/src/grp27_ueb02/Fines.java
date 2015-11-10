
package grp27_ueb02;

/**
 *
 * @author Robin Böckmann
 */
public class Fines {

    private static int[][] dataArray;

    /**
     * Bussgeldbereiche (Überschreitung bis zu x km/h) Fährt also jemand bei
     * erlaubten 10 Stundenkilometern außerorts 60 km/h, so muss er 10 Euro
     * bezahlen, bei 61 km/h muss er 20 Euro bezahlen.
     */
    private static final int[] RANGES = {
        10, 15, 20, 25, 30, 40, 50, 60, 70
    };
    
    /**
     * Bussgelder in Euro bei Geschwindigkeitsüberschreitung außerorts und
     * innerorts
     */
    private static final int[][] FINES = {
        {10, 20, 30, 70, 80, 120, 160, 240, 440, 600},
        {15, 25, 35, 80, 100, 160, 200, 280, 480, 680}
    };

    /**
     * Gibt die Anzahl der Strafbereiche zurück
     *
     * @return: Anzahl der Strafbereich
     */
    public static int getNoofFineRanges() {

        int NoofFineRanges = FINES[0].length;

        return NoofFineRanges;
    }

    /**
     * Gibt das Bußgeld für den eingebenen Strafindex zurück
     *
     * @param strafindex: Index für den Strafbereich
     * @return: Bussgeld für den Strafbereich
     */
    public static int getFineForIndex(int strafindex) {

        int fineIndex;

        if (MeasuredValues.isInTown() == false) {
            fineIndex = 0;
        } else {
            fineIndex = 1;
        }

        return FINES[fineIndex][strafindex];
    }

    /**
     * Gibt die minimale Geschwindigkeit eines Strafbereichs zurück.
     *
     * @param strafindex: Index für den Strafbereich
     * @return: minimale Geschwindigkeit für den Strafbereich
     */
    public static int getMinSpeedForFine(int strafindex) {

        int minSpeedForFine;

        if (strafindex == 0) {
            minSpeedForFine = MeasuredValues.getAllowedSpeed() + 1;
        } else {
            minSpeedForFine = MeasuredValues.getAllowedSpeed() + RANGES[strafindex - 1] + 1;
        }

        return minSpeedForFine;
    }

    /**
     * Gibt die maximale Geschwindigkeit eines Strafbereichs zurück.
     *
     * @param strafindex: Index für den Strafbereich
     * @return: maximale Geschwindigkeit für den Strafbereich
     */
    public static int getMaxSpeedForFine(int strafindex) {

        int maxSpeedForFine = 0;

        if (RANGES.length > strafindex) {
            maxSpeedForFine = MeasuredValues.getAllowedSpeed() + RANGES[strafindex];
        } else {
            maxSpeedForFine = MeasuredValues.getMaxSpeed();
        }
        return maxSpeedForFine;
    }

    /**
     * Erzeugt ein zweidimensionales Array. Die Größe der ersten Dimension ist
     * gleich der Anzahl der Strafbereiche. Die zweite Dimension besteht aus
     * Arrays, dessen Inhalte die gemessenen Geschwindigkeiten in dem
     * zugehörigen Strafbereich sind
     *
     */
    public static void createDataArray() {

        dataArray = new int[FINES[0].length][];

        for (int i = 0; i < FINES[0].length; i++) {

            dataArray[i] = MeasuredValues.getValuesInRange(getMinSpeedForFine(i), getMaxSpeedForFine(i));

        }
    }

    /**
     * Gibt das Array für den Strafindex zurück, dessen Inhalt alle gemessene
     * Werte in diesem Bereich sind
     *
     * @param strafindex: Index für den Strafbereich
     * @return: Array mit gemessenen Werte im Strafbereich
     */
    public static int[] getData(int strafindex) {

        return dataArray[strafindex];
    }

}
