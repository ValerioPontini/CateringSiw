package com.valeriopontini.catering.controller;

import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.valeriopontini.catering.controller.validator.BuffetValidator;
import com.valeriopontini.catering.model.Buffet;
import com.valeriopontini.catering.model.Chef;
import com.valeriopontini.catering.model.CustomAuthentication;
import com.valeriopontini.catering.model.FormObject;
import com.valeriopontini.catering.model.Role;
import com.valeriopontini.catering.model.comparator.BuffetComparator;
import com.valeriopontini.catering.model.comparator.ChefComparator;
import com.valeriopontini.catering.service.BuffetService;
import com.valeriopontini.catering.service.ChefService;
import com.valeriopontini.catering.service.RoleService;

@Controller
public class BuffetController {
	
	@Autowired
	BuffetService bs;
	@Autowired
	ChefService cs;
	@Autowired
	RoleService rs;
	@Autowired
	BuffetValidator validator;
	
	@PostMapping("/buffet/{id}")
	public String updateBuffet(@PathVariable("id") Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult br, Model model) {
		validator.validate(buffet, br);
		if(!br.hasErrors()) {
			buffet.setId(id);
			bs.save(buffet);
			model.addAttribute("buffet", buffet);
			return "menuView.html";
		}
		return "buffetForm.html";
	}
	
	@PostMapping("/buffet")
	public String addBuffet( @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult br, Model model) {
		validator.validate(buffet, br);
		if(!br.hasErrors()) {
			bs.save(buffet);
			model.addAttribute("buffet", buffet);
			return "menuView.html";
		}
		return "buffetForm.html";
	}

	@GetMapping("/buffet")
	public String getBuffetList(Model model) {
		List<Buffet> buffetList = bs.findAll();
		buffetList.sort(new BuffetComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("buffetName", new FormObject());
		model.addAttribute("buffetList", buffetList);
		return "buffetList.html";
	}
	
	@GetMapping("/buffet/Search")
	public String getBuffet( @ModelAttribute("buffetName") FormObject obj, Model model) {
		List<Buffet> buffetList = bs.findByNome(obj.getNome());
		Boolean found = true;
		if(buffetList.isEmpty()) {
			buffetList=bs.findAll();
			found = false;
		}
		buffetList.sort(new BuffetComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", found);
		model.addAttribute("role", role);
		model.addAttribute("buffetName", new FormObject());
		model.addAttribute("buffetList", buffetList);
		return "buffetList.html";
		}
	
	
	@GetMapping("/buffet/{id}")
	public String getBuffetView(@PathVariable("id") Long id, Model model){
		Buffet buffet = bs.findById(id);
		model.addAttribute("buffet", buffet);
		return "menuView.html";
	}
	
	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet", new Buffet());
		List<Chef> chefList = cs.findAll();
		chefList.sort(new ChefComparator());
		model.addAttribute("chefList", chefList);
		return "buffetForm.html";
	}
	
	@PostMapping("/azioni/eliminaBuffet/{id}")
	public String deleteBuffet(@PathVariable("id") Long id, Model model) {
		bs.delete(id);
		List<Buffet> buffetList = bs.findAll();
		buffetList.sort(new BuffetComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("buffetName", new FormObject());
		model.addAttribute("buffetList", buffetList);
		return "buffetList.html";
	}
	
	@PostMapping("/azioni/modificaBuffet/{id}")
	public String updateBuffet(@PathVariable("id") Long id, Model model) {
		Buffet b = bs.findById(id);
		List<Chef> chefList = cs.findAll();
		chefList.sort(new ChefComparator());
		model.addAttribute("buffet", b);
		model.addAttribute("chefList", chefList);
		return "buffetForm.html";
	}
	

	
}
