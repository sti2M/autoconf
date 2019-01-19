package autoconf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import autoconf.data.PhoneRepository;
import autoconf.data.VendorRepository;
import autoconf.data.AccountRepository;
import autoconf.data.PhoneAccountRepository;
import autoconf.domain.Account;
import autoconf.domain.CommonWrapper;
import autoconf.domain.Phone;
import autoconf.domain.PhoneAccount;
import autoconf.domain.PhoneAccountWrapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	
	private final PhoneRepository phoneRepo;
	private final VendorRepository vendorRepo;
	private final AccountRepository accountRepo;
	private final PhoneAccountRepository phoneAccountRepo;
	
	@Autowired
	public PhoneController(PhoneRepository phoneRepo, 
						   VendorRepository vendorRepo,
						   AccountRepository accountRepo,
						   PhoneAccountRepository phoneAccountRepo
						   ) {
		
		this.phoneRepo = phoneRepo;
		this.vendorRepo = vendorRepo;
		this.accountRepo = accountRepo;
		this.phoneAccountRepo = phoneAccountRepo;
	}

	@GetMapping("index")
	public String showPhones(Model model) {
		
		List<Phone> phones = new ArrayList<>();
		phones = phoneRepo.findAll();
		
		model.addAttribute("phones", phones);
		return "phones";
	}
	
	@PostMapping("/save")
	public String savePhone(Phone phone) {
		
		phoneRepo.save(phone);
		
		return "redirect:/linesettings/edit/" + phone.getId();
	}
	
	@GetMapping("add")
	public String addPhone(Model model) {
		model.addAttribute("phone", new Phone());
		model.addAttribute("vendors", vendorRepo.findAll());
		
		//model.addAttribute("accountsList", accountRepo.findAll());
//		List<PhoneAccount> phoneAccounts = new ArrayList<>();
//		model.addAttribute("phoneAccounts", phoneAccounts);
		model.addAttribute("macPattern", "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
//		model.addAttribute("idAccount", new int[6]);
		return "editPhone";
	}
	
	@GetMapping("edit/{id}")
	public String editPhone(Model model, @PathVariable Integer id) {
		model.addAttribute("phone", phoneRepo.findById(id));
		model.addAttribute("vendors", vendorRepo.findAll());
		model.addAttribute("accountsList", accountRepo.findAll());
		
				
		/*List<PhoneAccount> phoneAccounts = new ArrayList<>();
		phoneAccounts = phoneAccountRepo.findAllByPhone(phoneRepo.findById(id));
		
		PhoneAccountWrapper paWrapper = new PhoneAccountWrapper();
		paWrapper.setPhoneAccountList(phoneAccounts);
		*/
//		findAllByPhone(phoneRepo.findById(id))
//		model.addAttribute("phoneAccounts", phoneAccounts);
//		model.addAttribute("paWrapper", paWrapper);
		model.addAttribute("macPattern", "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
		return "editPhone";
	}
	
	@GetMapping("delete/{id}")
	public String deletePhone(@PathVariable Integer id) {
		phoneRepo.deleteById(id);
		return "redirect:/phone/index";
	}
	
	@GetMapping("test")
	public String test(Model model) {
		
		List<PhoneAccount> phoneAccounts = new ArrayList<>();
		phoneAccounts = phoneAccountRepo.findAll();
		model.addAttribute("phoneAccounts", phoneAccounts);
//		model.addAttribute("current", phoneAccountRepo.findAllByPhone(phoneRepo.findById(3)));
		return "test";
	}
	
	@GetMapping("test1")
	public String test1() {
		
		Phone phone = phoneRepo.findById(1).get();
		Account account = accountRepo.findById(4).get();
		PhoneAccount pa = new PhoneAccount();
		pa.setPhone(phone);
		pa.setAccount(account);
		pa.setLineNumber(21);
		
		Phone phone1 = phoneRepo.findById(1).get();
		Account account1 = accountRepo.findById(1).get();
		PhoneAccount pa1 = new PhoneAccount();
		pa1.setPhone(phone);
		pa1.setAccount(account);
		pa1.setLineNumber(22);
		
		
		
		List<PhoneAccount> paList = new ArrayList<>();
		paList.add(pa);
		paList.add(pa1);
		
		phoneAccountRepo.saveAll(paList);
		
		pa.setPhone(phone);
		pa.setAccount(account);
		pa.setLineNumber(312);
		
		phoneAccountRepo.save(pa);
//		phoneAccountRepo.deleteById(25);
		return "phones";
	}
}
