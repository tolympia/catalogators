import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private List<Course> coursesInUniverse;
    private List<String> selectedCourses; 
    private List<String> pastCourses; //coursesTaken = pastCourses
    private int risingGrade;

    public ConstructSchedule(int risingGrade){
        this.risingGrade = risingGrade;
    }

    public static void main(String[] args){

    }

    //Takes in the course catalogue file and returns a list of course objects that contain all of the necessary information from the file
    public List <Course> parseInput(File courseCatalogue){
       // 1. create a scanner to read in the course catalogue
        Scanner catalogueScanner = new Scanner (courseCatalogue);
        //2. create holder list of course objects listOfCourseObjs */ 
        ArrayList <Course> listOfCourseObjs = new ArrayList <> ();
        //2. use a while loop to iterate through the file line by line (until it has next line), use 1 row at a time
        while(catalogueScanner.hasNextLine()){
            //create a new default course object to hold information that will be extracted
            Course currentCourseObj = new Course();
            //get one row from the dataset
            String [] currentLineArray = catalogueScanner.nextLine().split(",");
            //change currentLine into an arrayList (rather than an arrray, so deletions can be made)
            ArrayList <String> currentLineAL  = new ArrayList <String> (Arrays.asList(currentLineArray));
            // create a lineScanner to sift through each row of the dataset
            Scanner lineScanner = new Scanner(currentLineAL);
            while(lineScanner.hasNext()){
                // set the department to the first index of currentLineAL
                String department = currentLineAL.get(0);
                currentCourseObj.setDepartment(department);
                // remove the department attribute
                currentLineAL.remove(0);
                // set the course name to the first index of currentLineAL 
                String currentCourseName = currentLineAL.get(0);
                currentCourseObj.setCourseName(currentCourseName);
                currentLineAL.remove(0);
                //create holder map that will hold the final list of prereqs
                Map <Integer, <ArrayList <String>>> finalMapOfPrereqs = new HashMap <> ();
                // save the list of prereqs to an array list
                String [] listOfPrereqsArray = currentLineAL.get(0).split(",");
                // making it into an arraylist so that .contains can be used
                ArrayList <String> listOfPrereqsAL =  new ArrayList <String> (Arrays.asList(listOfPrereqsArray));
                // iterate through each element in this arrayList
                for(int i = 0; i < listOfPrereqsAL.size(); i++){
                    ArrayList <String> currentRowToBeAddedToMap = new ArrayList <> ();
                    // if there is an "or," extract the two different class options
                    if(listOfPrereqsAL.get(i).contains("or")){
                        // save the index of the "or"
                        int indexOfOr = listOfPrereqsAl.get(i).indexOf("or");
                        // save the index so it does not include the space between the course name and the "or"
                        indexOfOr = indexOfOr - 1;
                        String firstClassInOr = listOfPrereqsAL.get(i).substring(0, indexOfOr);
                        // add it to the currentRow variable
                        currentRowToBeAddedToMap.add(firstClassInOr);
                        // the "+ 4" is so that this second substring does not contain "or" or the space after it
                        String secondClassInOr = listOfPrereqsAl.get(i).substring(indexOfOr + 4);
                        currentRowToBeAddedToMap.add(secondClassInOr);
                    }
                    else{
                        // if there is no "or," just add the current class string to the currentRow variable
                        currentRowToBeAddedToMap.add(listOfPrereqsAL.get(i));
                    }
                    // add the row to the map (i will serve as a dummy key)
                    finalMapOfPrereqs.add(i, currentRowToBeAddedToMap);
                }
                // set the prereq list to the prereq map
                currentCourseObj.setPrereqList(finalMapOfPrereqs);
                // remove the prereq column from current line so there is a new element at 0
                currentLineAL.remove(0);
                ArrayList <String> gradesAvailableTo = currentLineAL.get(0);
                currentCourseObj.setGradesAvailableTo(gradesAvailableTo);
                // add this course object to listOfCourseObjs
                listOfCourseObjs.add(currentCourseObj);
            }
        }
        /*
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
        return listOfCourseObjs;
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

        //this loop is for filtering availableCourses based on past courses taken & prereqs
        for(int i = 0; i<availableCourses.length; i++){
            //prereq map for that course
            Map<Integer, String> prereqs = availableCourses.get(i).getPrerequsites();
            //set of the prereq keys
            Set<Integer> keys = prereqs.keySet();
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
        int index = Math.random()*availableCourses.length;
        return String.valueOf(availableCourses.get(index));

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