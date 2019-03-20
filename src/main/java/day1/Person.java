package day1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private Person spouse;
    private List<Person> children = new ArrayList<>();
    private int money;
    private String email;

    private String regex = "^(.+)@(.+)$";   // wyrażenie regularne
    Pattern pattern = Pattern.compile(regex);


    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Person> getChildren() {
        return children;
    }

    public Person getSpouse() {
//        System.out.println("getSpouse od " + name);
        return spouse;
    }

    public int getMoney() {
        return money;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void marriage(Person newPersonInMyLife) {
        if (spouse == null && !newPersonInMyLife.isMarriedWithAnotherPerson(this)) {
            this.spouse = newPersonInMyLife;
            spouse.marriage(this);
        }
    }

    public void divorce() {
        Person ex = this.spouse;
        this.spouse = null;
        if (ex.getSpouse() != null) {
            ex.divorce();
        }
    }

    public boolean isMarriedWithAnotherPerson(Person personToCheck) {
        if (spouse == null) {
            return false;
        }
        //return !spouse.equals(personToCheck);
        if (!spouse.equals(personToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public void earn(int cash) {
        if (cash < 0) {
            throw new MinusMoneyException("No minus money -> :( ");
        }
        money += cash;
    }

    public void work(long time, int cash) {
        try {
            Thread.sleep(time);
            earn(cash);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmailValid() {
        return pattern.matcher(email).matches();   // matcher sprawdza czy wzór ( regex ) zgadza się z mailem
    }
}
