/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb07;

/**
 *
 * @author Robin
 */
public class ArrayRow extends Row {

    public Gap[] gapArray;

    public ArrayRow(int length) {
        gapArray = new Gap[1];
        this.gapArray[0] = new Gap(0, length);
        this.length = length;
    }

    public ArrayRow(int length, int[][] carList) {
        this(length);

        for (int[] carList1 : carList) {
            this.gapArray = this.remove(new Gap(carList1[0], carList1[0] + carList1[1]));
        }
    }

    public Gap[] getArray() {
        return this.gapArray;
    }

    /**
     * prüft, ob die Reihe unbesetzt ist
     *
     * @return true, wenn die Reihe komplett unbesetzt ist
     */
    @Override
    public boolean isEmpty() {

        return this.gapArray.length > 0 && this.gapArray[0].getLength() == this.getLength();
    }

    /**
     * prüft, ob keine Lücken enthalten sind
     *
     * @return true, wenn keine Lücke enthalten ist
     */
    @Override
    public boolean isOptimallyFilled() {

        return !this.hasGap(1);
    }

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     *
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    @Override
    public boolean hasFreeGap(int carLength) {

        return this.hasGap(carLength);
    }

    /**
     * liefert die größte Lücke in der Reihe
     *
     * @return die größte Lücke in der Reihe
     */
    @Override
    public int getLargestGap() {

        int largestGap = 0;

        for (int i = 0; i < getArray().length; i++) {
            if (getArray()[i].getLength() > largestGap) {
                largestGap = getArray()[i].getLength();
            }
        }
        return largestGap;
    }

    /**
     * liefert die Position der kleinsten Lücke, die größer ist als minLength
     *
     * @param minLength Mindestgröße der gesuchten Lücke
     * @param yetMinLength bisher kleinste gefundene Lücke mit Mindestgröße
     * @param foundPosition Position der bisher kleinsten gefundenen Lücke mit
     * Mindestgröße
     * @return Position der kleinsten Lücke, die größer ist als minLength, -1,
     * wenn keine passende Lücke gefunden wurde
     */
    private int getSmallestGapBiggerThan(int minLength, int yetMinLength, int foundPosition) {
        for (int i = 0; i < getArray().length; i++) {

            if (getArray()[i].getLength() >= minLength && getArray()[i].getLength() < yetMinLength) {

                yetMinLength = getArray()[i].getLength();
                foundPosition = getArray()[i].getStart();

            }
        }
        return foundPosition;
    }

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     *
     * @param carlength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    @Override
    public int findBestPosition(int carlength) {

        return this.getSmallestGapBiggerThan(carlength, Integer.MAX_VALUE, -1);
    }

    /**
     * enthält eine Lücke an der Position, Startposition und Länge muss nicht
     * mit einer bestehenden übereinstimmen
     *
     * @param position Position der Lücke
     * @param length Länge der Lücke
     * @return true, wenn Lücke gefunden wurde, die Lücke mit den Angaben
     * enthält
     */
    private boolean hasGapAt(int position, int length) {

        for (int i = 0; i < getArray().length; i++) {
            if (getArray()[i].contains(new Gap(position, position + length))) {
                return true;
            }
        }

        return false;
    }

    /**
     * belegt Lücke
     *
     * @param position an der Position
     * @param carLength mit der Länge
     * @return true, wenn die Lücke noch frei war
     */
    @Override
    public boolean occupyPlace(int position, int carLength) {
        if (hasGapAt(position, carLength)) {
            gapArray = remove(position, carLength);
            return true;
        } else {
            return false;
        }
    }

    /**
     * belegt Lücke für dieses Auto
     *
     * @param carLength
     * @return Position des Autos, -1, wenn keine passende Lücke vorhanden
     */
    @Override
    public int parkCar(int carLength) {
        int bestPosition = this.findBestPosition(carLength);

        if (bestPosition != -1) {
            gapArray = remove(bestPosition, carLength);
        }

        return bestPosition;
    }

    /**
     * gibt den Platz an position wieder frei. Die Angabe muss nicht exakt sein,
     * sondern darf mit einer bestehenden Lücke überlappen. Es wird dann nur der
     * belegte Teil freigegeben.
     *
     * @param position Startposition der Freigabe
     * @param length Länge der Freigabe
     */
    @Override
    public void freePlaceAt(int position, int length) {
        if (position + length <= this.getLength()) {
            gapArray = insert(new Gap(position, length + position));
        } else {
            assert false : "place to set free is outside of the row";
        }
    }

