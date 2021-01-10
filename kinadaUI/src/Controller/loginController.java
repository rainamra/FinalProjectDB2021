package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button registerButton;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField login_id;
    @FXML
    private PasswordField login_pass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("kinadaImage/login.PNG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("kinadaImage/locked.PNG");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!login_id.getText().isBlank() && !login_pass.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Invalid ID and Password. Please try again.");
        }
    }

    public void registerButtonOnAction(ActionEvent event) {
        registerForm();
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin = "SELECT count(1) FROM staff WHERE StaffID ='" + login_id.getText() + "' and Password ='" + login_pass.getText() + "'";

        try {
            Statement stat = connectDB.createStatement();
            ResultSet rs = stat.executeQuery(verifyLogin);

            while(rs.next()){
                if(rs.getInt(1)== 1){
                    loginMessageLabel.setText("Congratulations");
                    homePage();

                }
                else {
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerForm(){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root, 400, 500));
            registerStage.show();

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void homePage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/home.fxml"));
            Parent root = loader.load();

            //The following both lines are the only addition we need to pass the arguments
            homeController controller2 = loader.getController();
            controller2.setStaffID(login_id.getText());

            Stage homeStage = new Stage();
            homeStage.setScene(new Scene(root, 800, 420));
            homeStage.show();

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
