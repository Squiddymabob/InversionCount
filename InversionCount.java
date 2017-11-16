package defaultPackage;

public class InversionCount {

	/**
	 * This is the main method for testing purposes.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		int arr[] = { 1, 20, 6, 4, 5 };

		// Print out number of inversions for the test array
		System.out.println(countInversions(arr) + " inversions.");

	}

	/**
	 * countInversions takes the input array and divides it into two parts. This
	 * method is then recursively called on the resulting arrays, meaning that
	 * they are again split into two until the base case is reached.
	 * combineAndCount is called to count the inversions.
	 * 
	 * @param input
	 * @return inversionsLeft + inversionsRight + combine
	 */
	public static int countInversions(int input[]) {

		// Half of the size of the input array
		int half = input.length / 2;

		// Checking if the array can be split evenly or not
		int equalDivide = 1;
		if (input.length % 2 == 0) {
			equalDivide = 0;
		}

		// The temp arrays to divide the problem into
		int[] left = new int[half];
		// Second temp array will be bigger by 1 if the original input is not
		// evenly divided
		int[] right = new int[half + equalDivide];

		// Copying the values from the input array into the two temp arrays
		System.arraycopy(input, 0, left, 0, half);
		System.arraycopy(input, half, right, 0, half + equalDivide);

		int inversionsLeft, inversionsRight, combine;

		// If the list is small then the base case has been reached
		if (input.length <= 1) {
			return 0;
		}

		// Recursively count the inversions required to sort each
		inversionsLeft = countInversions(left);
		inversionsRight = countInversions(right);

		// Merge together and count the inversions needed to sort between arrays
		int output[] = new int[input.length];
		combine = combineAndCount(left, right, output);

		// Put final array into original array as this is required for the
		// recursions to work
		System.arraycopy(output, 0, input, 0, input.length);

		// Return sum of inversions to get the total inversions

		return (inversionsLeft + inversionsRight + combine);

	}

	/**
	 * This method combines the two arrays and counts the number of inversions
	 * needed between the lists. If an element in the 'right' array is less than
	 * an element in the 'left' array then an inversion is needed.
	 * 
	 * @param left
	 * @param right
	 * @param output
	 * @return count
	 */
	private static int combineAndCount(int left[], int right[], int output[]) {

		int leftIndex = 0;
		int rightIndex = 0;
		int outputIndex = 0;
		int count = 0;

		// Counting inversions
		// While the ends of the arrays have not been reached
		while ((leftIndex < left.length) && (rightIndex < right.length)) {
			// If the value in the left array is <= to the value in the right,
			// then add that to the output
			// As the value in the left is <= to that in the right, no inversion
			// is needed as it is already in the correct order
			if (left[leftIndex] <= right[rightIndex]) {
				output[outputIndex] = left[leftIndex];
				leftIndex++;
			}
			// Update the count by adding the length of the left array minus
			// where in the array it has got to
			else {
				output[outputIndex] = right[rightIndex];
				rightIndex++;
				// Count is only increased if the value being looked at in the
				// right array is not <= the value being looked at in the left
				// array, meaning that an inversion is needed for the values to
				// be in the correct order
				count = count + left.length - leftIndex;
			}
			outputIndex++;
		}

		// If at the end of the left array
		if (leftIndex == left.length) {
			// But not at the end of the right array
			for (int p = rightIndex; p < right.length; p++) {
				output[outputIndex] = right[p];
				outputIndex++;
			}
		} else {
			// Until end of left array, add the values to output array
			for (int q = leftIndex; q < left.length; q++) {
				output[outputIndex] = left[q];
				outputIndex++;
			}
		}

		return count;

	}

}
