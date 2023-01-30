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
    public String chooseSubject(int risingGrade, String department){
        //creating list that contains the courses of the subject currently being chosen
        List<Course> subjectCourses = getDepartmentList(department);
        //creating list of courses available to particular student to be randomly indexed through to pick class at the end of the method
        List<Course> availableCourses = new List<>();
        //going through subjectCourses and seeing what is available to student based on their rising grade (see below)
            //this loop gets all the available courses based on rising grade, then rest of code we filter out
        for(int i = 0; i<subjectCourses.length; i++){
            //using courses class getter method and comparing to grade; removing classes where they are not in an elegiable grade
            if(subjectCourses.get(i).getGrade().contains(risingGrade)){
                availableCourses.add(i);
            }
        }
        //set of the prereq keys
        Map<Integer, String> prereqs = availableCourses.getPrerequsites()
        Set<Integer> keys = prereqs.keySet();

        //this loop is for filtering availableCourses based on past courses taken & prereqs
        for(int i = 0; i<availableCourses.length; i++){
            //loop to compare to past transcript/past courses
            for(int j = 0; j<pastCourses.length; j++){
                //removing classes already taken from the available list
                if(availableCourses.get(i).equals(pastCourses.get(j))){
                    availableCourses.remove(i);
                    i--;
                }
                for(List<String> ors : keys){
                    if(!prereqs.get(ors).contains(pastCourses.get(j))){
                        availableCourses.remove(i);
                        
                    }
                }

                //checking if prereqs contain any of the past courses they have taken/vis versa
                // iterate through all of their past courses
                // iterate through all of the keys of the prerequisite map 
                // make sure that each key contains at least ONE prerequisite
                // if each key contains at least one, the course object is not removed 
            }
        }
        //calling helper method to choose the random course and return it as a String
        int index = Math.random()*list.length;
        return String.valueOf(list.get(index));

        /*questions for the group:
        - are we hard coding these based on the GA schedule or leaving it more general
            - ex. technically if I do it by rising grade I could hard code only to pick biology or we could just code a program to do that but it work more generally
        - is altering scienceCourses going to alter it if the program is run for another student (I dont think so but I want to be sure)
        - still a little confused about how we are comparing to the prereq map so we'll go over that at the next meet up
        */

    }

    //helper method to get the department of the subject we want
    public List<Course> getDepartmentList(String department){
        List<Course> departmentList = List<>();
        for(int i = 0; i<coursesInUniverse.length; i++){
            if(coursesInUniverse.get(i).getDepartment().equals(subject)){
                departmentList.add(i);
            }
        }
        return departmentList;
    }

}