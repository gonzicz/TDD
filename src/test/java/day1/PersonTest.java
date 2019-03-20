package day1;

import day1.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    void constructorShouldSetName() {
        Person person = new Person("Jan Kowalski");
        assertEquals("Jan Kowalski", person.getName());
    }

    @Test
    void personShouldChild() {
        Person person = new Person("Jan Kowalski");
        Person child = new Person("Adam Kowalski");

        person.addChild(child);
        assertNotNull(person.getChildren());
        assertEquals(1, person.getChildren().size());
    }

    @Test
    void personShouldHaveSpouse() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");

        person.marriage(spouse);

        assertNotNull(person.getSpouse());
    }

    @Test
    void personShouldNotHaveAnotherSpouse() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");
        Person lover = new Person("Beata K");

        person.marriage(spouse);
        person.marriage(lover);

        assertEquals(spouse, person.getSpouse(), "Małżonką Jana Nowaka powinna " +
                "być Alicja Nowak, a obecnie jest: " + person.getSpouse().getName());
    }

    @Test
    void spouseShouldHaveSpouse() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");

        person.marriage(spouse);

        assertEquals(person, spouse.getSpouse());
    }

    @Test
    void loverShouldNotMarriageMarriedPerson() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");
        Person lover = new Person("Beata K");

        person.marriage(spouse);
        lover.marriage(person);

        assertAll(
                () -> assertNull(lover.getSpouse()),
                () -> assertEquals(spouse, person.getSpouse())
        );
    }

    @Test
    void personShouldNotBeMarriedWithAnotherOne() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");

        person.marriage(spouse);

        assertFalse(person.isMarriedWithAnotherPerson(spouse));
    }

    @Test
    void personShouldBeMarriedWithAnotherOne() {
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");
        Person lover = new Person("Kasia W");

        person.marriage(spouse);

        assertTrue(person.isMarriedWithAnotherPerson(lover));
    }

    @Test
    void personShouldNotHaveSpouseAfetrDivorce(){
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");

        person.marriage(spouse);
        person.divorce();

        assertNull(person.getSpouse());
    }

    @Test
    void spouseShouldNotHaveSpouseAfterDivorce(){
        Person person = new Person("Jan Nowak");
        Person spouse = new Person("Alicja Nowak");

        person.marriage(spouse);
        person.divorce();

        assertNull(spouse.getSpouse());
    }
}
