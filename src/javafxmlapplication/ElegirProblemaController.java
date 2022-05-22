/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxmlapplication.FXMLDocumentController.a;
import static javafxmlapplication.MenuController.ejercicio;
import static javafxmlapplication.MenuController.i;
import static javafxmlapplication.MenuController.problemas;
import static javafxmlapplication.MenuController.res;
import static javafxmlapplication.ProblemaListController.hiteos;
import model.Problem;
import model.Session;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class ElegirProblemaController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private ListView<Problem> tabla;
    @FXML
    private Text text;
    
    ObservableList<Problem> tablita;
    public static List <Problem> enunP;
    public static int abierto;
    
    @FXML
    private Text aci;
    @FXML
    private Text err;
    
    public static String acis;
    public static String errs;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image fondos = new Image (getClass().getResourceAsStream("menu/nuevoPr.jpg"));
        fondo.setImage(fondos);
        
        tablita = null;
        enunP = MenuController.problemas;
        
        tablita = FXCollections.observableArrayList(enunP);
        tabla.setItems(tablita);
        abierto = 0;
        
        acis = Integer.toString(MenuController.hits);
        errs = Integer.toString(MenuController.errors);
        
        aci.setText(acis);
        err.setText(errs);
        
    }    

    @FXML
    private void volver(ActionEvent event) throws NavegacionDAOException, IOException {
        if (MenuController.hits != 0 || MenuController.errors != 0){
            MenuController.ses = new Session(MenuController.tiempo, MenuController.hits, MenuController.errors);
            a.addSession(MenuController.ses);  
        }
        
        MenuController.hits = 0;
        MenuController.errors = 0;
        ProblemaListController.fallasos = "0";
        ProblemaListController.hiteos = "0";
        
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
        
        if (ProblemaListController.elegir.isShowing() == true){
            ProblemaListController.elegir.close();
        }
        
    }

    @FXML
    private void realizarP(ActionEvent event) throws IOException {
    if (abierto == 0){    
        try{
        Integer i = tabla.getSelectionModel().getSelectedIndex();
        
        ejercicio = problemas.get(i);
        
        
        ProblemaListController.enunciado = ejercicio.getText();
        
        res = ejercicio.getAnswers();
        ProblemaListController.resp1 = res.get(0).getText();
        ProblemaListController.resp2 = res.get(1).getText();
        ProblemaListController.resp3 = res.get(2).getText();
        ProblemaListController.resp4 = res.get(3).getText();
        } catch (Exception e){
            text.setText("SELECCIONE UNA PREGUNTA");
        }
        
        
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("ProblemaList.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        ProblemaListController.elegir = new Stage();
        ProblemaListController.elegir.setScene(scenes);
        ProblemaListController.elegir.setTitle("Problema");
        ProblemaListController.elegir.setMaxWidth(613);
        ProblemaListController.elegir.setMaxHeight(488);
        abierto = 1;
        ProblemaListController.elegir.showAndWait();
        
        aci.setText(acis);
        err.setText(errs);
        
    } else {
        //No lo abre
    }
    
    }
    
}
