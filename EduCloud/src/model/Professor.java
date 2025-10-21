package model;

/**
 * Professor model holds a name and associated course.
 */
public class Professor {
    private String name;
    private Course course;

    public Professor(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() { return name; }
    public Course getCourse() { return course; }
}
