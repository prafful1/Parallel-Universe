import java.io.*;
import java.util.concurrent.*;

/*
System Info:
Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz (4 CPUs), ~2.6GHz,8192MB RAM,
Windows 7 Enterprise 64-bit (6.1, Build 7601) Service Pack 1

Java Info:
java version "1.8.0_51"

This problem is solved both in parallel withh FORK/JOIN framework and also by a single thread.
This program fills an array of desired size with random values.
It later returns the sum of all elements of the array.


After many experiments THRESHOLD_VALUE was decided to keep between 10000 to 999999.
Input array size varied between 40000000 to 99999999.
Performance consistently improved from 15% to more than 100% with Fork/Join framework compared to a single threaded solution.
Observed time difference is available after multiple tests in README document.
*/

// Extends RecursiveTask Class. 
// This class overrides compute() method from RecursiveTask Class.
class CalculateSum extends RecursiveTask<Long>{
	private int arr[];
	private int start,end;
	private static final int THRESHOLD_VALUE = 999999;
	
	public CalculateSum(int arr[],int start,int end)
	{
		this.arr = arr;
		this.start = start;
		this.end = end;
	}
	
	// This class is called everytime a new task is forked.
	// It returns a Long value which is sum of all elements  
	// of the array of the subtask.
	@Override
	protected Long compute(){
		if(end-start <= CalculateSum.THRESHOLD_VALUE)
		{
			long sum = 0;
			for(int i=start;i<=end;i++)
			{
				sum = sum + arr[i];
			}
			
			return sum;
		}
		
		else
		{
			int mid = start + (end-start) / 2;
			CalculateSum task1 = new CalculateSum(arr,start,mid);
			CalculateSum task2 = new CalculateSum(arr,mid+1,end);
			
			invokeAll(task1,task2);
			
			// subtasks returning thier output.
			return task1.join() + task2.join();
		}
	}
}



public class ForkJoinAddition{
	
	// java ForkJoinAddition args[0]
	// args[0] will be the size of array. 
	public static void main(String args[])throws IOException
	{
		long startTime;
		long stopTime;
		
		int arrSize = Integer.parseInt(args[0]);
		int arr[] = new int[arrSize];
		for(int i=0;i<arrSize;i++)
		{
			// Filling array with random values from 1 to 100 inclusive.
			arr[i] = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		}
		
		ForkJoinPool pool = new ForkJoinPool();
		CalculateSum calObject = new CalculateSum(arr,0,arrSize-1);
		
		
		startTime = System.currentTimeMillis();
		
		// Task is invoked here and will be divided into subtasks.
		// compute method is called and will be called further
		// to compute subproblems.
        long result = pool.invoke(calObject); 
        stopTime = System.currentTimeMillis();
        long multipleThreadTimes = stopTime - startTime;
		
		// Total time to calculate the total sum with fork/join
		// fromework is displayed here.
        System.out.println(result + ": Fork Join search took " + multipleThreadTimes + "ms");
		
		ForkJoinAddition object = new ForkJoinAddition();
		
		
		startTime = System.currentTimeMillis();
        result = object.singleThreadCalculation(arr,0,arrSize-1);
        stopTime = System.currentTimeMillis();
        long singleThreadTimes = stopTime - startTime;
		
		// Time calculation after computing on a single thread.
        System.out.println(result + ": Single Thread search took " + singleThreadTimes + "ms");
		
	}
	
	public Long singleThreadCalculation(int arr[],int start,int end)
	{
		long sum = 0;
		for(int i=start;i<=end;i++)
		{
			sum = sum + arr[i];
		}
			
		return sum;
	}
}
