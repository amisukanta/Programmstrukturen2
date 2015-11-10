
package grp27_ueb02;

/**
 *
 * @author Robin Böckmann
 */
public class ArraySort {

    /**
     * Sortiert die Werte eines Arrays nach aufsteigender Folge mit dem
     * sogenantnen Bubblesort-Algorithmus
     *
     * @param sortierArray: Das Array, dessen Inhalt sortiert werden soll
     * @return: Eine Kopie des Arrays in sortierter Form
     */
    public static int[] bubblesort(int[] sortierArray) {
        int temp;
        for (int i = 1; i < sortierArray.length; i++) {
            for (int j = 0; j < sortierArray.length - i; j++) {
                if (sortierArray[j] > sortierArray[j + 1]) {
                    temp = sortierArray[j];
                    sortierArray[j] = sortierArray[j + 1];
                    sortierArray[j + 1] = temp;
                }

            }
        }
        return sortierArray;
    }
}
