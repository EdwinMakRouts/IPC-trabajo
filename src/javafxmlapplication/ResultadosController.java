/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafxmlapplication.FXMLDocumentController.a;
import static javafxmlapplication.MenuController.ses;
import model.Session;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class ResultadosController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private ListView<Session> tabla;

    List <Session> ListSession;
    List <Session> aux;
    ObservableList<Session> tablita;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image fondos = new Image (getClass().getResourceAsStream("Resultados/aaa.jpg"));
        fondo.setImage(fondos);
        //Hay que enlazar de alguna forma la tabla con la base de datos creo
        ListSession = a.getSessions();
        tablita = null;

        tablita = FXCollections.observableArrayList(ListSession);

        tabla.setItems(tablita);
        
    }    


    @FXML
    private void volver(MouseEvent event) throws IOException {
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Menu");
        stages.setMaxWidth(613);
        stages.setMaxHeight(440);
        stages.show();
        
        Stage stage = (Stage) fondo.getScene().getWindow();
        stage.close();
    
    }
    
}
