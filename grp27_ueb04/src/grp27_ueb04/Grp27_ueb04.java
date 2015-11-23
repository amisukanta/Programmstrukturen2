/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb04;

import java.util.Arrays;

/**
 *
 * @author Robin
 */
public class Grp27_ueb04 {

    //TODO: testen (u.A. mit leeren Mengen)
    /**
     * definition of delimiter
     */
    public static final String trennzeichen = "/";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Set menge1 = new Set();
        Set menge2 = new Set();

        System.out.printf("Argumente \t\t: " + Arrays.toString(args) + "\n");
        getSetsFromArgs(menge1, menge2, args);
        testClassSet(menge1, menge2);
        

    }

    /**
     * split up of the arguments array into two sets
     *
     * @param menge1 set1 of two
     * @param menge2 set2 of two
     * @param args the command line arguments
     */
    public static void getSetsFromArgs(Set menge1, Set menge2, String[] args) {
        boolean passedDelimiter = false;
        for (String arg : args) {
            if (arg.equals(trennzeichen)) {
                passedDelimiter = true;
            } else {
                if (passedDelimiter == false) {
                    menge1.addElement(Integer.valueOf(arg));
                } else {
                    menge2.addElement(Integer.valueOf(arg));
                }
            }
        }
    }

    /**
     * testing set and their comparison with different methods
     *
     * @param menge1 set1 of two
     * @param menge2 set2 of two
     */
    public static void testClassSet(Set menge1, Set menge2) {
        System.out.printf("Menge A \t\t: " + menge1.showValues() + "\n");
        System.out.printf("Menge B \t\t: " + menge2.showValues() + "\n");
        System.out.printf("Vereinigung \t A ∪ B \t: " + menge1.union(menge2).showValues() + "\n");      
        System.out.printf("Schnittmenge \t A ∩ B \t: " + menge1.intersection(menge2).showValues() + "\n");
        System.out.printf("Differenzmenge\t A \\ B \t: " + menge1.diff(menge2).showValues() + "\n");
        System.out.printf("echte Teilmenge  A ⊂ B \t: " + menge1.isProperSubSet(menge2) + "\n\n");
        
        System.out.printf("isSame Test \t \t: " + menge1.isSame(menge2) + "\n");
        System.out.printf("Vereinigung2 \t A ∪ B \t: " + menge1.union2(menge2).showValues() + "\n");
        System.out.printf("isSame2 Test \t \t: " + menge1.isSame2(menge2) + "\n");
    }
}
