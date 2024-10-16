package com.example.assg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private TextView reportTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report); // Ensure this points to your layout file

        reportTextView = findViewById(R.id.reportTextView); // Initialize the TextView to display the report
        backButton = findViewById(R.id.backButton); // Initialize the back button

        // Generate and display the report
        String report = generateReport();
        reportTextView.setText(report); // Set the report text

        // Set up the back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(ReportActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Call finish to remove this activity from the back stack
            }
        });
    }

    // Student class to hold student information
    class Student {
        String rollNo, name, department;
        int semester;
        int[] marks;

        Student(String rollNo, String name, String department, int semester, int[] marks) {
            this.rollNo = rollNo;
            this.name = name;
            this.department = department;
            this.semester = semester;
            this.marks = marks;
        }

        // Calculate total marks
        int getTotal() {
            int total = 0;
            for (int mark : marks) {
                total += mark;
            }
            return total;
        }

        // Calculate percentage
        float getPercentage() {
            return (float) getTotal() / marks.length;
        }

        // Check if student has passed (assuming pass mark is 40 per subject)
        boolean hasPassed() {
            for (int mark : marks) {
                if (mark < 40) {
                    return false;
                }
            }
            return true;
        }
    }

    // Method to generate the report
    private String generateReport() {
        List<Student> students = getStudentData(); // Fetch student data
        StringBuilder report = new StringBuilder();

        // Sort students by total marks (descending order)
        Collections.sort(students, (s1, s2) -> s2.getTotal() - s1.getTotal());

        // Variables to hold report data
        int totalPassed = 0;
        int totalFailed = 0;
        List<String> passedWith60 = new ArrayList<>();

        // Generate report for each student
        report.append("Rank\tRoll No\tName\tDepartment\tTotal\tPercentage\tClass\tPass/Fail\n");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            String rank = String.valueOf(i + 1);
            String passFail = student.hasPassed() ? "Pass" : "Fail";
            if (student.hasPassed()) {
                totalPassed++;
                if (student.getPercentage() >= 60) {
                    passedWith60.add(student.name);
                }
            } else {
                totalFailed++;
            }

            // Add student data to the report
            report.append(rank).append("\t")
                    .append(student.rollNo).append("\t")
                    .append(student.name).append("\t")
                    .append(student.department).append("\t")
                    .append(student.getTotal()).append("\t")
                    .append(String.format("%.2f", student.getPercentage())).append("\t")
                    .append(getClassFromPercentage(student.getPercentage())).append("\t")
                    .append(passFail).append("\n");
        }

        // Add overall stats
        report.append("\nTotal Passed: ").append(totalPassed).append("\n");
        report.append("Total Failed: ").append(totalFailed).append("\n");
        report.append("Students with >= 60%: ").append(passedWith60).append("\n");

        return report.toString();
    }

    // Mock data for students (you can replace this with actual input or database)
    private List<Student> getStudentData() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "John", "CSE", 5, new int[]{80, 70, 65, 90}));
        students.add(new Student("S002", "Alice", "ECE", 5, new int[]{50, 55, 60, 75}));
        students.add(new Student("S003", "Bob", "EEE", 5, new int[]{35, 40, 30, 45}));
        return students;
    }

    // Get class based on percentage
    private String getClassFromPercentage(float percentage) {
        if (percentage >= 75) {
            return "Distinction";
        } else if (percentage >= 60) {
            return "First Class";
        } else if (percentage >= 50) {
            return "Second Class";
        } else {
            return "Pass Class";
        }
    }
}
