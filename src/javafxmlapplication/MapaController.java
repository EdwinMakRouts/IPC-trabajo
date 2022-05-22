/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import static javafxmlapplication.ElegirProblemaController.enunP;

/**
 * FXML Controller class
 *
 * @author makov
 */
public class MapaController implements Initializable {

    @FXML
    private ImageView basura;
    @FXML
    private ImageView lupa;
    @FXML
    private ImageView regla;
    @FXML
    private ImageView text;
    @FXML
    private ColorPicker lapizColor;
    @FXML
    private ImageView punto;
    @FXML
    private ColorPicker puntoColor;
    @FXML
    private MenuItem Tr;
    @FXML
    private MenuItem Tc;
    @FXML
    private MenuItem Gp;
    @FXML
    private MenuItem Gm;
    @FXML
    private MenuItem Gg;
    @FXML
    private ImageView mapa;
    @FXML
    private ImageView Imaglapiz;
    @FXML
    private MenuButton tipo;
    @FXML
    private MenuButton grossor;
    
    int i = -1;
    int j = -1;
    int draw = 1;
    GraphicsContext graficos;
    int z = 0;
    
    @FXML
    private Canvas canvas;
    @FXML
    private ImageView regliton; 
    
    private MenuItem Tl;
    
    @FXML
    private Slider zoom_slider;
    
    private Group zoomGroup;
    
    
    @FXML
    private ScrollPane mapita;
    @FXML
    private ColorPicker textColor;
    @FXML
    private TextField textoEsc;
    @FXML
    private Text PosX;
    @FXML
    private Text PosY;
    
    int reglamento = 0; //mover regla
    
    private Button boton;
    
    int p = -1;
    Arc arquito;
    double iXArco;
    double iYArco;
    @FXML
    private MenuItem Trectisimo;
    
    Line linea;
    @FXML
    private MenuButton grossor1;
    @FXML
    private MenuItem Gp1;
    @FXML
    private MenuItem Gm1;
    @FXML
    private MenuItem Gg1;
    
    int pl = 4;
    
    @FXML
    private ImageView marco;
    
    int pipi = -1;
    
    @FXML
    private ListView<String> lista;
    Integer lineaR = 0;
    Integer LineaC = 0;
    Integer Puntitos = 0;
    Integer cruz = 0;
    
    Integer popo;
    @FXML
    private ColorPicker ultimo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image map = new Image (getClass().getResourceAsStream("mapa/carta_nautica.jpg"));
        mapa.setImage(map); 
        
        Image lap = new Image (getClass().getResourceAsStream("mapa/icons8-lápiz-100.png"));
        Imaglapiz.setImage(lap);
        
        Image punt = new Image (getClass().getResourceAsStream("mapa/icons8-mapa-de-pokemon-100.png"));
        punto.setImage(punt);
        
        Image texto = new Image (getClass().getResourceAsStream("mapa/icons8-texto-100.png"));
        text.setImage(texto);
        
        Image regl = new Image (getClass().getResourceAsStream("mapa/icons8-grados-100.png"));
        regla.setImage(regl);
        
        Image lup = new Image (getClass().getResourceAsStream("mapa/icons8-búsqueda-100.png"));
        lupa.setImage(lup);
        
        Image bas = new Image (getClass().getResourceAsStream("mapa/icons8-basura-100.png"));
        basura.setImage(bas);
        
        Image pepe = new Image (getClass().getResourceAsStream("mapa/icons8-más-100.png"));
        marco.setImage(pepe);
        
        graficos = canvas.getGraphicsContext2D();
        
        Group contentGroup = new Group();
        
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(mapita.getContent());
        mapita.setContent(contentGroup);
        
