package controller;

import bo.custom.impl.CustomerBOImpl;
import com.jfoenix.controls.JFXButton;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {
    public Label lblMenu1;
    public Label lblMenu2;
    public Label lblMenu3;
    public Label lblDescription1;
    public Label lblDescription2;
    public Label lblDescription3;
    public AnchorPane mainAnchorPaneContext;
    public Label lblHomePage;
    public Label lblDate;
    public Label lblTime;
    public Label lblDescription4;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgCustomer;
    public ImageView imgItem;
    public ImageView imgOrder;
    public ImageView imgViewReports;
    public ImageView imgHomePage;
    public JFXButton btnAdministrator;
    public JFXButton btnCashier;
    public AnchorPane dashBoardAnchorPane;
    public Label lblCustomerCount;

    ResultSet rst = null;

    public void initialize(){
        loadDateAndTime();
        init();
    }

    private void init(){
        imgItem.setDisable(true);
        imgOrder.setDisable(true);
        imgViewReports.setDisable(true);
        imgCustomer.setDisable(true);
    }

    private void loadDateAndTime() {
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (true) {
                final String time = simpleTimeFormat.format(new Date());
                final String Date = simpleDayFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                    lblDate.setText(Date);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("COME AGAIN");
            lblDescription.setText("Please select one of above main operations.");

            lblMenu1.setText("COME AGAIN");
            lblDescription1.setText("Please select one of above main operations.");

            lblMenu2.setText("COME AGAIN");
            lblDescription2.setText("Please select one of above main operations.");

            lblMenu3.setText("COME AGAIN");
            lblDescription3.setText("Please select one of above main operations.");

            lblHomePage.setText("COME AGAIN");
            lblDescription4.setText("Please select one of above main operations.");
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Manage Customers");
                    lblDescription.setText("Click to add,edit,delete,search or view.");
                    break;

                case "imgItem":
                    lblMenu1.setText("Manage Items");
                    lblDescription1.setText("Click to add,edit,delete,search or view.");
                    break;

                case "imgOrder":
                    lblMenu2.setText("Place Orders");
                    lblDescription2.setText("Click here if you want to place a order");
                    break;

                case "imgViewReports":
                    lblMenu3.setText("System Reports");
                    lblDescription3.setText("Click if you want to get system reports.");
                    break;

                case "imgHomePage":
                    lblHomePage.setText("Home Page");
                    lblDescription4.setText("Click if you want to return home page");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BROWN);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            Parent dashBoardAnchorPane = null;

            switch (icon.getId()) {
                case "imgCustomer":
                    URL resource = getClass().getResource("/view/ManageCustomersForm.fxml");
                    Parent load = FXMLLoader.load(resource);
                    mainAnchorPaneContext.getChildren().clear();
                    mainAnchorPaneContext.getChildren().add(load);
                    break;

                case "imgItem":
                    resource = getClass().getResource("/view/ManageItemsForm.fxml");
                    load = FXMLLoader.load(resource);
                    mainAnchorPaneContext.getChildren().clear();
                    mainAnchorPaneContext.getChildren().add(load);
                    break;

                case "imgOrder":
                    resource = getClass().getResource("/view/ManageOrdersForm.fxml");
                    load = FXMLLoader.load(resource);
                    mainAnchorPaneContext.getChildren().clear();
                    mainAnchorPaneContext.getChildren().add(load);
                    break;

                case "imgViewReports":
                    resource = getClass().getResource("/view/SystemReportsForm.fxml");
                    load = FXMLLoader.load(resource);
                    mainAnchorPaneContext.getChildren().clear();
                    mainAnchorPaneContext.getChildren().add(load);
                    break;

                case "imgHomePage":
                    Stage stage = (Stage) mainAnchorPaneContext.getScene().getWindow();
                    resource = getClass().getResource("../view/DashboardForm.fxml");
                    load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    break;
            }

            if (dashBoardAnchorPane != null) {
                Scene subScene = new Scene(dashBoardAnchorPane);
                Stage primaryStage = (Stage) this.dashBoardAnchorPane.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void administratorOnAction(ActionEvent actionEvent) {
        imgViewReports.setDisable(false);
        imgItem.setDisable(false);
        imgCustomer.setDisable(true);
        imgOrder.setDisable(true);
    }

    public void cashierOnAction(ActionEvent actionEvent) {
        imgViewReports.setDisable(true);
        imgItem.setDisable(true);
        imgCustomer.setDisable(false);
        imgOrder.setDisable(false);
    }
}
