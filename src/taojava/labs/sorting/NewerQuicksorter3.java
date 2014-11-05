package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class NewerQuicksorter3<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select the median of three random elements of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    
    // generate three random numbers between the range lb - ub
    Random generator = new Random();
    int rand1 = generator.nextInt(ub - lb) + lb;
    int rand2 = generator.nextInt(ub - lb) + lb;
    int rand3 = generator.nextInt(ub - lb) + lb;
    
    // store those numbers in an array and sort them
    int[] randarray = {rand1, rand2, rand3};
    Arrays.sort(randarray);
    
    // return the element in the middle of the array
    return vals[randarray[1]];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewerQuicksorter3
