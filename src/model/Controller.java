package model;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	private MooreMachine moore;
	private MealyMachine mayle;
	
	public void createMooreAtomaton(int[] alphabetEnter, int numStates, String[] states, String[] transitions, String initialState, String[] statesAceptation) {

		ArrayList<State> statesComplete = new ArrayList<>();
		ArrayList<Transition> transitionsComplete = new ArrayList<>();
		ArrayList<State> statesCompleteAceptation = new ArrayList<>();
		
		for (int i=0; i<statesAceptation.length;i++) {
			if(statesAceptation[i]!=null) {
			statesCompleteAceptation.add(new State(statesAceptation[i]));}
		}
		
		for (int i=0; i<states.length;i++) {
			if(states[i]!=null) {
			statesComplete.add(new State(states[i]));}
		}
		
		for (int i=0; i<transitions.length;i++) {
			if(transitions[i]!=null) {
			String[] auxTransition = transitions[i].split(",");
			transitionsComplete.add(new Transition(auxTransition[0], auxTransition[1], new State(auxTransition[2]), new State(auxTransition[3])));}
			
		}
		System.out.println(transitionsComplete);
		System.out.println("_______________________________________________________");
		
		moore = new MooreMachine(alphabetEnter, numStates, statesComplete, transitionsComplete, new State(initialState),statesCompleteAceptation);
		moore.createTable();
		moore.showTable();
	}
	
	public boolean isConex() {
		return moore.calculateConexAutomaton();
	}
	
	public String showTable() {
		return moore.tableInTextArea();
	}
	
	public String nowIsConex() {
		return moore.nowIsConex();
	}
	
	public ArrayList<String> partition(){
		moore.minimize();
		return moore.getPartitions();
	}
}
