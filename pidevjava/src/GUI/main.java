/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author mahas
 */
public class main extends Application {
    Pagination pagination;
    public void start(final Stage stage) throws Exception{
        pagination = new Pagination(10);
        pagination.setStyle(".fx-border-color: blue");
        pagination.setPageFactory((Integer pageIndex)-> createPage(pageIndex));
   
        AnchorPane anchor = new AnchorPane();
       
    AnchorPane.setLeftAnchor(pagination, 10.0);
    AnchorPane.setRightAnchor(pagination, 10.0);
    anchor.getChildren().add(pagination);
    Scene scene= new Scene(anchor);
    stage.setScene(scene);
    stage.show();
    
    
    }

    private VBox createPage(Integer pageIndex) {
    VBox pageBox = new VBox();
    Label pageLabel = new Label("page : "+(pageIndex+1));
   pageBox.getChildren().add(pageLabel);
    return pageBox;
    }
    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
