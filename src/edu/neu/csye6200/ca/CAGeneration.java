package edu.neu.csye6200.ca;

import java.util.ArrayList;
import java.util.Random;

/*********************************************************************************************                                       													 * 
 * Created By:  FADI TABET 														 	     *
 * CLASS PURPOSE: This class contains Cell, CACell and CAGenerationset & their Methods       *
 *********************************************************************************************/


public class CAGeneration {
	
	private CARule cr;							//Instance of CARule Class
    private Random rnd = new Random(); 			//Used to generate random numbers
    private int cell; 				   		    //cells grid size
    private int[] CACell; 			   			//Array of Cells
    private ArrayList<int[]> CAGenerationSet;   //ArrayList of CACell's
    private int time;   					    //Time 
   
	
    //Constructor
    public CAGeneration() {
    	this.cr = new CARule();
    	this.init(600, 73); //Values for Initial Run (Rule Number, Grid Size or Number of Cells)
    }

    //Initialization Method
    private void init(int rule, int gridSize) {
        this.cell = gridSize;
        this.cr.setRuleNumber(rule);
        this.cr.createRule(rule);
        this.clear();
    }

    //Reset Method
    public void clear() {
        this.CACell = new int[this.cell];
        this.time = 0;
        this.CAGenerationSet = new ArrayList<int[]>();
        this.clearStates();
    }

    //Clear Method
    private void clearStates() {
        this.CAGenerationSet.clear();
        int i = 0;
        while (i < this.cell) { //Set all cells to 0
            this.CACell[i] = 0;
            ++i;
        }
        this.CACell[this.cell / 2] = 1; //Set Only the Middle Cell to 1 (Black)
        this.CAGenerationSet.add(this.CACell);
        this.time = 0;
    }

    //Set Default Middle Cell
    public void setDefaultInitialState() {
        this.clear();
        int i = 0;
        while (i < this.cell) { //set all cells to zero (White)
            this.CACell[i] = 0;
            ++i;
        }
        this.CACell[this.cell / 2] = 1; //set ONLY the middle cell to 1 (gridsize/2)
        this.CAGenerationSet.clear();
        this.CAGenerationSet.add(this.CACell);
    }

    //Generate a Random Initial Generation
    public void setRandomInitialState() {
        this.clear();
        int i = 0;
        while (i < this.cell) {
            this.CACell[i] = Math.abs(this.rnd.nextInt()) % 2; //absolute value of the next random then do mod 2 and push it to the index of CACell
            ++i;
        }
        this.CAGenerationSet.clear();
        this.CAGenerationSet.add(this.CACell);
    }

    //Flip Generation Method
    public void flipState(int i) {
        if (i >= 0 && i < this.cell) {//if zero & less than gridsize
            this.CACell[i] = this.CACell[i] == 0 ? 1 : 0; //(if true 1 else 0)
        }
        this.CAGenerationSet.clear();
        this.CAGenerationSet.add(this.CACell);
        this.time = 0;
    }
    
    //Get Generation from GenerationSet Method
    public int getState(int g, int i) {
        if (i < this.CAGenerationSet.size()) {
            return this.CAGenerationSet.get(i)[g];
        }
        return 0;
    }

    //Create The Next Generation Based on The Previous One
    public void nextState() {
        int[] neighbors = new int[3]; //Cell Radius = 3
        int[] newStates = new int[this.cell]; // New Generation Cells Array
        int i = 0;
        while (i < this.cell) {
        	
            int j = 0;
            while (j < 3) {
                int wrappedPosition = i -1 + j;
                
                if (wrappedPosition < 0) { //if negative
                    wrappedPosition += this.cell; // Add it to wrappedPosition
                   
                }
                if (wrappedPosition >= this.cell) { //if positive or =
                    wrappedPosition -= this.cell; // Subtract it from wrapedPosition
                    
                }
                neighbors[2 - j] = this.CACell[wrappedPosition];
                 ++j;
            }//inner while ends
            
            int index = 0;
            j = 2; // for 0,1,2
            double x = (Math.pow(this.cr.getBase(), j)-7); //-7 to avoid exceeding the array index size of 7
            while (j >= 0) {
                index = (int)((double)(index) + x * (double)1-neighbors[j]);            
                --j;
            }
            newStates[i] = this.cr.getRules()[index]; //set the new cell value either 0, 1 or 2 depending on the content of getRules()[index]
            ++i;
        }//outer while ends
        this.CACell = newStates; 
        this.CAGenerationSet.add(this.CACell);
        ++this.time;
    }
 
    //Method that recall another method in CARule class (Used in AppGUI.java)
    public void callcreateRule(int n){
    	this.clear();
    	this.cr.createRule(n);
    }
    
    public int callgetRuleNumber(){
    	return this.cr.getRuleNumber();
    }
  
    
    //Getters & Setters
    public int getTime() {
        return this.time;
    }

    public void setGridSize(int newGridSize) {
        this.init(this.cr.getRuleNumber(), newGridSize);
    }

    public int getGridSize() {
        return this.cell;
    }

    
}

