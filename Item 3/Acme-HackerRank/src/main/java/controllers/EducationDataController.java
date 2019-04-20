
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
import services.EducationDataService;
import services.HackerService;
import domain.Curricula;
import domain.EducationData;
import domain.Hacker;

@Controller
@RequestMapping("/educationData")
public class EducationDataController {

	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CurriculaService		curriculaService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int curriculaId) {

		ModelAndView result;

		final EducationData educationData = this.educationDataService.create();

		result = new ModelAndView("educationData/edit");
		result.addObject("educationData", educationData);
		result.addObject("curriculaId", curriculaId);
		result.addObject("message", null);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationDataId, @RequestParam final int curriculaId) {
		ModelAndView result;
		try {
			final EducationData educationData;
			final Hacker hacker = this.hackerService.findByPrincipal();
			educationData = this.educationDataService.findOne(educationDataId);

			Assert.isTrue(this.hackerService.hasEducationData(hacker.getId(), educationDataId), "This personal data is not of your property");

			result = new ModelAndView("educationData/edit");
			result.addObject("educationData", educationData);
			result.addObject("curriculaId", curriculaId);
			result.addObject("message", null);

		} catch (final Exception e) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int curriculaId, @Valid final EducationData educationData, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(educationData);
		else
			try {
				this.educationDataService.save(educationData, curriculaId);

				final Curricula curricula = this.curriculaService.findOne(curriculaId);
				result = new ModelAndView("curricula/display");
				result.addObject("curricula", curricula);
				result.addObject("message", null);
				result.addObject("buttons", true);

			} catch (final Throwable e) {
				System.out.println(e.getMessage());
				if (e.getMessage().equals("End date must be after start date")) {
					result = new ModelAndView("educationData/edit");
					result.addObject("educationData", educationData);
					result.addObject("curriculaId", curriculaId);
					result.addObject("message", "alert.dates");
				} else
					result = new ModelAndView("redirect:/misc/403.jsp");

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

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int educationDataId) {
		ModelAndView result;
		final EducationData educationData = this.educationDataService.findOne(educationDataId);
		final Curricula curricula = this.curriculaService.findCurriculaByEducationData(educationDataId);
		this.educationDataService.delete(educationData);

		result = new ModelAndView("curricula/display");
		result.addObject("curricula", curricula);
		result.addObject("message", null);
		result.addObject("buttons", true);

		return result;
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
