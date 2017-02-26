import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

/*********************************************************************************************             													 * 
 * Created By:  FADI TABET 							             *					 	         *
 * CLASS PURPOSE: This class contains the graphics settings to create & update the simulation*
 *********************************************************************************************/

public class UpdateCanvas extends Canvas {
	
	private static final long serialVersionUID = 1;

	//Constructor
    UpdateCanvas() {
    }

    //Update Graphics Method 
    @Override
    public void update(Graphics g) {
        Image screen = null; //Create a graphical images variable called screen
        Dimension d = this.getSize(); //Get the dimension (window Width & window Height) 
        screen = this.createImage(d.width, d.height); //Creates an off-screen drawable image to be used for double buffering to draw the full set of objects on an image and then tell the renderer to draw that entire image instead of drawing these objects one by one.
        Graphics gdc = screen.getGraphics(); //Creates a graphics context for 'screen'.
        gdc.setColor(this.getBackground()); //reset screen background color to white 
        gdc.fillRect(0, 0, d.width, d.height); //Graphics refill Rectangle (cell) takes (x-coordinates, y-coordinates, dim width, dim height)
        this.paint(gdc); //paint the content of gdc (grid of canvas and mouse grid)
        g.drawImage(screen, 0, 0, this); //draws the CA simulation
    }
}

