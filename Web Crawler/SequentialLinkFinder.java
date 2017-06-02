package webCrawler;

import java.net.URL;
import java.util.*;
import java.util.concurrent.RecursiveAction;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;


/* 
Implements sequential crawler. 
*/ 
class SequentialLinkFinder{
	
	private Collection<String> list1;
	private LinkedList<String> list2;
	
	public SequentialLinkFinder()
	{
		list1 = Collections.synchronizedSet(new HashSet<String>());
		list2 = new LinkedList<String>();
	}
	
	
	public void crawlSingleThread(String url, int maxLinksToVisit)
	{
		
		list2.add(url);
		
		while(!list2.isEmpty())
		{
			
			try{
			
			url = list2.poll();
			list1.add(url);
			
			URL uriLink = new URL(url);
            Parser parser = new Parser(uriLink.openConnection());
            NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

            for (int i = 0; i < list.size(); i++) {
                LinkTag extracted = (LinkTag) list.elementAt(i);
				 if (!extracted.extractLink().isEmpty() && !list1.contains(extracted.extractLink())) {
						list2.add(extracted.extractLink());
                    }
                }
				}catch(Exception e)
			{}
            if(list1.size() == maxLinksToVisit)
			{
				break;
			}
		}
		
		
	}
}
