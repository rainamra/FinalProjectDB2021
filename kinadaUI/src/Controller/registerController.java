package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private Button registerButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNum;
    @FXML
    private PasswordField pass;
    @FXML
    private ImageView shield;
    @FXML
    private Label registerMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File shieldFile = new File("kinadaImage/kinadaShield.png");
        Image shieldImage = new Image(shieldFile.toURI().toString());
        shield.setImage(shieldImage);
    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void emptyTextfield() {
        firstname.setText(null);
        lastname.setText(null);
        email.setText(null);
        phoneNum.setText(null);
        address.setText(null);
        pass.setText(null);
    }

    public void registButtonOnAction(ActionEvent event) {
        if (!firstname.getText().isBlank() && !lastname.getText().isBlank()
                && !email.getText().isBlank() && !address.getText().isBlank()
                && !phoneNum.getText().isBlank() && !pass.getText().isBlank())
        {
            registerStaff();
        }
        else {
            registerMessageLabel.setText("You haven't Fill up all the data");
        }
    }

    public void registerStaff() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

       String insertNewStaff = "INSERT into staff values (null,"
               + "'"+firstname.getText()+"', '"+lastname.getText()+"', '"+phoneNum.getText()+"', "
               + "'"+email.getText()+"', '"+address.getText()+"', '"+pass.getText()+"')";

        try {
            Statement stat = connectDB.createStatement();
            stat.executeUpdate(insertNewStaff);

            registerMessageLabel.setText("Staff registered successfully!");
            emptyTextfield();
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
