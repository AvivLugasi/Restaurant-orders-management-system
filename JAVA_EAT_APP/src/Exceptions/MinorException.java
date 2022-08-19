package Exceptions;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*thrown if the worker is under 18 */
public class MinorException extends Exception {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public MinorException() {
			
			JOptionPane.showMessageDialog(null, "The worker is to young!, "
					+ "\n the restaurant does not accept a worker who is younger than 18.",
				      "Ilegal age", JOptionPane.ERROR_MESSAGE);
		}

}
