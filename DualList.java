package cmsc420_s22; // Don't delete this line or your file won't pass the autograder

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * DualList (skeleton)
 *
 * MODIFY THE FOLLOWING CLASS.
 *
 * You are free to make whatever changes you like or to create additional
 * classes and files.
 */

public class DualList<Key1 extends Comparable<Key1>, Key2 extends Comparable<Key2>> {

	// -----------------------------------------------------------------
	// Public members - You should not modify the function signatures
	// -----------------------------------------------------------------
	private Key1 k1;
	private Key2 k2;
	private int size = 0;
	private ArrayList<ArrayList<Object>> dualArr; // list of objects
	private ArrayList<ArrayList<Object>> dualArr2; // list of objects
	private Map<Object, ArrayList<ArrayList<Object>>> key1Map;   //{A, {A, 5}{A, 6}}
	private Map<Object, ArrayList<ArrayList<Object>>> key2Map; //{5, {A, 5}}

	
	public DualList() {
		key1Map = new HashMap<Object, ArrayList<ArrayList<Object>>>(); //hashmap for sorting
		key2Map = new HashMap<Object, ArrayList<ArrayList<Object>>>();
		
		dualArr = new ArrayList<ArrayList<Object>>();
		dualArr2 = new ArrayList<ArrayList<Object>>();
		
		
		

	} // constructor
	
	
	
	
	public void insert(Key1 x1, Key2 x2) {
		
//		ArrayList<ArrayList<Object>> duals = new ArrayList<ArrayList<Object>>(); 
		ArrayList<Object> dual_element = new ArrayList<Object>(); 
		ArrayList<Object> dual_element2 = new ArrayList<Object>(); 
		
		
		
		dual_element.add(x1); //{A}
		dual_element.add(x2); //{A, 5}
		dualArr.add(dual_element);
		
		
		dual_element2.add(x2); //{A}
		dual_element2.add(x1); //{5, A}
		dualArr2.add(dual_element2);
				
		
		
		size++;
		

				
		} // insert a new pair
	
	
	public int size() { /* ... */ 
		
		return size; 
	} // return the number of pairs
	
