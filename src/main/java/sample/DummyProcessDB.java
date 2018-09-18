/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.ArrayList;
import java.util.List;

public class DummyProcessDB {
    public static List<ProcessLine> getListOfCars() {
        List<ProcessLine> list = new ArrayList<ProcessLine>();
        list.add(new ProcessLine(10,"08-2015"));
        list.add(new ProcessLine(9,"09-2015"));
        list.add(new ProcessLine(15,"10-2015"));
        list.add(new ProcessLine(32,"11-2015"));
        list.add(new ProcessLine(15,"12-2015"));
        return list;
    }
}
