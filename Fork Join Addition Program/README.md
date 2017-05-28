Readme document for ForkJoinAddition program

# What this program does?
+ This program performs addition of all elements inside the array using Divide and Conquer technique with Fork/Join framework. (Parallel threaded solution).
+ It also performs addition of all elements of array sequentially. (Single threaded solution).
+ Performance Comparison between both solutions is displayed below.

# System Info:
Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz (4 CPUs), ~2.6GHz,8192MB RAM,
Windows 7 Enterprise 64-bit (6.1, Build 7601) Service Pack 1

# Java Version:
java version "1.8.0_51"

# Performance difference between Single threaded and Multiple threaded solution.
+	C:\prafful_programs\Github stuff\Parallel Universe\Addition Program>java ForkJoinAddition 99999999
	  + 5049997171: Fork Join search took 39ms
	  + 5049997171: Single Thread search took 71ms
  
+	C:\prafful_programs\Github stuff\Parallel Universe\Addition Program>java ForkJoinAddition 99999999
	  + 5049868017: Fork Join search took 40ms
	  + 5049868017: Single Thread search took 71ms

+	C:\prafful_programs\Github stuff\Parallel Universe\Addition Program>java ForkJoinAddition 90000000
	  + 4544896136: Fork Join search took 35ms
	  + 4544896136: Single Thread search took 63ms
	
+	C:\prafful_programs\Github stuff\Parallel Universe\Addition Program>java ForkJoinAddition 90000000
      + 4545017083: Fork Join search took 39ms
    + 4545017083: Single Thread search took 85ms
