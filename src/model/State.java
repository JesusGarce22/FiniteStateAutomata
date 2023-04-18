package model;

public class State {
	
	 /*
     * Main attributes for this class.
     * This attributes are related to the state of a finite automaton.
     */
    private String name;
    private boolean aceptation;
    private boolean isConex; //es para saber si un estado esta como destino en una transicion
    
    public State(String name) {
        this.name = name;
        isConex = false;
         aceptation = false;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAceptation() {
		return aceptation;
	}

	public void setAceptation(boolean aceptation) {
		this.aceptation = aceptation;
	}

	public boolean isConex() {
		return isConex;
	}

	public void setConex(boolean isConex) {
		this.isConex = isConex;
	}
    
}
