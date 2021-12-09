package Lesson5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AppData {
    private String[] header;
    private int[][] data;

    public AppData(String[] header, int[][] data){
        this.header = header;
        this.data = data;
    }

    public void saveToCSVFile(String filename){
        try (FileWriter writer = new FileWriter(filename, false)){
            writer.write(this.toString());
            writer.flush();
        } catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void loadFromCSVFile(String filename){
        try(FileReader reader = new FileReader(filename)){
            Scanner scanner = new Scanner(reader);

            boolean isHeader = true;
            while (scanner.hasNextLine()){
                //System.out.println(scanner.nextLine());
                String line = scanner.nextLine();
                if (isHeader) {
                    List<String> values = new ArrayList<String>();
                    try (Scanner rowScanner = new Scanner(line)) {
                        rowScanner.useDelimiter(";");
                        while (rowScanner.hasNext()) {
                            System.out.println(rowScanner.next());
                           // values.add(rowScanner.next().toString());
                        }
                    }
                    this.header = new String[values.size()];
                    for (int i = 0; i < values.size(); i++) {
                        header[i] = values.get(i);
                    }
                    //System.out.println(this.header);
                }
            }
        } catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < header.length; i++) {
            sb.append((i < header.length - 1)? header[i] + ";" : header[i]);
        }
        sb.append('\n');
        for (int i = 0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                sb.append((j < data[0].length - 1)? data[i][j] + ";" : data[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void readCSVFile(String filename){
        List<List<String>> records = new ArrayList<>();
        Boolean isHeader = true;
        try (Scanner scanner = new Scanner(new File(filename));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(records.toString());

        this.header = records.get(0).toArray(new String[0]);

        this.data = new int[records.size()][records.get(0).size()];
        for (int i = 1; i < records.size(); i++)
            for (int j = 0; j < records.get(i).size(); j++){
                this.data[i][j] = Integer.parseInt(records.get(i).get(j));
            }

    }
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
