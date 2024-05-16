package com.springmvc.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
	private List<Book> listOfBooks = new ArrayList<Book>();
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	public BookRepositoryImpl() {
		Book book1 = new Book("ISBN1234","C#������",30000);
		book1.setAuthor("�ڿ���");
		book1.setDescription(
				"C#�������� ���� ù ���α׷��� ���� C#�� �����ϴ� ���ڸ� ������� �Ѵ�. Ư�� �������α׷��Ӹ� ���� C# �Թ�����, C#�� ����Ͽ� ����(����Ƽ), ��, �����, IoT ���� ������ �� �ʿ��� C# ���� ������ ������ �⺻�⸦ źź�ϰ� ������ ���� �����̴�."
				);
		book1.setPublisher("���");
		book1.setCategory("IT������");
		book1.setUnitsInStock(1000);
		book1.setReleaseDate("2020/01/01");
		
		Book book2 = new Book("ISBN1235", "Node.js������",36000);
		book2.setAuthor("������");
		book2.setDescription(
				"�� å�� ����Ʈ���� ����, �����ͺ��̽�, �������� �ƿ츣�� �������� ������ �ٷ��. �������� ���� �������� �������� �⺻ ������ Ȯ���� �����ϰ�, ���� ��ɰ� ���°踦 ����� ���鼭 ������ �����ϴ� ������ ������."
				);
		book2.setPublisher("���");
		book2.setCategory("IT������");
		book2.setUnitsInStock(1000);
		book2.setReleaseDate("2020/02/02");
		
		Book book3 = new Book("ISBN1236", "��� XD CC 2020",25000);
		book3.setAuthor("�����");
		book3.setDescription(
				"��� XD ���α׷��� ���� UI/UX �������� ������ �ϴ� ���� �����̳��� �����̿� �°� �⺻���� ������ Ȱ���� ������ �����ΰ� ��&�� ������ ������, UI ������, �� �����ο� �ִϸ��̼ǰ� ���ͷ����� ������ ������Ÿ���� �н��մϴ�."
				);
		book3.setPublisher("���");
		book3.setCategory("ITȰ�뼭");
		book3.setUnitsInStock(1000);
		book3.setReleaseDate("2020/03/03");
		
		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);		
	}
	
	@Override
	public List<Book> getAllBookList() {
		String SQL = "SELECT * FROM book";
		List<Book> listOfBooks = template.query(SQL, new BookRowMapper());
		return listOfBooks;
	}
	
	public List<Book> getBookListByCategory(String category) {
		List<Book> booksByCategory = new ArrayList<Book>();
		String SQL = "SELECT * FROM book where b_category LIKE '%"+category+"%'";
		booksByCategory = template.query(SQL, new BookRowMapper());
		return booksByCategory;
	}
	
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		Set<String> criterias = filter.keySet();
		
		if(criterias.contains("publisher")) {
			for(int j=0;j<filter.get("publisher").size();j++) {
				String publisherName = filter.get("publisher").get(j);
				String SQL = "SELECT * FROM book where b_publisher LIKE '%"+publisherName+"%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		
		if(criterias.contains("category") ) {
			for(int i=0;i<filter.get("category").size();i++) {
				String category = filter.get("category").get(i);
				String SQL = "SELECT * FROM book where b_category LIKE '%"+category+"%'";
				booksByCategory.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}
	
	public Book getBookById(String bookId) {
		Book bookInfo = null;
		String SQL = "SELECT count(*) FROM book where b_bookId=?";
		int rowCount = template.queryForObject(SQL, Integer.class, bookId);
		if(rowCount != 0) {
			SQL = "SELECT * FROM book where b_bookId=?";
			bookInfo = template.queryForObject(SQL, new Object[] {bookId}, new BookRowMapper());
		}
		if(bookInfo==null)
			throw new BookIdException(bookId);
			return bookInfo;
	}
	
	public void setNewBook(Book book) {
		String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate, b_condition, b_fileName)"
				+"Values(?,?,?,?,?,?,?,?,?,?,?)";
		template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName());
	}
	
	public void setUpdateBook(Book book) {
		if(book.getFileName() != null) {
			String SQL = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=?, b_fileName=? where b_bookId=?";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());	
		} else if(book.getFileName() == null) {
			String SQL = "UPDATE book SET b_name=?, b_unitPrice=?, b_author=?, b_description=?, b_publisher=?, b_category=?, b_unitsInStock=?, b_releaseDate=?, b_condition=? where b_bookId=?";
			template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getBookId());
		}
	}
	
	public void setDeleteBook(String bookId) {
		String SQL = "DELETE from Book where b_bookId = ?";
		this.template.update(SQL, bookId);
	}
}
