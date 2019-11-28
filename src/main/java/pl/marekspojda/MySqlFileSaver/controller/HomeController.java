package pl.marekspojda.MySqlFileSaver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(path = "/", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String homeAction() {
		return "index";
	}
}
