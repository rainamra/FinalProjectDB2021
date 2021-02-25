package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;


public class profileController implements Initializable {

    @FXML
    private ImageView avatarImageView;
    @FXML
    private Label LabelID;
    @FXML
    private Label LabelPhone;
    @FXML
    private Label LabelName;
    @FXML
    private Label LabelEmail;
    @FXML
    private Label LabelAddress;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File avatarFile = new File("kinadaImage/staff.png");
        Image avatarImage = new Image(avatarFile.toURI().toString());
        avatarImageView.setImage(avatarImage);
    }

    public void setProfile(Integer id, String name, String phone, String address, String email) {

        LabelID.setText(String.valueOf(id));
        LabelName.setText(name);
        LabelEmail.setText(email);
        LabelPhone.setText(phone);
        LabelAddress.setText(address);


    }
}
