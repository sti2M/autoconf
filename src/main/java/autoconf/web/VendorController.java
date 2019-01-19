package autoconf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import autoconf.data.PhoneRepository;
import autoconf.data.VendorRepository;
import autoconf.domain.Phone;
import autoconf.domain.Vendor;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/vendor")
public class VendorController {
	
	private final VendorRepository vendorRepo;
	
	@Autowired
	public VendorController(VendorRepository vendorRepo) {
		
		this.vendorRepo = vendorRepo;
	}

	@GetMapping("index")
	public String showVendors(Model model) {
		
		
		List<Vendor> vendors = new ArrayList<>();
		
		vendors = vendorRepo.findAll();
		
			
		model.addAttribute("vendors", vendors);
		return "vendors";
	}
	
	@PostMapping("save")
	public String saveVendor(@ModelAttribute("vendor") Vendor vendor) {
		vendorRepo.save(vendor);
		return "redirect:/vendor/index";
	}
	
	@GetMapping("add")
	public String addVendor(Model model) {
		model.addAttribute("vendor", new Vendor());
		return "editVendor";
	}
	
	@GetMapping("edit/{id}")
	public String editVendor(Model model, @PathVariable Integer id) {
		model.addAttribute("vendor", vendorRepo.findById(id));
		return "editVendor";
	}
	
	@GetMapping("delete/{id}")
	public String deleteVendor(@PathVariable Integer id) {
		vendorRepo.deleteById(id);
		return "redirect:/vendor/index";
	}
	
	@PostMapping("count")
	public @ResponseBody int getLinesCount(@RequestBody String id) {
		int intId = Integer.valueOf(id);
		return vendorRepo.findById(intId).get().getLinesCount(); 
	}
}
