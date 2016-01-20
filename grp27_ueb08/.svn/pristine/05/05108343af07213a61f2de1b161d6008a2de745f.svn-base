/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp27_ueb08;

/**
 *
 * @author Robin
 */
public class Calculator {

    private Double op1 = null;
    private Double op2 = null;
    private String operator;
    private Double memory = null;

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }
    

    public Double getOp1() {
        return op1;
    }

    public void setOp1(Double op1) {
        this.op1 = op1;
    }

    public Double getOp2() {
        return op2;
    }

    public void setOp2(Double op2) {
        this.op2 = op2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double calculate() {
        double result = 0;

        switch (operator) {

            case "+":
                result = (op1 + op2);
                break;

            case "-":
                result = (op1 - op2);
                break;

            case "*":
                result = (op1 * op2);
                break;

            case "/":
                result = (op1 / op2);
                break;

        }

        return result;

    }

}
