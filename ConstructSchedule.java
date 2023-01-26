import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private List<Courses> coursesInUniverse;
    private List<String> selectedCourses; 
    private List<String> pastCourses; //coursesTaken = pastCourses
    prvate int risingGrade;

    public ConstructSchedule(int risingGrade){
        this.risingGrade = risingGrade;
    }

    public Courses parseInput(String pathname){
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
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> mathCourses){
        pass;
    }

    //english
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> englishCourses){
        pass;
    }

    //language
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> languageCourses){
        pass;
    }

    //history
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> historyCourses){
        pass;
    }

    //extra
    public String chooseScience(int risingGrade, List<String> coursesTaken, List<Courses> extraCourses){
        pass;
    }

}