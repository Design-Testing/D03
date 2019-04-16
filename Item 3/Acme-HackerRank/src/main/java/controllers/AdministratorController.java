
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
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ActorService;
import services.AdministratorService;
import services.ConfigurationParametersService;
import services.UserAccountService;
import services.auxiliary.RegisterService;
import domain.Actor;
import domain.Administrator;
import forms.ActorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private UserAccountService				accountService;

	@Autowired
	private ActorService					actorService;

	@Autowired
	private RegisterService					registerService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {

		super();

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		Administrator admin;
		if (binding.hasErrors()) {
			result.addObject("errors", binding.getAllErrors());
			result.addObject("actorForm", actorForm);
		} else
			try {
				final UserAccount ua = this.accountService.reconstruct(actorForm, Authority.ADMIN);
				admin = this.administratorService.reconstruct(actorForm, binding);
				admin.setUserAccount(ua);
				this.registerService.saveAdmin(admin, binding);
				result.addObject("alert", "administartor.edit.correct");
				result.addObject("actorForm", actorForm);
			} catch (final ValidationException oops) {
				result = new ModelAndView("hacker/edit");
				result.addObject("actorForm", actorForm);
			} catch (final Throwable e) {
				if (e.getMessage().contains("username is register"))
					result.addObject("alert", "administartor.edit.usernameIsUsed");
				result.addObject("errors", binding.getAllErrors());
				result.addObject("actorForm", actorForm);
			}
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		result = new ModelAndView("administrator/edit");
		final Administrator admin = this.administratorService.findByPrincipal();
		final ActorForm actor = this.registerService.inyect(admin);
		actor.setTermsAndCondicions(true);
		result.addObject("actorForm", actor);
		return result;
	}

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView result = new ModelAndView();
		final ActorForm admin = new ActorForm();
		result = new ModelAndView("administrator/edit");
		result.addObject("actorForm", admin);
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		return result;
	}

	@RequestMapping("/display")
	public ModelAndView display() {
		ModelAndView result = new ModelAndView();
		final Administrator admin = this.administratorService.findByPrincipal();
		result = new ModelAndView("administrator/display");
		result.addObject("administrator", admin);
		result.addObject("cardmakes", this.configurationParametersService.find().getCreditCardMake());
		return result;
	}

	//GDPR

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

}
