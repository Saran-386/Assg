package com.example.assg;

class Student {
    private String name;
    private int[] marks; // Array to hold marks
    private int total;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public void calculateTotal() {
        total = 0;
        for (int mark : marks) {
            total += mark; // Sum up the marks
        }
    }

    public boolean hasPassed() {
        // Assuming passing requires all marks to be >= 40
        for (int mark : marks) {
            if (mark < 40) return false;
        }
        return true;
    }
}

