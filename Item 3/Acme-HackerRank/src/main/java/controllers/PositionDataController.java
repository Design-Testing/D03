
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
import services.PositionDataService;
import domain.Curricula;
import domain.Hacker;
import domain.PositionData;

@Controller
@RequestMapping("/positionData")
public class PositionDataController {

	@Autowired
	private PositionDataService	positionDataService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private CurriculaService	curriculaService;

	@Autowired
	private CurriculaController	curriculaController;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final PositionData positionData = this.positionDataService.create();
		result = this.createEditModelAndView(positionData);
		return result;
	}

	//Updating
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionDataId) {
		ModelAndView result;
		try {
			final PositionData positionData;
			final Hacker hacker = this.hackerService.findByPrincipal();
			positionData = this.positionDataService.findOne(positionDataId);
			//final Curricula curricula = this.curriculaService.findCurriculaByHacker(hacker.getId());
			//Assert.isTrue(curricula.getPositions().contains(positionData), "This personal data is not of your property");
			Assert.isTrue(this.hackerService.hasPositionData(hacker.getId(), positionDataId), "This personal data is not of your property");
			result = this.createEditModelAndView(positionData);
		} catch (final Exception e) {
			result = new ModelAndView("administrator/error");
			result.addObject("trace", e.getMessage());
		}

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PositionData positionData, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(positionData);
		else
			try {
				this.positionDataService.save(positionData);
				//TODO
				//result = this.curriculaController.displayAll();
				final Curricula curricula = this.curriculaService.findCurriculaByPositionData(positionData.getId());
				result = new ModelAndView("curricula/display");
				result.addObject("curricula", curricula);
				result.addObject("buttons", true);
			} catch (final Throwable e) {
				if (e.getMessage().equals("End date must be after start date"))
					result = this.createEditModelAndView(positionData, "alert.dates");
				else
					result = new ModelAndView("redirect:/misc/403.jsp");
			}

		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int positionDataId) {
		ModelAndView result;
		final PositionData positionData = this.positionDataService.findOne(positionDataId);
		this.positionDataService.delete(positionData);
		//TODO
		result = this.curriculaController.displayAll();

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionDataId) {

		ModelAndView res;

		final PositionData positionData = this.positionDataService.findOne(positionDataId);

		if (positionData != null) {

			res = new ModelAndView("positionData/display");
			res.addObject("positionData", positionData);
			res.addObject("buttons", false);

		} else
			res = new ModelAndView("redirect:/misc/403.jsp");

		return res;

	}

	protected ModelAndView createEditModelAndView(final PositionData positionData) {
		ModelAndView result;

		result = this.createEditModelAndView(positionData, null);

		return result;
	}
	// Edition ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final PositionData positionData, final String message) {
		ModelAndView result;

		result = new ModelAndView("positionData/edit");
		result.addObject("positionData", positionData);
		result.addObject("message", message);

		return result;

	}
}
