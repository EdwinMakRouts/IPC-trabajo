/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafxmlapplication.FXMLDocumentController.a;
import static javafxmlapplication.MenuController.ejercicio;
import static javafxmlapplication.MenuController.problemas;
import model.Answer;
import model.Session;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class ProblemaListController implements Initializable {
@FXML
    private Text pregunta;
    @FXML
    private Circle A_Cir;
    @FXML
    private Circle B_Cir;
    @FXML
    private Circle C_Cir;
    @FXML
    private Circle D_Cir;
    @FXML
    private Text A;
    @FXML
    private Text B;
    @FXML
    private Text C;
    @FXML
    private Text D;
    @FXML
    private ImageView fondo;
    @FXML
    private Text aciertos;
    @FXML
    private Text errores;

    public static Answer seleccionada;
    public static String enunciado;
    public static String resp1;
    public static String resp2;
    public static String resp3;
    public static String resp4;
    public static String hiteos;
    public static String fallasos;
    public static Stage elegir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image fondos = new Image (getClass().getResourceAsStream("Resultados/winters.jpg"));
        fondo.setImage(fondos);
        
        seleccionada = null;
        
        if (enunciado.length() < 150){
            pregunta.setStyle("-fx-font: 19 System");
        } else if (enunciado.length() < 200){
            pregunta.setStyle("-fx-font: 18 System");        
        } else if (enunciado.length() < 250){
            pregunta.setStyle("-fx-font: 17 System"); 
        } else if (enunciado.length() < 300){
            pregunta.setStyle("-fx-font: 16 System");
        } else if (enunciado.length() < 400){
            pregunta.setStyle("-fx-font: 15 System");
        } else {
            pregunta.setStyle("-fx-font: 11 System");
        }
        pregunta.setText(enunciado);
        A.setText(resp1);
        B.setText(resp2);
        C.setText(resp3);
        D.setText(resp4);
        
        aciertos.setText(hiteos);
        errores.setText(fallasos);
        
    }    

    @FXML
    private void uno(MouseEvent event) {
        if (A_Cir.getFill() == WHITE){
            A_Cir.setFill(BLACK);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = MenuController.res.get(0);
        } else {
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = null;
        }
        
    }

    @FXML
    private void dos(MouseEvent event) {
        if (B_Cir.getFill() == WHITE){    
            A_Cir.setFill(WHITE);
            B_Cir.setFill(BLACK);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = MenuController.res.get(1);
        } else {
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = null;
        }
        
    }

    @FXML
    private void tres(MouseEvent event) {
        if (C_Cir.getFill() == WHITE){
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(BLACK);
            D_Cir.setFill(WHITE);
            seleccionada = MenuController.res.get(2);
        } else {
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = null;
        }
        
    }

    @FXML
    private void cuatro(MouseEvent event) {
        if (D_Cir.getFill() == WHITE){
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(BLACK);
            seleccionada = MenuController.res.get(3);
        } else {
            A_Cir.setFill(WHITE);
            B_Cir.setFill(WHITE);
            C_Cir.setFill(WHITE);
            D_Cir.setFill(WHITE);
            seleccionada = null;
        }
        
    }

    @FXML
    private void cancelar(MouseEvent event) throws IOException, NavegacionDAOException {
        elegir = (Stage) fondo.getScene().getWindow();
        ElegirProblemaController.abierto = 0;
        elegir.close();
    }

    @FXML
    private void aceptar(MouseEvent event) throws Exception {
        if (seleccionada.equals(null) == true){
            //NO HACE NADA
        } else {
            //COMPRUEBA LA RESPUESTA
            if (seleccionada.getValidity() == true){
                MenuController.hits = MenuController.hits + 1;
                hiteos = Integer.toString(MenuController.hits);
                ElegirProblemaController.acis = hiteos;
                
            } else {
                MenuController.errors = MenuController.errors + 1;
                fallasos = Integer.toString(MenuController.errors);
                ElegirProblemaController.errs = fallasos;
            }
            elegir = (Stage) fondo.getScene().getWindow();
            ElegirProblemaController.abierto = 0;
            elegir.close();
            
        }
    }

    @FXML
    private void abrirMapa(MouseEvent event) throws IOException {
        //Abre el mapa (Basicamente todo el punto 3)
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Mapa.fxml"));
        Parent rote = loader.load();
        Scene scenes = new Scene(rote);
        Stage stages = new Stage();
        stages.setScene(scenes);
        stages.setTitle("Mapa");
        //stages.setMaxWidth(613);
        //stages.setMaxHeight(440);
        stages.show();
    }
}
