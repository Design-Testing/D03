
package controllers.company;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
import controllers.AbstractController;
import domain.Company;
import domain.Position;
import domain.Problem;
import forms.PositionForm;

@Controller
@RequestMapping("/position/company")
public class PositionCompanyController extends AbstractController {

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;

	@Autowired
	private ProblemService	problemService;

	final String			lang	= LocaleContextHolder.getLocale().getLanguage();


	// CREATE --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Position position;

		position = this.positionService.create();
		result = this.createEditModelAndView(position);
		return result;
	}

	// DISPLAY --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		final ModelAndView result;
		final Position position;
		final Company company;

		position = this.positionService.findOne(positionId);
		company = this.companyService.findByPrincipal();
		final Collection<Problem> problems = this.problemService.findProblemsByPosition(positionId);

		if (position != null) {
			result = new ModelAndView("position/display");
			result.addObject("position", position);
			result.addObject("company", company);
			result.addObject("rol", "company");
			result.addObject("lang", this.lang);
			result.addObject("problems", problems);

		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;
	}
	// LIST --------------------------------------------------------

	@RequestMapping(value = "/myPositions", method = RequestMethod.GET)
	public ModelAndView myPositions() {
		final ModelAndView result;
		final Collection<Position> positions;

		positions = this.positionService.findAllByCompany();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("lang", this.lang);
		result.addObject("rol", "company");
		result.addObject("listPositions", "myPositions");
		result.addObject("requetURI", "position/company/myPositions.do");
		result.addObject("principalID", this.companyService.findByPrincipal().getId());

		return result;
	}

	// TO FINAL MODE --------------------------------------------------------

	@RequestMapping(value = "/finalMode", method = RequestMethod.GET)
	public ModelAndView finalMode(@RequestParam final int positionId) {
		ModelAndView result;
		final Position position = this.positionService.findOne(positionId);

		if (position == null || !position.getMode().equals("DRAFT") || (this.problemService.findProblemsByPosition(positionId).size() < 2)) {
			result = new ModelAndView("position/error");
			result.addObject("ok", "Error al pasar a final mode la posicion.");
		} else
			try {
				this.positionService.toFinalMode(positionId);
				result = this.myPositions();
			} catch (final Throwable oops) {
				result = new ModelAndView("position/error");
			}

		return result;
	}

	// TO CANCELLED MODE --------------------------------------------------------

	@RequestMapping(value = "/cancelledMode", method = RequestMethod.GET)
	public ModelAndView cancelledMode(@RequestParam final int positionId) {
		final ModelAndView result;
		final Position position = this.positionService.findOne(positionId);

		if (position == null || !position.getMode().equals("FINAL"))
			result = new ModelAndView("position/error");
		else {
			this.positionService.toCancelMode(positionId);
			result = this.myPositions();
		}

		return result;
	}

	// EDIT --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;

		position = this.positionService.findOne(positionId);

		final Company company = this.companyService.findByPrincipal();

		if ((position.getMode().equals("DRAFT") && position.getCompany().equals(company)))
			result = this.createEditModelAndView(position);
		else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;
	}

	// SAVE --------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PositionForm positionForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("position/edit");
			result.addObject("position", positionForm);
		} else
			try {
				final Position position = this.positionService.reconstruct(positionForm, binding);
				this.positionService.save(position);
				result = this.myPositions();
			} catch (final ValidationException oops) {
				result = new ModelAndView("position/edit");
				result.addObject("position", positionForm);
			} catch (final Throwable oops) {
				result = new ModelAndView("position/error");
			}

		return result;
	}
	// DELETE --------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int positionId) {
		ModelAndView result;
		final Position position = this.positionService.findOne(positionId);

		//		try {
		this.positionService.delete(position);
		result = this.myPositions();
		//		} catch (final Throwable oops) {
		//			result = new ModelAndView("redirect:/position/error");
		//		}

		return result;

	}

	// ANCILLIARY METHODS --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Position position) {
		ModelAndView result;

		result = this.createEditModelAndView(position, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Position position, final String messageCode) {
		Assert.notNull(position);
		final ModelAndView result;

		result = new ModelAndView("position/edit");
		result.addObject("position", this.constructPruned(position)); //this.constructPruned(position)

		result.addObject("message", messageCode);

		return result;
	}

	public PositionForm constructPruned(final Position position) {
		final PositionForm pruned = new PositionForm();

		pruned.setId(position.getId());
		pruned.setVersion(position.getVersion());
		pruned.setTitle(position.getTitle());
		pruned.setDescription(position.getDescription());
		pruned.setProfile(position.getProfile());
		pruned.setDeadline(position.getDeadline());
		pruned.setSkills(position.getSkills());
		pruned.setTechnologies(position.getTechnologies());
		pruned.setSalary(position.getSalary());

		return pruned;
	}

}
