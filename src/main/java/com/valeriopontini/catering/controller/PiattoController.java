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

import com.valeriopontini.catering.controller.validator.PiattoValidator;
import com.valeriopontini.catering.model.Buffet;
import com.valeriopontini.catering.model.Chef;
import com.valeriopontini.catering.model.FormObject;
import com.valeriopontini.catering.model.Ingrediente;
import com.valeriopontini.catering.model.Piatto;
import com.valeriopontini.catering.model.Role;
import com.valeriopontini.catering.model.comparator.BuffetComparator;
import com.valeriopontini.catering.model.comparator.ChefComparator;
import com.valeriopontini.catering.model.comparator.IngredienteComparator;
import com.valeriopontini.catering.model.comparator.PiattoComparator;
import com.valeriopontini.catering.service.BuffetService;
import com.valeriopontini.catering.service.IngredienteService;
import com.valeriopontini.catering.service.PiattoService;
import com.valeriopontini.catering.service.RoleService;

@Controller
public class PiattoController {

	@Autowired
	PiattoService ps;
	@Autowired
	IngredienteService is;
	@Autowired
	BuffetService bs;
	@Autowired
	RoleService rs;
	@Autowired
	PiattoValidator validator;
	
	
	@PostMapping("/piatti")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult br, Model model) {
		validator.validate(piatto, br);
		if(!br.hasErrors()) {
			ps.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piattoView.html";
		}
		return "piattoForm.html";
			
	}
	
	@PostMapping("/piatti/{id}")
	public String updatePiatto(@PathVariable("id") Long id, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult br, Model model) {
		validator.validate(piatto, br);
		if(!br.hasErrors()) {
			piatto.setId(id);
			ps.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piattoView.html";
		}
		return "piattoForm.html";
	}
	
	@GetMapping("/piatti")
	public String getPiattiList(Model model) {
		List<Piatto> piatti = ps.findAll();
		piatti.sort(new PiattoComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("piattoName", new FormObject());
		model.addAttribute("piatti", piatti);
		return "piattiList.html";
	}
	
	@GetMapping("/piatti/Search")
	public String getBuffet( @ModelAttribute("piattoName") FormObject obj, Model model) {
		List<Piatto> piattoList = ps.findByNome(obj.getNome());
		piattoList.sort(new PiattoComparator());
		Boolean found = true;
		if(piattoList.isEmpty()) {
			piattoList = ps.findAll();
			found = false;
		}
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", found);
		model.addAttribute("role", role);
		model.addAttribute("piattoName", new FormObject());
		model.addAttribute("piatti", piattoList);
		return "piattiList.html";
		}
	
	
	@GetMapping("/piatti/{id}")
	public String getPiattoView(@PathVariable("id") Long id, Model model) {
		Piatto piatto = ps.findById(id);
		model.addAttribute("piatto", piatto);
		return "piattoView.html";
	}
	
	@PostMapping("/azioni/eliminaPiatto/{id}")
	public String deleteChef(@PathVariable("id")Long id, Model model) {
		ps.delete(id);
		List<Piatto> piatti = ps.findAll();
		piatti.sort(new PiattoComparator());
		Role role = new Role();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser"))
		role = rs.getRole((OAuth2User) auth.getPrincipal());
		else
		role.setRuolo("DEFAULT");
		model.addAttribute("found", true);
		model.addAttribute("role", role);
		model.addAttribute("piattoName", new FormObject());
		model.addAttribute("piatti", piatti);
		return "piattiList.html";
	}
	
	@PostMapping("/azioni/modificaPiatto/{id}")
	public String updateChef(@PathVariable("id") Long id, Model model) {
		Piatto p = ps.findById(id);
		List<Buffet> buffetList = bs.findAll();
		buffetList.sort(new BuffetComparator());
		List<Ingrediente> ingredienti = is.findAll();
		ingredienti.sort(new IngredienteComparator());
		model.addAttribute("buffetList", buffetList);
		model.addAttribute("ingredienteList", ingredienti);
		model.addAttribute("piatto", p);
		return "piattoForm.html";
	}
	
	@GetMapping("/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto", new Piatto());
		List<Buffet> buffetList = bs.findAll();
		buffetList.sort(new BuffetComparator());
		List<Ingrediente> ingredienti = is.findAll();
		ingredienti.sort(new IngredienteComparator());
		model.addAttribute("buffetList", buffetList);
		model.addAttribute("ingredienteList", ingredienti);
		return "piattoForm.html";
	}
}
