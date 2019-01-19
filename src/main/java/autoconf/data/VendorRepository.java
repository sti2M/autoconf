package autoconf.data;

import autoconf.domain.Vendor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
	
	@Override
	List<Vendor> findAll();
	
}
