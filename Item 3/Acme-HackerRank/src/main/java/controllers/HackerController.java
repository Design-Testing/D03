/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ActorService;
import services.ConfigurationParametersService;
import services.HackerService;
import services.UserAccountService;
import services.auxiliary.RegisterService;
import domain.Actor;
import domain.Hacker;
import forms.ActorForm;

@Controller
@RequestMapping("/hacker")
public class HackerController extends AbstractController {

	@Autowired
	private ActorService					actorService;

	@Autowired
	private HackerService					hackerService;

	@Autowired
	private RegisterService					registerService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Constructors -----------------------------------------------------------

	public HackerController() {
		super();
	}

	// CREATE ---------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result = new ModelAndView();
		final ActorForm hacker = new ActorForm();
		result = this.createEditModelAndView(hacker);
		return result;
	}

	// DISPLAY TABLA -----------------------------------------------------------

	@RequestMapping(value = "/displayTabla", method = RequestMethod.GET)
	public ModelAndView displayTabla(@RequestParam final int hackerId) {
		final ModelAndView result;
		final Hacker hacker = this.hackerService.findOne(hackerId);
		if (hacker != null) {
			result = new ModelAndView("hacker/display");
			result.addObject("hacker", hacker);
			result.addObject("displayButtons", true);
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;

	}

	// DISPLAY PRINCIPAL -----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		final ModelAndView result;
		final Hacker hacker = this.hackerService.findByPrincipal();
		if (hacker != null) {
			result = new ModelAndView("hacker/display");
			result.addObject("hacker", hacker);
			result.addObject("displayButtons", true);
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;

	}

	// EDIT -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("hacker/edit");
		final Hacker hacker = this.hackerService.findByPrincipal();
		final ActorForm actor = this.registerService.inyect(hacker);
		actor.setTermsAndCondicions(true);
		result.addObject("actorForm", actor);
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		result.addObject("countryPhoneCode", this.configurationParametersService.find().getCountryPhoneCode());
		return result;
	}

	// SAVE -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		result = new ModelAndView("hacker/edit");
		Hacker hacker;
		if (binding.hasErrors()) {
			result.addObject("errors", binding.getAllErrors());
			actorForm.setTermsAndCondicions(false);
			result.addObject("actorForm", actorForm);
		} else
			try {
				final UserAccount ua = this.userAccountService.reconstruct(actorForm, Authority.HACKER);
				hacker = this.hackerService.reconstruct(actorForm, binding);
				hacker.setUserAccount(ua);
				this.registerService.saveHacker(hacker, binding);
				result.addObject("alert", "hacker.edit.correct");
				result.addObject("actorForm", actorForm);
			} catch (final ValidationException oops) {
				result = this.createEditModelAndView(actorForm, null);
			} catch (final Throwable e) {
				if (e.getMessage().contains("username is register"))
					result.addObject("alert", "hacker.edit.usernameIsUsed");
				result.addObject("errors", binding.getAllErrors());
				actorForm.setTermsAndCondicions(false);
				result.addObject("actorForm", actorForm);
			}
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		result.addObject("countryPhoneCode", this.configurationParametersService.find().getCountryPhoneCode());
		return result;
	}

	// GDPR -----------------------------------------------------------
	@RequestMapping(value = "/deletePersonalData")
	public ModelAndView deletePersonalData() {
		final Actor principal = this.actorService.findByPrincipal();
		final Collection<String> surnames = Arrays.asList("DELETED");
		principal.setAddress("");
		principal.setEmail("DELETED@mail.de");
		principal.setSurname(surnames);
		//principal.setName("");
		principal.setPhone("");
		principal.setPhoto("");
		principal.setSpammer(false);
		principal.setVat(0.);
		final Authority ban = new Authority();
		ban.setAuthority(Authority.BANNED);
		principal.getUserAccount().getAuthorities().add(ban);
		this.actorService.save(principal);

		final ModelAndView result = new ModelAndView("redirect:../j_spring_security_logout");
		return result;
	}

	// ANCILLARY METHODS  ---------------------------------------------------------------		

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("hacker/edit");
		result.addObject("actorForm", actorForm);
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		result.addObject("countryPhoneCode", this.configurationParametersService.find().getCountryPhoneCode());
		result.addObject("message", messageCode);

		return result;
	}

}
