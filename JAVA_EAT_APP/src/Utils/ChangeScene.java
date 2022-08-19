package Utils;

import java.io.IOException;

import default_package.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ChangeScene {

	public  void changeScene(String sceneName, int title) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(sceneName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scn = new Scene(root);
		MainController.changeScene(title,scn);
	}
}
