package local.kresi.image2table;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Writes color hex codes from every pixel from a picture in a arraylist
 * @author kbaga
 */
public class TableMachine {

	// variables declaration
	private BufferedImage myImage;
	private int height;
	private int width;
	private ArrayList<String> pixList;

	/**
	 * main method
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new TableMachine();
	}

	/**
	 * constructor
	 */
	public TableMachine() {
		pixList = new ArrayList<String>();
		readImage();
		convIt();
		saveToXml();
	}

	/**
         * writes every pixel color in the ArrayList pixList
         */
	public void convIt() {
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				int rgbColor = myImage.getRGB(j,i);
				String hexColor = Integer.toHexString(rgbColor);
				pixList.add(hexColor);
				int ga = i + 1;
				int ag = j + 1;
				System.out.println("Pixel " + ga + "*" + ag + " " + hexColor);
			}
		}
	}

        /**
         * saves the list to a xml file ober DataBuffer
         */
	public void saveToXml() {
		new DataBuffer(pixList, width);
	}

	/**
	 * reads the image
	 */
	public void readImage() {
		try {
			myImage = ImageIO.read(new File("pic.png"));
		} catch (IOException e) {
			System.out.println("IOExeption!!!");
		}

		width = myImage.getWidth();
		height = myImage.getHeight();
	}

}
