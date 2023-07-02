import java.util.Arrays;

public class HeapSort {
    static void maxHeapPercolateDown(int nodeIndex, int[] heapArray, int heapSize) {
        int childIndex = 2 * nodeIndex + 1;
        int value = heapArray[nodeIndex];

        while (childIndex < heapSize) {
            int maxValue = value;
            int maxIndex = -1;
            int i = 0;

            while (i < 2 && i + childIndex < heapSize) {
                if (heapArray[i + childIndex] > maxValue) {
                    maxValue = heapArray[i + childIndex];
                    maxIndex = i + childIndex;
                }
                i++;
            }

            if (maxValue == value) {
                return;
            }

            int temp = heapArray[nodeIndex];
            heapArray[nodeIndex] = heapArray[maxIndex];
            heapArray[maxIndex] = temp;

            nodeIndex = maxIndex;
            childIndex = 2 * nodeIndex + 1;
        }
    }


    static void heapSort(int[] numbers) {
        int i = numbers.length / 2 - 1;

        while (i >= 0) {
            maxHeapPercolateDown(i, numbers, numbers.length);
            i--;
        }

        i = numbers.length - 1;

        while (i > 0) {
            int temp = numbers[0];
            numbers[0] = numbers[i];
            numbers[i] = temp;

            maxHeapPercolateDown(0, numbers, i);
            i--;
        }
    }

    public static void main(String[] args) {
        int[] numbers = { 82, 36, 49, 82, 34, 75, 18, 9, 23 };
        System.out.println("UNSORTED: " + Arrays.toString(numbers));

        heapSort(numbers);
        System.out.println("SORTED:   " + Arrays.toString(numbers));
    }
}
