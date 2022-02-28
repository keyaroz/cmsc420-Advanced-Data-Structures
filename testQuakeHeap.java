package cmsc420_s22;

import cmsc420_s22.QuakeHeap.Locator;

public class testQuakeHeap {
	


	public static void main(String[] args) throws Exception {
		
		QuakeHeap heap = new QuakeHeap(5);
		
		heap.insert(4, "JFK");
		Locator x =heap.insert( 9, "ORD");
		heap.insert(20, "DFW" );
		System.out.println("min 1): " + heap.getMinKey());
		
		heap.insert( 5, "DEL");
		heap.insert( 6, "LAX");
		
		System.out.println("min 2): " + heap.getMinKey());
		

		
		
		
		
		System.out.println("min: " + heap.getMinKey() + " max level: " + heap.getMaxLevel(x));
		

	}
	
	
	

}
