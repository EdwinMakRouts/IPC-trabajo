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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Navegacion;
import static model.User.checkEmail;
import static model.User.checkNickName;
import static model.User.checkPassword;

/**
 * FXML Controller class
 *
 * @author EMAKROU
 */
public class RegistrarController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private ImageView profile;
    @FXML
    private PasswordField contra;
    @FXML
    private TextField correo;
    @FXML
    private PasswordField contraR;
    @FXML
    private DatePicker fecha;
    int i = 1;
    String email;
    @FXML
    private Text error;
    @FXML
    private ImageView fondo;

    String imagen;
    Image perfil;
    
    Navegacion navegacion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagen = "avatars/default.png";
        
        perfil = new Image (getClass().getResourceAsStream(imagen));
        profile.setImage(perfil);
        Image fondos = new Image (getClass().getResourceAsStream("fondo.jpg"));
        fondo.setImage(fondos);
        
    }    


    @FXML
    private void cancelar(MouseEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Iniciar Sesión");
        stages.setMaxWidth(836);
        stages.setMaxHeight(585);
        stages.show();
        
        Stage stage = (Stage) nombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cambiar(MouseEvent event) throws IOException {
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
    private void registrar(MouseEvent event) throws NavegacionDAOException, IOException {
        navegacion = Navegacion.getSingletonNavegacion();
        
        error.setText("");
        try{
        LocalDate birthdate = fecha.getValue();
        if ((birthdate.getYear() > LocalDate.now().getYear() - 18) ||
            (birthdate.getYear() == LocalDate.now().getYear()- 18 &&
             birthdate.getMonthValue() <= LocalDate.now().getMonthValue() &&
             birthdate.getDayOfMonth() < LocalDate.now().getDayOfMonth())){
            
            error.setText("USUARIO MENOR DE EDAD");
        
        } else {
        
        String user = (String) nombre.getText();
        email = (String) correo.getText();
         
        String password = (String) contra.getText();
        String passwordR = (String) contraR.getText();
        
        if (!password.equals(passwordR)){
            error.setText("NO COINCIDEN LAS CONTRASEÑAS");
        } else if (email.contains("@") == false || email.contains(".") == false || checkEmail(email) == false){
            error.setText("CORREO ELECTRONICO NO TIENE UN FORMATO VALIDO");
        } else {
            if (checkPassword(password) == false){
                    error.setText("CONTRASEÑA NO CUMPLE CON EL FORMATO");
            } else {    
                if (checkNickName(user) == false){
                    error.setText("USUARIO ESCRITO NO TIENE UN FORMATO VALIDO");
                } else {
                    if(navegacion.exitsNickName(user) == true){
                        error.setText("USUARIO ESCRITO YA ESTA ESCOGIDO");
                    }else {
                        navegacion.registerUser(user, email, password, perfil, birthdate);
                        
                        FXMLLoader loader= new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent rote = loader.load();
                        Scene scenes = new Scene(rote);
                        Stage stages = new Stage();
                        stages.setScene(scenes);
                        stages.setTitle("Iniciar Sesión");
                        stages.setMaxWidth(836);
                        stages.setMaxHeight(585);
                        stages.show();
        
                        Stage stage = (Stage) nombre.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
        }
        } catch (Exception e){
            error.setText("SELECIONE UNA FECHA CLICKANDO EN EL ICONO");
        }
    }
}
    

