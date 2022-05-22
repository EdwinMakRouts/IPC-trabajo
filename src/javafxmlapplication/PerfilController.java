/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxmlapplication.FXMLDocumentController.a;
import model.Navegacion;
import model.User;
import static model.User.checkEmail;
import static model.User.checkNickName;
import static model.User.checkPassword;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class PerfilController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private ImageView profile;
    @FXML
    private TextField nombre;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField correo;
    @FXML
    private TextField contra;
    @FXML
    private Text error;

    int i = 0;
    Image perfil;
    String imagen;
    
    @FXML
    private ImageView l2;
    @FXML
    private ImageView l3;
    @FXML
    private ImageView l1;
  
    Navegacion navegacion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Image fondos = new Image (getClass().getResourceAsStream("perfil/asas.jpg"));
        fondo.setImage(fondos);
        
        Image lapiz = new Image (getClass().getResourceAsStream("perfil/icons8-punta-de-lápiz-50.png"));
        l1.setImage(lapiz);
        l2.setImage(lapiz);
        l3.setImage(lapiz);
        
        try {
        
            navegacion = Navegacion.getSingletonNavegacion();
            
            nombre.setText(a.getNickName());
            fecha.setValue(a.getBirthdate());
            correo.setText(a.getEmail());
            contra.setText(a.getPassword());
            perfil = a.getAvatar();
            profile.setImage(perfil);
            
        } catch (NavegacionDAOException ex) {}
        
    }    


    @FXML
    private void cancelar(MouseEvent event) throws IOException {
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Menu");
        stages.setMaxWidth(613);
        stages.setMaxHeight(440);
        stages.show();
        
        Stage stage = (Stage) nombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cambiarImagen(MouseEvent event) {
    if (i < 5){    
            if (i == 0){
                imagen = "avatars/default.png";
                perfil = new Image (getClass().getResourceAsStream(imagen));
                profile.setImage(perfil);
                i++;
            } else if (i == 1){
                imagen = "avatars/avatar1.png";
                perfil = new Image (getClass().getResourceAsStream(imagen));
                profile.setImage(perfil);
                i++;
            }else if (i == 2){
                imagen = "avatars/avatar2.png";
                perfil = new Image (getClass().getResourceAsStream("avatars/avatar2.png"));
                profile.setImage(perfil);
                i++;
            }else if (i == 3){
                imagen = "avatars/avatar3.png";
                perfil = new Image (getClass().getResourceAsStream(imagen));
                profile.setImage(perfil);
                i++;
            }else {
                imagen = "avatars/avatar4.png";
                perfil = new Image (getClass().getResourceAsStream(imagen));
                profile.setImage(perfil);
                i++;
            }
        } else {
            imagen = "avatars/default.png";
            perfil = new Image (getClass().getResourceAsStream(imagen));
            profile.setImage(perfil);
            i = 1;
        }
    }

    @FXML
    private void aplicar(MouseEvent event) throws NavegacionDAOException, IOException {
          
        error.setText("");
        
        LocalDate birthdate = fecha.getValue();
        if ((birthdate.getYear() > LocalDate.now().getYear() - 18) ||
            (birthdate.getYear() == LocalDate.now().getYear()- 18 &&
             birthdate.getMonthValue() <= LocalDate.now().getMonthValue() &&
             birthdate.getDayOfMonth() < LocalDate.now().getDayOfMonth())){
            
            error.setText("USUARIO MENOR DE EDAD");
        
        } else {
        String email = (String) correo.getText();
         
        String password = (String) contra.getText();
        
        if (email.contains("@") == false || email.contains(".") == false || checkEmail(email) == false){
            error.setText("CORREO ELECTRONICO NO TIENE UN FORMATO VALIDO");
        } else {
            if (checkPassword(password) == false){
                    error.setText("CONTRASEÑA NO CUMPLE CON EL FORMATO");
            } else {    
                
                a.setEmail(correo.getText());
                a.setPassword(contra.getText());
                a.setBirthdate(fecha.getValue());
                a.setAvatar(perfil);
                    
                FXMLLoader loader= new  FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent rote = loader.load();
                Scene scenes = new Scene(rote);
                Stage stages = new Stage();
                stages.setScene(scenes);
                stages.setTitle("Menu");
                stages.setMaxWidth(613);
                stages.setMaxHeight(440);
                stages.show();
                    
                Stage stage = (Stage) nombre.getScene().getWindow();
                stage.close();
                }
            }
        }
    }
}