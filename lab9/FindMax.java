package lab9;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	int max_1 = 0;
	int max_2 = 0;
	int max_3 = 0;
	public static void main(String[] args) {
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		Thread thread_1 = new Thread(()->{max_1 = findMax(0, 29);});
		Thread thread_2 = new Thread(()->{max_2 = findMax(30, 59);});
		Thread thread_3 = new Thread(()->{max_3 = findMax(60, 89);});
		thread_1.start();
		thread_2.start();
		thread_3.start();
		while (thread_1.isAlive() || thread_2.isAlive() || thread_3.isAlive()) {}
		int max = Collections.max(Arrays.asList(max_1, max_2, max_3));
		System.out.println("the max value is " + max);
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}
