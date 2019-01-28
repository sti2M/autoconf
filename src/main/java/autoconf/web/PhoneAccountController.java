package autoconf.web;

import org.springframework.web.bind.annotation.RequestMapping;

import autoconf.config.Configurator;
import autoconf.data.AccountRepository;
import autoconf.data.PhoneAccountRepository;
import autoconf.data.PhoneRepository;
import autoconf.domain.Phone;
import autoconf.domain.PhoneAccount;
import autoconf.domain.PhoneAccountWrapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("linesettings")
public class PhoneAccountController {

	private PhoneAccountRepository phoneAccountRepo;
	private PhoneRepository phoneRepo;
	private AccountRepository accountRepo;
	
	@Autowired
	public PhoneAccountController(PhoneAccountRepository phoneAccountRepo, 
								  PhoneRepository phoneRepo,
								  AccountRepository accountRepo) {
		this.phoneAccountRepo = phoneAccountRepo;
		this.phoneRepo = phoneRepo;
		this.accountRepo = accountRepo;
	}
	
	@GetMapping("/add/{id}")
	public String addLineSettings(Model model, @PathVariable Integer id) {
		
		List<PhoneAccount> phoneAccounts = new ArrayList<>(6);
		Phone phone = phoneRepo.findById(id).get();
		int lineNumber = 1;
		for (PhoneAccount pa : phoneAccounts) {
			pa.setPhone(phone);
			pa.setLineNumber(lineNumber);
			pa.setAccount(accountRepo.findById(4).get());
		}
		
		phone.setPhoneAccounts(phoneAccounts);
		PhoneAccountWrapper paWrapper = new PhoneAccountWrapper();
		paWrapper.setPhoneAccountList(phoneAccounts);
		
		model.addAttribute("paWrapper", paWrapper);
		model.addAttribute("accountsList", accountRepo.findAll());
		
		return "editPhoneAccount";
	}
	
	@GetMapping("/edit/{id}")
	public String editLineSettings(Model model, @PathVariable Integer id) {
		
		Phone phone = new Phone();
		phone = phoneRepo.findById(id).orElse(new Phone(id));
		
		int listSize = phone.getVendor().getLinesCount();
		
		List<PhoneAccount> phoneAccounts = new ArrayList<>();
		
		// Если добавили новый телефон, формируем новый список настроек линий для него 
		
		if (phoneAccountRepo.findAllByPhoneOrderByLineNumber(phone).isEmpty()) {
			System.err.println(phoneAccountRepo.findAllByPhoneOrderByLineNumber(phone).isEmpty());
			System.err.println(phoneAccounts);
			for (int index = 0; index < listSize; index++) {
				PhoneAccount pa = new PhoneAccount();
				pa.setPhone(phone);
//				pa.setPhone(phoneRepo.findById(id).get());
				pa.setLineNumber(index + 1);
				phoneAccounts.add(pa);
			}
		}
		
		// Иначе вытягиваем в список существующие настройки
		
		else {
			
			phoneAccounts = phoneAccountRepo.findAllByPhoneOrderByLineNumber(phone);
			int currentSize = phoneAccounts.size();
			int distinction = currentSize - listSize;
			
			// Проверка количества линий для телефона
			// Если количество линий, необходимое для телефона не равно 
			// количеству линий, вернувшихся из базы, нужно либо добавить 
			// новые, либо удалить лишние линии из базы
			
			if (distinction > 0) {
				for (int index = 0; index < listSize; index++) {
					phoneAccounts.remove(index);
				}
				phoneAccountRepo.deleteAll(phoneAccounts);
				phoneAccounts = phoneAccountRepo.findAllByPhoneOrderByLineNumber(phone);
			} else if (distinction < 0) {
				for (int index = currentSize; index < listSize; index++) {
					PhoneAccount pa = new PhoneAccount();
					pa.setPhone(phone);
					pa.setLineNumber(index + 1);
					phoneAccounts.add(pa);
				}
				
			}
		}
		
				
		PhoneAccountWrapper paWrapper = new PhoneAccountWrapper();
		paWrapper.setPhoneAccountList(phoneAccounts);
		
		model.addAttribute("paWrapper", paWrapper);
		model.addAttribute("accountsList", accountRepo.findAll());
		return "editPhoneAccount";
	}
	
	@PostMapping("/save")
	public String saveLineSettings(PhoneAccountWrapper paWrapper) {
		
		List<PhoneAccount> phoneAccounts = new ArrayList<>(paWrapper.getPhoneAccountList());

		phoneAccountRepo.saveAll(phoneAccounts);
		
		Phone phone = paWrapper.getPhoneAccountList().get(0).getPhone();
		Configurator configurator = new Configurator(phone);
		configurator.saveConfig();
		
//		phoneAccountRepo.saveAll(phoneAccounts);
		
		return "redirect:/phone/index";
		
	}
	
}
