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
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btnSub;
    @FXML
    private Button btn0;
    @FXML
    private Button btnPoint;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtFldDisplay;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btnMC;
    @FXML
    private Button btnMR;
    @FXML
    private Button btnMadd;
    @FXML
    private Button btnC;
    @FXML
    private Button btnMsub;
    @FXML
    private Button btnAC;
    @FXML
    private Button btnSub1;
    @FXML
    private Button btnSub2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnDigit(ActionEvent event) {
        if("=".equals(calculator.getOperator())){
            btnC.fire();
            txtFldDisplay.setText(((Button)(event.getSource())).getText());
        }else {
            String oldText = txtFldDisplay.getText();
            txtFldDisplay.setText(oldText + ((Button)(event.getSource())).getText());
        }
        
    }

    @FXML
    private void handleBtnPoint(ActionEvent event) {
        String oldText = txtFldDisplay.getText();
        if(!oldText.contains(".")){
            txtFldDisplay.setText(oldText + ".");
        }else
            txtFldDisplay.setText(oldText);
    }

    @FXML
    private void handleBtnCalc(ActionEvent event) {
        //TODO make calculations, set back or make operations, adjust display, exceptions
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
        calculator.setOperator("=");
    }

    @FXML
    private void handleBtnOpr(ActionEvent event) {
        //TODO exceptions if txtFldDisplay is empty or wrong format?
        double value;
        if("=".equals(calculator.getOperator()) || calculator.getOp1() == null){
            value = Double.valueOf(txtFldDisplay.getText());
            calculator.setOp1(value);
            calculator.setOperator(((Button)(event.getSource())).getText());
            txtFldDisplay.setText("");            
        }else{
            calculator.setOp2(Double.valueOf(txtFldDisplay.getText()));
            calculator.calculate();
            txtFldDisplay.setText("");   
        }
        
    }

    @FXML
    private void handleBtnClear(ActionEvent event) {
        if( "AC".equals(((Button)(event.getSource())).getText())){
            calculator.setOp1(null);
            calculator.setOp2(null);
            calculator.setOperator(null);
            txtFldDisplay.setText("");
        }else if(calculator.getOp1() != null && !"".equals(txtFldDisplay.getText())){
            calculator.setOp2(null);
            txtFldDisplay.setText(""); 
        }else{
            calculator.setOp1(null);
            txtFldDisplay.setText("");
        }
    }

    @FXML
    private void handleBtnMemory(ActionEvent event) {
        
        double value;
        if(!"".equals(txtFldDisplay.getText())){
            value = Double.valueOf(txtFldDisplay.getText()); 
        }else
            value = 0.0;
               
        
        if(!"MR".equals(((Button)(event.getSource())).getText())) {
            calculator.setOperator(((Button)(event.getSource())).getText());
            calculator.calcMemory(value);
        }else{
            if(calculator.getMemop1() == 0.0){
               txtFldDisplay.setText("0.0"); 
            }else {
                txtFldDisplay.setText(String.valueOf(calculator.getMemop1()));
            }
        }
    }
    
    
}
