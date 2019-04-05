
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import domain.Position;

@Controller
@RequestMapping("/position")
public class PositionController extends AbstractController {

	@Autowired
	private PositionService	positionService;

	final String			lang	= LocaleContextHolder.getLocale().getLanguage();


	// DISPLAY --------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		final ModelAndView result;
		final Position position;

		position = this.positionService.findOne(positionId);

		if (position != null && position.getMode().equals("FINAL")) {
			result = new ModelAndView("position/display");
			result.addObject("position", position);
			result.addObject("lang", this.lang);

		} else
			result = new ModelAndView("redirect:/misc/403.jsp");

		return result;
	}

	// LIST --------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView myPositions() {
		final ModelAndView result;
		final Collection<Position> positions;

		positions = this.positionService.findAllFinalMode();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("listPositions", "list");
		result.addObject("lang", this.lang);
		result.addObject("requetURI", "position/list.do");
		return result;
	}
}
