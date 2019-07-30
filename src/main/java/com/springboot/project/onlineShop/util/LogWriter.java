package com.springboot.project.onlineShop.util;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.*;

@Component
public class LogWriter {

//    private static final String OUTPUT_PATH = "/Users/shangtingli/Desktop/log.txt";
    private static final String OUTPUT_PATH = "C:\\Users\\i514767\\Desktop\\log.txt";

    private List<String> logs = new ArrayList<>();

    private List<String> status = new ArrayList<>();

    private Map<String,Integer> cartItemCounts = new HashMap<>();

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
            String temp = str;
            temp.replace("Request Completed For Customer", "");
            if (!cartItemCounts.containsKey(temp)){
                cartItemCounts.put(temp,0);
            }
            cartItemCounts.put(temp,cartItemCounts.get(temp)+1);
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
            for (int i=0; i < logs.size(); i++){
                String log = logs.get(i);
                fw.write("Event " + i + ":  " + log + "\n");
            }
            success = Collections.frequency(status,"Success");
            failure = Collections.frequency(status,"Failed");
            fw.write("\n");
            fw.write("Success: " + success + "\n");
            fw.write("Failure: " + failure + "\n");
            fw.write("\n");
            for (Map.Entry<String,Integer> entry : cartItemCounts.entrySet()){
                String toWrite = "Customer " + entry.getKey() + " gets : " + entry.getValue()+ " products.\n";
                fw.write(toWrite);
            }
            fw.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}