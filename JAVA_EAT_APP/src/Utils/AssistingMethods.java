package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import javax.imageio.ImageIO;

import default_package.MainController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AssistingMethods{

	public  void replacePane(AnchorPane toReplacePane, String path) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		AnchorPane pane = loader.load();
		toReplacePane.getChildren().removeAll(toReplacePane.getChildren());
		toReplacePane.getChildren().add(pane);
		}
	
	public void replace(String title, String location, AnchorPane toReplacePane) {
		
		MainController.stg.setTitle(title);
    	try {
			replacePane(toReplacePane, location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	toReplacePane.setVisible(true);
	}
	
	public int getBiggestID(Set<Integer> keySet) {
		
		ArrayList<Integer> IDs = new ArrayList<Integer>(keySet);
		Collections.sort(IDs);
		int biggestID;
		if(IDs.size() != 0) {
			biggestID = IDs.get(IDs.size()-1);
		}
		else {
			biggestID = 0;
		}
		return biggestID;
	}
	
	public String uploadImg(ImageView choosenImg, String imageName) {
		
		//creating a file choose window
    	FileChooser imgChooser = new FileChooser();
    	imgChooser.setTitle("UPLOAD PHOTO");
    	Stage stage = new Stage();
    	//setting a file filter to allow only pictures
    	imgChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),new FileChooser.ExtensionFilter("JPEG","*.jpg"));
    	File SelectedFile = imgChooser.showOpenDialog(stage);
    	//checking that the user really chose an image
    	if( SelectedFile != null) {
    		//creating an image an set it to the be visible for the user
    		Image original = new Image(SelectedFile.toURI().toString());
	    	choosenImg.setImage(original);
	    	choosenImg.setVisible(true);
	    	//extract the picture name
	    	String newpicName = extractImgName(SelectedFile.getPath());
			//setting the new place for the picture to be project folder media 
			//and saving the picture name
			String reletivePath = "/media/" + newpicName;
			imageName = newpicName;
	    	String projectPath = System.getProperty("user.dir");
	    	String currectpath = projectPath.replace('\\', '/');
	    	currectpath += reletivePath;
	    	//save the image in the project media folder
	    	savaToFile(original,currectpath);
	    	return imageName;
    	}
    	else return null;
	}
	
	private String extractImgName(String path) {
		
		String s = path;
		s = s.replace('\\', '/');
		String[] stringarr = s.split("/");
		String name = stringarr[stringarr.length - 1].toString();
		char[] picName = new char[name.length()]; name.getChars(0, name.length(), picName, 0);
		String str = "";
		for(int i = 0; i < picName.length; i++) {
			str +=  picName[i];
		}
		String newpicName = "";
		for(int i = 0; i <str.length(); i++) {
			if(str.charAt(i) != '.') {
				newpicName += str.charAt(i);
			}
			else {
				break;
			}
		}
		return newpicName;
	}
	
	//this method save the image in the given location.
    private void savaToFile(Image img, String path) {
    	
    	File outPutFile = new File(path + ".png");
    	BufferedImage Bi = SwingFXUtils.fromFXImage(img, null);
    	try {
			ImageIO.write(Bi, "png", outPutFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void showImage(String name, String currectpath, ImageView choosenImg) {
    	
    	String reletivePath = "/media/" + name + ".png";
    	String projectPath = System.getProperty("user.dir");
    	currectpath = projectPath.replace('\\', '/');
    	currectpath += reletivePath;
    	File f = new File(currectpath);
		Image original = new Image(f.toURI().toString());
		choosenImg.setImage(original);
		choosenImg.setVisible(true);
    }
    
    public void showGalleryImage(String imageName, String currectpath, ImageView selectedImg) {
    	
    	String reletivePath = "/media/" + imageName + ".JPG";
    	String projectPath = System.getProperty("user.dir");
    	currectpath = projectPath.replace('\\', '/');
    	currectpath += reletivePath;
    	File f = new File(currectpath);
		Image original = new Image(f.toURI().toString());
		selectedImg.setImage(original);
		selectedImg.setVisible(true);
    }

}
