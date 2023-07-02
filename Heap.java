class MaxHeap {
    int[] maxHeap;
    int heapSize;

    public MaxHeap() {
        this.maxHeap = new int[1];
        this.heapSize = 0;
    }

    private void resize() {
        int newLength = maxHeap.length * 2;
        int[] newHeap = new int[newLength];

        for (int i = 0; i < maxHeap.length; ++i) {
            newHeap[i] = maxHeap[i];
        }

        maxHeap = newHeap;
    }

    public void percolateUp(int nodeIndex) {
        while (nodeIndex > 0) {
            int parentIndex = (nodeIndex - 1) / 2;

            if (maxHeap[nodeIndex] <= maxHeap[parentIndex]) {
                return;
            }
            else {
                int temp = maxHeap[nodeIndex];
                maxHeap[nodeIndex] = maxHeap[parentIndex];
                maxHeap[parentIndex] = temp;

                nodeIndex = parentIndex;
            }
        }
    }

    public void percolateDown(int nodeIndex) {
        int childIndex = (nodeIndex * 2) + 1;
        int value = maxHeap[nodeIndex];

        while (childIndex < heapSize) {
            int maxValue = value;
            int maxIndex = -1;

            for (int i = 0; i < 2 && i + childIndex < heapSize; ++i) {
                if (maxHeap[i + childIndex] > maxValue) {
                    maxValue = maxHeap[i + childIndex];
                    maxIndex = i + childIndex;
                }
            }

            if (value == maxValue) {
                return;
            }
            else {
                int temp = maxHeap[nodeIndex];
                maxHeap[nodeIndex] = maxHeap[maxIndex];
                maxHeap[maxIndex] = temp;

                nodeIndex = maxIndex;
                childIndex = (nodeIndex * 2) + 1;
            }
        }
    }

    public void insert(int value) {
        if (heapSize == maxHeap.length) {
            resize();
        }

        maxHeap[heapSize] = value;
        ++heapSize;

        percolateUp(heapSize - 1);
    }

    public int remove() {
        int value = maxHeap[0];

        int replaceValue = maxHeap[heapSize - 1];
        --heapSize;

        if (heapSize > 0) {
            maxHeap[0] = replaceValue;

            percolateDown(0);
        }

        return value;
    }

    public String getHeapArrayString() {
        if (heapSize == 0) {
            return "[]";
        }

        String arrayString = String.format("[%d", maxHeap[0]);
        for (int i = 1; i < heapSize; i++) {
            arrayString += (", " + maxHeap[i]);
        }
        return arrayString + "]";
    }

    public int getHeapSize() {
        return heapSize;
    }
}

public class Heap {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int[] numbers = { 10, 2, 5, 18, 22 };

        for (int number : numbers) {
            maxHeap.insert(number);
            System.out.printf("   --> array: %s\n", maxHeap.getHeapArrayString());
        }

        while (maxHeap.getHeapSize() > 0) {
            int removedValue = maxHeap.remove();
            System.out.printf("   --> removed %d, array: %s\n", removedValue,
                    maxHeap.getHeapArrayString());
        }
    }
}
