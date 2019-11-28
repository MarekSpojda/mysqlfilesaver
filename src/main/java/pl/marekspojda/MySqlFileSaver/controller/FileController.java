package pl.marekspojda.MySqlFileSaver.controller;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import javax.swing.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.marekspojda.MySqlFileSaver.entity.FileRepresentation;
import pl.marekspojda.MySqlFileSaver.entity.User;
import pl.marekspojda.MySqlFileSaver.messages.Messages;
import pl.marekspojda.MySqlFileSaver.repository.UserRepository;

@Controller
public class FileController {
	private final JFileChooser fileChooser = new JFileChooser();
	private final UserRepository userRepository;

	private JFrame saveFrame;
	private JPanel panel;
	private File file;
	private JLabel fileLabel, fileLabelDescription;
	private Principal principal;
	private JButton closeButton, openButton, saveButton;

	public FileController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public void saveFilewindow(Principal principal) {
		this.principal = principal;
		saveFrame = new JFrame();
		saveFrame.setSize(700, 55);
		saveFrame.setLocationRelativeTo(null);
		saveFrame.setUndecorated(true);
		saveFrame.setResizable(false);

		drawPanel();

		saveFrame.setVisible(true);
	}

	@RequestMapping(path = "/userfiles", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String userFilesGet(Principal principal) {
		this.principal = principal;
		return Messages.displayFiles(this.principal, userRepository);
	}

	private void drawPanel() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setLayout(null);

		drawLabels();
		drawButtons();

		saveFrame.getContentPane().add(BorderLayout.CENTER, panel);
	}

	private void drawLabels() {
		fileLabel = new JLabel("");
		fileLabel.setBounds(130, 5, 565, 20);
		fileLabel.setBackground(Color.WHITE);
		fileLabel.setOpaque(true);
		fileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.add(fileLabel);

		fileLabelDescription = new JLabel("Wybrany plik:");
		fileLabelDescription.setBounds(5, 5, 120, 20);
		panel.add(fileLabelDescription);
	}

	private void drawButtons() {
		closeButton = new JButton("Exit");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFrame.dispose();
			}
		});
		closeButton.setBounds(215, 30, 100, 20);
		panel.add(closeButton);

		// Creating savebutton earlier to prevent null exception
		saveButton = new JButton("Save file");
		saveButton.setEnabled(false);

		openButton = new JButton("Open file");
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == openButton) {
					int returnVal = fileChooser.showOpenDialog(saveFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						fileLabel.setText(file.getName());
						saveButton.setEnabled(true);
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		openButton.setBounds(5, 30, 100, 20);
		panel.add(openButton);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fullFileName = file.getName();
				int dotPos = fullFileName.lastIndexOf(".");
				String fileName;
				String fileExtension;

				if (dotPos == -1) {
					fileName = fullFileName;
					fileExtension = "non";
				} else {
					fileName = fullFileName.substring(0, dotPos);
					fileExtension = fullFileName.substring(dotPos + 1, fullFileName.length());
				}

				// Creating file object to save
				FileRepresentation fileToSave = new FileRepresentation();
				fileToSave.setFileExtension(fileExtension);
				fileToSave.setFileName(fileName);
				try {
					fileToSave.setFileContent(Files.readAllBytes(file.toPath()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				User loadedUser = userRepository.findUserByEmailCustom(principal.getName());
				if (loadedUser != null) {
					User userToUpdate = loadedUser;
					userToUpdate.getFiles().add(fileToSave);
					userRepository.save(userToUpdate);
					saveFrame.dispose();
				}
			}
		});
		saveButton.setBounds(110, 30, 100, 20);
		panel.add(saveButton);
	}
}