package test;

import edu.ucla.cs.scai.CaseReport.TempExpExtract;
import edu.ucla.cs.scai.CaseReport.TempExpList;
import edu.ucla.cs.scai.CaseReport.tools;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Zeyu Li "zyli@cs.ucla.edu"
 * Main Java file of annotation
 */

public class annotate {
    public static void main(String[] args) {
        String fileList = "./sample_data/filelist.txt";
        String folder = "./sample_data/";
        String resultDir = "./result/";

        ArrayList<String> filePaths = new ArrayList<>(); // Read the file names
        System.out.println("Reading the list of input files ...");
        try(BufferedReader br = new BufferedReader(new FileReader(fileList))) {
            String line = br.readLine();
            while (line != null) {
                filePaths.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TempExpExtract anttr = new TempExpExtract();

        Iterator<String> fileNameIter = filePaths.iterator();
        System.out.println("Annotating the files ...");
        while(fileNameIter.hasNext()){
            String nextFile = fileNameIter.next();
            String rmExtFile = FilenameUtils.removeExtension(nextFile); // Remove the extension
            String fullText = tools.getFullText(folder + nextFile); // Get the full text of case report
            TempExpList expList = anttr.annotateText(fullText);
            tools.writeExpLists(resultDir + rmExtFile + ".json", expList);
            tools.writeReadable(resultDir + rmExtFile + ".txt", expList);
            System.out.println("Finished annotating " + nextFile);
        }
    }
}
