/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {
    final int frameCount = 10;
    final int directionCount = 8;
    int picNum = 0;
    BufferedImage[][] pics;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    int xLoc;
    int yLoc;
    int direction;
    JFrame frame;
    
    public View() {
	loadImages();
	frame = new JFrame();
	frame.getContentPane().add(this);
	frame.setBackground(Color.gray);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(frameWidth, frameHeight);
	frame.setVisible(true);
    }

    public void update(int x, int y, int d) {
	this.xLoc = x;
	this.yLoc = y;
	this.direction = d;
	frame.repaint();
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}	
    }

    public int getWidth(){
	return this.frameWidth;
    }

    public int getHeight(){
	return this.frameHeight;
    }

    public int getImageWidth(){
	return imgWidth;
    }

    public int getImageHeight(){
	return this.imgHeight;
    }
    
    @Override
    public void paint(Graphics g) {
	picNum = (picNum + 1) % frameCount;
	g.drawImage(pics[direction][picNum], xLoc, yLoc, Color.gray, this);
    }

     // Get images, segment and store in array
    public void loadImages() {
	pics = new BufferedImage[directionCount][frameCount];
	for (int j = 0; j < directionCount; j++) {
	    BufferedImage img = createImage(j);
	    for (int i = 0; i < frameCount; i++)
		pics[j][i] = img.getSubimage(imgWidth * i, 0, imgWidth, imgHeight);
	}
    }
    
    // Take a number and converts it to a String direction
    private String numToDir(int num) {
	return Direction.values()[num].getName();
    }

    // Read image from file and return
    private BufferedImage createImage(int direction) {
	BufferedImage bufferedImage;
	try {
	    //System.out.println("images/orc/orc_forward_" + numToDir(direction) + ".png");
	    bufferedImage = ImageIO.read(new File("images/orc/orc_forward_" + numToDir(direction)
						  + ".png"));
	    return bufferedImage;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;	
    }
}
