package edu.neu.csye6200.ca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/*********************************************************************************************              													 * 
 * Created By: FADI TABET 														 	         *
 * CLASS PURPOSE: This class contains SWING settings and method calls other classes          *
 *********************************************************************************************/

public class AppGUI extends JFrame {
   
	private static final long serialVersionUID = 1;
	
	private CAGeneration CAGeneration;
	private PaintMouseGrid PaintMouseGrid;
	private PaintCanvas PaintCanvas;
	
    private JPanel caPanel;
    private JPanel mainbarPanel;
    private JPanel controlPanel;
    private JPanel rulePanel;
    
    private JLabel ca1DStateLabel;
    private JLabel caLabel;
    private JLabel setNewRuleLabel;
    private JLabel ruleLabel;
    private JLabel generationLabel;
    private JLabel linklabel;
    
    private JButton nextButton;
    private JButton startStopButton;
    private JButton clearButton;
    private JButton DefaultCellButton;
    private JButton RandomCellsButton;
    private JButton setRuleButton;
    private JButton imageLink;
    private JTextField generationinputtxt;
    
    @SuppressWarnings("rawtypes")
	private JComboBox RuleCombox;
    
    private Timer timer;
    private int speed;
    private boolean running;
    private int currentime;
    private boolean checkinput = false;
    
    
    public static void main(String[] args) {
    	//Creating JFRAME (Inheritance From JFrame Parent Class)
        AppGUI mainFrame = new AppGUI(); 
        mainFrame.setVisible(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public AppGUI() {
    	//JFrame Properties
    	this.setSize(900, 650);
        this.setTitle("Totalistic 1 Dimensional CA with 3 Colors & 1 Neighborhood - Developed by  FADI TABET");
        this.setLayout(new BorderLayout());
        
        //Instance
        this.CAGeneration = new CAGeneration();
        
        //Creating Panels & setLayouts
        this.mainbarPanel = new JPanel();
        this.mainbarPanel.setLayout(new GridLayout(2, 2));//(2,2) Divides Panels on Two Lines
        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new FlowLayout());
        this.rulePanel = new JPanel();
        this.rulePanel.setLayout(new FlowLayout());
        this.caPanel = new JPanel();
        this.caPanel.setLayout(new FlowLayout());
        
        //Creating JButtons
        this.nextButton = new JButton("1 Step");
        this.startStopButton = new JButton("Start");
        this.clearButton = new JButton("Clear");
        this.DefaultCellButton = new JButton("Default Cell");
        this.RandomCellsButton = new JButton("Random Cells");
        
        //Control Panel
        this.generationinputtxt = new JTextField(4);
        this.generationLabel = new JLabel("(Optional) Number of Gen:");
        this.controlPanel.add(this.generationLabel);
        this.controlPanel.add(this.generationinputtxt);
        this.controlPanel.add(this.startStopButton);
        this.controlPanel.add(this.clearButton);
        this.controlPanel.add(this.nextButton);        
        this.controlPanel.add(this.DefaultCellButton);
        this.controlPanel.add(this.RandomCellsButton);
        this.controlPanel.add(this.RandomCellsButton);
        this.mainbarPanel.add(this.controlPanel);
        
        //Rule Panel
        //JCombox Settings & Styling
        this.RuleCombox= new JComboBox();
        this.RuleCombox.setPreferredSize(new Dimension(75,25));
        this.RuleCombox.setMaximumSize(RuleCombox.getPreferredSize());
        // disable lightweightpopupenabled and use heavy weight component instead to avoid mixing between light and weight components
        this.RuleCombox.setLightWeightPopupEnabled(false); // this will restrict the jcombox to goes under the canvas 
        this.RuleCombox.setBackground(Color.WHITE);
        this.RuleCombox.setForeground(Color.BLUE);
        
        //JCombox Rule Numbers
        this.RuleCombox.addItem("600");
        this.RuleCombox.addItem("777");
        this.RuleCombox.addItem("993");
        this.RuleCombox.addItem("1020");
        this.RuleCombox.addItem("1074");
        this.RuleCombox.addItem("1083");
        
        this.setNewRuleLabel = new JLabel("Choose Rule Number");
        this.setRuleButton = new JButton("Set Rule");
        this.ruleLabel = new JLabel(" (Current Rule is:  " + Integer.toString(this.CAGeneration.callgetRuleNumber()) + ")");
        this.rulePanel.add(this.setNewRuleLabel);
        this.rulePanel.add(RuleCombox);
        this.rulePanel.add(this.setRuleButton);
        this.ruleLabel.setForeground(Color.RED);
        this.rulePanel.add(this.ruleLabel);
        this.mainbarPanel.add(this.rulePanel);
        //Adding Rule & Control Panel to the MainBarPanel
        this.add((Component)this.mainbarPanel, BorderLayout.NORTH);
        
        //caPanel
        this.ca1DStateLabel = new JLabel("Mouse Selection Grid for Initial State");
       
        this.caPanel.add(this.ca1DStateLabel);
        this.PaintMouseGrid = new PaintMouseGrid(this.CAGeneration);
        this.caPanel.add(this.PaintMouseGrid);
        this.caLabel = new JLabel("Totalistic 1 Dimensional CA with 3 Colors & 1 Neighborhood");
        this.caPanel.add(this.caLabel);
        this.PaintCanvas = new PaintCanvas(this.CAGeneration);
        this.caPanel.add(this.PaintCanvas);
        this.imageLink = new JButton("See Rules & Graphics");
        this.imageLink.setBackground(Color.RED);
        this.imageLink.setForeground(Color.WHITE);
        this.linklabel = new JLabel("<html><body><a href=\"http://mathworld.wolfram.com/TotalisticCellularAutomaton.html\">Click for Reference Link</a></body></html>");
        this.linklabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.caPanel.add(imageLink);
        this.caPanel.add(linklabel);
        this.add((Component)this.caPanel, BorderLayout.CENTER);
        this.resize();

        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                AppGUI.this.resize();
            }
        });
        ActionListener buttonListener = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                String action = ae.getActionCommand();
                if (action.equals("1 Step")) {
                    AppGUI.this.step();
                } else if (action.equals("Start")) {
                    AppGUI.this.start();
                } else if (action.equals("Stop")) {
                    AppGUI.this.stop();
                } else if (action.equals("Set Rule")) {
                    AppGUI.this.setRule();
                } else if (action.equals("Clear")) {
                    AppGUI.this.clearAutomaton();
                } else if (action.equals("Default Cell")) {
                    AppGUI.this.setDefaultInitialState();
                } else if (action.equals("Random Cells")) {
                    AppGUI.this.setRandomInitialState();
                } 
             }
        };
        
        //Image Hyperlink Listener
        this.imageLink.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              try {
          		java.awt.Desktop.getDesktop().open(new java.io.File("CARules&Graphics.png"));
          	} catch (IOException e1) {
          		
          		e1.printStackTrace();
          	}
            }
        });
        
        //Link Label Listener
        this.linklabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() > 0) {
                  // for Windows OS
                  try {
 					Runtime.getRuntime().exec("cmd.exe /c start http://mathworld.wolfram.com/TotalisticCellularAutomaton.html");
 				} catch (IOException e1) {
 					e1.printStackTrace();
 				}
               }
            }
         });
        
        //Add Button's Listeners
        this.nextButton.addActionListener(buttonListener);
        this.startStopButton.addActionListener(buttonListener);
        this.clearButton.addActionListener(buttonListener);
        this.setRuleButton.addActionListener(buttonListener);
        this.DefaultCellButton.addActionListener(buttonListener);
        this.RandomCellsButton.addActionListener(buttonListener);
       
        //Mouse Listener 
        MouseListener mouseListener = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                int cellIndex = AppGUI.this.PaintMouseGrid.getCellFromCoordinates(e.getX(), e.getY()); //get selected cell coordinates
                AppGUI.this.CAGeneration.flipState(cellIndex);
                AppGUI.this.update();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        };
        
        this.PaintMouseGrid.addMouseListener(mouseListener);
        
        //Timer Listener
        ActionListener timerListener = new ActionListener(){
        	
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	
            	if(checkinput==true){
            		int value = Integer.valueOf(generationinputtxt.getText().trim());  
            		//The First Input Iteration OR The Next Input IterationS
            		//To Stop the Timer at the Input JTextfield
            		if((CAGeneration.getTime()+2)==(value) || (CAGeneration.getTime()+1)==(value+currentime)){	
            		timer.stop();
            		running=false;
            		startStopButton.setText("Start");
            		startStopButton.setActionCommand("Start");
            		generationinputtxt.setText("");	
            		}	
            	}
                AppGUI.this.CAGeneration.nextState();
                AppGUI.this.update();
            }
        };
        
        this.speed = 120;//Timer Delay Speed in ms
        this.timer = new Timer(this.speed, timerListener);//Creating a SWING Timer 
        this.running = false;//Flag for Toggle Stop/Start Button
    }
    
    public void actionPerformed(ActionEvent e){
    	timer.stop();
    }

    //Start Button Method (for both with input (Generation TextField) OR without input just click on start button)
    public void start() {
        if (!this.running) {
        	//If no input in the JTextField
        	if(isGenInput()==false){
        	this.checkinput=false;
            this.running = true;
            this.startStopButton.setText("Stop");
            this.startStopButton.setActionCommand("Stop");
            this.timer.start();
            //used to stop the timer when there's a generation input
            currentime=this.CAGeneration.getTime();   
        	}
        	//If there's an input in the JTextField
        	else 
             try{    
            	    int x = Integer.parseInt(this.generationinputtxt.getText().trim());
        			if(x>0){//if generation input is greater than zero 
        			this.checkinput=true;
        			this.running = true;
        			this.startStopButton.setText("Stop");
                    this.startStopButton.setActionCommand("Stop");
                    this.timer.start();
                    currentime=this.CAGeneration.getTime();   
        			} 
        			else
        			JOptionPane.showMessageDialog(null,"Please Enter a Number Greater Than ZERO","Only Positive Numbers", JOptionPane.WARNING_MESSAGE); 
        		}//end of Try Block
        		
        	catch (NumberFormatException ex)
        	{
        	JOptionPane.showMessageDialog(null,"Please Enter a Valid Number","Invalid Number", JOptionPane.ERROR_MESSAGE); 
            }//End of Catch Block
        }//End of running if
    }//End of start()   	
            
   
    //set the flag to TRUE if generation input JTextfield has a value (!empty||!Null) 
    public boolean isGenInput(){
    	String in = this.generationinputtxt.getText().trim();   
    	if(in.equals(null)||in.isEmpty()){
    		return false;
    	}
    	else
    	return true;
    }
    
    //Stop Button Method
    public void stop() {
        if (this.running) {
            this.running = false;
            this.startStopButton.setText("Start");
            this.startStopButton.setActionCommand("Start");
            this.timer.stop();
        }
    }

    //One Step Button Method
    public void step() {
        if (!this.running) {
            this.CAGeneration.nextState();//Create ONE generation
            this.update();
        }
    }

    //Update Canvas Methods (Call the repaint Methods to repaint Canvas & Mouse Selection Grid)
    //Repaint is a method defined already in class CANVAS that we inherit from in class update
    private void update() {
        this.PaintCanvas.repaint();
        this.PaintMouseGrid.repaint();
        this.resize();
    }

    //Canvas Resize Method
    private void resize() {
        this.PaintMouseGrid.setSize(this.getWidth() - 10, this.PaintMouseGrid.getMinimumHeight(this.getWidth() - 10));
        this.PaintCanvas.setSize(this.getWidth() - 10, this.getHeight() - 250);
        this.setVisible(true);
    }

    //Clear Button Grid Method
    private void clearAutomaton() {
        this.CAGeneration.clear();
        this.update();
    }

    //Default State Button Method 
    private void setDefaultInitialState() {
        this.CAGeneration.setDefaultInitialState();
        this.update();
    }
    
    //Random State Button Method
    private void setRandomInitialState() {
        this.CAGeneration.setRandomInitialState();
        this.update();
    }
    
    //Set Rule Button Method 
    private void setRule() {
        try {
        	
            int newRule = Integer.valueOf((String)this.RuleCombox.getSelectedItem()); //Get the value from the combo box
            
            //Hashset of integers contains the range of valid rules defined in createRule() inside CARule class
            Set<Integer> validRules = new HashSet<Integer>();
            validRules.add(600);
            validRules.add(777);
            validRules.add(993);
            validRules.add(1020);
            validRules.add(1074);
            validRules.add(1083);
            
            //check if the input is valid (almost no need for this check but it will make it more secure)
            if (validRules.contains(newRule)) {
            	//Display a popup message if the newrule is already set
            	if (newRule==this.CAGeneration.callgetRuleNumber()){	
                JOptionPane.showMessageDialog(null,"Rule Selected is the Current Rule!","Rule Already Set", JOptionPane.WARNING_MESSAGE); 
            	}
            	//else send the input to createrule method in class CARULE to get the rule from there
            	else{
                this.CAGeneration.callcreateRule(newRule); 
                this.ruleLabel.setText(" (Current Rule is:  " + newRule + ")");
                this.update();
            	}
            }
             
        }
        catch (Exception e) {
        	//the execution will not reach the catch (because of the above validation and nature of input which is a comboxbox and
        	//and not a textfield) but in case an error happened the catch will be executed
        	JOptionPane.showMessageDialog(null,e.getMessage(),"Sorry Unexpected Error Happened", JOptionPane.ERROR_MESSAGE);
        }
    }

}

