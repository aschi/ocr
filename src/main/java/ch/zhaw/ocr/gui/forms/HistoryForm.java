package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ch.zhaw.ocr.gui.InputNavigation;
import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.helper.TextFileHandler;

public class HistoryForm {

	private JTextArea historyText;
	private JPanel panel;
	private JPanel imgPanel;
	private JLabel imgLabel;
	private MainGui gui;
	private ImageIcon img;
	private InputNavigation inavi;

	public HistoryForm(MainGui gui) {
		this.gui = gui;
		createPanel();
	}

	public HistoryForm() {
		createPanel();
	}

	public void createPanel() {

		panel = new JPanel(new BorderLayout());
		loadImage(null);
		inavi = new InputNavigation(gui);
		panel.add(inavi.getPanel(), BorderLayout.WEST);
		panel.add(imgPanel, BorderLayout.CENTER);
		panel.add(loadTextArea(), BorderLayout.SOUTH);
	}
	
	public InputNavigation getInputNavitation() {
		return inavi;
	}

	public void loadImage(BufferedImage img) {

		if (img == null) {
			this.img = new ImageIcon();
		} else {
			this.img = new ImageIcon(img);
		}
		if (imgPanel == null) {
			imgPanel = new JPanel();
		}
		if (imgLabel == null) {
			imgLabel = new JLabel(this.img);
			imgPanel.add(imgLabel);
		}
		imgLabel.setIcon(this.img);
		
		imgPanel.repaint();
		panel.repaint();

	}

	public void setImg(BufferedImage img) {
		this.img = new ImageIcon(img);
	}

	public void setText(String datei) throws IOException {

		historyText.setText(TextFileHandler.readTextFile(datei));

	}

	private JComponent loadTextArea() {
		JPanel areaPanel = new JPanel();
		historyText = new JTextArea(20, 70);
		JScrollPane scrollHistory = new JScrollPane(historyText);

		areaPanel.add(scrollHistory);

		return areaPanel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
