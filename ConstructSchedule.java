import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private List<Courses> coursesInUniverse;
    private Map<Courses, ArrayList<Integer>> gradeRequirement;
    private Map<Courses, ArrayList<String>> prerequisites;
    private List<String> selectedCourses;
    private List<String> pastCourses; 

    public ConstructSchedule(){
        pass;
    }

    public Courses parseInputForCourses(String pathname){
        pass;
    }

    public Map<Courses, ArrayList<String>> parseInputPrerequisites(String pathname){
        pass;
    }

    public List<String> parseTranscript(File transcript){
        List <String> pastCourses = new ArrayList<>();
        Scanner transcriptScanner = new Scanner(transcript);
        while (transcriptScanner.hasNextLine()){
            String currentLine = transcriptScanner.nextLine();
            pastCourses.add(currentLine);
        }
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