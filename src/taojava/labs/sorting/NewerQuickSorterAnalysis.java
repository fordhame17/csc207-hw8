
package taojava.labs.sorting;

import java.io.PrintWriter;

/**
 * A very simple analysis of a few sorting algorithms.
 * 
 * @author Samuel A. Rebelsky
 * @author Eileen, Larry, Camila
 */
public class NewerQuickSorterAnalysis
{
  
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new NewQuicksorter(),
                                          new NewerQuicksorter(),
                                          new NewerQuicksorter2(),
                                          new NewerQuicksorter3()};
    String[] sorterNames = { "NewQuicksort", "NewerQuicksort", "NewerQuicksort2",
                             "NewerQuicksort3"};
    

    @SuppressWarnings("unchecked")
    ArrayBuilder<Integer>[] builders =
        (ArrayBuilder<Integer>[]) new ArrayBuilder[] {
                                                      SorterAnalyzer.randomIntArrBuilder,
                                                      SorterAnalyzer.increasingIntArrBuilder,
                                                      SorterAnalyzer.decreasingIntArrBuilder,
                                                      SorterAnalyzer.partlySwappedIntArrBuilder };
    String[] builderNames =
        { "Random", "Increasing", "Decreasing", "Partly Swapped" };

    SorterAnalyzer.combinedAnalysis(pen, sorters, sorterNames,
                                    SorterAnalyzer.standardIntComparator,
                                    builders, builderNames);
    
    /*
     * The times I will be referencing in the following analysis are all averages since
     * the data gathered for maximums and minimums varied very little.
     * Our analysis of the comparative running times of Quicksorts original,1, 2 and 3 
     * shows that 1 and 2 fare similarly as well as 3, 3 being the most stable. For increasing 
     * values, the Insertion sort was pretty efficient ahving a runtime of 1; in contrast the 
     * Quicksorts were more dependent upon the chosen pivot rather than the ordering of the array.
     * In the original quick sort the increasing array and 10% swap (which is still mostly
     * increasing) didn't have a very efficient run time. Having a random pivot was more efficent
     * than a chosen one and increasing randomness as seen between quicksorts 2 and 3 makes the 
     * algorithm respond similarly no matter which situation. 
     * 
     * Notes: *Averages*
     * Sorter   Random  Increasing      Decreasing      Partially Swapped
     * original      4          74               1                     30
     * 1             7           5               4                      5
     * 2             6           3               4                      6
     * 3             4           5               5                      5
     * 
     */
  } // main(String[]
} // InsertionSortAnalysis