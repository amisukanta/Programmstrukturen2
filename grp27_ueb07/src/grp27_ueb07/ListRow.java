package grp27_ueb07;


/**
 * eine Parkreihe wird abgebildet Initial besteht eine Reihe aus einer einzigen
 * riesigen Lücke.
 *
 * @author Gerit
 */
public class ListRow extends Row {

    private GapList gapList;

    /**
     * Konstruktor enthält Länge, damit gleich eine Lücke über diese Länge
     * angelegt werden kann
     *
     * @param length Länge der Reihe
     */
    public ListRow(int length) {

        this.gapList = new GapListEmptyElement().insert(new Gap(0, length));
        this.length = length;
    }

    /**
     * Konstruktor zum Testen bekommt alle Belegungen in Array übergeben
     *
     * @param length Länge der Reihe
     * @param carList zweidimensionales Array [alle Belegungen][Position und
     * Länge der Belegung]
     */
    ListRow(int length, int[][] carList) {
        this(length);

        for (int[] carList1 : carList) {
            gapList = gapList.remove(new Gap(carList1[0], carList1[0] + carList1[1]));
        }

      
    }

    /**
     * prüft, ob die Reihe aus einer einzigen Lücke besteht
     *
     * @return true, wenn die Reihe aus einer einzigen Lücke besteht
     */
    public boolean isEmpty2() {

        return gapList.hasJustOneElement();
    }

    /**
     * prüft, ob die Reihe unbesetzt ist
     *
     * @return true, wenn die Reihe komplett unbesetzt ist
     */
    @Override
    public boolean isEmpty() {

        return gapList.hasJustOneElement() && gapList.getPayload().getLength() == this.length;
    }

    /**
     * prüft, ob keine Lücken enthalten sind
     *
     * @return true, wenn keine Lücke enthalten ist
     */
    @Override
    public boolean isOptimallyFilled() {

        return !gapList.hasGap(1);
    }

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     *
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    @Override
    public boolean hasFreeGap(int carLength) {

        return gapList.hasGap(carLength);
    }

    /**
     * liefert die größte Lücke in der Reihe
     *
     * @return die größte Lücke in der Reihe
     */
    @Override
    public int getLargestGap() {

        return gapList.getLargestGap();
    }

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     *
     * @param carlength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    @Override
    public int findBestPosition(int carlength) {

        return gapList.findPositionFor(carlength);
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

        if (gapList.hasGapAt(position, carLength)) {
            gapList = gapList.remove(position, carLength);
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
            gapList = gapList.remove(bestPosition, carLength);
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
            gapList = gapList.insert(new Gap(position, length + position));
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
        if (obj instanceof ListRow) {

            ListRow rowObject = (ListRow) obj;

            return this.gapList.equals(rowObject.gapList);
        } else {
            return false;
        }

    }

    /**
     * liefert eine Stringdarstellung der Lückenliste
     *
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public String toString() {
        return "{" + this.gapList.toString() + "}";
    }

    // --- Tests ------------------------------------------------
    public static void main(String[] args) {

        testIsEmpty();
        testIsOptimallyFilled();
        testHasFreeGap();
        testGetLargestGap();
        testFindBestPosition();
        testOccupyPlace();
        testParkCar();
        testFreePlaceAt();
        testEqual();

    }

    private static void testIsEmpty() {

        int[][] carList1 = {{0, 10}, {10, 30},};
        int[][] carList3 = {{0, 20}};
        int[][] carList4 = {{0, 20}, {22, 30}};

        ListRow reihe1 = new ListRow(100);
        ListRow reihe2 = new ListRow(30, carList1);
        ListRow reihe3 = new ListRow(30, carList3);
        ListRow reihe4 = new ListRow(30, carList4);

        //assert true == reihe1.isEmpty2();
        //assert false == reihe2.isEmpty2();
        //assert true == reihe3.isEmpty2();
        //assert true == reihe4.isEmpty2();

        assert true == reihe1.isEmpty();
        assert false == reihe2.isEmpty();
        assert false == reihe3.isEmpty();
        assert false == reihe4.isEmpty();

        System.out.println("Test isEmpty() successful");
    }

    private static void testIsOptimallyFilled() {

        int[][] carList1 = {{0, 10}, {10, 20}};

        ListRow reihe1 = new ListRow(100);
        ListRow reihe2 = new ListRow(30, carList1);

        assert false == reihe1.isOptimallyFilled();
        assert true == reihe2.isOptimallyFilled();

        System.out.println("Test isOptimallyFilled() successful");
    }

    private static void testHasFreeGap() {
        int[][] carList1 = {{0, 10}, {10, 20},};
        int[][] carList2 = {{0, 5}, {7, 10}, {22, 8}};

        ListRow reihe1 = new ListRow(100);
        ListRow reihe2 = new ListRow(30, carList1);
        ListRow reihe3 = new ListRow(30, carList2);

        assert true == reihe1.hasFreeGap(5);
        assert false == reihe2.hasFreeGap(5);
        assert true == reihe3.hasFreeGap(5);
        assert false == reihe3.hasFreeGap(6);

        System.out.println("Test hasFreeGap() successful");

    }

    private static void testGetLargestGap() {
        int[][] carList1 = {{0, 5}, {7, 10}, {22, 10}, {37, 3}, {100, 150}};
        int[][] carList2 = {{0, 10}, {10, 20}};

        ListRow reihe = new ListRow(100);
        ListRow reihe2 = new ListRow(150, carList1);
        ListRow reihe3 = new ListRow(30, carList2);

        assert 100 == reihe.getLargestGap();
        assert 110 == reihe2.getLargestGap();
        assert 0 == reihe3.getLargestGap();

        System.out.println("Test getLargestGap() successful");
    }

    private static void testFindBestPosition() {

        int[][] carList = {{0, 10}, {23, 7}, {33, 7}, {42, 3}, {46, 4}};
        ListRow reihe = new ListRow(150, carList);

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
        ListRow reihe = new ListRow(100, carList);

        assert true == reihe.occupyPlace(10, 10);
        assert true == reihe.occupyPlace(50, 8);
        assert false == reihe.occupyPlace(10, 11);
        assert false == reihe.occupyPlace(9, 10);

        System.out.println("Test occupyPlace() successful");

    }

    private static void testParkCar() {

        int[][] carList = {{0, 10}, {23, 7}, {33, 7}, {42, 3}, {46, 4}};
        ListRow reihe = new ListRow(150, carList);

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
        ListRow reihe = new ListRow(150, carList);

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

        ListRow reihe1 = new ListRow(150, carList1);
        ListRow reihe2 = new ListRow(150, carList2);
        ListRow reihe3 = new ListRow(100, carList1);
        ListRow reihe4 = new ListRow(150, carList3);

        assert true == reihe1.equals(reihe2) : "equal rows as unequal detected";
        assert false == reihe2.equals(reihe3) : "unequal rows as equal detected";
        assert false == reihe1.equals(reihe3) : "unequal rows as equal detected";
        assert false == reihe1.equals(reihe4) : "unequal rows as equal detected";
        assert true == reihe4.equals(reihe4) : "equal rows as unequal detected";

        System.out.println("Test equal() successful");
    }

}
