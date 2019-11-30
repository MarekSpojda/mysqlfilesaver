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
		String prefix1 = "<button type=\"button\" id=\"file";
		String prefix2 = "\" fileidval=\"";
		String prefix3 = "\">";
		String prefix4 = "</button><br>";

		for (FileRepresentation fileRepresentation : loadedUser.getFiles()) {
			String entry = prefix1 + fileRepresentation.getFileId() + prefix2 + fileRepresentation.getFileId() + prefix3
					+ fileRepresentation.getFileName() + "." + fileRepresentation.getFileExtension() + prefix4;
			stringBuilder.append(entry);
		}

		return stringBuilder.toString();
	}
}