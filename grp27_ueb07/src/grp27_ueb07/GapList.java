
package grp27_ueb07;

/**
 *
 * @author Robin
 */
public interface GapList {

    Gap getPayload();
    GapList getNext();   
    int length();
    boolean isEmpty();
    boolean hasJustOneElement();
    boolean hasGap(int length);
    boolean hasGapAt(int position, int length);
    int getLargestGap(int yetLargest);
    int getLargestGap();
    int getSmallestGapBiggerThan(int minLength, int yetMinLength, int foundPosition);
    int findPositionFor(int length);
    GapList remove(int position, int length);
    GapList remove(Gap gap);
    GapList insert(Gap gap);
    GapList insert(int position, int length);
    @Override
    boolean equals(Object obj);
    @Override
    String toString();
    
}
