package taojava.labs.sorting;

import java.util.Comparator;
import java.util.Random;

public class NewerQuicksorter2<T>
    extends NewQuicksorter<T>
{
  @Override
  /**
   * Select a random element of the subarray as the pivot.
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    Random generator = new Random();
    int rand = generator.nextInt(ub - lb) + lb;
    return vals[rand];
  } // selectPivot(T[], vals, Comparator<T> order, int lb, int ub)
} // NewerQuicksorter2