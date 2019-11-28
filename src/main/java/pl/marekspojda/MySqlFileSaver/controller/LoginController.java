package pl.marekspojda.MySqlFileSaver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController implements ErrorController {
	private static final String PATH = "/error";

	@RequestMapping(path = "/login", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(path = "/admin", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String allowedToAdminsOnly(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "admin";
		}
		return "redirect:/";
	}

	@RequestMapping(path = "/user", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	public String allowedToUsersOnly(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_USER")) {
			return "user";
		}
		return "redirect:/";
	}

	@RequestMapping(value = PATH)
	public String error() {
		return "redirect:/";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}