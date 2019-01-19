package autoconf.data;

import autoconf.domain.Phone;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Integer> {

	@Override
	List<Phone> findAll();
	
}
