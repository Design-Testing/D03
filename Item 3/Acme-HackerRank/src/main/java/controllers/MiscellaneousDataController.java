
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CompanyService;
import services.CurriculaService;
import services.HackerService;
import services.MiscellaneousDataService;
import domain.Company;
import domain.Curricula;
import domain.Hacker;
import domain.MiscellaneousData;

@Controller
@RequestMapping("/miscellaneousData")
public class MiscellaneousDataController {

	@Autowired
	private MiscellaneousDataService	miscellaneousDataService;

	@Autowired
	private HackerService				hackerService;

	@Autowired
	private CurriculaService			curriculaService;

	@Autowired
	private CompanyService				companyService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {

		ModelAndView result;
		final MiscellaneousData miscellaneousData = this.miscellaneousDataService.create();

		result = new ModelAndView("miscellaneousData/edit");
		result.addObject("miscellaneousData", miscellaneousData);
		result.addObject("curriculaId", curriculaId);
		result.addObject("message", null);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousDataId, @RequestParam final int curriculaId) {
		ModelAndView result;
		try {
			final MiscellaneousData miscellaneousData;
			final Hacker hacker = this.hackerService.findByPrincipal();
			miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);

			Assert.isTrue(this.hackerService.hasMiscellaneousData(hacker.getId(), miscellaneousDataId), "This personal data is not of your property");

			result = new ModelAndView("miscellaneousData/edit");
			result.addObject("miscellaneousData", miscellaneousData);
			result.addObject("curriculaId", curriculaId);
			result.addObject("message", null);

		} catch (final Exception e) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int curriculaId, @Valid final MiscellaneousData miscellaneousData, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(miscellaneousData);
			result.addObject("curriculaId", curriculaId);
		} else
			try {
				this.miscellaneousDataService.save(miscellaneousData, curriculaId);

				final Curricula curricula = this.curriculaService.findOne(curriculaId);
				result = new ModelAndView("curricula/display");
				result.addObject("curricula", curricula);
				result.addObject("curriculaId", curricula.getId());
				result.addObject("message", null);
				result.addObject("buttons", true);

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(miscellaneousData, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int miscellaneousDataId) {

		ModelAndView res;

		final MiscellaneousData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);

		if (miscellaneousData != null) {

			res = new ModelAndView("miscellaneousData/display");
			res.addObject("miscellaneousData", miscellaneousData);
			res.addObject("curriculaId", this.curriculaService.findCurriculaByMiscellaneousData(miscellaneousData.getId()).getId());
			res.addObject("buttons", false);

			final Curricula curricula = this.curriculaService.findOne(this.curriculaService.findCurriculaByMiscellaneousData(miscellaneousData.getId()).getId());

			final UserAccount logged = LoginService.getPrincipal();

			final Authority authHacker = new Authority();
			authHacker.setAuthority(Authority.HACKER);
			final Authority authCompany = new Authority();
			authCompany.setAuthority(Authority.COMPANY);
			if (logged.getAuthorities().contains(authHacker)) {
				final Hacker hacker = this.hackerService.findByPrincipal();
				if (curricula.getHacker() != null)
					Assert.isTrue(curricula.getHacker().equals(hacker));
				else if (curricula.getHacker() == null)
					Assert.isTrue(this.hackerService.findHackerByCopyCurricula(curricula.getId()).equals(hacker));
			} else if (logged.getAuthorities().contains(authCompany)) {
				final Company company = this.companyService.findByPrincipal();
				Assert.isTrue(this.curriculaService.findCurriculasByCompany(company.getId()).contains(curricula));
			}

		} else
			res = new ModelAndView("redirect:misc/403");

		return res;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int miscellaneousDataId) {
		ModelAndView result;
		final MiscellaneousData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
		final Curricula curricula = this.curriculaService.findCurriculaByMiscellaneousData(miscellaneousDataId);
		this.miscellaneousDataService.delete(miscellaneousData);

		result = new ModelAndView("curricula/display");
		result.addObject("curricula", curricula);
		result.addObject("message", null);
		result.addObject("buttons", true);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousData miscellaneousData) {
		ModelAndView result;

		result = this.createEditModelAndView(miscellaneousData, null);

		return result;
	}
	// Edition ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final MiscellaneousData miscellaneousData, final String message) {
		ModelAndView result;

		result = new ModelAndView("miscellaneousData/edit");
		result.addObject("miscellaneousData", miscellaneousData);
		result.addObject("message", message);
		if (miscellaneousData.getId() != 0)
			result.addObject("curriculaId", this.curriculaService.findCurriculaByMiscellaneousData(miscellaneousData.getId()).getId());

		return result;

	}
}
