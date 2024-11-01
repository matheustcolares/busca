package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import algoritimos.BubbleSort;
import algoritimos.InsertionSort;
import algoritimos.MergeSort;
import algoritimos.ParallelBubbleSort;
import algoritimos.ParallelInsertionSort;
import algoritimos.ParallelMergeSort;
import algoritimos.ParallelQuickSort;
import algoritimos.QuickSort;


@ManagedBean(name="mainBean")
@SessionScoped
public class Main {
	private BarChartModel model;
	private int numberPages = 10;
	private int numberFrame;
	
	public void run() {
			setModel(new BarChartModel());
	
	        int[] pages = criarArrayAleatorio(this.numberPages);
	        
			/*
			 * for(int i =0;i<pages.length;i++) { System.out.println(pages[i]); }
			 */

	       // int frameSize = this.numberFrame;
	        
	        BubbleSort bs = new BubbleSort();
	        ParallelBubbleSort pbs = new ParallelBubbleSort(pages, pages[0], pages[pages.length-1]);
	        
	        InsertionSort is = new InsertionSort();
	        ParallelInsertionSort pis = new ParallelInsertionSort(pages, pages[0], pages[pages.length-1]);
	        
	        MergeSort ms = new MergeSort();
	        ParallelMergeSort pms = new ParallelMergeSort(pages, pages[0], pages[pages.length-1]);
	        
	        QuickSort qs = new QuickSort();
	        ParallelQuickSort pqs = new ParallelQuickSort(pages, pages[0], pages[pages.length-1]);
	        
	        ArrayList<Long> tempoBS = new ArrayList<Long>();
	        ArrayList<Long> tempoPBS = new ArrayList<Long>();
	        
	        ArrayList<Long> tempoIS = new ArrayList<Long>();
	        ArrayList<Long> tempoPIS = new ArrayList<Long>();
	        
	        ArrayList<Long> tempoMS = new ArrayList<>();
	        ArrayList<Long> tempoPMS = new ArrayList<Long>();
	        
	        ArrayList<Long> tempoQS = new ArrayList<Long>();
	        ArrayList<Long> tempoPQS = new ArrayList<Long>();
	        
	        for(int i=0;i<5;i++) {
	        	Long tempoInicial = System.nanoTime();
	        	BubbleSort.bubbleSort(pages);
	        	tempoBS.add(System.nanoTime() - tempoInicial);
	        	
	        	Long tempoIPBS = System.nanoTime();
	        	pbs.parallelBubbleSort(pages, i+1);
	        	tempoPBS.add(System.nanoTime() - tempoIPBS);
	        	
	        	Long tIS = System.nanoTime();
	        	InsertionSort.insertionSort(pages);
	        	tempoIS.add(System.nanoTime() - tIS);
	        	
	        	Long tPIS = System.nanoTime();
	        	pis.parallelInsertionSort(pages, i+1);
	        	tempoPIS.add(System.nanoTime() - tPIS);
	        	
	        	Long tMS = System.nanoTime();
	        	MergeSort.mergeSort(pages,  pages[0], pages[pages.length-1]);
	        	tempoMS.add(System.nanoTime() - tMS);
	        	
	        	Long tPMS = System.nanoTime();
	        	pms.parallelMergeSort(pages, i+1);
	        	tempoPMS.add(System.nanoTime() - tPMS);
	        	
	        	Long tQS = System.nanoTime();
	        	QuickSort.quickSort(pages, pages[0], pages[pages.length-1]);
	        	tempoQS.add(System.nanoTime() - tQS);
	        	
	        	Long tPQS = System.nanoTime();
	        	pqs.parallelQuickSort(pages, i+1);
	        	tempoPQS.add(System.nanoTime() - tPQS);
	        	
	        	 String fileName = "C:\\Users\\mathe\\Documents\\sort_times.csv";
	        	    int dataSize = pages.length;
	        	
	        	 exportToCSV("BubbleSort", "Serial", dataSize, 1, tempoBS.get(i) / 1e6, fileName); // Tempo em milissegundos
	        	    exportToCSV("BubbleSort", "Parallel", dataSize, i + 1, tempoPBS.get(i) / 1e6, fileName);

	        	    exportToCSV("InsertionSort", "Serial", dataSize, 1, tempoIS.get(i) / 1e6, fileName);
	        	    exportToCSV("InsertionSort", "Parallel", dataSize, i + 1, tempoPIS.get(i) / 1e6, fileName);

	        	    exportToCSV("MergeSort", "Serial", dataSize, 1, tempoMS.get(i) / 1e6, fileName);
	        	    exportToCSV("MergeSort", "Parallel", dataSize, i + 1, tempoPMS.get(i) / 1e6, fileName);

	        	    exportToCSV("QuickSort", "Serial", dataSize, 1, tempoQS.get(i) / 1e6, fileName);
	        	    exportToCSV("QuickSort", "Parallel", dataSize, i + 1, tempoPQS.get(i) / 1e6, fileName);
	        	
	        	
	        }
	        
	        System.out.println(tempoPMS);
	        ChartSeries algoritimos = new ChartSeries();
	        
	        algoritimos.set("BubbleSort", tempoBS.get(tempoBS.size()-1));
	        algoritimos.set("BubbleSort Paralelo", tempoPBS.get(tempoPBS.size()-1));
	        algoritimos.set("InsertionSort", tempoIS.get(tempoIS.size()-1));
	        algoritimos.set("InsertionSort Paralelo", tempoPIS.get(tempoPIS.size()-1));
	        algoritimos.set("MergeSort", tempoMS.get(tempoMS.size()-1));
	        algoritimos.set("MergeSort Paralelo", tempoPMS.get(tempoPMS.size()-1));
	        algoritimos.set("QuickSort", tempoQS.get(tempoQS.size()-1));
	        algoritimos.set("QuickSort Paralelo", tempoPQS.get(tempoPQS.size()-1));
	        
	        model.addSeries(algoritimos);
	        model.setTitle("Algoritimos de Busca");
	        model.setLegendPosition("ne");
	        
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Algoritimos");
	        
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Tempo de execução");
	        yAxis.setMin(0);
	        yAxis.setMax(500000);
	        
	        //System.out.println("Método 1 (FIFO) - " + fifoFaults + " faltas de página");
	        //System.out.println("Método 2 (LRU) - " + lruFaults + " faltas de página");
	        //System.out.println("Método 3 (Clock) - " + clockFaults + " faltas de página");
	        //System.out.println("Método 4 (NFU) - " + nfuFaults + " faltas de página");
	    }
	public static int[] criarArrayAleatorio(int tamanho) {
        Random random = new Random();
        int[] array = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(tamanho); 
        }

        return array;
    }
	
	public void exportToCSV(String algorithm, String executionType, int dataSize, int numThreads, double averageTime, String fileName) {
	    try (FileWriter writer = new FileWriter(fileName, true)) {
	        writer.append(algorithm)
	              .append(',')
	              .append(executionType)
	              .append(',')
	              .append(String.valueOf(dataSize))
	              .append(',')
	              .append(String.valueOf(numThreads))
	              .append(',')
	              .append(String.valueOf(averageTime))
	              .append('\n');
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public BarChartModel getModel() {
		return model;
	}
	public void setModel(BarChartModel model) {
		this.model = model;
	}
	public int getNumberPages() {
		return numberPages;
	}
	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}
	public int getNumberFrame() {
		return numberFrame;
	}
	public void setNumberFrame(int numberFrame) {
		this.numberFrame = numberFrame;
	}

	}

