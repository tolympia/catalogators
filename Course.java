import java.util.*;

// "course" class -> each course is a class with three instance variables: the grades the course is available to, the department the course is in
// and the prerequisites required for that course
public class Course {
        
    // main method
    public static void main(String[] args) {
        // ALL OF THE BELOW CODE IS FOR A DUMMY COURSE
        // creating tester arraylist for grades a course is available to
        ArrayList<String> testerGradesAvailableTo = new ArrayList<>();
        testerGradesAvailableTo.add("11");
        testerGradesAvailableTo.add("12");

        // creating tester map for prerequisites
        Map<Integer, String> testerPrereqs = new HashMap<Integer, String>();
        testerPrereqs.put(1, "Geometry");
        testerPrereqs.put(2, "Algebra II");

        // creating new course object (dummy), setting its instance variables, and printing the course
        Course gaCourse = new Course();
        gaCourse.setCourseName("Calculus");
        gaCourse.setGradesAvailableTo(testerGradesAvailableTo);
        gaCourse.setDepartment("Math");
        gaCourse.setPrerequisites(testerPrereqs);
        System.out.println(gaCourse.toString());
    }

    // instance variables
    private String courseName;
    private ArrayList<String> gradesAvailableTo;
    private String department;
    private Map<Integer, String> prerequisites;

    // default constructor of a course object
    public Course() {
       
    }

    // Constructor of a course object (arg constructor)
     public Course(String courseName, ArrayList<String> gradesAvailableTo, String department, Map<Integer, String> prerequisites) {
        this.courseName = courseName;
        this.gradesAvailableTo = gradesAvailableTo;
        this.department = department;
        this.prerequisites = prerequisites;
    }


     // setter methods for each instance variable of a course object
    public void setCourseName(String name) {
        this.courseName = name;
    }

    public void setGradesAvailableTo(ArrayList<String> availableGrades) {
        this.gradesAvailableTo = availableGrades;
    }  
    
    public void setDepartment(String dprtmnt) {
        this.department = dprtmnt;
    }

    public void setPrerequisites(Map<Integer, String> prereqs) {
        this.prerequisites = prereqs;
    }



    // getter methods for all three instance variables of each course object
    public String getCourseName() {
        return courseName;
    }

    public ArrayList<String> getGradesAvailableTo() {
        return gradesAvailableTo;
    }

    public String getDepartment() {
        return department;
    }

    public Map<Integer, String> getPrerequisites() {
        return prerequisites;
    }

    public String toString() {
        return "Course name: " + courseName + "\n" + "Grades available to: " + gradesAvailableTo + "\n" + "Department: " + department + "\n" + "Prerequisites: " + prerequisites;
    }

}
