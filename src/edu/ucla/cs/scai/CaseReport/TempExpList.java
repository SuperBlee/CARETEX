package edu.ucla.cs.scai.CaseReport;

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

}

