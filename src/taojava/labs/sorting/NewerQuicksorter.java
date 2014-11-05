package taojava.labs.sorting;

import java.util.Comparator;

public class NewerQuicksorter<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select the middle element of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    int mid = ((ub - lb) / 2) + lb;
    return vals[mid];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewerQuicksorter
