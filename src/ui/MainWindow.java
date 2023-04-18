package ui;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Controller;

public class MainWindow extends Stage{
	
	public static Controller ct = new Controller();
    @FXML
    private Button exit;
    @FXML
    private RadioButton mealyCheck;

    @FXML
    private TextField numTransitionsText;

    @FXML
    private RadioButton MooreCheck;

    @FXML
    private Button enterTransitionsButoon;

    @FXML
    private Button showButoon;

    @FXML
    private TextField inicialStateText;

    @FXML
    private Button loadDataButoon;

    @FXML
    private TextField statesText;

    @FXML
    private Button isConexButoon;

    @FXML
    private TextArea textForTable;

    @FXML
    private TextField alphabetEntersText;
    
    @FXML
    private TextField aceptationStates;

    @FXML
    private Button conexButoon;

    @FXML
    private Button minimizeButoon;

    @FXML
    private TextField numStatesText;

	    
	    private static MainWindow instance; 
	    public String[] transitions;
	    public int numTransitions;
	    public int numStates;
	    public int[] alfab;
	    public String states[];
	    public String initialState;
	    public int counter = 0;
	    public String statesAceptation[];
	    public String mooree;
	    public String mealyy;
	    
	    public MainWindow() {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow3.fxml"));
				Parent root = loader.load();

				Scene scene = new Scene(root, 700, 700);
				setScene(scene);

				statesText = (TextField) loader.getNamespace().get("statesText");
				textForTable = (TextArea) loader.getNamespace().get("textForTable");
				inicialStateText = (TextField) loader.getNamespace().get("inicialStateText");
				alphabetEntersText = (TextField) loader.getNamespace().get("alphabetEntersText");
				numStatesText = (TextField) loader.getNamespace().get("numStatesText");
				numTransitionsText = (TextField) loader.getNamespace().get("numTransitionsText");
				mealyCheck = (RadioButton) loader.getNamespace().get("mealyCheck");
				MooreCheck = (RadioButton) loader.getNamespace().get("MooreCheck");
				isConexButoon = (Button) loader.getNamespace().get("isConexButoon");
				enterTransitionsButoon = (Button) loader.getNamespace().get("enterTransitionsButoon");
				showButoon = (Button) loader.getNamespace().get("showButoon");
				conexButoon = (Button) loader.getNamespace().get("conexButoon");
				minimizeButoon = (Button) loader.getNamespace().get("minimizeButoon");
				loadDataButoon = (Button) loader.getNamespace().get("loadDataButoon");
				exit = (Button) loader.getNamespace().get("exit");
				aceptationStates = (TextField) loader.getNamespace().get("aceptationStates");
				
				textForTable.setText("Bienvenido, recuerde ingresar los valores separados paor ,. ejemplo a,b,c,d,e");
				alphabetEntersText.setText("0,1");
				numStatesText.setText("2");
				statesText.setText("A,B");
				numTransitionsText.setText("4");
				inicialStateText.setText("A");
				aceptationStates.setText("A");
		        textForTable.setWrapText(true);

				
				init();
			} catch (Exception ex) {

				ex.printStackTrace();
			}

		}
	    
	    public static MainWindow getInstance() {
	        if(instance == null) {
	            instance = new MainWindow();
	        }
	        return instance;
	    }
	    
	    private void init() {
	    	
	    	enterTransitionsButoon.setOnAction(event -> {
				if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
				}else {
	    		EnterTransitions x = new EnterTransitions();
	    		x.show();
	    		this.close();}
			});
	    	
	    	isConexButoon.setOnAction(event -> {
	    		isConex();
	    	});
	    	
	    	loadDataButoon.setOnAction(event -> {
	    		chargeData();
	    	});
	    	
	    	exit.setOnAction(event -> {
	    		System.exit(0);
	    	});
	    	
	    	showButoon.setOnAction(event -> {
		    	if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
				}else {
					if(MooreCheck.isSelected()) {
						String aux = ct.showTable();
						textForTable.setText(aux);
					}else {
						String aux = ct.maylEshowTable();
						textForTable.setText(aux);
					}
	    		}
	    	});
	    	
	    	conexButoon.setOnAction(event -> {
		    	if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
				}else {
					if(MooreCheck.isSelected()) {
						boolean x = ct.isConex();
						
						if(x) {
			    			JOptionPane.showMessageDialog(null,"El automata ya es conexo");
			    			}else {
			    				if(MooreCheck.isSelected()) {
			    					String nic = ct.nowIsConex();
			    					textForTable.setText(nic);
			    				}else {
			    					String nic = ct.maylEnowIsConex();
			    					textForTable.setText(nic);
			    				}

			    			}
					}else {
						boolean x = ct.maylEisConex();
						
						if(x) {
			    			JOptionPane.showMessageDialog(null,"El automata ya es conexo");
			    			}else {
			    				if(MooreCheck.isSelected()) {
			    					String nic = ct.nowIsConex();
			    					textForTable.setText(nic);
			    				}else {
			    					String nic = ct.maylEnowIsConex();
			    					textForTable.setText(nic);
			    				}

			    			}
					}
	    		}
	    	});
	    	
	    	minimizeButoon.setOnAction(event -> {
		    	if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
				}else {
					if(MooreCheck.isSelected()) {
						ArrayList<String> p=ct.partition();
						textForTable.setText(p.toString());}
					else if(mealyCheck.isSelected()){
						ArrayList<String> p=ct.maylEpartition();
						textForTable.setText(p.toString());
					}else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar que tipo de maquina ingreso (Mealy o Moore)");
					}
				}
	    	});
		}
	    
	    public void chargeData() {
	    	if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
				
				JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
			}else {
		    	String alphabe = alphabetEntersText.getText();
	    		
	    		String alfa[] = alphabe.split(",");
	    		
	    		alfab = new int[alfa.length];
	    		
	    		for(int i=0;i<alfa.length; i++) {
	    			if(alfa[i]!=null) {
	    			alfab[i] = Integer.parseInt(alfa[0]);
	    			}
	    		}
	    		
	    		numStates = Integer.parseInt(numStatesText.getText()) ;
	    		String statusAcep = aceptationStates.getText();
	    		statesAceptation = statusAcep.split(",");
	    		String status = statesText.getText();
	    		states = status.split(",");
	    		numTransitions = Integer.parseInt(numTransitionsText.getText());
	    		
	    		transitions = new String[numTransitions];
	    		mooree = MooreCheck.getText();
	    		mealyy = mealyCheck.getText();

	    		initialState = inicialStateText.getText();
	    		 JOptionPane.showMessageDialog(null, "Se cargaron los datos corectamente");
			}
	    }
	    
	    public void loadTransition(String i,String o, String s, String d) {
	    	textForTable.setText(i+"," + o+","  + s+","  + d);
	    	
	    	transitions[counter] = i+"," + o+","  + s+","  + d;
	    	counter++;
	    	for(int k=0; k<transitions.length; k++){
	    	    System.out.print(transitions[k]);
	    	    // output: 25461234
	    	}
	    	System.out.println();
	    	System.out.println("____________________________");
	    }
	    
	    public void createMoore() {
	    	ct.createMooreAtomaton(alfab, numStates, states, transitions, initialState,statesAceptation);
	    }
		
	    public void createMayle() {
	    	ct.createMealyAtomaton(alfab, numStates, states, transitions, initialState,statesAceptation);
	    }
	    
		public void isConex() {
			if(alphabetEntersText.getText().equals("") || numStatesText.getText().equals("") || numTransitionsText.getText().equals("") || statesText.getText().equals("") || inicialStateText.getText().equals("")) {
				
				JOptionPane.showMessageDialog(null, "Rellene todos los espacios antes de generar la tabla ");
			}else {
				if(MooreCheck.isSelected()) {
					createMoore();
					boolean x = ct.isConex();
					textForTable.setText("El resultado es "+ x);
				}else {
					createMayle();
					boolean x = ct.maylEisConex();
					textForTable.setText("El resultado es "+ x);
				}
			}
		}
}
