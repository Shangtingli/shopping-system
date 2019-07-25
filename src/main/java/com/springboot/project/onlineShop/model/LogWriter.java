package com.springboot.project.onlineShop.model;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogWriter {

    private static final String OUTPUT_PATH = "C:\\Users\\i514767\\Desktop\\logs.txt";

    private List<String> logs = new ArrayList<>();

    public void insert(String str){
        logs.add(str);
    }

    public void write(){
        try{
            FileWriter fw=new FileWriter(OUTPUT_PATH);
            for (String log : logs){
                fw.write(log + "\n");
            }
            fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}
