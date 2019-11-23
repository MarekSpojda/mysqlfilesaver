package pl.marekspojda.MySqlFileSaver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController implements ErrorController {
	private static final String PATH = "/error";

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/admin")
	public String allowedToAdminsOnly(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "admin";
		}
		return "redirect:/";
	}

	@RequestMapping("/user")
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