    /**
     * überschreibt equals()
     *
     * @param obj soll auch eine ListRow sein
     * @return true, wenn Reihen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        boolean isSame = false;

        if (obj instanceof ArrayRow) {

            ArrayRow rowObject = (ArrayRow) obj;

            for (int i = 0; i < rowObject.getArray().length || i < this.getArray().length; i++) {
                if (this.getArray()[i].equals(rowObject.getArray()[i])) {
                    isSame = true;
                }
            }

        }
        return isSame;
    }

    /**
     * liefert eine Stringdarstellung der Lückenliste
     *
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public String toString() {

        String returningString = "{";

        for (int i = 0; i < this.gapArray.length; i++) {
            returningString = returningString + this.gapArray[i].toString() + (i < this.gapArray.length - 1 ? ", " : "");
        }

        return returningString + "}";
    }

    /**
     * fügt die Lücke als neues Listenelement sortiert ein. Überschneidet
     * (overlaps) oder berührt (touches) die einzufügende Lücke gap eine in der
     * Liste vorhandene Lücke, so werden diese beiden vereinigt (union) und in
     * die Liste eingefügt, die vormals vorhandene ausgekettet.
     *
     * @param gap Lücke, die als Element einzufügen ist
     * @return die Liste mit eingefügter Lücke
     */
    public Gap[] insert2(Gap gap) {

        Gap[] newArr = this.gapArray;

        for (int i = 0; i < this.getArray().length; i++) {

            if (this.getArray()[i].touches(gap) || this.getArray()[i].overlaps(gap)) {
                Gap newUnionedGap = this.getArray()[i].union(gap);
                newArr = new Gap[this.getArray().length];
                System.arraycopy(this.getArray(), 0, newArr, 0, this.getArray().length);
                newArr[i] = newUnionedGap;
                return newArr;

            } else if (gap.precedes(this.getArray()[i])) {
                newArr = new Gap[this.getArray().length + 1];
                System.arraycopy(this.getArray(), 0, newArr, 0, i);
                newArr[i] = gap;
                System.arraycopy(this.getArray(), i, newArr, i + 1, this.getArray().length);
                return newArr;

            } else if (i == this.getArray().length - 1 && newArr == this.gapArray) {
                newArr = new Gap[this.getArray().length + 1];
                System.arraycopy(this.getArray(), 0, newArr, 0, this.getArray().length);
                newArr[this.getArray().length] = gap;

            }

        }
        return newArr;
    }

    public Gap[] insert(Gap gap) {

        Gap[] newArr = this.gapArray;
        int firstOccTouch = -1;
        int firstOccPrecedes = -1;
        int countTouches = 0;

        for (int i = 0; i < this.getArray().length; i++) {

            if (this.getArray()[i].touches(gap) || this.getArray()[i].overlaps(gap)) {
                countTouches++;
                if (firstOccTouch == -1) {
                    firstOccTouch = i;
                }

            } else if (gap.precedes(this.getArray()[i])) {
                if (firstOccPrecedes == -1) {
                    firstOccPrecedes = i;
                }
            }
        }

        if (firstOccTouch > -1) {

            Gap newUnionedGap = null;

            for (int i = 0; i < countTouches; i++) {
                newUnionedGap = this.getArray()[firstOccTouch + i].union(gap);
                gap = newUnionedGap;
            }

            newArr = new Gap[(this.getArray().length - countTouches) + 1];
            System.arraycopy(this.getArray(), 0, newArr, 0, firstOccTouch);
            newArr[firstOccTouch] = newUnionedGap;
            System.arraycopy(this.getArray(), firstOccTouch + countTouches, newArr, firstOccTouch + 1, this.getArray().length - firstOccTouch - countTouches);
            return newArr;
        } else if (firstOccPrecedes > -1) {
            newArr = new Gap[this.getArray().length + 1];
            System.arraycopy(this.getArray(), 0, newArr, 0, firstOccPrecedes);
            newArr[firstOccPrecedes] = gap;
            System.arraycopy(this.getArray(), firstOccPrecedes, newArr, firstOccPrecedes + 1, this.getArray().length);
            return newArr;
        } else {
            newArr = new Gap[this.getArray().length + 1];
            System.arraycopy(this.getArray(), 0, newArr, 0, this.getArray().length);
            newArr[this.getArray().length] = gap;
        }

        return newArr;
    }

    /**
     * belegt Platz an der angegebenen Position mit der angegebenen Länge.
     * Dadurch kann eine Lücke komplett verschwinden (ausgefüllt werden), oder
     * verkürzt werden oder zwei Lücken entstehen, wenn die Belegung mittig
     * erfolgt. Die Nutzung von Gap.sub() ist sinnvoll.
     *
     * @param position Position der Belegung
     * @param length Länge der Belegung
     * @return die evtl. veränderte Liste
     */
    public Gap[] remove(int position, int length) {
        Gap newGap = new Gap(position, position + length);
        return remove(newGap);
    }

