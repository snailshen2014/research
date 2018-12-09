package Thinking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemoveTest {
	public static void main(String... args) {
		List<String> listOfBooks = new ArrayList<>();
		listOfBooks.add("Programming Pearls");
		listOfBooks.add("Clean Code");
		listOfBooks.add("Effective Java");
		listOfBooks.add("Code Complete");
		 System.out.println("List before : " + listOfBooks);
		// Using forEach loop to iterate and removing
		// element during iteration will throw
		// ConcurrentModificationException in Java
		/*
		 * for (String book : listOfBooks) { if (book.contains("Code")) {
		 * listOfBooks.remove(book); } }
		 */
		
		 // This code doesn't throw ConcurrentModificationException because
		// here we are not using Iterator but we are just using traditional for
		// loop.
		
		  for (int i = 0; i < listOfBooks.size(); i++) { 
			  String book = listOfBooks.get(i); 
			  if (book.contains("Programming")) {
				  System.out.println("Removing " + book); 
				  listOfBooks.remove(i); 
				  //* will throw CME 
				  } 
			  }
		 
		
		//The real problem with this code is that even though the code is using Iterator to go over ArrayList,
		//it's not really using the Iterator.remove() method to remove the element. 
		//It is just using Iterator to get the next element but
		//calling the ArrayList.remove() method to delete the element.
		//throw ava.util.ConcurrentModificationException
		/*
		Iterator<String> iterator = listOfBooks.iterator();
		while (iterator.hasNext()) {
			String book = iterator.next();
			listOfBooks.remove(book);
		}*/
		//right way
//		Iterator<String> iterator = listOfBooks.iterator();
//		while (iterator.hasNext()) {
//			String book = iterator.next();
//			System.out.println("Removing " + book);
//			iterator.remove();
//		}
		System.out.println("List after : " + listOfBooks);
	}
}
