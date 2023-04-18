package model;

import java.util.ArrayList;
import java.util.List;

public class MooreMachine {
	
	 private int[] alphabetEnter;
	 private int numStates;
	 private ArrayList<State> states;
	 private ArrayList<Transition> transitions;
	 private ArrayList<State> statesAceptation;
	 private State initialState;
	 public String [][] table;
	 public ArrayList<State> destinations;
	 public ArrayList<String> partitions;
	 
	public MooreMachine(int[] alphabetEnter, int numStates, ArrayList<State> states, ArrayList<Transition> transitions,
			State initialState, ArrayList<State> statesAceptation) {
		super();
		this.alphabetEnter = alphabetEnter;
		this.numStates = numStates;
		this.states = states;
		this.transitions = transitions;
		this.initialState = initialState;
		this.statesAceptation = statesAceptation;
		table = new String[numStates+1][alphabetEnter.length+1];
        
		if(alphabetEnter.length>2) {
			System.out.println("solo se aceptan maquinas de entradas 0 y 1");
		}else if (alphabetEnter.length==1) {
			table [0][0] = " ";
			table [0][1] = "0";
		}
		else {
		table [0][0] = " ";
		table [0][1] = "0";
		table [0][2] = "1";
		}
		
		//Rellenar la colomna donde se muestran los estados al lado izquierdo
		int j = 0; // Número de columna a recorrer

		// Recorrer la columna j de la matriz
		for (int i = 1; i < table.length; i++) {
		     table[i][j] = states.get(i-1).getName();
		    
		}
		
	}

	public int[] getAlphabetEnter() {
		return alphabetEnter;
	}

	public void setAlphabetEnter(int[] alphabetEnter) {
		this.alphabetEnter = alphabetEnter;
	}

	public int getNumStates() {
		return numStates;
	}

	public void setNumStates(int numStates) {
		this.numStates = numStates;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public ArrayList<String> getPartitions() {
		return partitions;
	}

	public void setPartitions(ArrayList<String> partitions) {
		this.partitions = partitions;
	}
	
	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public ArrayList<State> getStatesAceptation() {
		return statesAceptation;
	}

	public void setStatesAceptation(ArrayList<State> statesAceptation) {
		this.statesAceptation = statesAceptation;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	
	public void createTable() {
		int counter = 0;
		for (int i = 1; i < numStates+1; i++) {
		    for (int j = 1; j < alphabetEnter.length+1; j++) {
		    	table[i][j] = transitions.get(counter).getDestination().getName();
		    	System.out.println(table[i][j]);
		    	counter++;	
		    }	
		}
		
		for (int j=0;j<statesAceptation.size();j++) {
			statesAceptation.get(j).setAceptation(true);
			System.out.println();
			System.out.println(statesAceptation.get(j).isAceptation());
			System.out.println();
		}
	}
		
	public void showTable() {
		for (int i = 0; i < numStates+1; i++) {
		    for (int j = 0; j < alphabetEnter.length+1; j++) {
		        System.out.print(table[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	//Este metodo calcula si el automata es conexo
	public boolean calculateConexAutomaton() {
		destinations = new ArrayList<>();
		
		for(int i=0;i<transitions.size();i++) {
			destinations.add(transitions.get(i).getDestination());
		}
		
		for(int i=0;i<destinations.size();i++) {
			for(int j=0;j<states.size();j++) {
				if(destinations.get(i).getName().equalsIgnoreCase(states.get(j).getName())) {
					states.get(j).setConex(true);
				}
			}
		}
		
		int aux = 0;
		for(int j=0;j<states.size();j++) {
			if(states.get(j).isConex()==true) {
				aux++;
				
			}
		}
		
		if(aux==states.size()) {
			return true;
		}else {
			return false;
		}
	}
	
	//mostrar tabla en textArea
	public String tableInTextArea() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < table.length; i++) {
		    for (int j = 0; j < table[i].length; j++) {
		        sb.append(table[i][j]).append("\t");
		    }
		    sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static String[][] eliminarFila(String[][] matriz, int fila) {
	    String[][] nuevaMatriz = new String[matriz.length-1][matriz[0].length];
	    int indice = 0;
	    for (int i = 0; i < matriz.length; i++) {
	        if (i != fila) {
	            nuevaMatriz[indice] = matriz[i];
	            indice++;
	        }
	    }
	    return nuevaMatriz;
	}

	
	public String nowIsConex() {
		//eliminar estado
		if(table.length==3) {
			table = eliminarFila(table, 2);
		}
		else {
			int counter = 0;
			for (int i = 1; i < numStates+1; i++) {
			    for (int j = 1; j < alphabetEnter.length+1; j++) {
			    	if(table[i][j] == destinations.get(counter).getName()) {
			    	counter++;	}
			    	else {
			    		table = eliminarFila(table, i);
			    	}
			    }	
			}
		}
		
		//enviamos la nueva matriz, una vez se han eliminado los estados necesarios para que sea conexo
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < table.length; i++) {
		    for (int j = 0; j < table[i].length; j++) {
		        sb.append(table[i][j]).append("\t");
		    }
		    sb.append("\n");
		}
		return sb.toString();
	}
	
	public void minimize() {
		//separos estados de aceptacion y los otros
		ArrayList<State> noAcep = new ArrayList<State>();
		partitions = new ArrayList<String>();
		
		noAcep.addAll(states);

		// Agregar algunos objetos State al ArrayList
		ArrayList<String> stateStrings = new ArrayList<String>();
		ArrayList<String> noStateStrings = new ArrayList<String>();
		ArrayList<String> allStatesInString = new ArrayList<String>();
		
		for (State statesAceptation : statesAceptation) {
		    stateStrings.add(statesAceptation.getName());
		}
		for (State nostatesAceptation : noAcep) {
			noStateStrings.add(nostatesAceptation.getName());
		}
		for (State sta : states) {
			allStatesInString.add(sta.getName());
		}
		
		noStateStrings.removeAll(stateStrings);
		partitions.add(" "+stateStrings+ " ");		
		
		//Revisamos si algun estado que no es de aceptacion tiene como destino un estado de aceptacion en alguna transicion
		ArrayList<String> newPartition = new ArrayList<String>();
			
		splitPartition(stateStrings, noStateStrings, newPartition,allStatesInString);
	}
	
	public void splitPartition(ArrayList<String> acep,ArrayList<String> noAcep,ArrayList<String> newPartition, ArrayList<String> allStates) {
		
		allStates.removeAll(acep);
		
		//condicion de parada
		if(allStates.isEmpty()) {
			return;
		}
		
		for (int i = 0; i < transitions.size(); i++) {
			for (int k = 0; k < acep.size(); k++) {
				if(transitions.get(i).getDestination().getName().equalsIgnoreCase(acep.get(k)) && !transitions.get(i).getSource().getName().equalsIgnoreCase(acep.get(k))) {
					
					newPartition.add(transitions.get(i).getSource().getName());
					
				}
			}
		}
		partitions.add(" "+newPartition+" ");
		acep.removeAll(acep);
		acep.addAll(newPartition);
		newPartition.removeAll(newPartition);
		
		//llamado recursivo
		splitPartition(acep, noAcep, newPartition, allStates);
	}
	
}
