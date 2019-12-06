package werner.e11.e2;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyTableModel extends AbstractTableModel {

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> surnames = new ArrayList<>();
    private ArrayList<Date> dates = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private ArrayList<Integer> heights = new ArrayList<>();
    private ArrayList<Integer> weights = new ArrayList<>();
    private ArrayList<Integer> bmi = new ArrayList<>();

    private ArrayList<Object> data = new ArrayList<>();
    private ArrayList<String> colNames = new ArrayList<>();

    private void readData() {
        colNames.addAll(Arrays.asList("Imie", "Nazwisko", "Dzień ur.", "Ulub. kolor", "Wzrost[cm]", "Waga[kg]", "BMI"));
        BufferedReader br;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        double h, w;
        int i;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("data/gui/tableData"), StandardCharsets.UTF_8));
            String st;
            String[] arr;
            String[] brr;
            while ((st = br.readLine()) != null) {
                arr = st.split("\\s+");
                names.add(arr[0]);
                surnames.add(arr[1]);
                dates.add(sdf.parse(arr[2]));
                brr = arr[3].split(",");
                colors.add(new Color(
                        Integer.parseInt(brr[0]),
                        Integer.parseInt(brr[1]),
                        Integer.parseInt(brr[2])
                ));
                heights.add(Integer.parseInt(arr[4]));
                weights.add(Integer.parseInt(arr[5]));
                h = Double.parseDouble(arr[4])/100;
                w = Double.parseDouble(arr[5]);
                i = (int)(w/(h*h));
                bmi.add(i);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        data.add(names);
        data.add(surnames);
        data.add(dates);
        data.add(colors);
        data.add(heights);
        data.add(weights);
        data.add(bmi);
    }

    MyTableModel() {
        readData();
    }

    @Override
    public int getRowCount() {
        return names.size();
    }

    @Override
    public int getColumnCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((ArrayList)data.get(columnIndex)).get(rowIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colNames.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return ((ArrayList)data.get(columnIndex)).get(0).getClass();
    }
}
