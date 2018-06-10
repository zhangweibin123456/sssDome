package elasticsearch;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface CrudRepository<T, ID extends Serializable>
extends Repository<T, ID> { 

}
