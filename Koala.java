public class Koala {

    private int age;
    private String status;

    public Koala()
    {
        age = 0;
        status = "";
    }

    public Koala(int newAge, String newStatus)
    {
        age = newAge;
        status = newStatus;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
