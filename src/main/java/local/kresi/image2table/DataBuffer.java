package local.kresi.image2table;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * writes a list with given hex codes to a html-file
 * @author kbaga
 */
public class DataBuffer {

    // variables declaration
    private File saveFile;
    private Document xmlDocument;
    private Element rootElement;
    private ArrayList<String> pixList;
    private int width;

    /**
     * constructor
     * @param pixList   list with hex codes
     * @param width     width of the picture
     */
    public DataBuffer(ArrayList<String> pixList, int width, File saveFile) {
        this.saveFile = saveFile;
    	this.pixList = pixList;
    	this.width = width;
    	saveToHtml();
    }

    /**
     * saves the list to a html file
     */
    public void saveToHtml() {
        rootElement = new Element("html");
        xmlDocument = new Document();
        int height = (pixList.size()/width);

        Format xmlFormat = Format.getPrettyFormat();
//        xmlFormat.setEncoding("ISO-8859-1");
//        xmlFormat.setOmitEncoding(false);
        xmlFormat.setOmitDeclaration(true);

        XMLOutputter outputter = new XMLOutputter(xmlFormat);

        //html structure
        Element headElement = new Element("head");
        Element styleElement = new Element("style");
        styleElement.setText("table {width: " + width + "; height: " + height +
                "; border: none; border-collapse: collapse; cellspacing: 0; "
                + "cellpadding: 0;} td {width: 1px; height: 1px;}");
        Element bodyElement = new Element("body");
        Element tableElement = new Element("table");

        headElement.addContent(styleElement);
        bodyElement.addContent(tableElement);
        rootElement.addContent(headElement).addContent(bodyElement);

        try {
        	for (int i=0;i<height;i++) {

        		Element tr = new Element("tr");

        		for (int j=0;j<width;j++) {

        			Element td = new Element("td");
        			Attribute bgcolor = new Attribute("style", "background:#"
        					+ pixList.get(i*width+j).substring(2) + ";");
        			td.setAttribute(bgcolor);
        			tr.addContent(td);
        		}

        		tableElement.addContent(tr);

        	}

            xmlDocument.setRootElement(rootElement);
            outputter.output(xmlDocument, new FileOutputStream(saveFile));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Write Error!",
                    "Error!",JOptionPane.ERROR_MESSAGE);
        }
    }

}