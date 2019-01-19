package autoconf.web;

import org.springframework.web.bind.annotation.RequestMapping;

import autoconf.data.AccountRepository;
import autoconf.domain.Account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private final AccountRepository accountRepo;
	
	@Autowired
	public AccountController(AccountRepository accountRepo) {
		
		this.accountRepo = accountRepo;
	}

	@GetMapping("index")
	public String showAccounts(Model model) {
		
		
		List<Account> accounts = new ArrayList<>();
		
		accounts = accountRepo.findAll();
		
			
		model.addAttribute("accounts", accounts);
		return "accounts";
	}
	
	@PostMapping("save")
	public String saveAccount(@ModelAttribute("account") Account account) {
		accountRepo.save(account);
		return "redirect:/account/index";
	}
	
	@GetMapping("add")
	public String addAccount(Model model) {
		model.addAttribute("account", new Account());
		return "editAccount";
	}
	
	@GetMapping("edit/{id}")
	public String editAccount(Model model, @PathVariable Integer id) {
		
		model.addAttribute("account", accountRepo.findById(id));
		return "editAccount";
	}
	
	@GetMapping("delete/{id}")
	public String deleteAccount(@PathVariable Integer id) {
		accountRepo.deleteById(id);
		return "redirect:/account/index";
	}
}
