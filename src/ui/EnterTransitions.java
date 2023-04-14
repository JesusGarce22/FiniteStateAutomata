package ui;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class EnterTransitions extends Stage {
	
    @FXML
    private TextField output;

    @FXML
    private Button add;

    @FXML
    private TextField input;

    @FXML
    private TextField destination;

    @FXML
    private TextField sour;

    public EnterTransitions() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterTransitions.fxml.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root, 600, 350);
			setScene(scene);

			input = (TextField) loader.getNamespace().get("input");
			output = (TextField) loader.getNamespace().get("output");
			sour = (TextField) loader.getNamespace().get("sour");
			destination = (TextField) loader.getNamespace().get("destination");

			add = (Button) loader.getNamespace().get("add");

			init();
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}
    
    private void init() {
    	add.setOnAction(event -> {
    		MainWindow main = MainWindow.getInstance();
    		main.show();
    		main.loadTransition(input.getText(), output.getText(), sour.getText(), destination.getText());
    		this.close();
		});
		
	}
}
