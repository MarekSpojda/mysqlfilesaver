package pl.marekspojda.MySqlFileSaver.controller;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.xml.bind.v2.TODO;

@Controller
public class FileController {
	private final JFileChooser fileChooser = new JFileChooser();

	private JFrame saveFrame;
	private JPanel panel;
	private File file;
	private JLabel fileLabel, fileLabelDescription;
//	JTextField poleTekstowe1;
	private JButton closeButton, openButton, saveButton;
//	JTextArea obszarTekstowy;
//	JCheckBox poleZaznaczenia;
//	JRadioButton radio1, radio2;
//	ButtonGroup grupaRadio;
//	JList<String> listaPrzewijalna;
//	JComboBox<String> listaRozwijalna;
//	JTabbedPane zakladka;
//	JTable tabela;

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public void saveFilewindow() {
		saveFrame = new JFrame();
		saveFrame.setSize(700, 55);
		saveFrame.setLocationRelativeTo(null);
		saveFrame.setUndecorated(true);

		saveFrame.setResizable(false);

		drawPanel();

		saveFrame.setVisible(true);
	}

	private void drawPanel() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setLayout(null);

		drawLabels();
//		rysujPoleTekstowe();
		drawButtons();
//		rysujObszarTekstowy();
//		rysujPoleZaznaczenia();
//		rysujRadioButton();
//		rysujListaPrzewijalna();
//		rysujListaRozwijalna();
//		rysujZakladka();
//		rysujTabela();

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

		openButton = new JButton("Open file");
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == openButton) {
					int returnVal = fileChooser.showOpenDialog(saveFrame);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						fileLabel.setText(file.getName());
					} else {
						System.out.println("Open command cancelled by user.");
					}
				}
			}
		});
		openButton.setBounds(5, 30, 100, 20);
		panel.add(openButton);

		saveButton = new JButton("Save file");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO implement savefile action
			}
		});
		saveButton.setBounds(110, 30, 100, 20);
		panel.add(saveButton);
	}
}