package autoconf.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import autoconf.domain.Phone;
import autoconf.domain.PhoneAccount;

public interface PhoneAccountRepository extends CrudRepository<PhoneAccount, Integer> {
	
		
	@Override
	List<PhoneAccount> findAll();

	
	List<PhoneAccount> findAllByPhoneOrderByLineNumber(Phone findById);
}
