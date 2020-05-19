import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class AnalyzeBattle {
	public AbilityQueue abilQ;
	int maxHealth;
	
	public static void main(String args[]) {
		try {
			getTxtOnScreen(780, 252, 45, 55, "Images\\in", "Images\\out.JPG");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void analyzeBattle() {
		Node ref = abilQ.getFirst();
		if(healthPerc()<40) {
			while(ref!=null) {
				if(ref.name.equals("Field Medic"))
					ref.setPriority(2);
				else if(ref.type.toLowerCase().contains("heal"))
					ref.setPriority(1);//add one to prio; increament priority of all healing abilities.
			}
		}
	}
	public static String getTxtOnScreen(int x, int y, int wid, int leng, String inputPath, String outputPath) throws AWTException {
		//capture image
		Robot r = new Robot(); 
        // It saves screenshot to desired path 
        File file = new File(inputPath);//for image input
    	File outFile = new File(outputPath);
    	
        BufferedImage specImage;
        Rectangle capture =  
        new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage Image = r.createScreenCapture(capture);
        specImage = Image.getSubimage(x, y, wid, leng);
        try {
			ImageIO.write(specImage, "jpg", file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} //664-191, 379-229
        System.out.println("Screenshot saved"); 
    	//capture image
    	
    	
    	BufferedImage image;
		try {
			image = ImageIO.read(file);
	    	int width = image.getWidth();
	    	int height = image.getHeight();
	    	int blackRGB = new Color(0, 0, 0).getRGB();
	    	int red;
	    	int blue;
	    	int green;
	    	int combine;
	    	
	    	for(int i=0; i<height; i++) {
	    		for(int j=0; j<width; j++) {
	    			Color color = new Color(image.getRGB(j, i));
	    			red = color.getRed();
	    			blue = color.getBlue();
	    			green = color.getGreen();
	    			combine = red+blue+green;
	    			if(combine<700) {
	    				image.setRGB(j, i, blackRGB);
	    			}
	    		}
	    	}
			ImageIO.write(image, "jpg", outFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        Tesseract tesseract = new Tesseract(); 
        try { 
  
            tesseract.setDatapath("Tess4J-3.4.8-src\\Tess4J\\tessdata"); 
  
            // the path of your tess data folder 
            // inside the extracted file 
            String text 
                = tesseract.doOCR(outFile); 
  
            // path of your image file 
            System.out.print(text); 
        } 
        catch (TesseractException e) { 
            e.printStackTrace(); 
        }
        return "sd";
	}
	public int health() {//returns how much health I have
		int x, y, wid, leng;
	}
	public int healthPerc() {//returns health percentage
		int x, y, wid, leng;
		return (int)(checkHealth(x, y, width, length)/maxHealth);
	}
	public int checkMana() {
		int x, y, wid, leng;
	}
	public int checkRage() {
		
	}
	public int checkState() {
		int x, y, wid, leng;
	}
	public Node getAbilities() {
		return abils.getFirst();
	}
}
//low health is considered being below 40% of full health