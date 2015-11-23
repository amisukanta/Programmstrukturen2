package grp27_ueb04;

/**
 *
 * @author Robin
 */
public class Element {

    /**
     * The value of the element
     */
    private int value;

    /**
     * The next element of the element
     */
    private Element next;

    /**
     * Appends a new element at the end of all connected elements
     *
     * @param value the value of the new element that should be appended
     * @return the first element of the list
     */
    public Element appendElement(int value) {
        if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.setNext(newElement);
        } else {
            this.setNext(this.next.appendElement(value));
        }
        return this;
    }

    /**
     * Inserts an new element with the committed value at the position so that
     * the sorted element chain would be still acending ordered
     *
     * @param value the value of the new element
     * @return the first element of the list
     */
    public Element insertElement(int value) {
        if (this.getValue() > value) {
            Element newElement = new Element();
            newElement.setValue(value);
            newElement.setNext(this);
            return newElement;
        } else if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.setNext(newElement);
            return this;
        } else {
            this.setNext(this.next.insertElement(value));
            return this;
        }
    }

    /**
     * Deletes the first element with the committed value. List must be sorted.
     * Starting from the instance that calls this method, the method interates
     * recursivly through all appended elements until an element with the
     * committed value is found, value is smaller than current value in the list
     * or the end is reached.
     *
     * @param value the value of the element that should be deleted
     * @return the next Element of the deleted Element or, if no element was
     * found with the committed value, the element itself
     */
    public Element deleteElement(int value) {
        if (this.getValue() == value) {
            return this.next;
        } else if (this.next != null || this.getValue() > value) {
            this.setNext(this.next.deleteElement(value));
        }
        return this;
    }

    /**
     * Gets the value of the current instance
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the current instance
     *
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the value 'next' of the current instance to the inserted instance of
     * type Element
     *
     * @param next the next to set
     */
    public void setNext(Element next) {
        this.next = next;
    }

    /**
     * detemines the amount of elements that are connected
     *
     * @return amount of elements = size of list
     */
    public int size() {

        if (this.next == null) {
            return 1;
        } else {

            return 1 + this.next.size();
        }

    }

    /**
     * sums up all values of the linked list
     *
     * @return sum of all values of the linked list
     */
    public int sum() {

        if (this.next == null) {
            return this.getValue();
        } else {

            return this.next.sum() + this.getValue();
        }
    }

    /**
     * detemines if the list is sorted or not
     *
     * @return true if list is sorted, false if list is not sorted
     */
    public boolean isSorted() {
        if (this.next == null) {
            return true;
        } else {
            if (this.getValue() <= this.next.getValue()) {
                return this.next.isSorted();
            } else {
                return false;
            }
        }
    }

    /**
     * detemines if list contains an element with the input value. List must be
     * sorted. Searching for the value stops if last element is reached or value
     * is smaller than current value in the list
     *
     * @param value the value that should be checked if its in the list
     * @return true if value is in the list, false if value isn't in the list
     */
    public boolean containsValue(int value) {

        if (this.getValue() == value) {
            return true;
        } else {
            if (this.next == null || this.getValue() > value) {
                return false;
            } else {
                return this.next.containsValue(value);
            }

        }
    }

    /**
     * gives back a string with all values of a list
     *
     * @return string with values of a list
     */
    public String showValues() {
        //TODO ohne Klassenvariable, ohne geschweifte Klammer erledigt
        if (this.next == null) {
            return this.getValue() + "";
        } else {
            return this.getValue() + " " + this.next.showValues();
        }
    }

    /**
     * gives back the value of the element with the given index
     *
     * @param index the index of an element
     * @return the value of the element at the given index
     */
    public int getValueAt(int index) {
        if (0 == index) {
            return getValue();
        } else {
            if (this.next == null) {
                return Integer.MAX_VALUE;
            } else {
                return this.next.getValueAt(index - 1);
            }
        }
    }

    /**
     * inserts an element with a given value at a given position in the list
     *
     * @param value the value of the new element
     * @param index the position of the new element
     * @return the first element in the list
     */
    public Element insertValueAt(int value, int index) {
        //todo keine Klassenvariable erledigt
        if (0 == index) {
            Element newElement = new Element();
            newElement.setValue(value);
            newElement.setNext(this);
            return newElement;
        } else if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.setNext(newElement);
            return this;
        } else {
            this.setNext(this.next.insertValueAt(value, index - 1));
            return this;
        }

    }

    /**
     * insert an element with a given value at the front of the list
     *
     * @param value the value of new element
     * @return the new element as the new beginning of the list
     */
    public Element insertValueAtFront(int value) {

        Element newElement = new Element();
        newElement.setValue(value);
        newElement.setNext(this);
        return newElement;
    }

    /**
     * checks if the elements of two lists are the same. Checks first if the
     * lists have the same size
     *
     * @param other the other element
     * @return true if all elements at the same position have the same value
     */
    public boolean isSame(Element other) {

        int zaehler = 0;

        if (this.size() != other.size()) {
            return false;
        }

        for (; zaehler < this.size() || this.getValueAt(zaehler) != other.getValueAt(zaehler); zaehler++) {

        }
        return zaehler == this.size();
    }

}
