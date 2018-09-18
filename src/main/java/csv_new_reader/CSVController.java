package csv_new_reader;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CSVController implements Initializable{

    @FXML
    private Label lbl_current_name;
    @FXML
    private Label lbl_current_session;
    @FXML
    private Label lbl_next_name;
    @FXML
    private Label lbl_next_session;
    @FXML
    private Label lbl_slot;
    @FXML
    private Label lbl_current_production_line;

    public void initialize(URL location, ResourceBundle resources) {
        add_data();
    }

    private static ArrayList<ArrayList<ArrayList<String[]>>> data=new ArrayList<ArrayList<ArrayList<String[]>>>();
    private static int current_production_line=0;
    private static int current_slot=0;
    private static LocalTime session_length;
    private static int production_lines;

    private void add_data(){
        try{
            List<String> lines= Files.readAllLines(Paths.get("src/main/java/csv_new_reader/employee_data.csv"));
            production_lines=Integer.parseInt(lines.get(0).split(",")[1]);
            int session_length_hour=Integer.parseInt(lines.get(1).split(",")[1].split(":")[0]);
            int session_length_minute=Integer.parseInt(lines.get(1).split(",")[1].split(":")[1]);
            //int[] no_of_slots=Integer.parseInt(lines.get(2).split(",")[1]);
            session_length=LocalTime.of(session_length_hour, session_length_minute);

            for(int i=0;i<production_lines;i++){
                data.add(new ArrayList<ArrayList<String[]>>());
                for(int j=0;j<Integer.parseInt(lines.get(2).split(",")[i+1]);j++){
                    data.get(i).add(new ArrayList<String[]>());
                }
            }

            lines.remove(0);
            lines.remove(0);
            lines.remove(0);
            lines.remove(0);

            for(String line : lines){
                line=line.replace("\"","");
                String []result=line.split(",");
                data.get(Integer.parseInt(result[3])-1).get(Integer.parseInt(result[2])-1).add(result);
            }

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    private void show_employee_data(){
        String name="";
        String session="";
        String slot="";
        LocalTime current_time=LocalTime.now();
        Duration dur1=Duration.between(LocalTime.MIN, session_length);
        lbl_current_production_line.setText(String.valueOf(current_production_line));
        for (int i=0;i<data.get(current_production_line).get(current_slot).size();i++) {
            int employee_hour = Integer.parseInt(data.get(current_production_line).get(current_slot).get(i)[1].substring(0, 2));
            int employee_minute = Integer.parseInt(data.get(current_production_line).get(current_slot).get(i)[1].substring(3, 5));
            LocalTime start_time = LocalTime.of(employee_hour, employee_minute);
            Duration dur2 = Duration.between(start_time, current_time);
            ///Duration dur3=Duration.between(start_time,);
            if (dur2.get(ChronoUnit.SECONDS) >= 0 && dur1.get(ChronoUnit.SECONDS) > dur2.get(ChronoUnit.SECONDS)) {

                name = data.get(current_production_line).get(current_slot).get(i)[0];
                slot = data.get(current_production_line).get(current_slot).get(i)[2];
                lbl_current_name.setText(name);
                lbl_slot.setText(slot);
                lbl_current_session.setText(LocalTime.now().toString().substring(0, 8));

                if ((data.get(current_production_line).get(current_slot).size() - 1) != i) {
                    name = data.get(current_production_line).get(current_slot).get(i + 1)[0];
                    session = data.get(current_production_line).get(current_slot).get(i + 1)[1];
                    lbl_next_name.setText(name);
                    lbl_next_session.setText(session);
                }
                break;
            }
        }
    }

    public void showNextData(ActionEvent actionEvent) {
        if(current_slot==data.get(current_production_line).size()-1){
            current_slot=0;
        }else{
            current_slot=current_slot+1;
        }
        show_employee_data();;
    }

    public void showPreviousData(ActionEvent actionEvent) {
        if(current_slot==0){
            current_slot=data.get(current_production_line).size()-1;
        }else{
            current_slot=current_slot-1;
        }
        show_employee_data();
    }

    public void showEmployeeData(ActionEvent actionEvent) {

        show_employee_data();
    }

    public void nextProductionLine(ActionEvent actionEvent) {
        if(current_production_line==production_lines-1){
            current_production_line=0;
        }else{
            current_production_line+=1;
        }
        current_slot=0;
        show_employee_data();
    }
}