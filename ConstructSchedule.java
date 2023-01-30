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
        //wouldn't matter if its same student but would overall list of scienceCourses change back for a new student?
        for(int i = 0; i<scienceCourses.length; i++){
            //using courses class getter method and comparing to grade; removing classes where they are not in an elegiable grade
            if(risingGrade < scienceCourses.get(i).getGrade()){
                scienceCourses.remove(i);
                i--;
            }
            for(int j = 0; j<coursesTaken.length; j++){
                //removing classes already taken from the available list
                if(scienceCourses.get(i).equals(coursesTaken.get(j))){
                    scienceCourses.remove(i);
                    i--;
                }
                //comparing to prereqs (need help understanding logic with the map)
                if(scienceCourses.get(i).getPrerequsites().contains())
                //checking if prereqs contain any of the past courses they have taken/vis versa
            }
        }
        //calling helper method to choose the random course and return it as a String
        return randomPick(scienceCourses);

        /*questions for the group:
        - are we hard coding these based on the GA schedule or leaving it more general
            - ex. technically if I do it by rising grade I could hard code only to pick biology or we could just code a program to do that but it work more generally
        - is altering scienceCourses going to alter it if the program is run for another student (I dont think so but I want to be sure)
        - still a little confused about how we are comparing to the prereq map so we'll go over that at the next meet up
        */

    }

    //math
    public String chooseMath(int risingGrade, List<String> coursesTaken, List<Courses> mathCourses){
        pass;
    }

    //english
    public String chooseEnglish(int risingGrade, List<String> coursesTaken, List<Courses> englishCourses){
        String englishChoice = "";
        if(risingGrade  == 9){
            return "English IX";
        }
        else if(risingGrade == 10){
            return "English X";
        }
        else if(risingGrade == 11){
            return "English XI";
        }
        
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

    //help methods
    public String randomPick(List<Courses> list){
        int index = Math.random()*list.length;
        return String.valueOf(list.get(index));
    }

    public void generateCoursesNextYear(){

    }

    public void generateCoursesAsFile(List <String> coursesList){
        //create a file that contains all of the courses for next year
        //input is courses list-the list that was returned containing all of the courses for next year
        Printstream p = new PrintStream("CoursesNextYear.txt");
        p.println("these are your recommended courses for next year: ");
        // for every course in coursesList add to the printstream on a new line
        for(String course: coursesList){
            p.println(course);
        }
        p.close();
    }

}