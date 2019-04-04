
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PositionService;
import services.ProblemService;
import domain.Company;
import domain.Problem;

@Controller
@RequestMapping("/problem")
public class ProblemController extends AbstractController {

	//Listar, mostrar, crear, actualizar y borrar.
	@Autowired
	private ProblemService	problemService;
	@Autowired
	private PositionService	positionService;
	@Autowired
	private CompanyService	companyService;


	//Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Problem problem = this.problemService.create();
		result = this.createEditModelAndView(problem);
		return result;
	}

	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int problemId) {

		ModelAndView res;

		final Problem problem = this.problemService.findOne(problemId);

		if (problem != null) {

			res = new ModelAndView("problem/display");
			res.addObject("problem", problem);

		} else
			res = new ModelAndView("redirect:/misc/403.jsp");

		return res;

	}

	//List

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Company company = this.companyService.findByPrincipal();
		final Collection<Problem> problems = this.problemService.findProblemByCompany();

		res = new ModelAndView("problem/list");
		res.addObject("company", company);
		res.addObject("problems", problems);

		return res;
	}
	// EDIT 

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int problemId) {
		ModelAndView result;
		Problem problem;

		problem = this.problemService.findOne(problemId);

		final Company company = this.companyService.findByPrincipal();

		if ((problem.getMode().equals("DRAFT") && problem.getCompany().equals(company)))
			result = this.createEditModelAndView(problem);
		else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;
	}

	// SAVE --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Problem problem, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(problem);
		else
			try {
				this.problemService.save(problem);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(problem, "problem.commit.error");
			}

		return result;
	}

	//DELETE

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Problem problem, final BindingResult binding) {
		ModelAndView result;

		try {
			this.problemService.delete(problem);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(problem, "problem.commit.error");
		}

		return result;

	}

	// TO FINAL MODE 

	@RequestMapping(value = "/finalMode", method = RequestMethod.GET)
	public ModelAndView finalMode(@RequestParam final int problemId) {
		final ModelAndView result;
		final Problem problem = this.problemService.findOne(problemId);
		final Collection<Problem> problems = this.problemService.findProblemByCompany();
		Assert.isTrue(problems.contains(problem));
		if (problem.getMode() == "DRAFT") {
			this.problemService.toFinalMode(problemId);
			result = new ModelAndView("redirect:list.do");
		} else
			result = new ModelAndView("redirect:/misc/403.jsp");
		return result;
	}

	protected ModelAndView createEditModelAndView(final Problem problem) {
		ModelAndView result;

		result = this.createEditModelAndView(problem, null);

		return result;
	}
	// Edition ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Problem problem, final String message) {
		ModelAndView result;

		result = new ModelAndView("problem/edit");
		result.addObject("problem", problem);
		result.addObject("message", message);

		return result;

	}

}
