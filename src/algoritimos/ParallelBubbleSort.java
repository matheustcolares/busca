package algoritimos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelBubbleSort extends RecursiveAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array;
    private int start, end;

    public ParallelBubbleSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if (end - start < 1000) { // Threshold for parallelism
            BubbleSort.bubbleSort(array); // Call serial version for small segments
        } else {
            int mid = (start + end) / 2;
            ParallelBubbleSort leftTask = new ParallelBubbleSort(array, start, mid);
            ParallelBubbleSort rightTask = new ParallelBubbleSort(array, mid, end);
            invokeAll(leftTask, rightTask);
        }
    }

    public void parallelBubbleSort(int[] array,int nucleos) {
        ForkJoinPool pool = new ForkJoinPool(nucleos);
        pool.invoke(new ParallelBubbleSort(array, 0, array.length));
    }
}