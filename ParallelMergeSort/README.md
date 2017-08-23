# Whis this program does:
* Uses fork/join framework of java to solve Merge Sort problem in parallel.

# System Info:
Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz (4 CPUs), ~2.6GHz,8192MB RAM, Windows 7 Enterprise 64-bit (6.1, Build 7601) Service Pack 1.

# Observed Output: 
* C:\prafful_programs\Github stuff\Parallel Universe\Parallel Merge Sort>java -Xmx3072m ParallelMerge
* Input the size of array
* 200000000
* Input the threshold value
* 10000
* Filling array with random elements
* Parallel Threaded Solution: 10689ms to sort 200000000 elements
* Single Threaded Times: 30190ms to sort 200000000 elements
-------------------------------------------------------------------------------------------------------------------------------------------
* C:\prafful_programs\Github stuff\Parallel Universe\Parallel Merge Sort>java -Xmx4096m ParallelMerge
* Input the size of array
* 300000000
* Input the threshold value
* 10000
* Filling array with random elements
* Parallel Threaded Solution: 13579ms to sort 300000000 elements
* Single Threaded Times: 48016ms to sort 300000000 elements
