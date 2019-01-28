package autoconf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import autoconf.data.PhoneRepository;
import autoconf.data.VendorRepository;
import autoconf.data.AccountRepository;
import autoconf.domain.Phone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@Autowired
	public PhoneController(PhoneRepository phoneRepo, 
						   VendorRepository vendorRepo,
						   AccountRepository accountRepo						   
						   ) {
		
		this.phoneRepo = phoneRepo;
		this.vendorRepo = vendorRepo;
		this.accountRepo = accountRepo;
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
		model.addAttribute("macPattern", "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
		return "editPhone";
	}
	
	@GetMapping("edit/{id}")
	public String editPhone(Model model, @PathVariable Integer id) {
		model.addAttribute("phone", phoneRepo.findById(id));
		model.addAttribute("vendors", vendorRepo.findAll());
		model.addAttribute("accountsList", accountRepo.findAll());
		
		model.addAttribute("macPattern", "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
		return "editPhone";
	}
	
	@GetMapping("delete/{id}")
	public String deletePhone(@PathVariable Integer id) {
		phoneRepo.deleteById(id);
		return "redirect:/phone/index";
	}
	

}
