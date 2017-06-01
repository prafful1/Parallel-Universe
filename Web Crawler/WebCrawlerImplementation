package webCrawler;

import java.util.Collection;
import java.util.*;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.HashSet;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;



public class WebCrawlerImplementation implements WebLinksHandler {
	

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>()); // Does not store duplicate links.

    private String url;
    private ForkJoinPool mainPool;
	
	
	public WebCrawlerImplementation()
	{}

    public WebCrawlerImplementation(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    private void startCrawlingParallel(int maxLinksToVisit) {
        mainPool.invoke(new ParallelLinkFinder(this.url, this,maxLinksToVisit));
    }
	
	private void startCrawlingSequential(String uri, int maxLinksToVisit)
	{
		(new SequentialLinkFinder()).crawlSingleThread(uri, maxLinksToVisit);
	}

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    
    public static void main(String[] args) throws Exception {
		
		//Parallel threaded solution
		Long startTime = System.currentTimeMillis();
        WebCrawlerImplementation wb = new WebCrawlerImplementation("http://www.javaworld.com", 1);
		wb.startCrawlingParallel(100);
		Long stopTime = System.currentTimeMillis();
        Long forkedThreadTimes = (stopTime - startTime);
        System.out.println(wb.size() + "Fork / join search took " + forkedThreadTimes + "ms");
		
		//Single threaded solution
		startTime = System.currentTimeMillis();
		WebCrawlerImplementation wb2 = new WebCrawlerImplementation();
		wb2.startCrawlingSequential("http://www.javaworld.com",100);
		stopTime = System.currentTimeMillis();
		Long singleThreadTimes = (stopTime - startTime);
		System.out.println("Single threaded time " + singleThreadTimes + "ms");
    }
}
