# What this application does?
This application is an implementation of a Web Crawler in Java. It crawls a specified number of web links provided by the user both in parallel an also in a sequence. Later the application displays the time difference to visit same number of links by both approaches.

# Flow of Application? 
+ # WebCrawlerImplementation: 
This class contains the main method.It implements WebLinksHandler interface and calls methods from ParallelLinkFinder and SequentialLinkFinder classes. It finally displays the time difference between parallel and sequential approcahes in visiting same number of links. 
+ # ParallelLinkFinder: 
This class extends RecursiveAction and overrides compute method. Compute method contains the business logic for traversing through web links in parallel using fork/join framework.
+ # SequentialLinkFinder: 
This class contains the business logic for traversing through web links in a sequence. 
+ # WebLinksHandler: 
It is an interface and is implemented by WebCrawlerImplementation. It helps WebCrawlerImplementation to manage parallel web links traversal in a better way. 

# System Info:
Intel(R) Core(TM) i5-3320M CPU @ 2.60GHz (4 CPUs), ~2.6GHz,8192MB RAM, Windows 7 Enterprise 64-bit (6.1, Build 7601) Service Pack 1

# Performance difference between Single threaded and Multiple threaded solution: (This output will be different for different users depending on source link and internet speed)
+ # First Trial:
+ Fork / join search took 21787ms to visit 124 links
+ Single threaded time 387017ms to visit 100 links

+ # Second Trial:
+ Fork / join search took 25138ms to visit 124 links
+ Single threaded time 484218ms to visit 100 links
