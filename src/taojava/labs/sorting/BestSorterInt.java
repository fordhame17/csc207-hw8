package taojava.labs.sorting;

import java.util.Comparator;

/**
 * Sort using insertion sort.
 * 
 * @author Samuel A. Rebelsky
 */
public class BestSorterInt
extends SorterBridge
{
  /**
   * Sort vals using insertion sort. See the Sorter<T> interface for additional
   * details.
   */
  public int[] sorti(int[] vals)
  {
    for (int i = 1; i < vals.length; i++)
      {
        insert(vals, i);
      } // for
    return vals;
  } // sorti(int[])

  /**
   * Insert the value in position i into the sorted subarray in positions
   * 0..(n-1). (Using the shift method and no overhead)
   * 
   * @param values
   *   the array into which we are inserting values.
   * @param order
   *   the comparator used to determine order.
   * @param n
   *   the bound on the end of the subarray.
   * 
   * @pre 0 <= n < values.length
   * @pre Utils.sorted(values, order, 0, n).
   * @pre order can be compared to any pair of values in values.
   * @post Utils.sorted(values, order, 0, n-1)
   * @post No elements have been added or removed.
   */
  void insert(int[] vals, int n)
  {
    int i = n;
    int temp = vals[i];
    i--;
    while ((i >= 0) && vals[i] > temp)
      {
        vals[i + 1] = vals[i];
        i--;
      } // while
    vals[i + 1] = temp;
  } // insert(T[], Comparator<T>, int)
} // BestSorterInt