package grp27_ueb03;

/**
 *
 * @author Robin
 */
public class MyList {

    /**
     * Variable of typ Element
     */
    private Element elements;

    /**
     * Proofs if a list contains elements or not
     *
     * @return true if list contains elements, false if not
     */
    public boolean isEmpty() {
        return elements == null;
    }

    /**
     * Appends an element at the end of a list.
     *
     * @param value the value of the element that should be appended
     */
    public void appendElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.appendElement(value);
        }
    }

    /**
     * Inserts an element to a list so that the elements are in ascending order
     *
     * @param value the value of the element that should be inserted
     */
    public void insertElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.insertElement(value);
        }

    }

    /**
     * Deletes the first element of a list with the committed value
     *
     * @param value the value of the element that should be deleted
     */
    public void deleteElement(int value) {
        if (!isEmpty()) {
            elements = elements.deleteElement(value);
        }
    }

    public int size() {

        if (isEmpty()) {
            return 0;
        } else {
            int listSize = elements.size();
            elements.resetSizeOfElements();
            return listSize;
        }
    }

    public int sum() {
        if (isEmpty()) {
            return 0;
        } else {
            int elementSum = elements.sum();
            elements.resetSumOfElements();
            return elementSum;
        }
    }

    public boolean isSorted() {
        if (isEmpty()) {
            return true;
        } else {
            return elements.isSorted();
        }
    }

    public boolean containsValue(int value) {
        if (isEmpty()) {
            return false;
        } else {
            return elements.containsValue(value);
        }
    }

    public String showValues() {
        if (isEmpty()) {
            return "{}";
        } else {
            String elementValues = elements.showValues();
            elements.resetReturningString();
            return elementValues;
        }
    }

    public int getValueAt(int value) {
        if (isEmpty()) {
            return 0;
        } else {
            int elementValue = elements.getValueAt(value);
            elements.resetElementCounter();
            return elementValue;
        }
    }

    public void insertValueAt(int value, int index) {
        if (isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.insertValueAt(value, index);
            elements.resetElementCounter();
        }
    }

    public void insertValueAtFront(int value) {
        if (isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.insertValueAtFront(value);
        }
    }

    public void insertSortedIfUnique(int value) {

        if (!containsValue(value)) {
            insertElement(value);
        }
    }
    /*
     * You can find this method in Prof. Häusleins slides but it is 
     * not necessary for the exercise
     *
     public void printList() {
     if (!isEmpty()) {
     elements.printList();
     }
     }
     */
}
