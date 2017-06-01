package webCrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;


/* 
Implements parallel crawler. 
*/ 
class ParallelLinkFinder extends RecursiveAction {

    private String url;
    private WebLinksHandler cr;
	private static int crawlerFlag = 0;
	private static int maxLinksToVisit = 0;
    /**
     * Used for statistics
     */
    private static final long t0 = System.currentTimeMillis();

    public ParallelLinkFinder(String url, WebLinksHandler cr, int maxLinksToVisit) {
        this.url = url;
        this.cr = cr;
		ParallelLinkFinder.maxLinksToVisit = maxLinksToVisit;
    }

    @Override
    public void compute() {
		if(ParallelLinkFinder.crawlerFlag == 1)
			return;
		
        if (!cr.visited(url)) {
            try {
                List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

                for (int i = 0; i < list.size(); i++) {
					if(ParallelLinkFinder.crawlerFlag == 1)
						break;
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.extractLink().isEmpty()
                            && !cr.visited(extracted.extractLink())) {

                        actions.add(new ParallelLinkFinder(extracted.extractLink(), cr, ParallelLinkFinder.maxLinksToVisit));
                    }
                }
                cr.addVisited(url);

                if (cr.size() == ParallelLinkFinder.maxLinksToVisit) {
					ParallelLinkFinder.crawlerFlag = 1;
					return;
                }

                //invoke recursively
                invokeAll(actions);
            } catch (Exception e) {
               
            }
        }
    }
}
