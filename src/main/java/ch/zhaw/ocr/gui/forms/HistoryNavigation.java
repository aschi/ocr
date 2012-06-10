package ch.zhaw.ocr.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.zhaw.ocr.Properties;
import ch.zhaw.ocr.gui.MainGui;

public class HistoryNavigation {

	// protected static final String HISTORYPANEL = null;

	private JPanel panel;
	private JTree tree;
	private DefaultMutableTreeNode top;
	private MainGui gui;

	public HistoryNavigation(MainGui gui) {
		this.gui = gui;
		createPanel();
	}

	public HistoryNavigation() {
		createPanel();
	}

	private void createPanel() {
		panel = new JPanel(new BorderLayout());
		// panel.add(loadLists(), BorderLayout.CENTER);
		loadImageTree();
		panel.add(tree, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void loadImageTree() {

			top = new DefaultMutableTreeNode("List of used Images:");
		if (tree == null) {
			tree = new JTree(top);
		} else {
			((DefaultTreeModel)tree.getModel()).setRoot(null);
			((DefaultTreeModel)tree.getModel()).reload();
			((DefaultTreeModel)tree.getModel()).setRoot(top);
		}
		getFileFromDir();

		tree.scrollPathToVisible(new TreePath(top.getLastLeaf().getPath()));
		addListener();

		((DefaultTreeModel)tree.getModel()).reload();
	}

	private void addListener() {
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					TreePath p = tree.getPathForLocation(e.getX(), e.getY());
					if (p != null) {
						TreeNode node = (TreeNode) p.getLastPathComponent();
						File files = new File(Properties.historyResourcefoler);
						for (File s : files.listFiles()) {
							if (s.isFile() == true) {
								String k = node.toString();
								String st = s.getAbsolutePath();

								if ((k.substring(k.lastIndexOf(0) + 1,
										k.lastIndexOf('.')) + ".txt").equals(st
										.substring(st.lastIndexOf('/') + 1))) {
									try {
										gui.getHistoryForm().setText(st);
										;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} else {
									if (k.equals((st.substring(st
											.lastIndexOf('/') + 1)))) {
										BufferedImage img;
										try {
											img = ImageIO.read(new File(st));

											gui.getHistoryForm().loadImage(img);
											gui.getHistoryForm().getPanel()
													.repaint();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								}
							} else {
								JOptionPane
										.showMessageDialog(
												null,
												"no corresponding file found in the directory!",
												"Error",
												JOptionPane.ERROR_MESSAGE);
							}
						}

					}

				}
			}

		});
	}

	private void getFileFromDir() {
		File actual = new File(Properties.historyResourcefoler);
		if (actual.exists()) {
			for (File f : actual.listFiles()) {
				if (f.getAbsolutePath().toLowerCase().endsWith(".jpg")
						|| f.getAbsolutePath().toLowerCase().endsWith(".png")) {
					top.add(new DefaultMutableTreeNode(f.getName()));
				}
			}
		}

	}

}
