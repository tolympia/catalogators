import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private static List<Course> coursesInUniverse;
    private static List<String> selectedCourses = new ArrayList <> ();
    private static List<String> pastCourses;
    private static int risingGrade;

    // Main method that actually runs our code.
    public static void main(String[] args) throws FileNotFoundException {
        // ask the user for the grade they are entering
        Scanner sc = new Scanner(System.in);
        System.out.println("What grade are you entering?");
        risingGrade = sc.nextInt();
        sc.close();
        // parse the mini dataset and the sample transcript files
        File f = new File("MiniDataSet.csv");
        File sampleTranscript = new File("SampleTranscript.txt");
        parseTranscript(sampleTranscript);
        parseInputVersion1(f);
        // it is as if there is a prerequisite called "none" since if a course objects' prerequisite is "none", it can be taken by anyone
        pastCourses.add("none");
        // create list of the courses for next year
        generateCoursesNextYear(risingGrade);
        // write courses for next year to a file
        generateCoursesAsFile(selectedCourses);
    }
    //Takes in the big course catalogue file and returns a list of course objects that contain all of the necessary information from the file
    public static void parseInput(File courseCatalogue) throws FileNotFoundException{
       // 1. create a scanner to read in the course catalogue
        Scanner catalogueScanner = new Scanner (courseCatalogue);
        //2. create holder list of course objects listOfCourseObjs */ 
        ArrayList <Course> listOfCourseObjs = new ArrayList <> ();
        //2. use a while loop to iterate through the file line by line (until it has next line), use 1 row at a time
        // we do not want the while loop to go through the headers as well
        catalogueScanner.nextLine();
        while(catalogueScanner.hasNextLine()){
            //get one row from the dataset
            // FIRST UNDO
            String [] currentLineArray = catalogueScanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            while(currentLineArray.length < 7){
                currentLineArray = catalogueScanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            }
            //change currentLine into an arrayList (rather than an arrray, so deletions can be made)
            ArrayList <String> currentLineAL1  = new ArrayList <> (Arrays.asList(currentLineArray));
            // making currentLineAL as only the first 6 rows
            ArrayList <String> currentLineAL = new ArrayList <> ();
            for(int i = 0; i <= 6; i++){
                currentLineAL.add(currentLineAL1.get(i));
            }
            // set the department to the string at the department index 
            String department = currentLineAL.get(0);
            // remove the department attribute
            // set the course name to the first index of currentLineAL 
            String currentCourseName = currentLineAL.get(3);
            //create holder map that will hold the final list of prereqs
            Map <Integer, ArrayList <String>> finalMapOfPrereqs = new HashMap <> ();
            // save the list of prereqs to an array list
            String [] listOfPrereqsArray = currentLineAL.get(6).split(" and ");
            // making it into an arraylist so that .contains can be used
            ArrayList <String> listOfPrereqsAL =  new ArrayList <String> (Arrays.asList(listOfPrereqsArray));
            // iterate through each element in this arrayList
            for(int i = 0; i < listOfPrereqsAL.size(); i++){
                ArrayList <String> currentRowToBeAddedToMap = new ArrayList <> ();
                // need to deal with "department approval" --> if it is the first index, you just want the prerequisite map to be department approval
                if (listOfPrereqsAL.indexOf("Department Approval") == 0){
                    currentRowToBeAddedToMap.add("DepartmentApproval");
                    finalMapOfPrereqs.put(0, currentRowToBeAddedToMap);
                    break;
                }
                // if there is an "or," extract the two different class options
                if(listOfPrereqsAL.get(i).contains(" or ")){
                    // save the index of the "or"
                    int indexOfOr = listOfPrereqsAL.get(i).indexOf(" or ");
                    // save the index so it does not include the space between the course name and the "or"
                    String firstClassInOr = listOfPrereqsAL.get(i).substring(0, indexOfOr);
                    // add it to the currentRow variable
                    currentRowToBeAddedToMap.add(firstClassInOr);
                    // the "+ 4" is so that this second substring does not contain "or" or the space after it
                     String secondClassInOr = listOfPrereqsAL.get(i).substring(indexOfOr + 4);
                    currentRowToBeAddedToMap.add(secondClassInOr);
                }
                else{
                        // if there is no "or," just add the current class string to the currentRow variable
                        currentRowToBeAddedToMap.add(listOfPrereqsAL.get(i));
                }
                    // add the row to the map (i will serve as a dummy key)
                    finalMapOfPrereqs.put(i, currentRowToBeAddedToMap);
            }

                // makes news string equal to the grades available to
                String tempGradesAvailableTo = currentLineAL.get(5);
                ArrayList <String> gradesAvailableToAL = new ArrayList <> ();
                // sets the String array equal to the grades each course is available to
                if (tempGradesAvailableTo.contains("9th - 12th")) {
                   gradesAvailableToAL = new ArrayList <> (Arrays.asList("9", "10", "11", "12"));
                }
                else if (tempGradesAvailableTo.contains("9th - 11th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("9", "10", "11"));
                }
                else if (tempGradesAvailableTo.contains("9th-10th")) {
                    gradesAvailableToAL =  new ArrayList <>(Arrays.asList("9", "10"));
                }
                else if (tempGradesAvailableTo.contains("9th")) {
                    gradesAvailableToAL =  new ArrayList <>(Arrays.asList("9"));
                }
                else if (tempGradesAvailableTo.contains("10th - 12th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("10", "11", "12"));
                }
                else if (tempGradesAvailableTo.contains("10th - 11th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("10", "11"));
                }
                else if (tempGradesAvailableTo.contains("10th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("10"));
                }
                else if (tempGradesAvailableTo.contains("11th - 12th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("11", "12"));
                }
                else if (tempGradesAvailableTo.contains("11th")) {
                    gradesAvailableToAL =  new ArrayList <> (Arrays.asList("11"));
                }
                else if (tempGradesAvailableTo.contains("12th")) {
                   gradesAvailableToAL =  new ArrayList <> (Arrays.asList("12"));
                }
                // makes an AL of the grades available array
                Course courseToAdd = new Course(currentCourseName, gradesAvailableToAL, department, finalMapOfPrereqs);
                listOfCourseObjs.add(courseToAdd);;
            }
        catalogueScanner.close();
        coursesInUniverse = listOfCourseObjs;
    }

    // Version one of the parseInput Method that takes in the baby dataset
    public static void parseInputVersion1(File babyCourseCatalogue) throws FileNotFoundException{
       // 1. create a scanner to read in the course catalogue
        Scanner catalogueScanner = new Scanner (babyCourseCatalogue);
        //2. create holder list of course objects listOfCourseObjs */ 
        ArrayList <Course> listOfCourseObjs = new ArrayList <> ();
        //want to skip over the first row of the datset becasue that row is just the headers
        catalogueScanner.nextLine();
        //2. use a while loop to iterate through the file line by line (until it has next line), use 1 row at a time
        while(catalogueScanner.hasNextLine()){
            //get one row from the dataset
            String [] currentLineArray = catalogueScanner.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            //change currentLine into an arrayList (rather than an arrray, so deletions can be made)
            ArrayList <String> currentLineAL  = new ArrayList <> (Arrays.asList(currentLineArray));
            // set the department to the first index of currentLineAL
            String department = currentLineAL.get(0);
            // remove the department attribute
            // set the course name to the first index of currentLineAL 
            String currentCourseName = currentLineAL.get(1);
            //create holder map that will hold the final list of prereqs
            Map <Integer, ArrayList <String>> finalMapOfPrereqs = new HashMap <> ();
            // save the list of prereqs to an array list
            String [] listOfPrereqsArray = currentLineAL.get(2).split(",");
            // making it into an arraylist so that .contains can be used
            ArrayList <String> listOfPrereqsAL =  new ArrayList <String> (Arrays.asList(listOfPrereqsArray));
            // iterate through each element in this arrayList
            for(int i = 0; i < listOfPrereqsAL.size(); i++){
                ArrayList <String> currentRowToBeAddedToMap = new ArrayList <> ();
                // if there is an "or," extract the two different class options
                if(listOfPrereqsAL.get(i).contains(" or ")){
                    // save the index of the "or"
                    int indexOfOr = listOfPrereqsAL.get(i).indexOf(" or ");
                    // save the index so it does not include the space between the course name and the "or"
                    String firstClassInOr = listOfPrereqsAL.get(i).substring(0, indexOfOr);
                    // add it to the currentRow variable
                    currentRowToBeAddedToMap.add(firstClassInOr);
                    // the "+ 4" is so that this second substring does not contain "or" or the space after it
                     String secondClassInOr = listOfPrereqsAL.get(i).substring(indexOfOr + 4);
                    currentRowToBeAddedToMap.add(secondClassInOr);
                }
                else{
                        // if there is no "or," just add the current class string to the currentRow variable
                        currentRowToBeAddedToMap.add(listOfPrereqsAL.get(i));
                }
                    // add the row to the map (i will serve as a dummy key)
                    finalMapOfPrereqs.put(i, currentRowToBeAddedToMap);
            }
                // set the prereq list to the prereq map
                // cleaning up the final map of prereqs a bit by removing the ""
                String [] gradesAvailableToArray = currentLineAL.get(3).split(",");
                ArrayList <String> gradesAvailableToAL = new ArrayList <String> (Arrays.asList(gradesAvailableToArray));
                Course currentCourseObj = new Course(currentCourseName, gradesAvailableToAL, department, finalMapOfPrereqs);
                // add this course object to listOfCourseObjs
                listOfCourseObjs.add(currentCourseObj);
            }
            coursesInUniverse = listOfCourseObjs;
            catalogueScanner.close();
            coursesInUniverse = listOfCourseObjs;
    }
    // helper method to clean up the prereq map further
    public static Map <Integer, ArrayList <String>> cleanUpMap (Map <Integer, ArrayList <String>> prereqMap){
        // first, remove all unnecessary quotation marks
        Set <Integer> keys = prereqMap.keySet();
        for(Integer key : keys){
            ArrayList <String> currentRowInMap = prereqMap.get(key);
            for(int i = 0; i < currentRowInMap.size(); i++){
                // if the first character is a quotation mark or a space, remove it
                String cleanedElement = "";
                if(currentRowInMap.get(i).charAt(0) == ' ' || currentRowInMap.get(i).charAt(0) == '\"'){
                    cleanedElement = currentRowInMap.get(i).substring(1);
                    currentRowInMap.remove(i);
                    // add the cleaned element back
                    currentRowInMap.add(cleanedElement);
                }
                //if the last character is a quotation mark, delete it
                int lastIndexOfLastElement = currentRowInMap.get(i).length() - 1;
                if(currentRowInMap.get(i).charAt(lastIndexOfLastElement) == '\"'){
                    cleanedElement = currentRowInMap.get(i).substring(0, lastIndexOfLastElement);
                    currentRowInMap.remove(i);
                    // add the cleaned element back
                    currentRowInMap.add(cleanedElement);
                }
            }
        }
        return prereqMap;
    }
    // return a list of the classes that the user has already taken 
    public static void parseTranscript(File transcript) throws FileNotFoundException{
        List <String> takenCourses = new ArrayList<>();
        Scanner transcriptScanner = new Scanner(transcript);
        while (transcriptScanner.hasNextLine()){
            String currentLine = transcriptScanner.nextLine();
            takenCourses.add(currentLine);
        }
        transcriptScanner.close();
        pastCourses = takenCourses;
    }


    public static String chooseSubject(int risingGrade, String department){
        //creating list that contains the courses of the subject currently being chosen
        List<Course> subjectCourses = getDepartmentList(department);
        //System.out.println(subjectCourses);
        //creating list of courses available to particular student to be randomly indexed through to pick class at the end of the method
        List<Course> availableCourses = new ArrayList<>();
        //going through subjectCourses and seeing what is available to student based on their rising grade (see below)
            //this loop gets all the available courses based on rising grade, then rest of code we filter out
        for(int i = 0; i < subjectCourses.size(); i++){
            //using courses class getter method and comparing to grade; removing classes where they are not in an elegiable grade
            if(subjectCourses.get(i).getGradesAvailableTo().contains(String.valueOf(risingGrade))){
                availableCourses.add(subjectCourses.get(i));
            }
        }
        System.out.println("after getting availableCourses");

        //this loop is for filtering availableCourses based on past courses taken & prereqs
        for(int i = 0; i < availableCourses.size(); i++){
            //prereq map for that course
            Map<Integer, ArrayList<String>> prereqs = availableCourses.get(i).getPrerequisites();
            //set of the prereq keys
            Set<Integer> keys = prereqs.keySet();
            //loop to compare to past transcript/past courses
            int count = 0;
            for(int j = 0; j<pastCourses.size(); j++){
                //removing classes already taken from the available list
                if(availableCourses.get(i).equals(pastCourses.get(j))){
                    availableCourses.remove(i);
                    i--;
                }
                System.out.println("in past courses loop before for each loop");

                //count is incremented by one when a pastCourse IS a prereq
                //System.out.println("key length: " + keys.size());
                //System.out.println(prereqs);
                //System.out.println(Arrays.asList(pastCourses));
                for(int ors : keys){
                    System.out.println(prereqs.get(ors));
                    System.out.println(pastCourses.get(j));
                    if(prereqs.get(ors).contains(pastCourses.get(j))){
                        System.out.println("entered for each loop");
                        count++;
                    }
                }
                System.out.println("after count");
            }
                //if there is not at least ONE prereq match, taken out of available list
            if(count < 1*keys.size()){
                System.out.println("hello");
                availableCourses.remove(i);
                i--;
            }

                //checking if prereqs contain any of the past courses they have taken/vis versa
                // iterate through all of their past courses
                // iterate through all of the keys of the prerequisite map 
                // make sure that each key contains at least ONE prerequisite
                // if each key contains at least one, the course object is not removed 
            
        }
        //calling helper method to choose the random course and return it as a String
        int index = (int) Math.random()*availableCourses.size();
        return String.valueOf(availableCourses.get(index));
    }
        public static String chooseSubjectVersion2(int risingGrade, String department){
        //creating list that contains the courses that fall under the department from the parameter "department"
        List<Course> availableCourses = getDepartmentList(department);
        String risingGradeAsStr = "" + risingGrade;
        //going through subjectCourses and seeing what is available to student based on their rising grade (see below)
        for(int i = availableCourses.size() -1; i >= 0; i--){
            // convert gradesAvailableTo for this course object into a string so that the indexOf method can be used
            String gradesAvailableTo = "";
            for(int j = 0; j < availableCourses.get(i).getGradesAvailableTo().size(); j++){
                gradesAvailableTo = gradesAvailableTo + availableCourses.get(i).getGradesAvailableTo().get(j);
            }
            // if the risingGradeAsStr is not included, it should be removed
            if (gradesAvailableTo.indexOf(risingGradeAsStr) == -1){
                availableCourses.remove(i);
            }
        }
        // filter availableCourses based on past courses taken/prereqs
        // use this big for loop to filter through each course 1 by 1
        for(int i = availableCourses.size() - 1; i >= 0; i--){
            Map <Integer, ArrayList <String>> currentPrereqMap =  availableCourses.get(i).getPrerequisites();
            currentPrereqMap = cleanUpMap(currentPrereqMap);
            Set <Integer> keysOfCurrentCourseObjPrereqs = currentPrereqMap.keySet();
            // iterating through the map of prerequisites from the current course object
            for(Integer key : keysOfCurrentCourseObjPrereqs){
                // getting the values under the current key in the prereq map of the current object
                ArrayList <String> currentRowOfPrereqArray = currentPrereqMap.get(key);
                // if it is an or, check for containing either element of the or, if it doesn't contain one of them, it gets removed
                if(currentRowOfPrereqArray.size() == 2){
                    // if pastCourses doesn't contain either of the elements in an "or" row, the course object should be removed
                    if (!(pastCourses.contains(currentRowOfPrereqArray.get(0)) || pastCourses.contains(currentRowOfPrereqArray.get(1)))){
                        availableCourses.remove(i);
                    }
                }
                // if there it is not an or, and pastCourses does not contain it, the current course object should be removed
                if (currentRowOfPrereqArray.size() == 1 && !pastCourses.contains(currentRowOfPrereqArray.get(0))){
                    availableCourses.remove(i);
                }
            }
        }
        int randomIndex = (int) (Math.random() * availableCourses.size());
        // if no courses are available in that departemt, indicate that
        if(availableCourses.size() == 0){
            return "" + department + ": Sorry, there are no courses available to you for this subject.";
        }
        return "" + department + ": " + availableCourses.get(randomIndex).getCourseName();
        //calling helper method to choose the random course and return it as a String
        }
    //helper method to get the department of the subject we want
    public static ArrayList <Course> getDepartmentList(String department){
        ArrayList <Course> departmentList = new ArrayList<>();
        for(int i = 0; i<coursesInUniverse.size(); i++){
            if(coursesInUniverse.get(i).getDepartment().equals(department)){
                departmentList.add(coursesInUniverse.get(i));
            }
        }
        return departmentList;
    }

    public static void generateCoursesNextYear(int risingGrade){
        //generate the courses for next year
        List<String> coursesNextYear = new ArrayList<>();
        //create a list of all of the departments
        List<String> departments = new ArrayList<>(Arrays.asList("Arts", "Science", "History", "Math", "Science", "World Languages", "English"));
        //iterate through each department
        for(String department: departments){
            coursesNextYear.add(chooseSubjectVersion2(risingGrade, department));
        }
        selectedCourses = coursesNextYear;
    }

    public static void generateCoursesAsFile(List <String> coursesList) throws FileNotFoundException {
        //create a file that contains all of the courses for next year
        //input is courses list-the list that was returned containing all of the courses for next year

        //string of new file name
        String newFileName = "CoursesNextYear.txt";

        //printstream to add top words to the new file (named string above)
        PrintStream p = new PrintStream(new FileOutputStream(newFileName, false));
        p.println("these are your recommended courses for next year: ");
        // for every course in coursesList add to the printstream on a new line
        for(String course: coursesList){
            p.println(course);
        }
        p.close();
    }

}