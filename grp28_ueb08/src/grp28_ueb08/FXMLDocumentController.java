/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp28_ueb08;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author thomasd
 */
public class FXMLDocumentController implements Initializable {
    
    CalculatorWork calculator = new CalculatorWork();
    @FXML
    private TextField txtFldDisplay;
    @FXML
    private Button btnC;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    

    @FXML
    private void handleBtnDigit(ActionEvent event) {
        
        if("=".equals(calculator.getOperator()) || "MR".equals(calculator.getLastInput())){
            btnC.fire();
            txtFldDisplay.setText(((Button)(event.getSource())).getText());
        }else if(calculator.anyOperator() == true){
            txtFldDisplay.setText("");
            String oldText = txtFldDisplay.getText();
            txtFldDisplay.setText(oldText + ((Button)(event.getSource())).getText());
        }else {
            String oldText = txtFldDisplay.getText();
            txtFldDisplay.setText(oldText + ((Button)(event.getSource())).getText());
        }
        calculator.setLastInput(((Button)(event.getSource())).getText());
    }

    @FXML
    private void handleBtnPoint(ActionEvent event) {
        calculator.setLastInput(((Button)(event.getSource())).getText());
        String oldText = txtFldDisplay.getText();
        if(!oldText.contains(".")){
            txtFldDisplay.setText(oldText + ".");
        }else
            txtFldDisplay.setText(oldText);
    }

    @FXML
    private void handleBtnCalc(ActionEvent event) {
        //TODO make calculations, set back or make operations, adjust display, exceptions
        calculator.setLastInput(((Button)(event.getSource())).getText());
        double value = 0;
        
        if("".equals(txtFldDisplay.getText())){
            calculator.setOperator("+");
            calculator.setOp1(value);
            calculator.setOp2(value);
        }else if (calculator.getOp1() == null){
            calculator.setOp1(Double.valueOf(txtFldDisplay.getText()));
            calculator.setOperator("+");
            calculator.setOp2(value);
        }else if (calculator.getOp2() == null){
            value = Double.valueOf(txtFldDisplay.getText());
            calculator.setOp2(value);
        }else {
            calculator.setOp2(Double.valueOf(txtFldDisplay.getText()));
        }
              
        txtFldDisplay.setText(String.valueOf(calculator.calculate()));
        
    }

    @FXML
    private void handleBtnOpr(ActionEvent event) {

        calculator.setLastInput(((Button)(event.getSource())).getText());
        try {  
            double value;
            if("=".equals(calculator.getOperator()) || calculator.getOp1() == null){
                value = Double.valueOf(txtFldDisplay.getText());
                calculator.setOp1(value);
                calculator.setOperator(((Button)(event.getSource())).getText());
                //txtFldDisplay.setText("");            
            }else if (calculator.getLastInput() != calculator.getOperator()){
                calculator.setOp2(Double.valueOf(txtFldDisplay.getText()));
                calculator.calculate();
                calculator.setOperator(((Button)(event.getSource())).getText());
                //txtFldDisplay.setText("");   
            }
        }
        catch (NumberFormatException e) { // java.lang.reflect.InvocationTargetException) {
            System.err.println("Input error!"); 
            System.err.println("Repeatedly pressed: '" + ((Button)(event.getSource())).getText() + "'");
            calculator.setOperator(((Button)(event.getSource())).getText());
        }
        
    }

    @FXML
    private void handleBtnClear(ActionEvent event) {
        calculator.setLastInput(((Button)(event.getSource())).getText());
        if( "AC".equals(((Button)(event.getSource())).getText())){
            calculator.clearMemory();          
        }else if(calculator.getOp1() != null && !"".equals(txtFldDisplay.getText())){
            calculator.setOp2(null);
        }else{
            calculator.setOp1(null);
        }
        txtFldDisplay.setText("");
    }

    @FXML
    private void handleBtnMemory(ActionEvent event) {

        calculator.setLastInput(((Button)(event.getSource())).getText());
        calculator.setOperator(((Button)(event.getSource())).getText());
        double value;
        if(!"".equals(txtFldDisplay.getText())){
            value = Double.valueOf(txtFldDisplay.getText()); 
        }else
            value = 0.0;  
        
        if("MC".equals(calculator.getLastInput())) {
            calculator.calcMemory(value);
            txtFldDisplay.setText("");
        }else if("MR".equals(calculator.getLastInput())){
            calculator.calcMemory(value);
            txtFldDisplay.setText(String.valueOf(calculator.getMemop1()));
        }else {
            calculator.calcMemory(value);
        }

        
        
        
        /*
        //MR dann andere Zahl...Operation starten
        double value;
        if(!"".equals(txtFldDisplay.getText())){
            value = Double.valueOf(txtFldDisplay.getText()); 
        }else
            value = 0.0;     
        
        if("MR".equals(((Button)(event.getSource())).getText()) || "MC".equals(calculator.getLastInput())){
           if(calculator.getMemop1() == 0.0 || "MC".equals(calculator.getLastInput())){
               txtFldDisplay.setText("");
               
            }else {
                txtFldDisplay.setText(String.valueOf(calculator.getMemop1()));
            } 
        }else {
            calculator.setOperator(((Button)(event.getSource())).getText());
            calculator.calcMemory(value);
        }
        
        if(!"MR".equals(((Button)(event.getSource())).getText())) {
            calculator.setOperator(((Button)(event.getSource())).getText());
            calculator.calcMemory(value);
        }else{
            if(calculator.getMemop1() == 0.0){
               txtFldDisplay.setText(""); 
            }else {
                txtFldDisplay.setText(String.valueOf(calculator.getMemop1()));
            }
        }*/
    }
    
    
}
