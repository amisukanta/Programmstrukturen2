
package grp27_ueb02;

/**
 *
 * @author Robin Böckmann
 */
public class Grp27_ueb02 {

    /**
     * Gibt folgende Daten direkt auf der Konsole aus: Ort der Messung, erlaubte
     * Geschwindigkeit, mittlere Geschwindigkeit und Median Geschwindigkeit.
     * Weitere Ausgaben erfolgen über die aufgerufene Methode
     * printValuesOfOffenders.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String location;

        if (MeasuredValues.isInTown() == true) {
            location = "innerorts";
        } else {
            location = "außerorts";
        }

        System.out.printf("Messung \t \t: %s %n", location);
        System.out.printf("erlaubte Geschwindigkeit: %d    km/h %n", MeasuredValues.getAllowedSpeed());
        System.out.printf("mittlere Geschwindigkeit: %.2f km/h %n", MeasuredValues.getAverage());
        System.out.printf("Median Geschwindigkeit\t: %.2f km/h %n %n", MeasuredValues.getMedian());

        Fines.createDataArray();

        for (int i = 0; i < 10; i++) {
            printValuesOfOffenders(i);
        }

    }

    /**
     * Gibt folgende Werte für einen eingegeben Strafindex in der Konsole aus:
     * Anzahl der Überschreitungen, Bereich des Strafindex in km/h, Strafe in
     * Euro und gemessene Werte in aufsteigenender Ordnung.
     *
     * @param strafindex: Die Nummer des Strafbereichs an (0 - 9)
     */
    public static void printValuesOfOffenders(int strafindex) {

        if (Fines.getData(strafindex).length != 0 && strafindex <= 9) {
            System.out.printf("%d Überschreitung(en) [ %3d .. %3d km/h] mit %3d € Strafe: ",
                    Fines.getData(strafindex).length, Fines.getMinSpeedForFine(strafindex),
                    Fines.getMaxSpeedForFine(strafindex), Fines.getFineForIndex(strafindex));

            for (int i = 0; i < Fines.getData(strafindex).length; i++) {
                System.out.printf("%d", Fines.getData(strafindex)[i]);
                if (i + 1 < Fines.getData(strafindex).length) {
                    System.out.printf(", ");
                } else {
                    System.out.printf("%n");
                }
            }

        }

    }
}
