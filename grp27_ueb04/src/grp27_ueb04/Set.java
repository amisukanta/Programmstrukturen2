/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb04;

/**
 *
 * @author Robin
 */
public class Set {

    /**
     * Variable of typ Element
     */
    private Element elements;

    /**
     * Proofs if a set contains elements or not
     *
     * @return true if set contains elements, false if not
     */
    public boolean isEmpty() {
        return elements == null;
    }

    /**
     * Inserts an element to a set so that the element is still in ascending
     * order if it was before
     *
     * @param value the value of the element that should be inserted
     */
    public void addElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            if (!existsElement(value)) {
                elements = elements.insertElement(value);
            }
        }

    }

    /**
     * detemines if set contains an element with a given value
     *
     * @param value the to be checked value
     * @return true, if an element exist with the input value
     */
    public boolean existsElement(int value) {
        if (this.isEmpty()) {
            return false;
        } else {
            return elements.containsValue(value);
        }
    }

    /**
     * Deletes the first element of a set with the committed value
     *
     * @param value the value of the element that should be deleted
     */
    public void deleteElement(int value) {
        if (!this.isEmpty()) {
            elements = elements.deleteElement(value);
        }
    }

    /**
     * gives back a string with all values of all elements
     *
     * @return a string with all values of all elements
     */
    public String showValues() {
        if (this.isEmpty()) {
            return "{}";
        } else {
            return "{" + elements.showValues() + "}";
        }
    }

    /**
     * determines the union set of two sets
     *
     * @param otherSet which should be compared with the calling instance set
     * @return the union of both sets as resulting set
     */
    public Set union(Set otherSet) {
        Set resultSet = new Set();
        if (!this.isEmpty()) {
            for (int i = 0; i < this.elements.size(); i++) {

                resultSet.addElement(this.elements.getValueAt(i));

            }
        }

        if (!otherSet.isEmpty()) {
            for (int i = 0; i < otherSet.elements.size(); i++) {
                if (!resultSet.existsElement(otherSet.elements.getValueAt(i))) {
                    resultSet.addElement(otherSet.elements.getValueAt(i));
                }
            }
        }

        return resultSet;
    }

    /**
     * determines the union set of two sets
     *
     * @param otherSet which should be compared with the calling instance set
     * @return the union of both sets as resulting set
     */
    public Set union2(Set otherSet) {
        Set resultSet = new Set();

        if (!this.isEmpty() && !otherSet.isEmpty()) {
            resultSet.addElementList(this.elements);
            resultSet.addElementList(otherSet.elements);
            return resultSet;
        } else if (!this.isEmpty() && otherSet.isEmpty()) {
            return this;
        } else {
            return otherSet;
        }

    }

    /**
     * detemines the intersection set between two sets
     *
     * @param otherSet which should be compared with the calling instance set
     * @return the intersections set between the two sets
     */
    public Set intersection(Set otherSet) {
        Set resultSet = new Set();

        if (!this.isEmpty() && !otherSet.isEmpty()) {

            Set menge1 = this;
            Set menge2 = otherSet;

            if (this.elements.size() > otherSet.elements.size()) {
                menge1 = otherSet;
                menge2 = this;
            }

            for (int i = 0; i < menge1.elements.size(); i++) {

                if (menge2.existsElement(menge1.elements.getValueAt(i))) {
                    resultSet.addElement(menge1.elements.getValueAt(i));
                }
            }
        }

        return resultSet;
    }

    /**
     * determines the difference between two sets
     *
     * @param otherSet which should be compared with the calling instance set
     * @return all values which are in set1 and not in both or set2
     */
    public Set diff(Set otherSet) {
        Set resultSet = new Set();

        if (!this.isEmpty() && !otherSet.isEmpty()) {

            for (int i = 0; i < this.elements.size(); i++) {

                if (!otherSet.existsElement(this.elements.getValueAt(i))) {
                    resultSet.addElement(this.elements.getValueAt(i));
                }
            }
        } else if (!this.isEmpty() && otherSet.isEmpty()) {
            resultSet = this;
        }

        return resultSet;
    }

    /**
     * detemines if two sets are the same
     *
     * @param otherSet which should be compared with the calling instance set
     * @return true if the two sets have the same elements
     */
    public boolean isSame(Set otherSet) {

        if (!this.isEmpty() && !otherSet.isEmpty()) {

            Set menge1 = this;
            Set menge2 = otherSet;
            int zaehler = 0;

            if (this.elements.size() > otherSet.elements.size()) {
                menge1 = otherSet;
                menge2 = this;
            }

            for (; (menge1.elements.getValueAt(zaehler) != menge2.elements.getValueAt(zaehler))
                    || zaehler < menge1.elements.size(); zaehler++) {

            }
            return zaehler == menge1.elements.size();
        } else {
            return this.isEmpty() && otherSet.isEmpty();
        }
    }

    /**
     * detemines if two sets are the same
     *
     * @param otherSet which should be compared with the calling instance set
     * @return true if the two sets have the same elements
     */
    public boolean isSame2(Set otherSet) {

        if (!this.isEmpty() && !otherSet.isEmpty()) {

            return this.elements.isSame(otherSet.elements);

        } else {
            return this.isEmpty() && otherSet.isEmpty();
        }
    }

    /**
     * determines if set1 is a proper subset of set2
     *
     * @param otherSet which should be compared with the calling instance set
     * @return true if set1 is a proper subset of set2
     */
    public boolean isProperSubSet(Set otherSet) {

        return this.isSame(this.intersection(otherSet)) && !otherSet.isSame(this.intersection(otherSet));
    }

    /**
     * adding all elements of a set to an other set if the element isn't already
     * existing in the first set
     *
     * @param otherSet which should be compared with the calling instance
     * element
     * @return first element of the new element lists
     */
    private Element addElementList(Element list) {

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (this.elements == null) {
                    Element newElement = new Element();
                    newElement.setValue(list.getValueAt(i));
                    elements = newElement;
                } else {
                    if (!elements.containsValue(list.getValueAt(i))) {
                        elements.insertElement(list.getValueAt(i));
                    }
                }
            }
        }

        return elements;
    }
}