    /**
     * entfernt eine Lücke aus der Liste. Die Lücke muss nicht mit den gleichen
     * Grenzen in der Liste stehen! Entfernen einer Lücke ist gleichbedeutend
     * mit Okkupieren einer Stelle.
     *
     * @param gap freizugebendes Lücke
     * @return die Liste mit freigegebenem Lücke
     */
    private Gap[] remove(Gap gap) {
        Gap[] arrayCopy = new Gap[this.getArray().length];
        System.arraycopy(this.getArray(), 0, arrayCopy, 0, this.getArray().length);

        for (int i = 0; i < arrayCopy.length; i++) {

            Gap[] arrayGap = arrayCopy[i].sub(gap);

            if (arrayGap.length == 0) {

                Gap[] newArr = new Gap[arrayCopy.length - 1];

                for (int j = i; j < arrayCopy.length - 1; j++) {
                    arrayCopy[j] = arrayCopy[j + 1];
                }

                System.arraycopy(arrayCopy, 0, newArr, 0, arrayCopy.length - 1);
                arrayCopy = new Gap[newArr.length];
                System.arraycopy(newArr, 0, arrayCopy, 0, arrayCopy.length);

            } else if (arrayGap.length == 1 && arrayGap[0] != arrayCopy[i]) {
                arrayCopy[i] = arrayGap[0];
            } else if (arrayGap.length == 2) {

                Gap[] newArr = new Gap[arrayCopy.length + 1];

                System.arraycopy(arrayCopy, 0, newArr, 0, i);
                newArr[i] = arrayGap[0];
                newArr[i + 1] = arrayGap[1];

                System.arraycopy(arrayCopy, i + 1, newArr, i + 2, arrayCopy.length - i - 1);
                arrayCopy = new Gap[newArr.length];
                System.arraycopy(newArr, 0, arrayCopy, 0, newArr.length);
            }
        }

        return arrayCopy;
    }

    private boolean hasGap(int length) {

        boolean foundGap = false;

        for (int i = 0; i < this.getArray().length; i++) {
            if (this.getArray()[i].getLength() >= length) {
                foundGap = true;
            }
        }

        return foundGap;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        testIsOptimallyFilled();
        testIsEmpty();
        testHasFreeGap();
        testGetLargestGap();
        testFindBestPosition();
        testOccupyPlace();
        testParkCar();
        testFreePlaceAt();
        testEqual();
    }

    private static void testIsOptimallyFilled() {

        int[][] carList1 = {{0, 10}, {10, 20}};

        ArrayRow reihe1 = new ArrayRow(100);
        ArrayRow reihe2 = new ArrayRow(30, carList1);

        assert false == reihe1.isOptimallyFilled();
        assert true == reihe2.isOptimallyFilled();

        System.out.println("Test isOptimallyFilled() successful");
    }

     private static void testIsEmpty() {

        int[][] carList1 = {{0, 10}, {10, 30},};
        int[][] carList3 = {{0, 20}};
        int[][] carList4 = {{0, 20}, {22, 30}};

        ArrayRow reihe1 = new ArrayRow(100);
        ArrayRow reihe2 = new ArrayRow(30, carList1);
        ArrayRow reihe3 = new ArrayRow(30, carList3);
        ArrayRow reihe4 = new ArrayRow(30, carList4);

        assert true == reihe1.isEmpty();
        assert false == reihe2.isEmpty();
        assert false == reihe3.isEmpty();
        assert false == reihe4.isEmpty();

        System.out.println("Test isEmpty() successful");
    }
    
   private static void testHasFreeGap() {
        int[][] carList1 = {{0, 10}, {10, 20},};
        int[][] carList2 = {{0, 5}, {7, 10}, {22, 8}};

        ArrayRow reihe1 = new ArrayRow(100);
        ArrayRow reihe2 = new ArrayRow(30, carList1);
        ArrayRow reihe3 = new ArrayRow(30, carList2);

        assert true == reihe1.hasFreeGap(5);
        assert false == reihe2.hasFreeGap(5);
        assert true == reihe3.hasFreeGap(5);
        assert false == reihe3.hasFreeGap(6);

        System.out.println("Test hasFreeGap() successful");

    }

      private static void testGetLargestGap() {
        int[][] carList1 = {{0, 5}, {7, 10}, {22, 10}, {37, 3}, {100, 150}};
        int[][] carList2 = {{0, 10}, {10, 20}};

        ArrayRow reihe = new ArrayRow(100);
        ArrayRow reihe2 = new ArrayRow(150, carList1);
        ArrayRow reihe3 = new ArrayRow(30, carList2);

        assert 100 == reihe.getLargestGap();
        assert 110 == reihe2.getLargestGap();
        assert 0 == reihe3.getLargestGap();

        System.out.println("Test getLargestGap() successful");
    }

