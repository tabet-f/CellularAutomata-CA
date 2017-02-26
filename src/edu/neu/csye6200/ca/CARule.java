/*********************************************************************************************              													 * 
 * Created By:  FADI TABET 							             *									 	     *
 * CLASS PURPOSE: This class contains CARule -> creates the rules and related variables      *
 *********************************************************************************************/

public class CARule {

	private int[] rules;					 // Rule Array 
	int[] newRules = new int[7];		     //0 to 6 there are 3^7 rules = 2187 rules in 3 states CA
	private int ruleNumber; 				 // The Rule Number Entered By The User
	private int base = 3; 				     // 3 color states use 2187 rules of base 3^7
	
	 
	//Constructor 
	public CARule() {
	}

	/**Removed Note**/
	//Calculate & Populate The Rule Array ex {01011010} From Rule Number Gotten From The User 
    public void createRule(int number) {
    	     
    	this.ruleNumber = number;
    	    //Switch between rules
    	     switch(this.ruleNumber){
	         	case 600:   //Rule 600
	         		newRules= new int[] {0,2,1,1,0,2,0};
	         		this.rules = newRules; //set the new rule
	         		break;
	         	case 777:   //Rule 777
	         		newRules= new int[] {1,0,0,1,2,1,0}; 
	         		this.rules = newRules;
	         		break;
	         	case 993:   //Rule 993
	         		newRules= new int[] {1,1,0,0,2,1,0}; 
	         		this.rules = newRules;
	         		break;
	         	case 1020:  //Rule 1020
	         		newRules= new int[] {1,1,0,1,2,1,0};
	         		this.rules = newRules;
	         		break;
	         	case 1074: //Rule 1074
	         		newRules= new int[] {1,1,1,0,2,1,0};
	         		this.rules = newRules;
	         		break;
	         	case 1083: //Rule 1083
	         		newRules= new int[] {1,1,1,1,0,1,0};
	         		this.rules = newRules;
	         		break;
    	     }
    	}  

    /** 
     * public void createRule(int number) {
    	
        this.ruleNumber = number;
        int possibleNeighborhoodStates = 8; //Rule Array Size {xxxxxxxx}
        int val = number; 
        int[] newRules = new int[possibleNeighborhoodStates]; //Rule Array 8 Indexes from 0 to 7
        int j = 0; //Simple Counter
        
        while (j < possibleNeighborhoodStates) {//While j < 8, iterate through the rule array indexes and set either 0 or 1 to each index
            if ((double) val >= Math.pow(this.base, possibleNeighborhoodStates - j - 1)) { //if input rule >= 128 or less depending on j
                    newRules[possibleNeighborhoodStates - j - 1] = 1; //new rules index either will be = one
                    val = (int)((double) val - Math.pow(this.base, possibleNeighborhoodStates - j - 1));
                } else {
                    newRules[possibleNeighborhoodStates - j - 1] = 0; // or zero
                }
                ++j;
            }
            this.setRules(newRules); //set the new created rule into rules array set method
      }  
     *
     */
    
    
    //Getters & Setters
    public int getRuleNumber() {
		return ruleNumber;
	}

	public void setRuleNumber(int ruleNumber) {
		this.ruleNumber = ruleNumber;
	}

	public int[] getRules() {
		return rules;
	}

	public int getBase() {
		return base;
	}

}
