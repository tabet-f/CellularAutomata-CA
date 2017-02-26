import java.awt.Color;
import java.awt.Graphics;

/*********************************************************************************************              													 * 
 * Created By:  FADI TABET 								     *											 	         *
 * CLASS PURPOSE: This class contains the graphics settings of CA Canvas		     *		     *
 *********************************************************************************************/

public class PaintCanvas extends UpdateCanvas {
	
    private static final long serialVersionUID = 1;
    
    private CAGeneration cag;   //Instance of Class CAGeneration
    private int cells;   	    //Number of Cells or Grid Size
    private int cellSize; 	  	//Cell Size
    private int boardBorderX; 	//Mouse Selection Grid Distance from JFrame Window Border

    //Constructor
    public PaintCanvas(CAGeneration newcageneration) {
        this.cag = newcageneration;
    }

    //CANVAS Paint Method
    @Override
    public void paint(Graphics g) {
        this.cells = this.cag.getGridSize(); //Get the grid size or number of cells defined in class CAGeneration 
        this.cellSize = Math.max(this.getWidth() / this.cells, 1); //Return the maximum value between the 2 parameters given
        this.boardBorderX = (this.getWidth() - this.cells * this.cellSize) / 2; //Calculate the distance to the JFrame Border & used to make the CANVAS GRID responsive when minimizing or maximizing the JFrame window
        
        int verticalCells = this.getHeight() / this.cellSize; //Calculate CANVAS Height
        int startTime = Math.max(this.cag.getTime() - verticalCells, 0); //Return the max between (time - height) & 0
        //startTime is used to auto scroll down the simulation when it fills the windows height
        
        int i = 0; //counter
      //while 'i' is < Number of Cells ---> used to iterate through CANVAS cells 
        while (i < this.cells) {
            int j = 0;
          //Iterate through cells with 0's, 1's and 2's and set their colors to depending on their value -->, WHITE for 0, GRARY for 1 and BLACK for 2
            while (j < verticalCells) {
                if (this.cag.getState(i, startTime + j) == 1){ 
                    g.setColor(Color.GRAY);
                }
                else if (this.cag.getState(i, startTime + j) == 2){  
                    g.setColor(Color.BLACK);
                }
                else if (this.cag.getState(i, startTime + j) == 0){
                    g.setColor(Color.WHITE);
                }
                //Graphics fill Rectangle (cell) takes (x-coordinates, y-coordinates, width, height)
                g.fillRect(this.boardBorderX + i * this.cellSize, j * this.cellSize, this.cellSize, this.cellSize);
                ++j;
            }//end of if
            ++i;
        }//end of while
        
        //Draw Border for CANVAS GRID if cellSize is Greater than 3 otherwise there will be NO grid
        if (this.cellSize > 3) {
            g.setColor(Color.BLACK);
            i = 0;
            //while 'i' < gridsize which is 73 for now as defined in CAGeneration Constructor
            while (i <= this.cells) {
            	//Draw VERTICAL LINES BETWEEN CANVAS CELLS TO DIVIDE THEM LIKE A GRID
            	//and takes as parameter (1st point x-coordinates, 1st point y-coordinates, 2nd pt x-coordinates, 2nd pt y-coordinates)
                g.drawLine(this.boardBorderX + i * this.cellSize, 0, this.boardBorderX + i * this.cellSize, verticalCells * this.cellSize);
                ++i;
            }
            i = 0;
            //while 'i' <= to CANVAS HEIGH
            while (i <= verticalCells) {
            	//Draw HORIZENTAL LINES BETWEEN CANVAS CELLS TO COMPLETE THE SHAPE OF THE GRID
                g.drawLine(this.boardBorderX, i * this.cellSize, this.boardBorderX + this.cells * this.cellSize, i * this.cellSize);
                ++i;
            }
        }
    }
}