	public Key2 extractMinKey1() throws Exception { /* ... */ 
		if(size == 0) {
			throw new Exception("Attempt to extract from an empty list");
		}
		
		
		
		HashSet<ArrayList> hashy = new HashSet<>();
		List<Object> noDupes = new ArrayList<>();
		List<Object> noDupes2 = new ArrayList<>();
		
		ArrayList<ArrayList<Object>> concatArr = new ArrayList<ArrayList<Object>>();
		
		
		
		for(ArrayList l : dualArr) { // hash of pairs flipped pairs
			hashy.add(l); 			
		}
		
		
		
		
		List<Object> listSort = new ArrayList<Object>(hashy);
		listSort.sort((p1, p2) -> ((Comparable<Key1>) ((ArrayList<ArrayList<Object>>) p1).get(0)).compareTo((Key1) ((ArrayList<ArrayList<Object>>) p2).get(0)));
		int i = 0;
		
		//listSort is sorted by Key1 
		
		for(Object pair : listSort) { // creating a list of non dupe Key1s
			noDupes.add(((ArrayList<ArrayList<Object>>) listSort.get(i)).get(0));

			i++;
		}
		
		
		for(Object el : noDupes) {
			if (!noDupes2.contains(el)) {
				noDupes2.add(el);
			}		
		}
		//noDupes2 contains a list of Key1s without duplicates
		

		
		for(Object k : noDupes2) {
			concatArr.addAll(dupesHelper(listSort, k));
		}
		
		// concatArr is a list sorted by Key1, then each Key1 is sorted by Key2
		
		
		ArrayList<Object> minKey1 = new ArrayList<Object>();
		ArrayList<Object> flippedMinKey1 = new ArrayList<Object>();
		
		
		Key2 x = null;
		for (Object arr : concatArr) {

			x = (Key2) ((ArrayList<ArrayList<Object>>) arr).get(1); // get first Key2 
			break;
	    }
		
	//-------------------------------------------------
		
		
		minKey1 = (ArrayList<Object>) concatArr.get(0); // smallest Key2 pair
		
		flippedMinKey1.add(minKey1.get(1));
		flippedMinKey1.add(minKey1.get(0)); // flipped


		int theIndex = dualArr.indexOf(minKey1);
		
		int theIndex2 = dualArr2.indexOf(flippedMinKey1);
		
		dualArr2.remove(theIndex2);
		
		dualArr.remove(theIndex);
		
	//------------------------------------------------
		
		size--;
		
		
		return x;
		
		 
	} 
	
	
//	//------------------------------------------------------
//	
	public Key1 extractMinKey2() throws Exception { /* ... */ 
		if(size == 0) {
			throw new Exception("Attempt to extract from an empty list");
		}
		
		
		
		HashSet<ArrayList> hashy = new HashSet<>();
		List<Object> noDupes = new ArrayList<>();
		List<Object> noDupes2 = new ArrayList<>();
		
		ArrayList<ArrayList<Object>> concatArr = new ArrayList<ArrayList<Object>>();
		
		
		
		for(ArrayList l : dualArr2) { // hash of pairs flipped pairs
			hashy.add(l); 			
		}
		
		
		
		
		List<Object> listSort = new ArrayList<Object>(hashy);
		listSort.sort((p1, p2) -> ((Comparable<Key1>) ((ArrayList<ArrayList<Object>>) p1).get(0)).compareTo((Key1) ((ArrayList<ArrayList<Object>>) p2).get(0)));
		int i = 0;
		
		//listSort is sorted by Key2 (still flipped pairs
		
		for(Object pair : listSort) { // creating a list of non dupe Key2s
			noDupes.add(((ArrayList<ArrayList<Object>>) listSort.get(i)).get(0));

			i++;
		}
		
		for(Object el : noDupes) {
			if (!noDupes2.contains(el)) {
				noDupes2.add(el);
			}		
		}
		

		
		for(Object k : noDupes2) {
			concatArr.addAll(dupesHelper(listSort, k));
		}
		

		
		
		ArrayList<Object> minKey2 = new ArrayList<Object>();
		ArrayList<Object> flippedMinKey2 = new ArrayList<Object>();
		
		
		
		Key1 x = null;
		for (Object arr : concatArr) {

			x = (Key1) ((ArrayList<ArrayList<Object>>) arr).get(1);
			break;
	    }
		
	//-------------------------------------------------
		
		
		minKey2 = (ArrayList<Object>) concatArr.get(0); // smallest Key2 pair
		
		flippedMinKey2.add(minKey2.get(1));
		flippedMinKey2.add(minKey2.get(0)); // flipped


		int theIndex = dualArr.indexOf(flippedMinKey2);
		
		int theIndex2 = dualArr2.indexOf(minKey2);
		
		dualArr2.remove(theIndex2);
		
		dualArr.remove(theIndex);
		
	//------------------------------------------------
		
		size--;
		
		
		return x;
		
		
		
	
		
	} // remove smallest by Key2 and return its Key1 value
//	
//	
//	
//	
	
