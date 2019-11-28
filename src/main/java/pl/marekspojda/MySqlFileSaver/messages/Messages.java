package pl.marekspojda.MySqlFileSaver.messages;

import java.security.Principal;

import org.springframework.stereotype.Component;

import pl.marekspojda.MySqlFileSaver.entity.FileRepresentation;
import pl.marekspojda.MySqlFileSaver.entity.User;
import pl.marekspojda.MySqlFileSaver.repository.UserRepository;

@Component
public class Messages {
	public static String displayFiles(Principal principal, UserRepository userRepository) {
		StringBuilder stringBuilder = new StringBuilder();

		User loadedUser = userRepository.findUserByEmailCustom(principal.getName());
		String prefix = "<button type=\"button\" class=\"downloadButton\" value=\"";
		String middlefix = "\" onclick=\"showDownloadPanel()\">";
		String suffix = "</button><br>";

		for (FileRepresentation fileRepresentation : loadedUser.getFiles()) {
			String entry = prefix + fileRepresentation.getFileId() + middlefix + fileRepresentation.getFileName() + "."
					+ fileRepresentation.getFileExtension() + suffix;
			stringBuilder.append(entry);
		}
		
		stringBuilder.append("<script>"
				+ "function showDownloadPanel() {"
				+ "let loadId = $(this).attr('value');"
				+ ""
				+ "$.ajax({\n" + 
				"        url:'/files/'+loadId,\n" + 
				"        type:'post',\n" + 
				"        success:function(){\n" + 
				"        }\n" + 
				"      });"
				+ "};"
				+ "</script>");

		return stringBuilder.toString();
	}
}