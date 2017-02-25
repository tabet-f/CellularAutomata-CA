package edu.neu.csye6200.ca;


import java.awt.Color;
import java.awt.Graphics;

/********************************************************************************************* 
 * Created By:  FADI TABET 														 	         *
 * CLASS PURPOSE: This class contains the graphics settings of Mouse Selection Grid          *
 *********************************************************************************************/

public class PaintMouseGrid extends UpdateCanvas {
	
    private static final long serialVersionUID = 1;
    
    private CAGeneration cag; //Instance of CAGeneration Class
    private int cells;   	  //Number of Cells or Grid Size
    private int cellSize; 	  //Cell Size
    private int boardBorderX; //Mouse Selection Grid Distance from JFrame Window Border

    //Constructor
    public PaintMouseGrid(CAGeneration newcageneration) {
        this.cag = newcageneration;
    }

    //MOUSE SELECTION GRID Paint Method
    @Override
    public void paint(Graphics g) {
        this.cells = this.cag.getGridSize(); //get the grid size or number of cells defined in class CAGeneration
        this.cellSize = this.getWidth() / this.cells; //cellsize = Frame Window Width / Number of cells in one row
        this.boardBorderX = (this.getWidth() - this.cells * this.cellSize) / 2; //Calculate the distance to the JFrame Border & used to make the MOUSE SELECTION GRID responsive minimizing or maximizing the JFrame window
       
        int i = 0; //counter
        //while 'i' is < Number of Cells ---> used to iterate through mouse selection grid cells
        while (i < this.cells) {
            if (this.cag.getState(i, 0) == 1) {
            	//set cells with ONE to RED and Others that are unfilled to WHITE
            	g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            //Graphics fill Rectangle (cell) takes (x-coordinates, y-coordinates, width, height)
            g.fillRect(this.boardBorderX + i * this.cellSize, 1, this.cellSize, this.cellSize);
            ++i;
        }//end of while
        
        //Draw Border for Mouse Selection if cellSize is Greater than 3 otherwise there will be NO grid 
        if (this.cellSize > 3) { 
        	//set mouse grid border color to BLACK 
            g.setColor(Color.BLACK);
            i = 0; 
            //Draw Vertical Border Between Cells in MOUSE SELECTION GRID
            while (i <= this.cells) { //while 'i' < gridsize which is 73 for now as defined in CAGeneration Constructor
            	//Draw VERTICAL LINES between THE CELLS TO DIVIDE THEM LIKE A GRID
            	//and takes as parameter (1st point x-coordinates, 1st point y-coordinates, 2nd pt x-coordinates, 2nd pt y-coordinates)
                g.drawLine(this.boardBorderX + i * this.cellSize, 0, this.boardBorderX + i * this.cellSize, this.cellSize);
                ++i;
            }//end of while
            //Draw Upper Horizontal border line in MOUSE SELECTION GRID
            g.drawLine(this.boardBorderX, 0, this.boardBorderX + this.cells * this.cellSize, 0); 
          //Draw Lower Horizontal border line in MOUSE SELECTION GRID
            g.drawLine(this.boardBorderX, this.cellSize, this.boardBorderX + this.cells * this.cellSize, this.cellSize); 
        }//end of if
    }

    //Get The Coordinates of Selected Cell in Mouse Selection Grid
    public int getCellFromCoordinates(int x, int y) {
        if ( (x > this.boardBorderX ) && (x < this.cells * this.cellSize) && (y > 1) && (y < this.cellSize)) {
            return (x - this.boardBorderX) / this.cellSize;
        }
        //else return -1 if failed
        return -1; 
    }
    
    //Calculate Height Ratio which is used in AppGUI.resize() 
    public int getMinimumHeight(int width) {
        return width / this.cag.getGridSize() + 20; // +30 is used as a margin to separate control panels and Mouse grid from CANVAS GRID
    }
}

