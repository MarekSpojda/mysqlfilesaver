package pl.marekspojda.MySqlFileSaver.controller;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileController {
	JFrame saveFrame;
	JPanel panel;
//	JLabel etykieta;
//	JTextField poleTekstowe1;
	JButton closeButton;
//	JTextArea obszarTekstowy;
//	JCheckBox poleZaznaczenia;
//	JRadioButton radio1, radio2;
//	ButtonGroup grupaRadio;
//	JList<String> listaPrzewijalna;
//	JComboBox<String> listaRozwijalna;
//	JTabbedPane zakladka;
//	JTable tabela;

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public String saveFilewindow() {
		saveFrame = new JFrame();
		saveFrame.setSize(600, 400);
		saveFrame.setLocationRelativeTo(null);
		saveFrame.setTitle("Welcome :)");

		saveFrame.setResizable(false);

		drawPanel();

		saveFrame.setVisible(true);
		return "/user";
	}

	public static void main(String[] args) {
		new FileController().saveFilewindow();
	}

	private void drawPanel() {
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

//		rysujEtykieta();
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

	private void drawButtons() {
		closeButton = new JButton("Exit");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFrame.setVisible(false);
			}
		});
		closeButton.setBounds(5, 135, 100, 20);
		panel.add(closeButton);
	}
}