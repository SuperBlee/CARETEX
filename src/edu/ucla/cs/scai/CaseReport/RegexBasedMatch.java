package edu.ucla.cs.scai.CaseReport;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.ucla.cs.scai.CaseReport.TempExpTerm;
import org.apache.commons.io.FilenameUtils;

/**
 * Created by Zeyu Li on 9/10/17.
 * zyli@cs.ucla.edu
 */
public class RegexBasedMatch {
    private ArrayList<String> RegexList = new ArrayList<>();
    private ArrayList<Pattern> PatternList = new ArrayList<>();

    private void parseRegex(String content){
        String[] lines = content.split("\\n");
        RegexList.addAll(Arrays.asList(lines));
    }

    private void compileRegex(){
        for(String regexStr: RegexList){
            Pattern x = Pattern.compile(regexStr.trim());
            PatternList.add(x);
        }
    }

    private TempExpList matchRegex(String text){
        TempExpList res = new TempExpList();
        for(Pattern pattern: PatternList){
            System.out.println(pattern.toString());
            System.out.println(text);
            Matcher mtr = pattern.matcher(text);
            while(mtr.find()) {
                TempExpTerm term = new TempExpTerm(mtr.group(), Integer.toString(mtr.start()), Integer.toString(mtr.end()));
                res.addTerm(term);
            }
        }
        return res;
    }

    public TempExpList processText(String regexes, String fullText){
        System.out.println("Loading the candidate regex ...");
        String content = "";
        String text = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(regexes)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        parseRegex(content); // Load regex into String list
        compileRegex(); // Compile the String to Patterns
//        try {
//        text = new String(Files.readAllBytes(Paths.get(filePath)));
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
        TempExpList result = matchRegex(fullText);
        return result;
    }

    public RegexBasedMatch(){
        System.out.print("good");
    }


    public static void main(String[] args){
        String filePath = "./sample_data/example.txt";
        String text = "";
        RegexBasedMatch a = new RegexBasedMatch();
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        TempExpList l = a.processText("./src/rules/regex_rules.txt", text);
        l.printList();
    }

}
