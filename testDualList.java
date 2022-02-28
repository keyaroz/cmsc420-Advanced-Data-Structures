package cmsc420_s22;
//

public class testDualList {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DualList<String, Integer> l = new DualList<String, Integer>();

		l.insert("A", 5);

		l.insert("Z", 3);
		
		l.insert("A", 3);
		
		l.insert("C", 2);
		l.insert("Z", 1);
		System.out.println("listBykey1: " + l.listByKey1());
		System.out.println("listBykey2: " + l.listByKey2());
		l.extractMinKey2();

		
//		  l.insert("JFK", 94);
//		  l.insert("IAD", 26);
//		  l.insert("BWI", 88);
//		  l.insert("BWI", 20);
//		  l.insert("LAX", 42);
//		  System.out.println("listBykey1: " + l.listByKey1());
//		l.extractMinKey2();	
		
		
		
}
	}
