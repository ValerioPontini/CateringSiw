package com.valeriopontini.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.valeriopontini.catering.controller.validator.ChefValidator;
import com.valeriopontini.catering.model.Buffet;
import com.valeriopontini.catering.model.Chef;
import com.valeriopontini.catering.model.FormObject;
import com.valeriopontini.catering.model.Role;
import com.valeriopontini.catering.model.comparator.ChefComparator;
import com.valeriopontini.catering.service.ChefService;
import com.valeriopontini.catering.service.RoleService;

@Controller
public class ChefController {

	@Autowired
	ChefService cs;
	@Autowired
	RoleService rs;
	@Autowired
	ChefValidator validator;
	
	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult br, Model model){
		validator.validate(chef,  br);
		if(!br.hasErrors()) {
			cs.save(chef);
			model.addAttribute("chef", chef);
			return "chefView.html";
		}
		return "chefForm.html";
	}
	
	@PostMapping("/chef/{id}")
	public String updateChef(@PathVariable("id") Long id, @Valid @ModelAttribute("chef") Chef chef, BindingResult br, Model model) {
		validator.validate(chef,  br);
		if(!br.hasErrors()) {
			chef.setId(id);
			cs.save(chef);
			model.addAttribute("buffet", chef);
			return "chefView.html";
		}
		return "chefForm.html";
	}
	
	
	@GetMapping("/chef")
	public String getChefList(Model model) {
		List<Chef> chefList = cs.findAll();
		chefList.sort(new ChefComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("chefName", new FormObject());
		model.addAttribute("chefList", chefList);
		return "chefList.html";
	}
	
	@GetMapping("/chef/Search")
	public String getBuffet( @ModelAttribute("chefName") FormObject obj, Model model) {
		List<Chef> chefList = cs.findByNome(obj.getNome());
		Boolean found = true;
		if(chefList.isEmpty()) {
			chefList=cs.findAll();
			found = false;
		}
		chefList.sort(new ChefComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", found);
		model.addAttribute("role", role);
		model.addAttribute("chefName", new FormObject());
		model.addAttribute("chefList", chefList);
		return "chefList.html";
		}
	
	@PostMapping("/azioni/eliminaChef/{id}")
	public String deleteChef(@PathVariable("id")Long id, Model model) {
		cs.delete(id);
		List<Chef> chefList = cs.findAll();
		chefList.sort(new ChefComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("chefName", new FormObject());
		model.addAttribute("chefList", chefList);
		return "chefList.html";
	}
	
	@PostMapping("/azioni/modificaChef/{id}")
	public String updateChef(@PathVariable("id") Long id, Model model) {
		Chef c = cs.findById(id);
		model.addAttribute("chef", c);
		return "chefForm.html";
	}
	
	@GetMapping("/chef/{id}")
	public String getChefView(@PathVariable("id")Long id, Model model){
		Chef chef = cs.findById(id);
		model.addAttribute("chef", chef);
		return "chefView.html";
	}
	
	@GetMapping("/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
}
