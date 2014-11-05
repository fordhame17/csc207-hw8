package taojava.labs.sorting;

import java.io.PrintWriter;

import java.util.Comparator;
import java.util.Random;

/**
 * Tools for analyzing sorters.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class SorterAnalyzer
{
  // +---------------+---------------------------------------------------
  // | Configuration |
  // +---------------+

  /**
   * The number of repetitions we do in gathering statistics.
   */
  static final int REPETITIONS = 12;

  /**
   * The smallest array size we use.
   */
  static final int SMALLEST = 100;

  /**
   * The largest array size we use.
   */
  static final int LARGEST = 100000;

  /**
   * The amount we scale the array size between tests.
   */
  static final int SCALE = 2;

  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A comparator for integers.
   */
  public static final Comparator<Integer> standardIntComparator =
      (left, right) -> left.compareTo(right);

  /**
   * Build arrays of random integer values.
   */
  public static final ArrayBuilder<Integer> randomIntArrBuilder = (length) -> {
    Integer[] vals = new Integer[length];
    Random random = new Random();
    for (int i = 0; i < length; i++)
      vals[i] = random.nextInt(length);
    return vals;
  }; // randomIArrayBuilder

  /** 
   * Build arrays of integer values in increasing order.
   */
  public static final ArrayBuilder<Integer> increasingIntArrBuilder =
      (length) -> {
        Integer[] vals = new Integer[length];
        for (int i = 0; i < length; i++)
          vals[i] = i;
        return vals;
      };

  /** 
   * Build arrays of integer values in decreasing order.
   */
  public static final ArrayBuilder<Integer> decreasingIntArrBuilder =
      (length) -> {
        Integer[] vals = new Integer[length];
        for (int i = 0, j = length; i < length; i++, j--)
          vals[i] = j;
        return vals;
      };

  /** 
   * Build arrays of integer values in mostly increasing order with a 10% swap.
   */
  public static final ArrayBuilder<Integer> partlySwappedIntArrBuilder =
      (length) -> {
        Random generator = new Random();
        Integer[] vals = new Integer[length];
        for (int i = 0; i < length; i++)
          vals[i] = i;
        for (int j = length / 20; j > 0; j--)
          {
            int randIndex0 = generator.nextInt(length);
            int randIndex1 = generator.nextInt(length);
            vals[randIndex0] = randIndex1;
            vals[randIndex1] = randIndex0;
          }
        return vals;
      };

  // +--------------+----------------------------------------------------
  // | Class Fields |
  // +--------------+

  // +---------------+---------------------------------------------------
  // | Class Methods |
  // +---------------+

  /**
   * Determine the amount of time sorter takes to sort an array of
   * the given size created by builder.
   *
   * @param sorter
   *   The sorting routine we are testing.
   * @param builder
   *   The constructor for the array we will sort.
   * @param order
   *   The comparator we use in sorting.
   * @param size
   *   The size of the array that we sort.
   *   
   * @return the number of milliseconds that sorting took, or 
   *   Long.MAX_VALUE if the sorter breaks.
   */
  public static <T> long basicAnalysis(Sorter<T> sorter, Comparator<T> order,
                                       ArrayBuilder<T> builder, int size)
  {
    // Prepare for timing
    SimpleTimer timer = new SimpleTimer();

    // Build the array.
    T[] values = builder.build(size);

    // Run the garbage collector so that garbage collection
    // is less likely to be counted as part of the time for
    // sorting.
    gc();

    // Start the timer.  (Duh.)
    timer.start();

    // Do the real work.
    try
      {
        sorter.sort(values, order);
      } // try            
    catch (Throwable error)
      {
        // Sorting failed with some error.  Return -1 to
        // indicate failure.
        return Long.MAX_VALUE;
      } // catch

    // Stop the timer.
    timer.pause();

    // And report the time taken
    return timer.elapsed();
  } // basicAnalysis(Sorter<T>, ArrayBuilder<T>, int)

  /**
   * Repeatedly perform basic analysis and gather statistics
   * (e.g., minimum time, maximum time, average time.
   */
  public static <T> long[] compoundAnalysis(Sorter<T> sorter,
                                            Comparator<T> order,
                                            ArrayBuilder<T> builder, int size,
                                            int repetitions)
  {
    // to make things easier, grab the first iteration
    long first = basicAnalysis(sorter, order, builder, size);
    // initialize stat holders based off the first iteration
    long time;
    long min = first, max = first;
    long ave = first / repetitions;
    // loop through the rest
    for (int i = 1; i < repetitions; i++)
      {
        // grab each time spent on sorting
        time = basicAnalysis(sorter, order, builder, size);
        // compare with current stats
        if (time <= min)
          {
            min = time;
          } // if
        else if (time >= max)
          {
            max = time;
          } // else if
        // update average
        ave += (time / repetitions);
      } // for
    return new long[] { ave, min, max };
  } // compoundAnalysis(Sorter<T>, ArrayBuilder<T>, int, int)

  /**
   * Given a variety of sorters and builders, does some analysis
   * of each sorter/builder pair using a variety of array sizes
   * and prints out the results.
   *
   * @param pen
   *   Where to send the output
   * @param sorters
   *   The objects that do the sorting
   * @param sorterNames
   *   The names of those sorters
   * @param builders
   *   The objects to build the arrays
   * @param builderNames
   *    The names of those builders
   */
  public static <T> void combinedAnalysis(PrintWriter pen, Sorter<T>[] sorters,
                                          String[] sorterNames,
                                          Comparator<T> order,
                                          ArrayBuilder<T> builders[],
                                          String[] builderNames)
  {
    // print the headers of the table of statistics
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "Sorter", "Builder",
               "Input Size", "Average Time", "Minimum Time", "Maximum Time");
    pen.printf("%-16s%-16s%-16s%-16s%-16s%-16s\n", "------", "-------", "------------",
               "------------", "------------", "------------");
    // loop through all the sorters
    for (int a = 0; a < sorters.length; a++)
      {
        // loop through all the builders
        for (int b = 0; b < builders.length; b++)
          {
            // jump through the array sizes
            for (int size = SMALLEST; size <= LARGEST; size *= SCALE)
              {
                // take the array of statistics on each size
                long[] stats =
                    compoundAnalysis(sorters[a], order, builders[b], size,
                                     REPETITIONS);
                // printing as a row on the table
                pen.printf("%-16s%-16s%12d    %12d%12d%12d\n", sorterNames[a],
                           builderNames[b], size, stats[0], stats[1], stats[2]);
              } // for size
          } // for builder : builders
      } // for sorter : sorters
  } // combinedAnalysis(PrintWRiter, Sorter<T>, String[], ...)

  /**
   * Force garbage collection to the best of our ability.
   */
  public static void gc()
  {
    // Right now, we use the quick and dirty "suggest garbage
    // collection".  Over the long term, we will probably try
    // something like "get the pid and execute 'jcmd <pid> GC.run'"
    // The pid *might* be in the environment.
    System.gc();
  } // gc()
} // class SorterAnalyzer
