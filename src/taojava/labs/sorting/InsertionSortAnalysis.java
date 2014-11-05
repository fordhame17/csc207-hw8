package taojava.labs.sorting;

import java.io.PrintWriter;

/**
 * A very simple analysis of a few sorting algorithms.
 * 
 * @author Samuel A. Rebelsky
 * @author Eileen, Larry, Camila
 */
public class InsertionSortAnalysis
{
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    @SuppressWarnings("unchecked")
    Sorter<Integer>[] sorters =
        (Sorter<Integer>[]) new Sorter[] { new InsertionSorter<Integer>(),
                                          new InsertionSorter2<String>(),
                                          new InsertionSorter3<String>() };
    String[] sorterNames = { "Insertion", "Insertion2", "Insertion3" };

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
     * Our analysis of the comparative running times of Insertions 1, 2 and 3 shows that 
     * 1 and 2 fare similarly, however the small effect of the overhead was opposite to
     * the expected. For the random arrays, there was a noticeable exponential increase
     * of times after a size of 3200 units and the second sorter, without overhead actually
     * fared 250 units more than sorter 1 with overhead (2649 vs. 2899). All four sorters
     * had a maximum time of 1 unit for the increasing arrays since they are essentially 
     * already sorted. The 3rd sorter did by far the best job with approximately 1/3 the 
     * times as the first and second sorters in all categories except the increasing one.
     * 
     * Notes: *Averages*
     * Sorter   Random  Increasing      Decreasing      Partially Swapped
     * 1        2649    1               5040            327
     * 2        2899    1               5129            340
     * 3        1297    1               1890            142
     * 
     */
  } // main(String[]
} // InsertionSortAnalysis
