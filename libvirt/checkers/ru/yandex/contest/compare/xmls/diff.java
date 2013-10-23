package ru.yandex.contest.compare.xmls;
 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
 
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;
 
public class diff {
 
    public static int OK = 0;
    public static int WA = 1;
    public static int PE = 2;
    public static int FL = 3;

    public static void main(String[] args) {
        if (args.length != 3) {
			System.out.println("Usage: java CompareXmls infile.txt outfile.txt answerfile.txt");
		    System.exit(1);
		}
        FileReader fr1 = null;
        FileReader fr2 = null;
        try {
            fr1 = new FileReader(args[1]);
            fr2 = new FileReader(args[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        try {
            Diff diff = new Diff(fr1, fr2);
            if (diff.similar()) {
					System.exit(OK);
			}
			else {
					System.exit(WA);
			}
            System.out.println("Identical? " + diff.identical());
 
            DetailedDiff detDiff = new DetailedDiff(diff);
            List differences = detDiff.getAllDifferences();
            for (Object object : differences) {
                Difference difference = (Difference)object;
                System.out.println("***********************");
                System.out.println(difference);
                System.out.println("***********************");
            }
 
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}

