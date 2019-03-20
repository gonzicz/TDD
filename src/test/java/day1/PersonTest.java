package day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person;
    Person spouse;

    @BeforeEach
    void setUp() {
        person = new Person("Jan Kowalski");
        spouse = new Person("Alicja Nowak");
    }

    @Test
    void constructorShouldSetName() {
        assertEquals("Jan Kowalski", person.getName());
    }

    @Test
    void personShouldChild() {
        Person child = new Person("Adam Kowalski");

        person.addChild(child);
        assertNotNull(person.getChildren());
        assertEquals(1, person.getChildren().size());
    }

    @Test
    void personShouldHaveSpouse() {
        person.marriage(spouse);

        assertNotNull(person.getSpouse());
    }

    @Test
    void personShouldNotHaveAnotherSpouse() {
        Person lover = new Person("Beata K");

        person.marriage(spouse);
        person.marriage(lover);

        assertEquals(spouse, person.getSpouse(), "Małżonką Jana Nowaka powinna " +
                "być Alicja Nowak, a obecnie jest: " + person.getSpouse().getName());
    }

    @Test
    void spouseShouldHaveSpouse() {
        person.marriage(spouse);

        assertEquals(person, spouse.getSpouse());
    }

    @Test
    void loverShouldNotMarriageMarriedPerson() {
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
        person.marriage(spouse);

        assertFalse(person.isMarriedWithAnotherPerson(spouse));
    }

    @Test
    void personShouldBeMarriedWithAnotherOne() {
        Person lover = new Person("Kasia W");

        person.marriage(spouse);

        assertTrue(person.isMarriedWithAnotherPerson(lover));
    }

    @Test
    void personShouldNotHaveSpouseAfetrDivorce() {
        person.marriage(spouse);
        person.divorce();

        assertNull(person.getSpouse());
    }

    @Test
    void spouseShouldNotHaveSpouseAfterDivorce() {
        // Arrange / Given
//        Person person = new Person("Jan Nowak");
//        Person spouse = new Person("Alicja Nowak");

        // Act / When
        person.marriage(spouse);
        person.divorce();

        // Assert / Then
        assertNull(spouse.getSpouse());
    }
}
