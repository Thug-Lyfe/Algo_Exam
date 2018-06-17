package sorting_and_searching;

import java.util.*;

public class grade_table {
    static public class Pair {
        private double value;
        private String grade;

        public Pair(double value, String grade) {
            this.value = value;
            this.grade = grade;
        }

        public double getValue() {
            return value;
        }

        public String getGrade() {
            return grade;
        }
    }
    public static void main(String[] args) {
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("A+",4.33);
        map.put("A",4.00);
        map.put("A-",3.67);
        map.put("B+",3.33);
        map.put("B",3.00);
        map.put("B-",2.67);
        map.put("C+",2.33);
        map.put("C",2.00);
        map.put("C-",1.67);
        map.put("D",1.00);
        map.put("F",0.00);
        String[] grades = {"A+","A","A-","B+","B","B-","C+","C","C-","D","F"};
        Random ran = new Random();
        for (int i = 1;i<6;i++){
            String[] studentGrades = new String[10];
            String studentString = "";
            for(int k = 0;k<10;k++){
                studentGrades[k] = grades[ran.nextInt(11)];
                studentString += studentGrades[k]+", ";
            }
            System.out.println("student "+i+", has a gpa on: "+computeGPA(map,studentGrades)+"   :: with these grads: "+studentString);
        }

    }
    static private double computeGPA(HashMap<String,Double> scoresMap, String[] grades) {

        double totalGrades = 0;
        for(String grade : grades) {
            totalGrades += scoresMap.get(grade);
        }
        return totalGrades / grades.length;
    }
}
