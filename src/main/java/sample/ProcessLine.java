/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author HP
 */
public class ProcessLine {
    
    
    public ProcessLine() {
        super();
    }
        
    private int errors;
    private String date;
    
    public ProcessLine(int errors, String date){
        super();
        this.errors = errors;
        this.date = date;
    }

    /**
     * @return the errors
     */
    public int getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(int errors) {
        this.errors = errors;
    }

    /**
     * @return the total
     */
   

    /**
     * @return the name
     */
    public String getDate() {
        return date;
    }

    /**
     * @param name the name to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
