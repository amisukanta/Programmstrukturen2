/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp28_ueb08;

/**
 *
 * @author thomasd
 */
public class CalculatorWork {
    private Double op1;
    private Double op2;
    private Double memOp1 = 0.0; //nuull setzen
    private String operator;
    private String lastInput;
    
    //TODO memory function 
    
    public Double getOp1() {
        return op1;
    }

    public void setOp1(Double op1) {
        //leading "." is missing
        this.op1 = op1;
    }

    public Double getOp2() {
        return op2;
    }

    public void setOp2(Double op2) {
        //leading "." is missing       
        this.op2 = op2;
    }

    public Double getMemop1() {
        return memOp1;
    }
    
    public void setMemOp1(Double memOp1) {
        this.memOp1 = memOp1;
    }   

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public String getLastInput() {
        return lastInput;
    }
    
    public void setLastInput(String lastInput) {
        this.lastInput = lastInput;
    }
    
    public boolean anyOperator(){
        return "+".equals(getLastInput()) || "-".equals(getLastInput()) 
                || "*".equals(getLastInput()) || "/".equals(getLastInput());
    }
    
    public double calculate() {
        double result = 0;
        
        switch (getOperator()){
            case "+":
                result = getOp1() + getOp2();
                break;
            case "-":
                result = getOp1() - getOp2();
                break;
            case "*":
                result = getOp1() * getOp2();
                break;
            case "/":
                if (getOp2() != 0){
                    result = getOp1() / getOp2();
                    break;
                }else{
                    //throw exception maybe
                    result = getOp1() / getOp2();
                }
        }
        setOp1(result);
        setOperator("=");
        return result;
    }
    public void calcMemory (Double value) {
        
        switch (getOperator()){
            case "M+":
                memOp1 = memOp1 + value;
                break;
            case "M-":
                memOp1 = memOp1 - value;
                break;
            case "MC":
                memOp1 = 0.0;
                break;
        }
        
    }
    public void clearMemory() {
        setOp1(null);
        setOp2(null);
        setOperator(null);
    }
    
}
