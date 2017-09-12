package edu.ucla.cs.scai.CaseReport;

import edu.stanford.nlp.time.SUTime.Temporal;
import org.json.simple.JSONObject;

/**
 * Created by Zeyu Li on 8/14/17.
 * zyli@cs.ucla.edu
 */


public class TempExpTerm {
    private String _text;
    private String _start;
    private String _end;
    private Temporal temporal;

    public TempExpTerm(String name, String start, String end){
        _text = name;
        _end = end;
        _start = start;
    }

    public TempExpTerm(String name, Temporal temp, String start, String end){
        _text = name;
        _start = start;
        _end = end;
        temporal = temp;
    }

    public void print(){
        System.out.println("===============");
        System.out.println("Token Text: " + _text);
//        System.out.println("Temporal Value : " + temporal.toString());
//        System.out.println("Timex : " + temporal.getTimexValue());
//        System.out.println("Type : " + temporal.getTimexType());
        System.out.println("Start : " + _start);
        System.out.println("End : " + _end);
    }

    public String toString(){
        return  getStart() + "\t" +
                getEnd() + "\t" +
//                getTempValue() + "\t" +
//                getType() + "\t" +
                getText() + "\n";
    }

    public JSONObject convertJson(){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Start", getStart());
        jsonObj.put("End", getEnd());
//        jsonObj.put("Type", getType());
//        jsonObj.put("Value", getTempValue());
//        jsonObj.put("Timex", getTimex());
        jsonObj.put("Text", getText());
        return jsonObj;
    }

    public String getText(){return _text;}
    public String getStart(){return _start;}
    public String getEnd(){return _end;}
    public String getTimex(){return temporal.getTimexValue();}
    public String getType(){return temporal.getTimexType().toString();}
    public String getTempValue(){return temporal.toString();}
}
