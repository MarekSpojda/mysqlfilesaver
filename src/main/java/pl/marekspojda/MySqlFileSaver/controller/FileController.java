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
import org.springframework.web.bind.annotation.PathVariable;
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

	private JFrame saveFrame, downloadFrame;
	private JPanel savePanel, downloadPanel;
	private File file;
	private FileRepresentation fileRepresentation;
	private JLabel fileSaveLabel, fileLoadLabel, fileSaveLabelDescription, fileLoadLabelDescription;
	private Principal principal;
	private JButton closeSaveButton, openSaveButton, saveSaveButton;

	public FileController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/savefile", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	public void saveFilewindow(Principal principal) {
		this.principal = principal;
		saveFrame = new JFrame();
		saveFrame.setSize(700, 55);
		saveFrame.setLocationRelativeTo(null);
		saveFrame.setUndecorated(true);
		saveFrame.setResizable(false);

		drawSavePanel();

		saveFrame.setVisible(true);
	}

	@RequestMapping(path = "/userfiles", produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String userFilesGet(Principal principal) {
		this.principal = principal;
		return Messages.displayFiles(this.principal, userRepository);
	}

	@RequestMapping(path = "/files/{id}", produces = "text/html; charset=UTF-8", method = RequestMethod.POST)
	public void downloadFileWindow(@PathVariable String id) {
		Long fileId = Long.parseLong(id);
		downloadFrame = new JFrame();
		downloadFrame.setSize(700, 55);
		downloadFrame.setLocationRelativeTo(null);
		downloadFrame.setUndecorated(true);
		downloadFrame.setResizable(false);
		fileRepresentation = this.getFileRepresentation(fileId);

		drawDownloadPanel();

		downloadFrame.setVisible(true);
	}

	private FileRepresentation getFileRepresentation(Long id) {
		FileRepresentation fileRepresentation = null;

		for (FileRepresentation file : userRepository.findUserByEmailCustom(principal.getName()).getFiles()) {
			if (file.getFileId().equals(id)) {
				fileRepresentation = file;
				break;
			}
		}

		return fileRepresentation;
	}

	private void drawDownloadPanel() {
		downloadPanel = new JPanel();
		downloadPanel.setBackground(Color.LIGHT_GRAY);
		downloadPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		downloadPanel.setLayout(null);

		drawDownloadLabels();
		//TODO draw download buttons
//		drawButtons();

		downloadFrame.getContentPane().add(BorderLayout.CENTER, downloadPanel);
	}

	private void drawSavePanel() {
		savePanel = new JPanel();
		savePanel.setBackground(Color.LIGHT_GRAY);
		savePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		savePanel.setLayout(null);

		drawSaveLabels();
		drawSaveButtons();

		saveFrame.getContentPane().add(BorderLayout.CENTER, savePanel);
	}

	private void drawDownloadLabels() {
		fileLoadLabel = new JLabel(fileRepresentation.getFileName() + "." + fileRepresentation.getFileExtension());
		fileLoadLabel.setBounds(130, 5, 565, 20);
		fileLoadLabel.setBackground(Color.WHITE);
		fileLoadLabel.setOpaque(true);
		fileLoadLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		downloadPanel.add(fileLoadLabel);

		fileLoadLabelDescription = new JLabel("Wybrany plik:");
		fileLoadLabelDescription.setBounds(5, 5, 120, 20);
		downloadPanel.add(fileLoadLabelDescription);
	}

	private void drawSaveLabels() {
		fileSaveLabel = new JLabel("");
		fileSaveLabel.setBounds(130, 5, 565, 20);
		fileSaveLabel.setBackground(Color.WHITE);
		fileSaveLabel.setOpaque(true);
		fileSaveLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		savePanel.add(fileSaveLabel);

		fileSaveLabelDescription = new JLabel("Wybrany plik:");
		fileSaveLabelDescription.setBounds(5, 5, 120, 20);
		savePanel.add(fileSaveLabelDescription);
	}

	private void drawSaveButtons() {
		closeSaveButton = new JButton("Exit");
		closeSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFrame.dispose();
			}
		});
		closeSaveButton.setBounds(215, 30, 100, 20);
		savePanel.add(closeSaveButton);

		// Creating saveSavebutton earlier to prevent null exception
		saveSaveButton = new JButton("Save file");
		saveSaveButton.setEnabled(false);

		openSaveButton = new JButton("Open file");
		openSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == openSaveButton) {
					int returnVal = fileChooser.showOpenDialog(saveFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						fileSaveLabel.setText(file.getName());
						saveSaveButton.setEnabled(true);
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		openSaveButton.setBounds(5, 30, 100, 20);
		savePanel.add(openSaveButton);

		saveSaveButton.addActionListener(new ActionListener() {
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
		saveSaveButton.setBounds(110, 30, 100, 20);
		savePanel.add(saveSaveButton);
	}
}