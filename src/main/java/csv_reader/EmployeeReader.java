
package csv_reader;

import javax.swing.JFrame;

public class EmployeeReader {
    
    public static void main(String[] args) {
        JFrame test=new JFrame("App");
        test.setContentPane(new EmployeeDisplay().getContentPane());
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.pack();
        test.setVisible(true);
    }
    
}
