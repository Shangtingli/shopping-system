package com.springboot.project.onlineShop.model;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class LogWriter {

    private static final String OUTPUT_PATH = "C:\\Users\\i514767\\Desktop\\logs.txt";

    private List<String> logs = new ArrayList<>();

    private List<String> status = new ArrayList<>();

    private int success = -1, failure = -1;

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }


    public void insert(String str){
        if (str.equals("Request Not Completed")){
            status.add("Failed");
        }
        else if (str.startsWith("Request Completed For Customer")){
            status.add("Success");
        }
        else{
            status.add("Signal For Sending Message");
        }
        logs.add(str);
    }

    public void write(){
        try{
            FileWriter fw=new FileWriter(OUTPUT_PATH);
            for (String log : logs){
                fw.write(log + "\n");
            }
            success = Collections.frequency(status,"Success");
            failure = Collections.frequency(status,"Failed");
            fw.write("Success: " + success + "\n");
            fw.write("Failure: " + failure + "\n");
            fw.close();
        }catch(Exception e){System.out.println(e);}
    }

}
