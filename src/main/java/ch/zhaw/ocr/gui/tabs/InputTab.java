package ch.zhaw.ocr.gui.tabs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.gui.MainGui;
import ch.zhaw.ocr.gui.helper.ImageFileFilter;
import ch.zhaw.ocr.gui.helper.SpringUtilities;
import ch.zhaw.ocr.gui.helper.TextFileFilter;
import ch.zhaw.ocr.gui.helper.TextFileHandler;

/**
 * InputTab. Offers a UI to parse an image and display the result. 
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class InputTab {

	final JFileChooser fc = new JFileChooser();

	private JTextField imagePath;
	private JTextArea analysedText;
	private JButton saveButton;
	private JButton browseButton;
	private JButton analyseButton;
	private MainGui gui;
	private JPanel panel;

	public InputTab(MainGui gui) {
		this.gui = gui;
		createPanel();
	}

	private void createPanel() {
		panel = new JPanel(new BorderLayout());
		panel.add(loadTextArea(), BorderLayout.CENTER);
		panel.add(loadButtons(), BorderLayout.SOUTH);
	}

	private JComponent loadButtons() {

		JPanel buttonPanel = new JPanel(new SpringLayout());

		JLabel browseImg = new JLabel("Browse Image:");

		imagePath = new JTextField();

		browseButton = new JButton("Browse");
		analyseButton = new JButton("Analyse");

		browseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ImageFileFilter filter = new ImageFileFilter();
				fc.setFileFilter(filter);

				if (e.getSource() == browseButton) {

					int returnVal = fc.showOpenDialog(browseButton);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						imagePath.setText(file.getPath());
						gui.setConsoleText(new StringBuffer());
						analysedText.setText(gui.getOcr().parseImage(file,
								gui.getConsoleText()));
						gui.getKnnPanel().setConsoleText(gui.getConsoleText());

						// save analysed image and text for historiy
						try {
							saveForHistory(file, analysedText.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});

		buttonPanel.add(browseImg);
		buttonPanel.add(imagePath);
		buttonPanel.add(browseButton);
		buttonPanel.add(analyseButton);

		SpringUtilities.makeCompactGrid(buttonPanel, 1, 4, 5, 5, 5, 5);

		return buttonPanel;

	}

	private void saveForHistory(File imgFile, String text) throws IOException {
		File historyFolder = new File(Properties.historyResourcefoler);
		if(!historyFolder.exists()){
			historyFolder.mkdir();
		}
		
		
    	File newHistoryFile = new File("history/" + imgFile.getName());
    	int i = 1;
    	while (newHistoryFile.exists()) {
    		newHistoryFile = new File("history/" + imgFile.getName().substring(0,imgFile.getName().lastIndexOf(".")) + "_ " + i + imgFile.getName().substring(imgFile.getName().lastIndexOf(".")));
    		i++;
    	}
    	BufferedImage img = ImageIO.read(imgFile);
    	String imgEnding = newHistoryFile.getAbsolutePath().substring(newHistoryFile.getAbsolutePath().lastIndexOf(".") + 1, newHistoryFile.getAbsolutePath().length());
    	Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(imgEnding);
    	ImageWriter writer = writers.next();

    	
    	ImageOutputStream ios = ImageIO.createImageOutputStream(newHistoryFile);
    	writer.setOutput(ios);

    	writer.write(img);
    	ios.close();
    	
    	String path = newHistoryFile.getAbsolutePath().substring(0, newHistoryFile.getAbsolutePath().lastIndexOf(".")) + ".txt";
    	
    	BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File (path)));
    	bw2.write(text);
    	bw2.close();
    	
    	gui.getHistoryForm().getInputNavitation().loadImageTree();
    	gui.getHistoryForm().getInputNavitation().getPanel().repaint();
    	gui.getHistoryForm().getPanel().repaint();
    	
    }

	public JPanel getPanel() {
		return panel;
	}

	private JComponent loadTextArea() {

		JPanel areaPanel = new JPanel(new SpringLayout());
		JPanel button2Panel = new JPanel();

		analysedText = new JTextArea(20, 70);
		JScrollPane scrollAnalysed = new JScrollPane(analysedText);

		saveButton = new JButton("Save as text");

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TextFileFilter filter = new TextFileFilter();
				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(saveButton);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						TextFileHandler.saveTextFile(file,
								analysedText.getText());
						JOptionPane.showMessageDialog(null,
								"File saved successfully!", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null,
								"File could not be saved!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		button2Panel
				.setLayout(new BoxLayout(button2Panel, BoxLayout.LINE_AXIS));
		button2Panel.add(Box.createHorizontalGlue());
		button2Panel.add(saveButton);

		areaPanel.add(scrollAnalysed);
		areaPanel.add(button2Panel);

		SpringUtilities.makeCompactGrid(areaPanel, 2, 1, 10, 10, 10, 20);

		return areaPanel;
	}

}
