package edu.ucla.cs.scai.CaseReport;

import org.joda.time.Interval;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zeyu Li on 8/14/17.
 * zyli@cs.ucla.edu
 */

public class TempExpList {

    private ArrayList<TempExpTerm> ExpList = new ArrayList<>();

    public void addTerm(TempExpTerm term){
        ExpList.add(term);
    }

    public TempExpTerm getTerm(int index){
        return ExpList.get(index);
    }

    public void printList(){
        try {
            for (TempExpTerm exp : ExpList) {
                exp.print();
            }
            System.out.println("===================");
        } catch (Exception e){
          e.printStackTrace();
        }
    }

    public Iterator<TempExpTerm> iterator(){return ExpList.iterator(); }

    public static TempExpList mergeList(TempExpList l1, TempExpList l2){
        TempExpList res = new TempExpList();
        Iterator<TempExpTerm> i1 = l1.iterator();
        Iterator<TempExpTerm> i2 = l2.iterator();
        while(i1.hasNext()){
            res.addTerm(i1.next());
        }
        while(i2.hasNext()){
            res.addTerm(i2.next());
        }
        return res;
    }

}

