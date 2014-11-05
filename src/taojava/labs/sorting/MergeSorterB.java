package taojava.labs.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSorterB<T>
    extends SorterBridge<T>
{
  /**
  * Sort vals using iterative merge sort. See the Sorter<T> interface for
  * additional details.
  */
  @SuppressWarnings("unchecked")
  @Override
  public T[] sort(T[] vals, Comparator<T> order)
  {
    return sorter(vals, order, (T[]) new Object[vals.length]);
  } // sort(T[], Comparator<T>)

  public T[] sorter(T[] vals, Comparator<T> order, T[] scratch)
  {
    // Base case: Singleton arrays need not be sorted.
    if (vals.length <= 1)
      {
        return vals.clone();
      } // if length <= 1
    else
      {
        int mid = vals.length / 2;
        T[] left = sorter(Arrays.copyOfRange(vals, 0, mid), order, scratch);
        T[] right =
            sorter(Arrays.copyOfRange(vals, mid, vals.length), order, scratch);
        scratch = Utils.merge(order, left, right);
        return vals;
      } // recursive case: More than one element
  }
} // MergeSorterB
