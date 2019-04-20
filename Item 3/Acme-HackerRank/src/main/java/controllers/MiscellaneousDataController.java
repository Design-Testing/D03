
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

import services.CurriculaService;
import services.HackerService;
import services.MiscellaneousDataService;
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
	private CurriculaController			curriculaController;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final MiscellaneousData miscellaneousData = this.miscellaneousDataService.create();
		result = this.createEditModelAndView(miscellaneousData);
		return result;
	}

	//Updating
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousDataId) {
		ModelAndView result;
		try {
			final MiscellaneousData miscellaneousData;
			final Hacker hacker = this.hackerService.findByPrincipal();
			miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
			//final Curricula curricula = this.curriculaService.findCurriculaByHacker(hacker.getId());
			//Assert.isTrue(curricula.getMiscellaneous().contains(miscellaneousData), "This personal data is not of your property");
			Assert.isTrue(this.hackerService.hasMiscellaneousData(hacker.getId(), miscellaneousDataId), "This personal data is not of your property");
			result = this.createEditModelAndView(miscellaneousData);
		} catch (final Exception e) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", e.getMessage());
		}

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MiscellaneousData miscellaneousData, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(miscellaneousData);
		else
			try {
				this.miscellaneousDataService.save(miscellaneousData);
				//TODO
				result = this.curriculaController.displayAll();
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
			res.addObject("buttons", false);

		} else
			res = new ModelAndView("redirect:/misc/403.jsp");

		return res;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int miscellaneousDataId) {
		ModelAndView result;
		final MiscellaneousData miscellaneousData = this.miscellaneousDataService.findOne(miscellaneousDataId);
		this.miscellaneousDataService.delete(miscellaneousData);
		//TODO
		result = this.curriculaController.displayAll();

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

		return result;

	}
}
