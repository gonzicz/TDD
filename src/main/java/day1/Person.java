package day1;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Person spouse;
    private List<Person> children = new ArrayList<>();


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

    public void addChild(Person child) {
        children.add(child);
    }

    public void marriage(Person newPersonInMyLife) {
        if (spouse == null && !newPersonInMyLife.isMarriedWithAnotherPerson(this)) {
            this.spouse = newPersonInMyLife;
            spouse.marriage(this);
        }
    }

    public void divorce(){
        this.spouse = null;
        spouse.divorce();
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
}