    private static void testFindBestPosition() {

        int[][] carList = {{0, 10}, {23, 7}, {33, 7}, {42, 3}, {46, 4}};
        ArrayRow reihe = new ArrayRow(150, carList);

        //System.out.println(reihe.toString());
        assert "{ 10.. 23,  30.. 33,  40.. 42,  45.. 46,  50..150}".equals(reihe.toString());

        assert 40 == reihe.findBestPosition(2);

        assert 50 == reihe.findBestPosition(20);

        assert 10 == reihe.findBestPosition(10);

        assert -1 == reihe.findBestPosition(110);

        System.out.println("Test findBestPosition() successful");
    }

    private static void testOccupyPlace() {

        int[][] carList = {{0, 10}, {20, 10},{40,10}};
        ArrayRow reihe = new ArrayRow(100, carList);

        assert true == reihe.occupyPlace(10, 10);
        assert true == reihe.occupyPlace(50, 8);
        assert false == reihe.occupyPlace(10, 11);
        assert false == reihe.occupyPlace(9, 10);

        System.out.println("Test occupyPlace() successful");

    }

    private static void testParkCar() {

        int[][] carList = {{0, 10}, {23, 7}, {33, 7}, {42, 3}, {46, 4}};
        ArrayRow reihe = new ArrayRow(150, carList);

        //System.out.println(reihe.toString());
        assert "{ 10.. 23,  30.. 33,  40.. 42,  45.. 46,  50..150}".equals(reihe.toString());

        assert 40 == reihe.parkCar(2);
        //System.out.println(reihe.toString());
        assert "{ 10.. 23,  30.. 33,  45.. 46,  50..150}".equals(reihe.toString());

        assert 50 == reihe.parkCar(20);
        //System.out.println(reihe.toString());
        assert "{ 10.. 23,  30.. 33,  45.. 46,  70..150}".equals(reihe.toString());

        assert 10 == reihe.parkCar(10);
        //System.out.println(reihe.toString());
        assert "{ 20.. 23,  30.. 33,  45.. 46,  70..150}".equals(reihe.toString());

        assert -1 == reihe.parkCar(100);
        //System.out.println(reihe.toString());
        assert "{ 20.. 23,  30.. 33,  45.. 46,  70..150}".equals(reihe.toString());

        System.out.println("Test parkCar() successful");
    }

    private static void testFreePlaceAt() {

        int[][] carList = {{0, 5}, {7, 3}, {15, 5}, {25, 5}, {100, 50}};
        ArrayRow reihe = new ArrayRow(150, carList);

        //System.out.println(reihe.toString());
        assert "{  5..  7,  10.. 15,  20.. 25,  30..100}".equals(reihe.toString());

        reihe.freePlaceAt(0, 20);
        //System.out.println(reihe.toString());
        assert "{  0.. 25,  30..100}".equals(reihe.toString()) : "free place, across three car spots, went wrong";

        reihe.freePlaceAt(120, 5);
        //System.out.println(reihe.toString());
        assert "{  0.. 25,  30..100, 120..125}".equals(reihe.toString()) : "free place, in between a car spot, went wrong";

        reihe.freePlaceAt(25, 5);
        //System.out.println(reihe.toString());
        assert "{  0..100, 120..125}".equals(reihe.toString()) : "free place, across one car spot, went wrong";

        reihe.freePlaceAt(50, 25);
        //System.out.println(reihe.toString());
        assert "{  0..100, 120..125}".equals(reihe.toString()) : "free place, across a free place, went wrong";

        reihe.freePlaceAt(145, 5);
        //System.out.println(reihe.toString());
        assert "{  0..100, 120..125, 145..150}".equals(reihe.toString()) : "free place, across one car spot at the end of the row, went wrong";

        System.out.println("Test freePlaceAt() successful");

    }

    private static void testEqual() {
        int[][] carList1 = {{0, 10}, {10, 30},};

        int[][] carList2 = {{0, 10}, {10, 30},};

        int[][] carList3 = {{0, 10}, {80, 100}};

        ArrayRow reihe1 = new ArrayRow(150, carList1);
        ArrayRow reihe2 = new ArrayRow(150, carList2);
        ArrayRow reihe3 = new ArrayRow(100, carList1);
        ArrayRow reihe4 = new ArrayRow(150, carList3);

        assert true == reihe1.equals(reihe2) : "equal rows as unequal detected";
        assert false == reihe2.equals(reihe3) : "unequal rows as equal detected";
        assert false == reihe1.equals(reihe3) : "unequal rows as equal detected";
        assert false == reihe1.equals(reihe4) : "unequal rows as equal detected";
        assert true == reihe4.equals(reihe4) : "equal rows as unequal detected";

        System.out.println("Test equal() successful");
    }
}
