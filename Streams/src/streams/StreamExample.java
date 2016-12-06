package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {

	public static void main(String[] args) {
		List<Book> bookList = new ArrayList<>();
		
		bookList.add(BookFactory.createBook());
		bookList.add(BookFactory.createSpecialBook());
		bookList.add(BookFactory.createSpecialBook());
		
		Object[] libros = clunkyWayGetBooksStream(bookList);
		
		for (int i = 0; i < libros.length; i++) {
			System.out.println((Book)libros[i]);
		}
		
		for(Book book : niceWayGetBooksStream(bookList))
			System.out.println(book);
		
	}
	
	

	private static List<Book> niceWayGetBooksStream(List<Book> bookList) {
		return bookList.stream()
				.filter(book -> book.getName().length() > 10)
				.collect(Collectors.toList());
		}
	
	private static Object[] clunkyWayGetBooksStream(List<Book> bookList) {
		Object[] libros = bookList.parallelStream()
				.filter(book -> book.getName().startsWith("A"))
				.map(new Function<Book, Book>() {
					@Override
					public Book apply(Book t) {
						
						t.getName().replace("E", "e");

						return t;
					}
				}).toArray();
		return libros;
	}
	
	static class Book {
		private String name;
		private String author;
		
		public Book(String name, String author) {
			super();
		
			this.name = name;
			this.author = author;
		}

		public String getName() {
			return name;
		}

		public String getAuthor() {
			return author;
		}
		
		
		
		@Override
		public String toString() {
			return "Book [name=" + name + ", author=" + author + "]";
		}

		public void print(){
			System.out.println(this.toString());
		}
		
		
		
	}

	
	static class BookFactory{
		
		static Book createBook(){
			return new Book(UUID.randomUUID().toString(), Thread.currentThread().getName());
			
		}
	
		static Book createSpecialBook(){
			return new Book("ArtE", Thread.currentThread().getName());
			
		}
	
	}
}
