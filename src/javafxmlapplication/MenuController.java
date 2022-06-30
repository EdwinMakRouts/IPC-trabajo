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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafxmlapplication.FXMLDocumentController.a;
import model.Answer;
import model.Navegacion;
import model.Problem;
import model.Session;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private ImageView logOut;

    public static int hits;
    public static int errors;
    public static LocalDateTime tiempo;
    public static Session ses;
    
    public static List <Problem> problemas;
    public static Problem ejercicio;
    public static List <Answer> res;
    public static int i;
    
    Navegacion navegacion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image fondos = new Image (getClass().getResourceAsStream("menu/FondoMenu1.jpg"));
        fondo.setImage(fondos);
        Image log = new Image (getClass().getResourceAsStream("menu/icons8-salida-50.png"));
        logOut.setImage(log);
        
        try {
            navegacion = Navegacion.getSingletonNavegacion();
            problemas = navegacion.getProblems();
        } catch (NavegacionDAOException ex) {
            System.out.println("XD");
        }

    }    

    @FXML
    private void cerrarS(MouseEvent event) throws IOException, NavegacionDAOException {
        //al cerrar sesion tiene que guardar la informacion de los hits fallos y fecha
        
        
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Iniciar Sesión");
        stages.setMaxWidth(836);
        stages.setMaxHeight(585);
        stages.show();
        
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void realizarP(MouseEvent event) throws IOException, NavegacionDAOException {
        MenuController.tiempo = LocalDate.now().atStartOfDay();
        hits = 0;
        errors = 0;
        
        i = (int) (Math.random() * (problemas.size() - 1));
        
        try {
        ejercicio = problemas.get(i);
        
        ProblemaController.enunciado = ejercicio.getText();
        
        res = ejercicio.getAnswers();
        ProblemaController.resp1 = res.get(0).getText();
        ProblemaController.resp2 = res.get(1).getText();
        ProblemaController.resp3 = res.get(2).getText();
        ProblemaController.resp4 = res.get(3).getText();
        } catch (Exception e){
            
        }
        
        /**
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.hide();
        **/
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Problema.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Problema");
        stages.setMaxWidth(613);
        stages.setMaxHeight(488);
        stages.show();
        
        /**
        stages.showAndWait();
        
        if (MenuController.hits != 0 || MenuController.errors != 0){
            MenuController.ses = new Session(MenuController.tiempo, MenuController.hits, MenuController.errors);
            a.addSession(MenuController.ses);  
        }
        */
    }


    @FXML
    private void mostrarR(MouseEvent event) throws IOException {
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Resultados.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Resultados");
        stages.setMaxWidth(836);
        stages.setMaxHeight(498);
        stages.show();
        
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.close();
    
    }

    @FXML
    private void editarP(MouseEvent event) throws IOException {
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Perfil.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Editar Perfil");
        stages.setMaxWidth(738);
        stages.setMaxHeight(544);
        stages.show();
        
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void listaP(MouseEvent event) throws NavegacionDAOException, IOException {
        MenuController.tiempo = LocalDate.now().atStartOfDay();
        hits = 0;
        errors = 0;
        ProblemaListController.fallasos = "0";
        ProblemaListController.hiteos = "0";
        
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.hide();
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("ElegirProblema.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Elegir un Problema");
        stages.setMaxWidth(745);
        stages.setMaxHeight(524);
        stages.showAndWait();
        
        if (MenuController.hits != 0 || MenuController.errors != 0){
            MenuController.ses = new Session(MenuController.tiempo, MenuController.hits, MenuController.errors);
            a.addSession(MenuController.ses);  
        }
        
    }
 
}
