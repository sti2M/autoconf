package autoconf.data;

import autoconf.domain.Account;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Override
	List<Account> findAll();
}
