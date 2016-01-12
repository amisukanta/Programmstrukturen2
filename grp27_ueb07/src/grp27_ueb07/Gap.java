package grp27_ueb07;

//import static java.lang.Integer.max;
//import static java.lang.Integer.min;
/**
 * Eine Lücke besitzt eine Start- und ein Endposition. Die Startposition ist
 * inklusive, die Endangabe exklusive Es darf also nie die Startpositon gleich
 * der Endpositon sein.
 *
 * @author Gerit
 */
public class Gap {

    /**
     * Start- und Endposition der Lücke die Startposition ist inklusive, die
     * Endangabe exklusive Es darf also nie die Startpositon gleich der
     * Endpositon sein.
     */
    private final int start;
    private final int end;

    public Gap(int start, int end) {
        assert start >= 0 && end > start;
        this.start = start;
        this.end = end;
    }

    /**
     * @return Startposition der Lücke (diese Angabe ist inklusiv)
     */
    public int getStart() {
        return start;
    }

    /**
     * @return Endposition der Lücke (diese Angabe ist exclusiv)
     */
    public int getEnd() {
        return end;
    }

    /**
     * die Angabe ist rechtsoffen, d.h. beträgt die EndPosition 30, so gehört
     * die Position 30 nicht mehr zu der Belegung
     *
     * @return die Länge der Lücke
     */
    public int getLength() {
        return this.getEnd() - this.getStart();
    }

    /**
     * prüft, ob this hinter other liegt. Dafür muss this beginnen, nachdem
     * other beendet ist.
     *
     * @param other Lücke, welche möglicherweise hinter der aktuellen liegt
     * @return true, wenn this hinter other liegt
     */
    public boolean succeeds(Gap other) {
        return this.getStart() >= other.getEnd();
    }

    /**
     * prüft, ob this vor other liegt. Dafür muss this enden bevor other
     * beginnt.
     *
     * @param other Lücke, die möglicherweise vor der aktuellen liegt
     * @return true, wenn this vor other liegt
     */
    public boolean precedes(Gap other) {
        return other.getStart() >= this.getEnd();
    }

    /**
     * prüft, ob other direkt an this angrenzt
     *
     * @param other Lücke, die direkt angrenzen könnte
     * @return true, wenn other direkt an this angrenzt
     */
    public boolean touches(Gap other) {
        return this.getStart() == other.getEnd() || this.getEnd() == other.getStart();
    }

    /**
     * prüft, ob this die Position posi enthält
     *
     * @param posi Position, die möglicherweise enthalten ist
     * @return true, wenn this die Position posi enthält, sonst false
     */
    public boolean contains(int posi) {
        return posi >= this.getStart() && posi < this.getEnd();
    }

    /**
     * prüft, ob this other komplett enthält oder gleich ist
     *
     * @param other Lücke, die möglicherweise enthalten ist
     * @return true, wenn this other komplett enthält oder gleiche Grenzen hat,
     * sonst false
     */
    public boolean contains(Gap other) {
        return this.contains(other.getStart()) && (this.contains(other.getEnd()) || this.getEnd() == other.getEnd());
    }

    /**
     * prüft, ob this und other überlappen. Dafür muss this beginnen, bevor
     * other endet und enden, nachdem other startet.
     *
     * @param other Lücke, die möglicherweise überlappt
     * @return true, wenn this und other überlappen
     */
    public boolean overlaps(Gap other) {
        return !(this.precedes(other) || this.succeeds(other));
    }

    /**
     * liefert eine neue Lücke, welche this und other vereinigt
     *
     * @param other zu vereinigende Lücke
     * @return eine neue Lücke, welche this und other vereinigt; null, wenn this
     * und other weder überlappen noch aneinander grenzen
     */
    public Gap union(Gap other) {
        if (this.overlaps(other) || this.touches(other)) {
            Gap unionGap = new Gap(this.getStart() < other.getStart() ? this.getStart() : other.getStart(),
                    this.getEnd() > other.getEnd() ? this.getEnd() : other.getEnd());
            return unionGap;
        } else {
            return null;
        }

    }

    /**
     * liefert eine neue Lücke zwischen this und other
     *
     * @param other die zweite Lücke
     * @return eine neue Lücke zwischen this und other null, wenn die Lücken
     * überlappen oder angrenzen
     */
    public Gap between(Gap other) {
        if (!(this.overlaps(other) || this.touches(other))) {

            Gap betweenGap;

            if (this.getStart() > other.getStart()) {
                betweenGap = new Gap(other.getEnd(), this.getStart());
            } else {
                betweenGap = new Gap(this.getEnd(), other.getStart());
            }

            return betweenGap;
        } else {
            return null;
        }
    }

