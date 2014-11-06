package taojava.labs.sorting;

import java.util.Comparator;

public class MergeSorterB<T>
    extends SorterBridge<T>
{
  /**
  * Sort vals using recursive merge sort. See the Sorter<T> interface for
  * additional details.
  */
  @SuppressWarnings("unchecked")
  @Override
  public T[] sort(T[] vals, Comparator<T> order)
  {
    //(T[]) new Object[vals.length]
    return sorter(vals, order, 0, vals.length, vals);
  } // sort(T[], Comparator<T>)

  public T[] sorter(T[] vals, Comparator<T> order, int lb, int ub, T[] scratch)
  {
    // Base case: Singleton arrays need not be sorted.
    if ((lb+1) == ub)
      {
        return scratch;
      }// if length <= 1
    else
      {
        int mid = (ub - lb) / 2;
        Utils.merge(order, sorter(vals, order, lb, mid, scratch), lb, mid,
                        sorter(vals, order, mid, ub, scratch), mid, ub,
                        scratch, lb, ub);
        vals = scratch;
        return vals;
      } // recursive case: More than one element
  } // sorter(T[], Comparator<T>, int, int, T[])
} // MergeSorterB
