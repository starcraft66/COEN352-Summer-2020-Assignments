package co.tdude.COEN352Assignment2Test;

public class DictionaryManualTest {

		public static void main(String[] args) {
		   
			DictionaryManualTest.runManualTest();
		   
	   }
	   
	   public static void runManualTest() {
		   DictionaryJUnitTest t = new DictionaryJUnitTest();
		   DictionaryJUnitTest.setUp();
		   t.testClear();
		   t.testFind();
		   t.testInsert();
		   t.testRemove();
		   t.testRemoveAny();
		   t.testSize();
	   }
	   
	
}