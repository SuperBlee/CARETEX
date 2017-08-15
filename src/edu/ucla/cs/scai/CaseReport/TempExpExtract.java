package edu.ucla.cs.scai.CaseReport;

/**
 * Created by Zeyu Li on 8/13/17.
 * zyli@cs.ucla.edu
 */

import java.util.*;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;

import edu.stanford.nlp.time.SUTime.Temporal;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;

import edu.stanford.nlp.util.CoreMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class TempExpExtract {

//    public static String dateFormatString = "yyyy-MM-dd HH:mm";

    private static AnnotationPipeline pipeline = new AnnotationPipeline();

    private final static String ruleFiles = "./rules/";
    private static String defs_sutime = ruleFiles + "defs.sutime.txt";
    private static String holiday_sutime = ruleFiles + "english.holidays.sutime.txt";
    private static String _sutime = ruleFiles + "english.sutime.txt";

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TempExpExtract(){
        System.out.println("Setting up annotators with default rules ...");
        setup();
    }

    public TempExpExtract(String defs_rule, String holiday_rule, String sutime_rule){
        System.out.println("Setting up annotators using customized rules ...");
        defs_sutime = defs_rule;
        holiday_sutime = holiday_rule;
        _sutime = sutime_rule;
        setup();
    }

    private static void setup() {
//        String rulesFiles = "./rules/";
//        String defs_sutime = rulesFiles + "defs.sutime.txt";
//        String holiday_sutime = rulesFiles + "english.holidays.sutime.txt";
//        String _sutime = rulesFiles + "english.sutime.txt";
        String sutimeRules = defs_sutime + "," + holiday_sutime + "," + _sutime;
        try {
            Properties props = new Properties();
            props.setProperty("sutime.rules", sutimeRules);
            props.setProperty("sutime.binders", "0");
            props.setProperty("sutime.markTimeRanges", "true");
            props.setProperty("sutime.includeRange", "true");
            pipeline.addAnnotator(new TokenizerAnnotator(false));
            pipeline.addAnnotator(new TimeAnnotator("sutime", props));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TempExpList annotateText(String text) {
        String today = this.dateFormat.format(Calendar.getInstance().getTime());
        return annotateText(text, today);
    }

    public TempExpList annotateText(String text, String referenceDate) {
        TempExpList expList = new TempExpList();
        try {
            if (pipeline != null) {
                Annotation annotation = new Annotation(text);
                annotation.set(CoreAnnotations.DocDateAnnotation.class, referenceDate);
                pipeline.annotate(annotation);
                List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
                for (CoreMap cm : timexAnnsAll) {
                    try {
                        List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
                        Temporal temporal = cm.get(TimeExpression.Annotation.class).getTemporal();
                        String start = tokens.get(0).get(CoreAnnotations.CharacterOffsetBeginAnnotation.class).toString();
                        String end = tokens.get(tokens.size()-1).get(CoreAnnotations.CharacterOffsetEndAnnotation.class).toString();

                        TempExpTerm expTerm = new TempExpTerm(cm.toString(), temporal, start, end);
                        expList.addTerm(expTerm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Annotation Pipeline object is NULL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        expList.printList();
        return expList;
    }

    public static void main(String[] args) {
        TempExpExtract time = new TempExpExtract();
        setup();
        time.annotateText("every 2 weeks", "2015-07-20T10:00");
    }
}
