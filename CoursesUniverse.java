import java.util.*;
// contains "Course" class and maint method
public class CoursesUniverse {

    // main method
    public static void main(String[] args) {
        // creating new course object (dummy)
        Course gaCourse = new Course();
    }

    // "course" class -> each course is a class with three instance variables: the grades the course is available to, the department the course is in
    // and the prerequisites required for that course
    public class Course {
        // ninstance variables
        private ArrayList<String> gradesAvailableTo[];
        private String department;
        private Map<Integer, String> prerequisites;


        // default constructor of a course object
        public Course() {
            this.gradesAvailableTo = gradesAvailableTo;
            this.department = department;
            this.prerequisites = prerequisites;
        }


        // setter methods for each instance variable of a course object
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
        public ArrayList<String> getGradesAvailableTo() {
            return gradesAvailableTo;
        }

        public String getDepartment() {
            return department;
        }

        public Map<Integer, String> getPrerequisites() {
            return prerequisites;
        }

    }
}
