package cmsc420_s22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.lang.*;


public class QuakeHeap<Key extends Comparable<Key>, Value> {

	
	LinkedList[] roots;
	LinkedList[] rootsCopy;
	int nodeCt[];
	
	class Node implements Comparable<Node> { 
		Key key;
		Value value;
		Node left;
		Node right;
		Node parent;
		int lev;
		
		
		@Override
		public int compareTo(QuakeHeap<Key, Value>.Node arg0) {
			if(key.compareTo(arg0.key) > 0) {
				return 1;
			}
			
			else if(key.compareTo(arg0.key) < 0) { 
				return -1;
			}
			
			return 0;
		}		
	} 
	
	
	
    	
	public class Locator { /* locates a node */
		Node u;
		private Locator(Node u) { this.u = u; }
		private Node get() { return u; }
	}
// add node to the level 0 of, roots[0].add
		// child null and stuff
	
	public Locator insert(Key x, Value v) { 
		// insert (x,v)
		// ... your code to create a new leaf node u
		Node u = new Node();
		u.key = x;
		u.value = v;
		u.left = null;
		u.right = null;
		u.parent = null;
		
		u.lev = 0;
		
		roots[0].add(u);
		rootsCopy[0].add(u);
		
		nodeCt[0]+= 1; 					
		
		return new Locator(u);
	}
	
	
	public QuakeHeap(int nLevels) {
		
		
		this.roots = new LinkedList[nLevels];
		rootsCopy = new LinkedList[nLevels];;
		
		for(int i = 0; i < nLevels; i++){ // number of levels to allocate			
			roots[i] = new LinkedList<Node>();
			rootsCopy[i] = new LinkedList<Node>();
			
		}
				
		nodeCt = new int[nLevels];
		
	}
	
	
	
	
	public void clear() { 

		
		for(int i=0; i < roots.length; i++) {
			nodeCt[i] = 0;
			roots[i] = new LinkedList<Node>();
			rootsCopy[i] = new LinkedList<Node>();
		}
	}
	
	
	
	
	
	
	public Key getMinKey() throws Exception {
		
		if(nodeCt[0] == 0) {
			throw new IllegalArgumentException("Empty heap");
		}
		else {		
			for(int i = 0; i < roots.length-1; i++) { // for every level starting at 0
				
				if(nodeCt[i] > 1) {
					while(roots[i].size() > 1) {
									
						Collections.sort(roots[i]); //sort the level 0 linked list
						
						Node n1 = (QuakeHeap<Key, Value>.Node) roots[i].remove(); // first two from sorted will merge
						Node n2 = (QuakeHeap<Key, Value>.Node) roots[i].remove();
						
						Node newParent = new Node(); // Create new parent for the first two nodes
						newParent.lev = i+1;
						
						
						n1.parent = newParent; // giving the two min nodes a parent
						n2.parent = newParent; // the minimum of n1 and n2 becomes parent
						
						newParent.key = n1.key; // giving the parent the min node's key
						newParent.value = n1.value;
						newParent.left = n1;  // setting nodes as parent's children 
						newParent.right = n2;

						
						roots[i+1].add(newParent); // putting new parent (min key) at the level above
						rootsCopy[i+1].add(newParent);
						
						nodeCt[i+1] += 1; // the level above increments in number of nodes
					}
				}
			}		
		
		int s = 0;	
		for(int i = nodeCt.length-1 ; i > 0; i--) {
			if(roots[i].size() != 0) {				
				s = i;
				break;
			}	
		}
				
		Collections.sort(rootsCopy[s]); // might have to make a copy		
		
		Node nn = (QuakeHeap<Key, Value>.Node) rootsCopy[s].getFirst();
		
		return nn.key;
		}
	}
	
	// helper for getMax
	public int recurseMax(Node n, Locator r) {
		int m = 0;
		int left = 0 ;
		int right = 0;
		
		if(n == null) {
			return 0;
		}
		
		else if(n.value == r.get().value) {
			return n.lev;
		}
		
		left = recurseMax(n.left, r); // search left
		right = recurseMax(n.right,r); // search right
		return Math.max(left, right);
		
		
	}
	
	
	public int getMaxLevel(Locator r) { 
		int max = 0;
		int level = 0;
		
		for(int i=0; i < nodeCt.length; i++) { 	// for every level
			for (int j = 0; j < roots[i].size(); j++) { 	// for every node in level
			    level = recurseMax((Node)roots[i].get(j), r); 	// recursively get max key from left/right
			    max = Math.max(level, max); 	// save the max
			}			
		}
		return max;
	}
		
		

	
	public void helper(Node n, ArrayList<String> hList) {
		
		if(n == null) {			
			hList.add("[null]");
			return;
		}		
		if(n.left == null && n.right == null) {
			hList.add("[" + n.key + " " + n.value + "]");
			return;
		}		
		else {
			hList.add("(" + n.key + ")");
		}
		
		helper(n.left, hList);
		helper(n.right, hList);
	}
	
	
	public ArrayList<String> listHeap() { /* ... */ 
		
		ArrayList<String> heapList = new ArrayList<String>();	
		
		int topLevel = 0;
				
		for(int i = nodeCt.length-1 ; i > 0; i--) { // finding highest level
			if(nodeCt[i] != 0) {						
				topLevel = i; 				
				break;
			}	
		}

		for(int i=0; i <= topLevel; i++) { //from level 0 to top level i
			if(nodeCt[i] != 0) {
				String header = "{lev: " + i + " nodeCt: " + nodeCt[i] + "}";
				heapList.add(header);
				
				Collections.sort(roots[i]);
				
				for (int j = 0; j < roots[i].size(); j++) {
				    helper((QuakeHeap<Key, Value>.Node) roots[i].get(j), heapList);
				}
			}	
		}	
		return heapList; 
	}
	
	

}
