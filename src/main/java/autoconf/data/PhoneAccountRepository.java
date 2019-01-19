package autoconf.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import autoconf.domain.Account;
import autoconf.domain.Phone;
import autoconf.domain.PhoneAccount;

public interface PhoneAccountRepository extends CrudRepository<PhoneAccount, Integer> {
	
	
//	public List<PhoneAccount> findAllByPhone(Optional<Phone> phoneId);
	
	@Override
	List<PhoneAccount> findAll();

//	List<PhoneAccount> findAllByPhone(Optional<Phone> findById);
	
	List<PhoneAccount> findAllByPhoneOrderByLineNumber(Phone findById);
}
