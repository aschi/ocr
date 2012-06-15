package ch.zhaw.ocr.gui.tabs;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.helper.SpringUtilities;
import ch.zhaw.ocr.gui.helper.TextFileHandler;

/**
 * HistoryTab offers an UI to show a history of analysed images
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 * 
 */
public class HistoryTab {
	private JTextArea historyText;
	private JPanel panel;
	private JComponent imgPanel;
	private JLabel imgLabel;
	private MainGui gui;
	private ImageIcon img;
	private HistoryNavigation inavi;

	/**
	 * Constructor
	 * @param gui main gui
	 */
	public HistoryTab(MainGui gui) {
		this.gui = gui;
		createPanel();
	}

	/**
	 * creates the main panel of the history tab with all elements
	 */
	public void createPanel() {

		panel = new JPanel(new BorderLayout());
		loadImage(null);
		inavi = new HistoryNavigation(gui);
		panel.add(inavi.getPanel(), BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel(new SpringLayout());
		centerPanel.add(new JScrollPane(imgLabel));
		centerPanel.add(loadTextArea());
		SpringUtilities.makeCompactGrid(centerPanel, 2, 1, 10, 10, 10, 20);
		panel.add(centerPanel, BorderLayout.CENTER);
	}
	
	/**
	 * returns the inputnavigation (JTree with all analysed images)
	 * @return HistoryNavigation
	 */
	public HistoryNavigation getInputNavitation() {
		return inavi;
	}

	/**
	 * loads the image into the label and shows it on the top of the tab
	 * @param img
	 */
	public void loadImage(BufferedImage img) {
		
		if (img == null) {
			this.img = new ImageIcon();
		} else {
			this.img = new ImageIcon(img);
		}
//		if (imgPanel == null) {
//			imgPanel = new JScrollPane();
//		}
		if (imgLabel == null) {
			imgLabel = new JLabel(this.img);
//			imgPanel.add(imgLabel);
		}
		imgLabel.setIcon(this.img);	
		
		imgLabel.repaint();
//		imgPanel.repaint();
		panel.repaint();
		

	}

	/**
	 * sets the image
	 * @param img BufferedImage
	 */
	public void setImg(BufferedImage img) {
		this.img = new ImageIcon(img);
	}

	/**
	 * sets the text of the choosen analysed image which has to be showned on the tab
	 * @param datei filename as String
	 * @throws IOException when there was a problem while reading the file
	 */
	public void setText(String datei) throws IOException {

		historyText.setText(TextFileHandler.readTextFile(datei));

	}

	private JComponent loadTextArea() {
		
		historyText = new JTextArea();
		JScrollPane scrollHistory = new JScrollPane(historyText);


		return scrollHistory;
	}

	/**
	 * returns the main-history-tab-panel
	 * @return JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}

}
