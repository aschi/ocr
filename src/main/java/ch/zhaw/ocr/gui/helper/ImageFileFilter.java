package ch.zhaw.ocr.gui.helper;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * FileFilter used for image open dialog
 * @author Corinne Zeugin, Priscilla Schneider, Adrian Schmid
 */
public class ImageFileFilter extends FileFilter {  
    public boolean accept(File f) {  
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".jpeg")
        || f.getName().toLowerCase().endsWith(".bmp") || f.getName().toLowerCase().endsWith("wbmp") ||
        f.getName().toLowerCase().endsWith(".gif") || f.getName().toLowerCase().endsWith("png");
    }  
      
    public String getDescription() {  
        return "Only Images (.jpg, .jpeg, .bmp, .wbmp, .gif, .png)";  
    }   
} 