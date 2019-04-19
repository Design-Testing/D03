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
import services.EducationDataService;
import domain.Curricula;
import domain.Hacker;
import domain.EducationData;

@Controller
@RequestMapping("/educationData")
public class EducationDataController {

private EducationDataService			educationDataService;
	
	@Autowired
	private HackerService				hackerService;
	
	@Autowired
	private CurriculaService     curriculaService;
	
	@Autowired
	private CurriculaController    curriculaController;



	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final EducationData educationData = this.educationDataService.create();
		result = this.createEditModelAndView(educationData);
		return result;
	}

	//Updating
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationDataId) {
		ModelAndView result;
		try {
			final EducationData educationData;
			final Hacker hacker = this.hackerService.findByPrincipal();
			educationData = this.educationDataService.findOne(educationDataId);
			final Curricula curricula = this.curriculaService.findCurriculaByHacker(hacker.getId());
			Assert.isTrue(curricula.getPersonalRecord().equals(educationData), "This personal data is not of your property");
			result = this.createEditModelAndView(educationData);
		} catch (final Exception e) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", e.getMessage());
		}

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final EducationData educationData, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(educationData);
		else
			try {
				this.educationDataService.save(educationData);
				result = this.curriculaController.list();
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(educationData, "general.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int educationDataId) {

		ModelAndView res;

		final EducationData educationData = this.educationDataService.findOne(educationDataId);

		if (educationData != null) {

			res = new ModelAndView("educationData/display");
			res.addObject("educationData", educationData);
			res.addObject("buttons", false);

		} else
			res = new ModelAndView("redirect:/misc/403.jsp");

		return res;

	}

	protected ModelAndView createEditModelAndView(final EducationData educationData) {
		ModelAndView result;

		result = this.createEditModelAndView(educationData, null);

		return result;
	}
	// Edition ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final EducationData educationData, final String message) {
		ModelAndView result;

		result = new ModelAndView("educationData/edit");
		result.addObject("educationData", educationData);
		result.addObject("message", message);

		return result;

	}
}
