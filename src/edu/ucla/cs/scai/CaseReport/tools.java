package edu.ucla.cs.scai.CaseReport;

import org.json.simple.JSONArray;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

/**
 * @author Zeyu Li "zyli@cs.ucla.edu"
 * tools used in temporal extration.
 */

public class tools{
    /**
     * get the full text from a case report.
     * @param path the path of the case report file
     * @return string loaded from the file
     */
    public static String getFullText(String path){
        String everything = "";
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return everything;
    }

    /**
     * writeExpLists in to JSON file
     * @param path output path
     * @param expList the list of expression to be written into a json file.
     */
    public static void writeExpLists(String path, TempExpList expList){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            Iterator<TempExpTerm> expIter = expList.iterator();
            JSONArray jsonArray = new JSONArray();
            while(expIter.hasNext()){
                TempExpTerm next = expIter.next();
                jsonArray.add(next.convertJson());
            }
            writer.write(jsonArray.toString()+ "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * write a readable version of file, easy to debug.
     * @param path the output path
     * @param expList the list of expression to be written into a txt file.
     */
    public static void writeReadable(String path, TempExpList expList){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            Iterator<TempExpTerm> expIter = expList.iterator();
            while(expIter.hasNext()){
                TempExpTerm next = expIter.next();
                writer.write(next.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
