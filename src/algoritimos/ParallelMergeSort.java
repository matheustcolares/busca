package algoritimos;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort extends RecursiveAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array;
    private int left, right;

    public ParallelMergeSort(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left < right) {
            int mid = (left + right) / 2;
            ParallelMergeSort leftTask = new ParallelMergeSort(array, left, mid);
            ParallelMergeSort rightTask = new ParallelMergeSort(array, mid + 1, right);
            invokeAll(leftTask, rightTask);
            MergeSort.merge(array, left, mid, right);
        }
    }

    public void parallelMergeSort(int[] array,int nucleos) {
        ForkJoinPool pool = new ForkJoinPool(nucleos);
        pool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }
}