package com.repo;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.Author;
import com.bean.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-persist.xml")
public class UserRepositoryTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testAuthorList() {
	List<Author> authorList=authorRepository.findAll();
	
	for(Author author:authorList){
		System.out.println(author.getBooks());
		System.out.println(author.getBooks().size());
	}
		System.out.println(authorList.size());
		
	}

	@Test
	public void testBookList() {
		List<Book> bookList=bookRepository.findAll();
		for(Book book:bookList){
			
			System.out.println(book.getAuthor());
			System.out.println(book.getAuthor().getName());
		}
	}
	
	@Test
	private void jdbcTemplateTest(){
		
		List<Map<String, Object>> list=	jdbcTemplate.queryForList("select * from author");
		logger.info(" "+list.size());
	}

}
