package algoritimos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelInsertionSort extends RecursiveAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array;
    private int start, end;

    public ParallelInsertionSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < 1000) {
            InsertionSort.insertionSort(array);
        } else {
            int mid = (start + end) / 2;
            ParallelInsertionSort leftTask = new ParallelInsertionSort(array, start, mid);
            ParallelInsertionSort rightTask = new ParallelInsertionSort(array, mid + 1, end);
            invokeAll(leftTask, rightTask);
        }
    }

    public void parallelInsertionSort(int[] array, int nucleos) {
        ForkJoinPool pool = new ForkJoinPool(nucleos);
        pool.invoke(new ParallelInsertionSort(array, 0, array.length));
    }
}
