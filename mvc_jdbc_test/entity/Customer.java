package mvc_jdbc_test.entity;

public class Customer extends Entity {
    String id;
    String name;
    int age;
    String grade;
    String job;
    int point;

    public Customer(String id, String name, int age, String grade, String job, int point) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.job = job;
        this.point = point;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
