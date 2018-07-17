import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class About {
	
	@FXML
	private Button closebut;
	
	@FXML
	private void closeButton() {
		Stage stage = (Stage) closebut.getScene().getWindow();
		stage.close();
	}
	
}
