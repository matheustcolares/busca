package algoritimos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelQuickSort extends RecursiveAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array;
    private int low, high;

    public ParallelQuickSort(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            int pivotIndex = QuickSort.partition(array, low, high);
            ParallelQuickSort leftTask = new ParallelQuickSort(array, low, pivotIndex - 1);
            ParallelQuickSort rightTask = new ParallelQuickSort(array, pivotIndex + 1, high);
            invokeAll(leftTask, rightTask);
        }
    }

    public void parallelQuickSort(int[] array,int nucleos) {
        ForkJoinPool pool = new ForkJoinPool(nucleos);
        pool.invoke(new ParallelQuickSort(array, 0, array.length - 1));
    }
}
