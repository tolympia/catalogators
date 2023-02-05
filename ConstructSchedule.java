import java.util.*;
import java.io.*;
public class ConstructSchedule {

    private static List<Course> coursesInUniverse;
    private List<String> selectedCourses; 
    private static List<Str-ing> pastCourses;
    private int risingGrade;

    // Main method that actually runs our code.
  //  public static void main(String[] args) throws FileNotFoundException {
        //adding scanner input for the rising grade of the user
        /* Scanner sc = new Scanner();
        System.out.println("What grade are you entering?");
        int risingGrade = sc.nextLine();
        */
        // File courseCat = new File("MiniDataSet.csv");
        // coursesInUniverse = parseInput(courseCat);
        // for (int i = 0; i < coursesInUniverse.size(); i++){
        //     System.out.println(coursesInUniverse.get(i).getCourseName() + " Prereqs:"  + coursesInUniverse.get(i).getGradesAvailableTo());
        // }
        // // Testing the parseTranscript method individually 
        // File testerTranscript1 = new File("SampleTranscript.txt");
        // File testerTranscript2 = new File("SampleTranscript2.txt");
       // System.out.println("Print out tester transcript #1: " + parseTranscript(testerTranscript1));
        //System.out.println("Print out tester transcript #2: " + parseTranscript(testerTranscript2));

    // Main method that actually runs our code.
        // if (args.length > 0 && args[0].equals("testParse")){
        //     testerCatClass();
        // }
        // testerCatClass();
        // System.out.println(coursesInUniverse.size());
        // // PRINT ALL COURSES IN UNI
        // System.out.println(coursesInUniverse.size());

        // else{
        //     //1. ask for user input of the grade entering
        //     Scanner sc = new Scanner(System.in);
        // System.out.println("What grade are you entering?");
        // int risingGrade = Integer.parseInt(sc.next());


        // //2. parse the course catalog and the transcript
        // File courseCat = new File("MiniDataSet.csv");
        // parseInput(courseCat);
        // //System.out.println(coursesInUniverse);

        // File transcript = new File("SampleTranscript.txt");
        // parseTranscript(transcript);

        // //3. generate the courses for next year
        // List<String> coursesNextYear = generateCoursesNextYear(risingGrade);
        // generateCoursesAsFile(coursesNextYear);
        // }
        
        File courseCat = new File("MiniDataSet.csv");
        parseInputVersion1(courseCat);

        File transcript = new File("SampleTranscript.txt");
        parseTranscript(transcript);

        //System.out.println(coursesInUniverse);

        String test = chooseSubject(11, "Math");
        System.out.println(test);

    }

    public static void testerCatClass () throws FileNotFoundException{
        File cc = new File("courseCatalogueWithoutRow12.csv");
        parseInput(cc);
        System.out.println(coursesInUniverse.get(0).getPrerequisites());
        System.out.println(coursesInUniverse.get(1).getPrerequisites());
    }
    // return a list of the classes that the user has already taken 
    public static List<String> parseTranscript(File transcript) throws FileNotFoundException{
        List <String> pastCourses = new ArrayList<>();
        Scanner transcriptScanner = new Scanner(transcript);
        while (transcriptScanner.hasNextLine()){
            String currentLine = transcriptScanner.nextLine();
            pastCourses.add(currentLine);
        }
        transcriptScanner.close();
        return pastCourses;
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
                // remove the prereq column from current line so there is a new element at 0
                String [] gradesAvailableToArray = currentLineAL.get(3).split(",");
                ArrayList <String> gradesAvailableToAL = new ArrayList <String> (Arrays.asList(gradesAvailableToArray));
                Course currentCourseObj = new Course(currentCourseName, gradesAvailableToAL, department, finalMapOfPrereqs);
                // add this course object to listOfCourseObjs
                listOfCourseObjs.add(currentCourseObj);
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
        coursesInUniverse = listOfCourseObjs;
            catalogueScanner.close();
            coursesInUniverse = listOfCourseObjs;
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
        //creating list that contains the courses of the subject currently being chosen
        List<Course> subjectCourses = getDepartmentList(department);
        //System.out.println(subjectCourses);
        //creating list of courses available to particular student to be randomly indexed through to pick class at the end of the method
        List<Course> availableCourses = new ArrayList<>();
        List <Course> finalAvailableCourses = new ArrayList <> ();
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
        for (for int i = 0; i < availableCourses.size(); i++){
            //go to a course object
            // check if each row contains at least 1 prereq
            // get prereq map of the current course object
            Map <Integer, ArrayList <String>> currentCoursePrereqMap = availableCourses.get(i).getPrerequisites();
            Set <Integer> keysOfPrereqMap = currentCoursePrereqMap.keySet();
            for ()
            // if it does, add it to availablecourses 
        }
        //calling helper method to choose the random course and return it as a String
        int index = (int) Math.random()*availableCourses.size();
        return String.valueOf(availableCourses.get(index));
    }

        //this loop is for filtering availableCourses based on past courses taken & prereqs
        for(int i = 0; i<availableCourses.size(); i++){
            //prereq map for that course
            Map<Integer, ArrayList<String>> prereqs = availableCourses.get(i).getPrerequisites();
            //set of the prereq keys
            Set<Integer> keys = prereqs.keySet();
            //loop to compare to past transcript/past courses
            for(int j = 0; j<pastCourses.size(); j++){
                //removing classes already taken from the available list
                if(availableCourses.get(i).equals(pastCourses.get(j))){
                    availableCourses.remove(i);
                    i--;
                }
                for(int ors : keys){
                    if(!prereqs.get(ors).contains(pastCourses.get(j))){
                        availableCourses.remove(i);
                        i--;
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
        int index = (int) Math.random()*availableCourses.size();
        return String.valueOf(availableCourses.get(index));

        /*questions for the group:
        - are we hard coding these based on the GA schedule or leaving it more general
            - ex. technically if I do it by rising grade I could hard code only to pick biology or we could just code a program to do that but it work more generally
        - is altering scienceCourses going to alter it if the program is run for another student (I dont think so but I want to be sure)
        - still a little confused about how we are comparing to the prereq map so we'll go over that at the next meet up
        */



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

    // public static List<String> generateCoursesNextYear(int risingGrade){
    //     //generate the courses for next year
    //     List<String> coursesNextYear = new ArrayList<>();
    //     //create a list of all of the departments
    //     List<String> departments = new ArrayList<>(Arrays.asList("Arts", "Science", "History", "Math", "Science", "World Languages", "English"));
    //     //iterate through each department
    //     for(String department: departments){
    //         coursesNextYear.add(chooseSubject(risingGrade, department));
    //     }
    //     return coursesNextYear;
    // }

    // public static void generateCoursesAsFile(List <String> coursesList) throws FileNotFoundException {
    //     //create a file that contains all of the courses for next year
    //     //input is courses list-the list that was returned containing all of the courses for next year

    //     //string of new file name
    //     String newFileName = "CoursesNextYear.txt";

    //     //printstream to add top words to the new file (named string above)
    //     PrintStream p = new PrintStream(new FileOutputStream(newFileName, true));
    //     p.println("these are your recommended courses for next year: ");
    //     // for every course in coursesList add to the printstream on a new line
    //     for(String course: coursesList){
    //         p.println(course);
    //     }
    //     p.close();
    // }

}

/* Departments available:
- Arts
- Math
- English
- World Language
- Classics
- Computer Science + Engineering
- History
- Science
*/
