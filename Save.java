import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Save {
	
	private Controller controller;
	
	@FXML
	private void initialize() {
		controller = Controller.self;
	}
	
	@FXML
	private Button closebut;
	@FXML
	private Button savebut;
	@FXML
	private TextField textf;
	
	String text; 
	
	@FXML
	private void closeButton() {
		Stage stage = (Stage) closebut.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void saveButton() throws FileNotFoundException, UnsupportedEncodingException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		PrintWriter write = new PrintWriter("QdTool_output.txt", "UTF-8");
				
		write.println("------------QdTool------------");
		write.println("Data: " + dtf.format(now));
		write.println("Identyfikator: " + text);
		write.println("Powierzchnia strefy pozarowej: " + controller.box1.getValue());
		write.println("Gestosc obciazenia ogniowego: " + controller.box2.getValue());
		write.println("Wymagana ilosc wody do zewnetrznego gaszenia pozaru: " + controller.showWynik());
		write.println("------------koniec------------");
		write.close();
	}
	
	@FXML
	private void text() {
		text = textf.getText();
	}
	
	
}