        zoom_slider.setMin(0.5);
        zoom_slider.setMax(3.0);
        zoom_slider.setValue(1.0);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
        
    }    

    @FXML
    private void SelTr(ActionEvent event) {
        i = 0;
        tipo.setText(Tr.getText());
    }
    
    @FXML
    private void SelTc(ActionEvent event) {
        i = 1;
        tipo.setText(Tc.getText());
    }
    
    @FXML
    private void SelGp(ActionEvent event) {
        j = 0;
        grossor.setText(Gp.getText());
    }

    @FXML
    private void SelGm(ActionEvent event) {
        j = 1;
        grossor.setText(Gm.getText());
    }

    @FXML
    private void SelGg(ActionEvent event) {
        j = 2;
        grossor.setText(Gg.getText());
    }

    @FXML
    private void pintar(MouseEvent event) {
        if (i != -1 && j != -1){
            if (draw == 0){
                draw = 1;
            } else {
                draw = 0;
            }   
        } else {
            //NO HACE NADA
            draw = 1;
        }
    }
    
    @FXML
    private void lineas(MouseEvent event) {
        if (draw == 0){
            graficos.setStroke(lapizColor.getValue());
            if (i == 2 && j == 0){
            
                linea.setEndX(event.getX());
                linea.setEndY(event.getY());                
                event.consume();
                
            } else if (i == 2 && j == 1){
            
                linea.setEndX(event.getX());
                linea.setEndY(event.getY());
                event.consume();
                
            } else if (i == 2 && j == 2){
            
                linea.setEndX(event.getX());
                linea.setEndY(event.getY());
                event.consume();
                
            } else if (i == 0 && j == 0){
                
                graficos.setStroke(lapizColor.getValue());      
                graficos.setLineWidth(3);
                graficos.lineTo(event.getX(), event.getY());
                graficos.stroke();
                
            } else if (i == 0 && j == 1){
                
                graficos.setStroke(lapizColor.getValue());
                graficos.setLineWidth(6);
                graficos.lineTo(event.getX(), event.getY());
                graficos.stroke();
                
            }else if (i == 0 && j == 2){
                
                graficos.setStroke(lapizColor.getValue());
                graficos.setLineWidth(9);
                graficos.lineTo(event.getX(), event.getY());
                graficos.stroke();
                
            }else if (i == 1 && j == 0){
                                
                arquito.setRadiusX(Math.abs(event.getX() - iXArco));
                arquito.setRadiusY(Math.abs(event.getY() - iYArco));
                arquito.setStartAngle(event.getX() + event.getY() - (iXArco + iYArco));
                event.consume();
                        
            }else if (i == 1 && j == 1){
                
                arquito.setRadiusX(Math.abs(event.getX() - iXArco));
                arquito.setRadiusY(Math.abs(event.getY() - iYArco));
                arquito.setStartAngle(event.getX() + event.getY() - (iXArco + iYArco));
                event.consume();
                
            } else {
                
                arquito.setRadiusX(Math.abs(event.getX() - iXArco));
                arquito.setRadiusY(Math.abs(event.getY() - iYArco));
                arquito.setStartAngle(event.getX() + event.getY() - (iXArco + iYArco));
                event.consume();
            } 
        }else {
            graficos.closePath();
            event.consume();
        }            
        
    }

    @FXML
    private void lineasP(MouseEvent event) {
        if (draw == 0){
            graficos.setStroke(lapizColor.getValue());
            if (i == 2 && j == 0){
            
                linea = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                linea.setStrokeWidth(3);
                linea.setStroke(lapizColor.getValue());
                linea.setId("Linea Recta: " + lineaR.toString());
                lista.getItems().add(linea.getId());
                zoomGroup.getChildren().add(linea);
                lineaR++;
                
            } else if (i == 2 && j == 1){
            
                linea = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                linea.setStrokeWidth(6);
                linea.setStroke(lapizColor.getValue());
                Node a = linea;
                a.setId("Linea Recta: " + lineaR.toString());
                lista.getItems().add(a.getId());
                zoomGroup.getChildren().add(a);
                lineaR++;
                
            } else if (i == 2 && j == 2){
            
                linea = new Line(event.getX(), event.getY(), event.getX(), event.getY());
                linea.setStrokeWidth(9);
                linea.setStroke(lapizColor.getValue());
                Node a = linea;
                a.setId("Linea Recta: " + lineaR.toString());
                lista.getItems().add(a.getId());
                zoomGroup.getChildren().add(a);
                lineaR++;
                
            } else if (i == 0 && j == 0){
                
                graficos.setStroke(lapizColor.getValue());
                graficos.setLineWidth(3);
                graficos.beginPath();
                graficos.moveTo(event.getX(), event.getY());
                graficos.stroke();
                
            } else if (i == 0 && j == 1){
                
                graficos.setStroke(lapizColor.getValue());
                graficos.setLineWidth(6);
                graficos.beginPath();
                graficos.moveTo(event.getX(), event.getY());
                graficos.stroke();
                
            }else if (i == 0 && j == 2){
                
                graficos.setStroke(lapizColor.getValue());
                graficos.setLineWidth(9);
                graficos.beginPath();
                graficos.moveTo(event.getX(), event.getY());
                graficos.stroke();
                
            }else if (i == 1 && j == 0){
                arquito = new Arc (50.0, 50.0, 100.0, 100.0, 0.0, 180.0);
                arquito.setStroke(lapizColor.getValue());
                arquito.setFill(Color.TRANSPARENT);
                Node a = arquito;
                a.setId("Linea Curva: " + LineaC.toString());
                lista.getItems().add(a.getId());
                zoomGroup.getChildren().add(a);
                LineaC++;

           
                arquito.setCenterX(event.getX());
                arquito.setCenterY(event.getY());
                iXArco = event.getX();
                iYArco = event.getY();
                arquito.setStrokeWidth(3);
                
                
            }else if (i == 1 && j == 1){
                arquito = new Arc (50.0, 50.0, 100.0, 100.0, 0.0, 180.0);
                arquito.setStroke(lapizColor.getValue());
                arquito.setFill(Color.TRANSPARENT);
                Node a = arquito;
                a.setId("Linea Curva: " + LineaC.toString());
                lista.getItems().add(a.getId());
                zoomGroup.getChildren().add(a);
                LineaC++;
           
                arquito.setCenterX(event.getX());
                arquito.setCenterY(event.getY());
                iXArco = event.getX();
                iYArco = event.getY();
                arquito.setStrokeWidth(6);
            } else {
                arquito = new Arc (50.0, 50.0, 100.0, 100.0, 0.0, 180.0);
                arquito.setStroke(lapizColor.getValue());
                arquito.setFill(Color.TRANSPARENT);
                Node a = arquito;
                a.setId("Linea Curva: " + LineaC.toString());
                lista.getItems().add(a.getId());
                zoomGroup.getChildren().add(a);
                LineaC++;
           
                arquito.setCenterX(event.getX());
                arquito.setCenterY(event.getY());
                iXArco = event.getX();
                iYArco = event.getY();
                arquito.setStrokeWidth(9);
            }
        }else {
            graficos.closePath();
            event.consume();
        }
    }
    
    @FXML
    private void masZoom(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    private void menosZoom(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    } 

    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = mapita.getHvalue();
        double scrollV = mapita.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        mapita.setHvalue(scrollH);
        mapita.setVvalue(scrollV);
    }
    
    @FXML
    private void limpiar(MouseEvent event) {
        
        graficos.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        i = -1;
        j = -1;
        draw = 1;
        p = -1; 
        pl = -1;
        tipo.setText("TIPO");
        grossor.setText("GROSSOR");
        regliton.visibleProperty().set(false);
        
        while (lista.getItems().size() > 0){
            lista.getItems().remove(0);
        }
        
        while (zoomGroup.getChildren().size() > 0) {
                zoomGroup.getChildren().remove(zoomGroup.getChildren().get(1));
        }
        
        lineaR = 0;
        LineaC = 0;
        Puntitos = 0;
        cruz = 0;
    }

    @FXML
    private void regla(MouseEvent event) {
        Image reglita = new Image (getClass().getResourceAsStream("mapa/transportador.png"));
        regliton.setImage(reglita);
        if (z == 0){
            regliton.visibleProperty().set(true);
            z++;
        } else {
            regliton.visibleProperty().set(false);
            z--;
        }
    }

    @FXML
    private void reglitaCaida(MouseEvent event) {
        if (regliton.visibleProperty().get() == true){
            regliton.setX(event.getX());
            regliton.setY(event.getY());
        } else {
            //NADA, pero por si acaso
        }
    }

    @FXML
    private void genText(MouseEvent event) {
        if (textoEsc.equals("null") == false || textoEsc.equals("") == false){
            graficos.setFill(textColor.getValue());
            graficos.setFont(new Font(15));
            graficos.fillText(textoEsc.getText(), event.getSceneX() -15 , event.getSceneY() - 100);
            
        }
    }
    @FXML
    private void listClicked(MouseEvent event) {
        //selecciona elemento de la listview
        popo = lista.getSelectionModel().getSelectedIndex() + 1;
    }

    @FXML
    private void camColor(MouseEvent event) {
        //elemento del listview y que esta en el canvas
        if (popo != null || popo > -1){
            Node amigo = zoomGroup.getChildren().get(popo);
            
            if (amigo.getId().contains("Linea Curva: ")){
                Arc arcos = (Arc) amigo;
                arcos.setStroke(ultimo.getValue());
                   
            } else if (amigo.getId().contains ("Linea Recta: ")){
                Line lin = (Line) amigo;
                lin.setStroke(ultimo.getValue());
            } else if (amigo.getId().contains("Cruz part1: ")){
                Line lin = (Line) amigo;
                lin.setStroke(ultimo.getValue());
                amigo = zoomGroup.getChildren().get(popo + 1);
                lin = (Line) amigo;
                lin.setStroke(ultimo.getValue());
            } else if (amigo.getId().contains("Cruz part2: ")){
                Line lin = (Line) amigo;
                lin.setStroke(ultimo.getValue());
                amigo = zoomGroup.getChildren().get(popo - 1);
                lin = (Line) amigo;
                lin.setStroke(ultimo.getValue());
            } else {
                Circle cir = (Circle) amigo;
                cir.setStroke (ultimo.getValue());
                cir.setFill (ultimo.getValue());
            }
        }
    }

    @FXML
    private void eliminar(MouseEvent event) {
        //elemento del listview y que esta en el canvas
        if (popo != null || popo > -1){
            
            zoomGroup.getChildren().remove(zoomGroup.getChildren().get(popo));
            lista.getItems().remove(popo - 1);
            
        }
    }
    
    @FXML
    private void punto(MouseEvent event) {
        if (pipi == 0){
                
                linea = new Line(0, event.getY(), mapa.getFitWidth(), event.getY());
                linea.setStrokeWidth(3);
                linea.setStroke(BLACK);
                linea.setId("Cruz part1: " + cruz);
                lista.getItems().add(linea.getId());
                zoomGroup.getChildren().add(linea);        
                
                linea = new Line(event.getX(), 0, event.getX(), mapa.getFitHeight());
                linea.setStrokeWidth(3);
                linea.setStroke(BLACK);
                linea.setId("Cruz part2: " + cruz);
                lista.getItems().add(linea.getId());
                zoomGroup.getChildren().add(linea);
                
                cruz++;
        }
        
        if (p == 0){
            
            if (pl == 0){
                Circle circle = new Circle(1);
                circle.setFill(puntoColor.getValue());
            
                circle.setId("Circulo: " + Puntitos);
                lista.getItems().add(circle.getId());
                zoomGroup.getChildren().add(circle);
                
                Puntitos++;
        
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                circle.setRadius(10);
            } else if (pl == 1){
                Circle circle = new Circle(1);
                circle.setFill(puntoColor.getValue());
            
                circle.setId("Circulo: " + Puntitos);
                lista.getItems().add(circle.getId());
                zoomGroup.getChildren().add(circle);
                
                Puntitos++;
        
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                circle.setRadius(20);
            } else if (pl == 2){
                Circle circle = new Circle(1);
                circle.setFill(puntoColor.getValue());
            
                circle.setId("Circulo: " + Puntitos);
                lista.getItems().add(circle.getId());
                zoomGroup.getChildren().add(circle);
            
                Puntitos++;
                
                circle.setCenterX(event.getX());
                circle.setCenterY(event.getY());
                circle.setRadius(30);
            } else {
                
            }    
        }
    }

    @FXML
    private void puntoS(MouseEvent event) {
        if (p != 0){
            p = 0;
        } else {
            p = -1;
        }
    }

    @FXML
    private void Trectazo(ActionEvent event) {
        i = 2;
        tipo.setText(Trectisimo.getText());
    }

    @FXML
    private void SelGp1(ActionEvent event) {
        pl = 0;
        grossor1.setText(Gp1.getText()); 
    }

    @FXML
    private void SelGm1(ActionEvent event) {
        pl = 1;
        grossor1.setText(Gm1.getText());
    }

    @FXML
    private void SelGg1(ActionEvent event) {
        pl = 2;
        grossor1.setText(Gg1.getText());
    }

    @FXML
    private void marcoP(MouseEvent event) {
        if (pipi != 0){
            pipi = 0;
        } else {
            pipi = -1;
        }
    }

    @FXML
    private void muestraPosicion(MouseEvent event) throws InterruptedException {
        PosX.setText("PosX: " + (int) event.getSceneX());
        PosY.setText("PosY: " + (int) event.getSceneY());
    }
    
    
}
