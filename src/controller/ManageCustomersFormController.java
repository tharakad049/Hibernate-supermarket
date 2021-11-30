package controller;

import Hibernate.util.FactoryConfiguration;
import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.CustomerDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CustomerTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageCustomersFormController {
    private final CustomerBO cusBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnAddNewCustomer;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TableView<CustomerTM> tblCustomers;
    public JFXButton btnReport;

    public void initialize() {
/*        cmbCustomerTitle.getItems().addAll("Male", "Female");
        cmbCustomerTitle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });*/

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Title"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblCustomers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("City"));
        tblCustomers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Province"));
        tblCustomers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        initUI();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                //cmbGender.getValue();
                //cmbCustomerTitle.setAccessibleText(newValue.getTitle());
                //cmbCustomerTitle.setPromptText(newValue.getTitle());
                txtCustomerTitle.setText(newValue.getTitle());
                txtCustomerName.setText(newValue.getName());
                txtCustomerAddress.setText(newValue.getAddress());
                txtCustomerCity.setText(newValue.getCity());
                txtCustomerProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

                txtCustomerId.setDisable(false);
                //cmbCustomerTitle.setDisable(false);
                txtCustomerTitle.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
                txtCustomerCity.setDisable(false);
                txtCustomerProvince.setDisable(false);
                txtPostalCode.setDisable(false);
            }
        });
        txtCustomerAddress.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomers.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = cusBO.getAllCustomer();
            for (CustomerDTO customer : allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(customer.getId(),customer.getTitle(), customer.getName(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtCustomerId.clear();
        //cmbCustomerTitle.getSelectionModel().clearSelection();
        txtCustomerTitle.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtPostalCode.clear();

        txtCustomerId.setDisable(true);
        //cmbCustomerTitle.setDisable(true);
        txtCustomerTitle.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCustomerCity.setDisable(true);
        txtCustomerProvince.setDisable(true);
        txtPostalCode.setDisable(true);

        txtCustomerId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) {
        txtCustomerId.setDisable(false);
        //cmbCustomerTitle.setDisable(false);
        txtCustomerTitle.setDisable(false);
        txtCustomerName.setDisable(false);
        txtCustomerAddress.setDisable(false);
        txtCustomerCity.setDisable(false);
        txtCustomerProvince.setDisable(false);
        txtPostalCode.setDisable(false);

        txtCustomerId.clear();
        txtCustomerId.setText(generateNewId());
        //cmbCustomerTitle.getSelectionModel().clearSelection();
        txtCustomerTitle.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtPostalCode.clear();

        txtCustomerName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblCustomers.getSelectionModel().clearSelection();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        //String title = String.valueOf(cmbCustomerTitle.getValue());
        String title = txtCustomerTitle.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String city = txtCustomerCity.getText();
        String province = txtCustomerProvince.getText();
        String postalCode = txtPostalCode.getText();

        if (!title.matches("^(Male|Female)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Title. You can entered 'Male' or 'Female' ").show();
            txtCustomerTitle.requestFocus();
            return;

        }else if (!name.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Name should be at least 3 characters long").show();
            txtCustomerName.requestFocus();
            return;

        }else if (!address.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCustomerAddress.requestFocus();
            return;

        }else if (!city.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "City should be at least 3 characters long").show();
            txtCustomerCity.requestFocus();
            return;

        }else if (!province.matches("^[A-z]{3,}$")) {
            new Alert(Alert.AlertType.ERROR, "Province should be at least 3 characters long").show();
            txtCustomerProvince.requestFocus();
            return;

        }else if (!postalCode.matches("^[1-9]{3,5}(s)$")) {
            new Alert(Alert.AlertType.ERROR, "Postal Code should be at least 3 letters and, finally you should entered simple 's' ").show();
            txtPostalCode.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                CustomerDTO customerDTO = new CustomerDTO(id,title, name,  address, city, province, postalCode);
                cusBO.addCustomer(customerDTO);
                tblCustomers.getItems().add(new CustomerTM(id, title, name,  address, city, province, postalCode));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
                CustomerDTO customerDTO = new CustomerDTO(id,title, name,  address, city, province, postalCode);
                cusBO.updateCustomer(customerDTO);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
            selectedCustomer.setTitle(title);
            selectedCustomer.setName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setCity(city);
            selectedCustomer.setPostalCode(province);
            selectedCustomer.setPostalCode(postalCode);
            tblCustomers.refresh();
        }
        btnAddNewCustomer.fire();
    }

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return cusBO.ifCustomerExist(id);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = tblCustomers.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            cusBO.deleteCustomer(id);
            tblCustomers.getItems().remove(tblCustomers.getSelectionModel().getSelectedItem());
            tblCustomers.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getId();
    }

    private String generateNewId() {
        try {
            return cusBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblCustomers.getItems().isEmpty()) {
            return "C-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C-", "")) + 1;
            return String.format("C-%03d", newCustomerId);
        }
    }

    public void customerReport(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../reports/CustomerReport.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, (Connection) FactoryConfiguration.getInstance().getSession());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void searchCustomer(ActionEvent actionEvent){
    }
}
