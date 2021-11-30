package controller;

import Hibernate.util.FactoryConfiguration;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.IOException;
import java.sql.SQLException;

public class SystemReportsFormController {
    public AnchorPane reportPane;
    public ImageView imgWellcome;
    public ImageView imgCustomer;
    public ImageView imgItems;
    public Label lblMenu1;
    public Label lblMenu2;
    public Label lblMenu;
    public ImageView imgOrder;
    public Label lblMenu3;
    public ImageView imgOrderDetails;
    public Label lblMenu4;

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent reportPane = null;

            switch (icon.getId()) {
                case "imgWellcome":
                    try {
                        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/Wellcome.jrxml"));
                        JasperReport compileReport = JasperCompileManager.compileReport(design);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, FactoryConfiguration.getInstance().getSession());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    break;

                case "imgCustomer":
                    try {
                        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/CustomerReport.jrxml"));
                        JasperReport compileReport = JasperCompileManager.compileReport(design);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, FactoryConfiguration.getInstance().getSession());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    break;

                case "imgItems":
                    try {
                        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/ItemReport.jrxml"));
                        JasperReport compileReport = JasperCompileManager.compileReport(design);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, FactoryConfiguration.getInstance().getSession());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    break;

                case "imgOrder":
                    try {
                        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/OrderReport.jrxml"));
                        JasperReport compileReport = JasperCompileManager.compileReport(design);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, FactoryConfiguration.getInstance().getSession());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    break;
                case "imgOrderDetails":
                    try {
                        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/OrderDetails.jrxml"));
                        JasperReport compileReport = JasperCompileManager.compileReport(design);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, FactoryConfiguration.getInstance().getSession());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                    break;

            }
                if (reportPane != null) {
                    Scene subScene = new Scene(reportPane);
                    Stage primaryStage = (Stage) this.reportPane.getScene().getWindow();
                    primaryStage.setScene(subScene);
                    primaryStage.centerOnScreen();

                    TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                    tt.setFromX(-subScene.getWidth());
                    tt.setToX(0);
                    tt.play();

                }
            }
        }
    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgWellcome":
                    lblMenu.setText("Welcome Report");
                    break;
                case "imgCustomer":
                    lblMenu1.setText("Customer Report");
                    break;
                case "imgItems":
                    lblMenu2.setText("Item Report");
                    break;
                case "imgOrder":
                    lblMenu3.setText("Order Report");
                    break;
                case "imgOrderDetails":
                    lblMenu4.setText("Order Details Report");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BLACK);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("REPORT");
            lblMenu1.setText("REPORT");
            lblMenu2.setText("REPORT");
            lblMenu3.setText("REPORT");
            lblMenu4.setText("REPORT");
        }
    }
}
