package ch.zhaw.ocr;

import java.awt.image.BufferedImage;

/**
 * ContrastMatrix
 * @author adrian
 *
 */
public class ContrastMatrix {
	private int[][] contrastMatrix;
	private int width;
	private int height;
	
	/**
	 * Initialise an empty ContrastMatrix using the given height + width
	 * @param width
	 * @param height
	 */
	public ContrastMatrix(int width, int height){
		contrastMatrix = new int[width][height];
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Return the string representative of the current object
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int y = 0;y < contrastMatrix[0].length;y++){
			for(int x = 0;x < contrastMatrix.length;x++){
				sb.append(contrastMatrix[x][y] + ((x!=contrastMatrix.length-1) ? " " : ""));
			}
			sb.append(y!=contrastMatrix[0].length ? System.getProperty("line.separator") : "");
		}
		return sb.toString();
	}
	
	/**
	 * Get a value
	 * @param x X-Coordinate
	 * @param y Y-Coordinate
	 * @return value value of the field
	 */
	public int getValue(int x, int y){
		return contrastMatrix[x][y];
	}
	
	/**
	 * Set a matrix value
	 * @param x X-Coordinate
	 * @param y Y-Coordinate
	 * @param value value to set
	 */
	public void setValue(int x, int y, int value){
		contrastMatrix[x][y] = value;
	}
	
	/**
	 * Delete a column from the matrix
	 * @param colNo column to delete
	 */
	public void removeCol(int colNo){
		int[][] newMatrix = new int[width-1][height];
		for(int x = 0;x < width;x++){
			for(int y = 0;y < height;y++){
				if(x != colNo){
					newMatrix[x][y] = contrastMatrix[x][y];
				}
			}
		}
		this.contrastMatrix = newMatrix;
	}
	
	/**
	 * Delete a row from the matrix
	 * @param rowNo row to delete
	 */
	public void removeRow(int rowNo){
		int[][] newMatrix = new int[width][height-1];
		for(int x = 0;x < width;x++){
			for(int y = 0;y < height;y++){
				if(y != rowNo){
					newMatrix[x][y] = contrastMatrix[x][y];
				}
			}
		}
		this.contrastMatrix = newMatrix;
	}
	
	/**
	 * Get a sub matrix
	 * @param offsetX
	 * @param offsetY
	 * @param width
	 * @param height
	 * @return
	 */
	public ContrastMatrix getSubMatrix(int offsetX, int offsetY, int width, int height){
		//avoid array erros ... TODO: exception?
		if((offsetX+width) > this.width && (offsetY+height) > this.height){
			return null;
		}
		
		ContrastMatrix rv = new ContrastMatrix(width, height);
		
		//copy values
		for(int x = offsetX;x < (offsetX+width);x++){
			for(int y = offsetY;y < (offsetY+height);y++){
				rv.setValue(x, y, getValue(offsetX+x, offsetY+y));
			}
		}
		
		return rv;
	}
	
	/**
	 * Parses an image
	 * @param image the image to be parsed
	 * @return ContrastMatrix representing the image
	 */
	public static ContrastMatrix parseImage(BufferedImage image){
		ContrastMatrix rv = new ContrastMatrix(image.getWidth(), image.getHeight());
		
		int rgb = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for(int y = 0;y < image.getHeight();y++){
			for(int x = 0;x < image.getWidth();x++){
				rgb = image.getRGB(x, y);
				
				red = (rgb >> 16) & 0x000000FF;
				green = (rgb >>8 ) & 0x000000FF;
				blue = (rgb) & 0x000000FF;
				
				//average rgb value < 200 = 1
				if((red+blue+green)/3 < 200){
					rv.setValue(x, y, 1);
				}else{
					rv.setValue(x, y, 0);
				}
			}
		}
		
		return rv;
	}
	

	
}
