import java.io.*;
import java.util.concurrent.*;
import java.util.*;

class ParallelMergeUtil extends RecursiveAction{
	
	private int arr[];
	private int start, end;
	private int threshold;
	
	public ParallelMergeUtil(int arr[],int start,int end,int threshold)
	{
		this.arr = arr;
		this.start = start;
		this.end = end;
		this.threshold = threshold;
	}
	
	// Sequential merge sort call
	public void mergeSort(int low,int high)
	{
		if(low < high)
		{
			int mid = (low + high)/2;
			mergeSort(low,mid);
			mergeSort(mid+1,high);
			
			merge(low,mid,high);
		}
	}
	
	// Parallel merge sort call
	@Override
	public void compute()
	{
		if((end+1) - start <= threshold)
		{
			Arrays.sort(arr,start,end+1);
			return;
		}
		
		int mid = (start + end)/2;
		ParallelMergeUtil task1 = new ParallelMergeUtil(arr,start,mid,threshold);
		ParallelMergeUtil task2 = new ParallelMergeUtil(arr,mid+1,end,threshold);
		
		invokeAll(task1,task2);
		task1.join();
		task2.join();
		
		merge(start,mid,end);
	}
	
	// Sequential merge method used by both solutions
	public void merge(int low,int mid,int high)
	{
		//System.out.println(low + " " + mid + " " + high);
		int size = high - low + 1;
		int arrTemp[] = new int[size];
		int i = low;
		int k = mid + 1;
		int temp = 0;
		while((i <= mid) && (k <= high))
		{
			//System.out.println(i + " " + k + " " + high);
			if(arr[i] < arr[k])
			{
				//System.out.println("i :" + arr[i] + "k :" + arr[k]);
				arrTemp[temp] = arr[i];
				temp = temp + 1;
				i = i + 1;
			}
			else
			{
				
				arrTemp[temp] = arr[k];
				temp = temp + 1;
				k = k + 1;
			}
		}
		
		if(i > mid)
		{
			while(k <= high)
			{
				arrTemp[temp] = arr[k];
				temp = temp + 1;
				k = k + 1;
			}
		}else
		{
			while(i <= mid)
			{
				
				arrTemp[temp] = arr[i];
				temp = temp + 1;
				i = i + 1;
			}
		}
		int v = 0;
		for(int j = low; j <= high; j++)
		{
			
			
			arr[j] = arrTemp[v];
			v = v + 1;
		}
	}
}

public class ParallelMerge{
	
	private int arr[];
	private int size;
	
	public ParallelMerge(int size)
	{
		this.size = size;
		arr = new int[this.size];
	}
	
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input the size of array");
		int size = Integer.parseInt(br.readLine());
		
		System.out.println("Input the threshold value");
		int threshold = Integer.parseInt(br.readLine());
		
		ParallelMerge obj = new ParallelMerge(size);
		
		System.out.println("Filling array with random elements");
		
		Random r = new Random();
		for(int i=0;i<size;i++)
		{
			obj.arr[i] = r.nextInt(101);
		}
		
		ForkJoinPool pool = new ForkJoinPool(64);
		ParallelMergeUtil utilObj = new ParallelMergeUtil(obj.arr,0,obj.size-1,threshold);
		
		Long startTime = System.currentTimeMillis();
		pool.invoke(utilObj);
		Long stopTime = System.currentTimeMillis();
        Long parallelThreadTimes = (stopTime - startTime);
        System.out.println("Parallel Threaded Solution: " + parallelThreadTimes + "ms" + " to sort " + size + " elements");
		
		for(int i=0;i<size;i++)
		{
			obj.arr[i] = r.nextInt(101);
		}
		
		startTime = System.currentTimeMillis();
		utilObj.mergeSort(0,size-1);
		stopTime = System.currentTimeMillis();
        Long singleThreadTimes = (stopTime - startTime);
        System.out.println("Single Threaded Times: " + singleThreadTimes + "ms" + " to sort " + size + " elements");
		
		
	}
}
