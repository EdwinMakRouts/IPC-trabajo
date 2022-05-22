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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxmlapplication.MenuController.errors;
import static javafxmlapplication.MenuController.hits;
import static javafxmlapplication.MenuController.tiempo;
import model.Navegacion;
import model.Session;
import model.User;


/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField Usuario;
    @FXML
    private PasswordField Contra;
    @FXML
    private Text errorInicio;
    @FXML
    private ImageView fondo;
    @FXML
    private ImageView icono;
    
    public static User a;
    public static Navegacion navegacion;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fondo
       Image fondos = new Image (getClass().getResourceAsStream("fondo.jpg"));
       fondo.setImage(fondos);
       Image iconos = new Image (getClass().getResourceAsStream("iconom.png"));
       icono.setImage(iconos);
       
        try {
            navegacion = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {}
      
    }    

    @FXML
    private void crearCuenta(MouseEvent event) throws IOException {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Registrar.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stage = new Stage();
        stage.setScene(scenes);
        stage.setTitle("Registrarse");
        stage.setMaxWidth(836);
        stage.setMaxHeight(585);
        stage.show();
        
        Stage stages = (Stage) Usuario.getScene().getWindow();
        stages.close();
    }

    @FXML
    private void iniciarS(MouseEvent event) throws NavegacionDAOException, IOException {
        if (navegacion.exitsNickName(Usuario.getText()) == true){
            if (navegacion.loginUser(Usuario.getText(), Contra.getText()) != null){
                
                a = navegacion.getUser(Usuario.getText());
                
                MenuController.hits = 0;
                MenuController.errors = 0;
                
                MenuController.problemas = navegacion.getProblems();
                
                FXMLLoader loader= new  FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent rote = loader.load();
                Scene scenes = new Scene(rote);
                Stage stage = new Stage();
                stage.setScene(scenes);
                stage.setTitle("Menu");
                stage.setMaxWidth(613);
                stage.setMaxHeight(440);
                stage.show();
                
                Stage stages = (Stage) Usuario.getScene().getWindow();
                stages.close();
                
            } else {
                errorInicio.setText("CONTRASEÑA ICORRECTA");
            }
        } else {
            errorInicio.setText("USUARIO NO EXISTENTE");
        }
    }
    
    
}