    /**
     * Vergleich der Startpunkte der beiden Lücken.
     *
     * @param other zu vergleichende Lücken
     * @return -1, wenn this vor der übergebenen Lücke beginnt 0, wenn this und
     * other an der gleichen Position starten +1, wenn this nach other beginnt
     */
    public int compareTo(Gap other) {
        if (this.getStart() < other.getStart()) {
            return -1;
        } else if (this.getStart() == other.getStart()) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * zieht other aus der aktuellen Lücke ab (weil ein Auto abgestellt wird).
     * Ist other gleich this, so wird ein leeres Array zurückgegeben, da die
     * Lücke komplett belegt wird. Ist other nicht in this enthalten, enthält
     * das zurückgegebene Array ein Element, welches der aktuellen Lücke
     * entspricht. Liegt other mitten in this, entstehen zwei Lücken, sonst
     * eine.
     *
     * @param other abzuziehende Lücke (Belegung)
     * @return eine oder zwei Lücke(n), die nach Abziehen von other
     * entsteht/entstehen
     */
    public Gap[] sub(Gap other) {
        Gap[] arrayGap;

        if (this.contains(other) && this.getLength() == other.getLength()) {
            arrayGap = new Gap[0];
        } else if (!this.contains(other)) {
            arrayGap = new Gap[1];
            arrayGap[0] = this;
        } else {
            if (this.getStart() == other.getStart()) {
                Gap newGap = new Gap(other.getEnd(), this.getEnd());
                arrayGap = new Gap[1];
                arrayGap[0] = newGap;

            } else if (this.getEnd() == other.getEnd()) {
                Gap newGap = new Gap(this.getStart(), other.getStart());
                arrayGap = new Gap[1];
                arrayGap[0] = newGap;
            } else {
                Gap newGap1 = new Gap(this.getStart(), other.getStart());
                Gap newGap2 = new Gap(other.getEnd(), this.getEnd());
                arrayGap = new Gap[2];
                arrayGap[0] = newGap1;
                arrayGap[1] = newGap2;
            }

        }

        return arrayGap;
    }

    /**
     * liefert true, wenn das übergebene Objekt eine Lücke ist und den gleichen
     * Start- und Endpunkt hat wie this. Damit die allgemeine Methode
     * Object.equals() überschrieben wird, muss der Parameter vom Typ Object
     * sein und in der Methode gecastet werden.
     *
     * @param obj Lücke zum Vergleich
     * @return true, wenn das übergebene Objekt eine Lücke ist und den gleichen
     * Startpunkt und Länge hat wie this
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gap) {
            Gap objAsGap = (Gap) obj;
            return objAsGap.getStart() == this.getStart() && objAsGap.getEnd() == this.getEnd();
        } else {
            return false;
        }
    }

    /**
     * liefert diese Lücke in Stringdarstellung
     *
     * @return diese Lücke in Stringdarstellung
     */
    @Override
    public String toString() {
        return String.format("%3d..%3d", this.start, this.end);
    }

    //--- Testaufrufe ----
    /**
     * Testen der enthaltenen Routinen per Assert Notwendig ist, das Projekt mit
     * "Enable Assertions" zu kompilieren.
     *
     * @param args
     */
    public static void main(String[] args) {
        Gap gap = new Gap(10, 20);

        assert gap.precedes(new Gap(30, 40));
        assert gap.precedes(new Gap(20, 40));
        assert !gap.precedes(new Gap(15, 40));

        assert gap.succeeds(new Gap(0, 5));
        assert gap.succeeds(new Gap(0, 10));
        //    assert gap.succeeds(new Gap(0, 11));
        assert !gap.succeeds(new Gap(0, 11));

        assert gap.touches(new Gap(0, 10));
        assert gap.touches(new Gap(20, 30));
        assert !gap.touches(new Gap(0, 11));
        assert !gap.touches(new Gap(19, 20));
        assert !gap.touches(new Gap(21, 25));

        assert gap.contains(15);
        assert gap.contains(10);
        assert !gap.contains(22);
        assert !gap.contains(20);

        assert gap.contains(new Gap(15, 16));
        assert gap.contains(new Gap(10, 16));
        assert gap.contains(new Gap(15, 20));
        assert !gap.contains(new Gap(9, 15));
        assert !gap.contains(new Gap(15, 21));

        assert gap.overlaps(new Gap(8, 12));
        assert gap.overlaps(new Gap(18, 22));
        assert !gap.overlaps(new Gap(8, 9));
        assert !gap.overlaps(new Gap(22, 25));
        assert !gap.overlaps(new Gap(20, 25));
        assert gap.overlaps(new Gap(8, 25));
        assert gap.overlaps(new Gap(12, 18));

        assert " 10.. 20".equals(gap.toString());
        assert "  0.. 20".equals((new Gap(0, 20)).toString());
        assert "  0..  2".equals((new Gap(0, 2)).toString());
        assert "100..200".equals((new Gap(100, 200)).toString());

        assert gap.equals(new Gap(10, 20));
        assert !gap.equals(new Gap(1, 20));
        Gap testGap = null;
        assert !gap.equals(testGap) : "equals mit null";
        assert !gap.equals("String") : "equals mit falschem Objekttyp";

        assert (new Gap(0, 20)).equals(gap.union(new Gap(0, 10))) : "Vereinigung mit links angrenzender Lücke";
        assert gap != gap.union(new Gap(0, 10)) : "Vereinigung muss neues Element zurückgeben";
        assert (new Gap(10, 30)).equals(gap.union(new Gap(20, 30))) : "Vereinigung mit rechts angrenzender Lücke";
        assert (new Gap(5, 20)).equals(gap.union(new Gap(5, 15))) : "Vereinigung mit links überlappender Lücke";
        assert (new Gap(10, 25)).equals(gap.union(new Gap(15, 25))) : "Vereinigung mit rechts überlappender Lücke";
        assert null == (gap.union(new Gap(0, 5))) : "keine Vereinigung unabhängiger Lücken";
        assert null == (gap.union(new Gap(25, 35))) : "keine Vereinigung unabhängiger Lücken";

        assert (new Gap(5, 10)).equals(gap.between(new Gap(0, 5)));
        assert (new Gap(20, 25)).equals(gap.between(new Gap(25, 30)));
        assert null == (gap.between(new Gap(0, 10)));
        assert null == (gap.between(new Gap(20, 30)));
        assert null == (gap.between(new Gap(5, 15)));
        assert null == (gap.between(new Gap(15, 25)));

        assert 0 == gap.compareTo(new Gap(10, 20));
        assert -1 == gap.compareTo(new Gap(30, 40));
        assert -1 == gap.compareTo(new Gap(11, 15));
        assert 1 == gap.compareTo(new Gap(0, 5));
        assert 1 == gap.compareTo(new Gap(5, 10));
        assert 1 == gap.compareTo(new Gap(9, 20));

        // sub: zieht other aus der aktuellen Lücke ab (weil ein Auto abgestellt wird).
        // Ist other gleich this, so wird ein leeres Array zurückgegeben.
        Gap[] sub = gap.sub(new Gap(10, 20));
        assert 0 == sub.length : "sub gleiche Lücke ergibt kein leeres Array";

        // keinerlei Zusammenhang muss Element wie this zurückliefern
        sub = gap.sub(new Gap(0, 5));
        assert 1 == sub.length : "sub ohne Überlagerung darf nur ein Array mit einem Element liefern";
        assert gap.equals(sub[0]) : "sub ohne Überlagerung soll Array mit this zurückliefern";

        sub = gap.sub(new Gap(25, 30));
        assert 1 == sub.length : "sub ohne Überlagerung darf nur ein Array mit einem Element liefern";
        assert gap.equals(sub[0]) : "sub ohne Überlagerung soll Array mit this zurückliefern";

        // Überlappung geht außerhalb der eigenen Lücke -> muss Element wie this zurückliefern
        sub = gap.sub(new Gap(5, 12));
        assert 1 == sub.length : "sub bei Überlappung links darf das Array nur ein Element enthalten";
        assert gap.equals(sub[0]) : "sub bei Überlappung links muss this zurückgeliefert werden";

        sub = gap.sub(new Gap(18, 22));
        assert 1 == sub.length : "sub bei Überlappung rechts darf das Array nur ein Element enthalten";
        assert gap.equals(sub[0]) : "sub bei Überlappung rechts muss this zurückgeliefert werden";

        // Ein Rand stimmt überein -> ein neues Element entsteht     
        sub = gap.sub(new Gap(18, 20));
        assert 1 == sub.length : "sub rechter Rand gleich darf nur ein Array mit einem Element liefern";
        assert (new Gap(10, 18)).equals(sub[0]) : "sub mit gleichem rechten Rand";

        sub = gap.sub(new Gap(10, 12));
        assert 1 == sub.length : "sub linker Rand gleich darf nur ein Array mit einem Element liefern";
        assert (new Gap(12, 20)).equals(sub[0]) : "sub mit gleichem linken Rand";

        // mittiges Abziehen -> zwei neue Elemente entstehen
        sub = gap.sub(new Gap(12, 18));
        assert 2 == sub.length : "sub mittig muss Array mit zwei Elementen liefern";
        assert (new Gap(10, 12)).equals(sub[0]) : "sub mittig linkes Element falsch";
        assert (new Gap(18, 20)).equals(sub[1]) : "sub mittig rechtes Element falsch";

        System.out.println("Tests erfolgreich (oder Assertions off)");
    }
}
