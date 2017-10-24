package hibernate;


public class Student {
	 
    private Integer sid; //学号
    private Integer age; //年龄
    private String name; //姓名
    public Integer getSid() {
        return sid;
    }
    public void setSid(Integer sid) {
        this.sid = sid;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student [sid=" + sid + ", age=" + age + ", name=" + name + "]";
    }
     
     
}