	public ArrayList<ArrayList<Object>> dupesHelper(List<Object> list, Object k){
		int i = 0;
		HashSet<ArrayList> hashy = new HashSet<>();
		ArrayList<ArrayList<Object>> dupes = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> returnArr = new ArrayList<ArrayList<Object>>();
		for(Object pair : list) {
			
			
							
//			System.out.println("pair: " + pair);
//			System.out.println("pair: " + ((ArrayList<ArrayList<Object>>) pair).get(0) + "listsortget(0): " + ((ArrayList<ArrayList<Object>>) listSort.get(i)).get(0));
//			System.out.println("oh no");
			if (((Comparable<Key1>) ((ArrayList<ArrayList<Object>>) pair).get(0)).compareTo((Key1) k) == 0) { //comparing key1s
//				System.out.println("pair.get(0): " + ((ArrayList<ArrayList<Object>>) pair).get(0));
//				System.out.println("noDupes.get(i): " + k);
				dupes.add((ArrayList<Object>) pair); // key1
//				System.out.println("dupess: " + dupes);
			}
			
//			dupes.sort((p1, p2) -> ((Comparable<Key1>) p1.get(1)).compareTo((Key1) p2.get(1)));
			
			
			
		}
		
		for(ArrayList<Object> l : dupes) {
			hashy.add(l);
		}
		
		List<Object> listSort = new ArrayList<Object>(dupes);
		listSort.sort((p1, p2) -> ((Comparable<Key2>) ((ArrayList<ArrayList<Object>>) p1).get(1)).compareTo((Key2) ((ArrayList<ArrayList<Object>>) p2).get(1)));
		
		for(Object o : listSort) {
			returnArr.add((ArrayList<Object>) o);
		}
		 
		return returnArr; // returns sorted dupes
		
	}
	
	
	//-----------------------------------------------
	public ArrayList<String> listByKey1() { /* ... */ 
		
		ArrayList<String> returnArr = new ArrayList<String>();
		if(dualArr.isEmpty()) {
			return returnArr;
		}
		
		HashSet<ArrayList> hashy = new HashSet<>();
		ArrayList<Object> noDupes = new ArrayList<>();
		ArrayList<Object> noDupes2 = new ArrayList<>();
		ArrayList<ArrayList<Object>> concatArr = new ArrayList<ArrayList<Object>>();
		concatArr.add(dualArr.get(0));
		concatArr.remove(0);
		
		
		
		for(ArrayList l : dualArr) {
			hashy.add(l);
		}
		
//		System.out.println("listbykey1 hashy: " + hashy);
		
		
		
		List<Object> listSort = new ArrayList<Object>(hashy);
		listSort.sort((p1, p2) -> ((Comparable<Key1>) ((ArrayList<ArrayList<Object>>) p1).get(0)).compareTo((Key1) ((ArrayList<ArrayList<Object>>) p2).get(0)));
		
		
		// listSort  is sorted by Key1, but Key1s are not sorted by Key2 example:  {A, 3}{A, 5},
		int i = 0; 
		for(Object pair : listSort) {
			noDupes.add(((ArrayList<ArrayList<Object>>) pair).get(0));  //{A, A, C, C, C, C, Z} all Key1s
//			System.out.println("pair: " + pair); // this is fine
			i++;
		}
		
		for(Object el : noDupes) {         // removing duplicates -> {A, C, Z}
			if (!noDupes2.contains(el)) {
				noDupes2.add(el);
			}
		}
		
		
		
		
	
		for(Object k : noDupes2) {	
			ArrayList<ArrayList<Object>> temp = dupesHelper(listSort, k);
			
			for(Object j : temp ) {
				concatArr.add((ArrayList<Object>) j);
			}
			temp.clear();
		}
//		System.out.println("conat arr: " + concatArr);
		for(int n=0; n<concatArr.size(); n++) {
			returnArr.add("(" + concatArr.get(n).get(0) + ", " + concatArr.get(n).get(1) + ")");
		} //why is gradescope saying its size 2 concatArr
		
//		for (Object arr : concatArr) {
//			returnArr.add("(" + ((ArrayList<ArrayList<Object>>) arr).get(0) + ", " + ((ArrayList<ArrayList<Object>>) arr).get(1) + ")");
//	    }
		
		return returnArr;
		
		
		
		
		
		
	} // return a list sorted by Key1
	
//	
//	
	
	
	
	
	
	public ArrayList<String> listByKey2() { /* ... */ 
		
		
		
		ArrayList<String> returnArr = new ArrayList<String>();
		if(dualArr.isEmpty()) {
			return returnArr;
		}
		
		HashSet<ArrayList> hashy = new HashSet<>();
		List<Object> noDupes = new ArrayList<>();
		List<Object> noDupes2 = new ArrayList<>();
		
		ArrayList<ArrayList<Object>> concatArr = new ArrayList<ArrayList<Object>>();
		
		
		
		for(ArrayList l : dualArr2) { // hash of pairs flipped pairs
			hashy.add(l); 			
		}
		
		
		
		
		List<Object> listSort = new ArrayList<Object>(hashy);
		listSort.sort((p1, p2) -> ((Comparable<Key1>) ((ArrayList<ArrayList<Object>>) p1).get(0)).compareTo((Key1) ((ArrayList<ArrayList<Object>>) p2).get(0)));
		int i = 0;
		
		//listSort is sorted by Key2 (still flipped pairs
		
		for(Object pair : listSort) { // creating a list of non dupe Key2s
			noDupes.add(((ArrayList<ArrayList<Object>>) listSort.get(i)).get(0));

			i++;
		}
		
		for(Object el : noDupes) {
			if (!noDupes2.contains(el)) {
				noDupes2.add(el);
		}
		
			                }
		

		
		for(Object k : noDupes2) {

			concatArr.addAll(dupesHelper(listSort, k));

		}
		

		
		for (Object arr : concatArr) {
			returnArr.add("(" + ((ArrayList<ArrayList<Object>>) arr).get(1) + ", " + ((ArrayList<ArrayList<Object>>) arr).get(0) + ")");
	    }
		

		return returnArr;
		
	} // return a list sorted by Key2
	

}
