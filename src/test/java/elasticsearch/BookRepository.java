package elasticsearch;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> { 

	  List<Book> findByName(String name);
	
}
