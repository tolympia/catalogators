import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private List<Courses> coursesInUniverse;
    private List<String> selectedCourses; 
    private List<String> pastCourses; //coursesTaken = pastCourses
    private int risingGrade;

    public ConstructSchedule(int risingGrade){
        this.risingGrade = risingGrade;
    }
    //Takes in the course catalogue file and returns a list of course objects that contain all of the necessary information from the file
    public List <Course> parseInput(File courseCatalogue){
        /* 
        1. create a scanner to read in the course catalogue
        2. create holder list of course objects listOfCourseObjs
        2. use a while loop to iterate through the file line by line (until it has next line), use 1 row at a time
            a. create a new default course object to hold information that will be extracted call it currentCourseObj, create a lineScanner/nested while loop to scan through each line (use .split()), save as currentLine
            b. change currentLine into an arrayList (rather than an arrray, so deletions can be made)
            b. set the "department" attribute in currentCourseObj to currentline.get(0), delete currentLine.get(0)
            d. set the courseName attribute in currentCourseObj to currentLine.get(0), delete currentLine.get(0)
            e. create holder arrayList of arrayLists that will hold the 2D prerequisite list, call it finalListOfPrereqs
            f. save currentLine.get(0) to a new String [] split by commas, call it listOfPrereqs
                g. use a for loop (for the length of listOfPrereqs) iterate through each element
                h. if listOfPrereqs[i].contains("or") != 0, create a substring of the first class and a substring of the second class, add both to finalListOfPrereqs
                i. if listOfPrereqs[i] does not contain "or", add listofprereqs[i] to finalListOfPrereqs
            j. use the setPrereqList method of the currentCourseObj to set its list to finalListOfPrereqs
            k. delete element at 0 from currentLine
            l. (might have to parse this line first as an array, kind of unclear) using set method of course object, add all the remaining elements of current line to the "gradeAvailable" attribute of the course object
            m. add the course object to the listOfCourseObjs
        3. close scanners
        4. return the listOfCourseObjs
            */
        pass;
    }

    // return a list of the classes that the user has already taken 
    public List<String> parseTranscript(File transcript){
        List <String> pastCourses = new ArrayList<>();
        Scanner transcriptScanner = new Scanner(transcript);
        while (transcriptScanner.hasNextLine()){
            String currentLine = transcriptScanner.nextLine();
            pastCourses.add(currentLine);
        }
        transcriptScanner.close();
        return pastCourses;
    }

    //science
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> scienceCourses){
        pass;
    }

    //math
    public String chooseMath(int risingGrade, List<String> coursesTaken, List<Courses> mathCourses){
        pass;
    }

    //english
    public String chooseEnglish(int risingGrade, List<String> coursesTaken, List<Courses> englishCourses){
        pass;
    }

    //language
    public String chooseLanguage(int risingGrade, List<String> coursesTaken, List<Courses> languageCourses){
        pass;
    }

    //history
    public String chooseHistory(int risingGrade, List<String> coursesTaken, List<Courses> historyCourses){
        pass;
    }

    //extra
    public String chooseExtra(int risingGrade, List<String> coursesTaken, List<Courses> extraCourses){
        pass;
    }

}