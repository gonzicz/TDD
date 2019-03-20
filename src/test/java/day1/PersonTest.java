package day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class PersonTest {
    private Person person;
    private Person spouse;

    static Stream provideValidEmail() {
        return Stream.of("jan.kowalski@szkolenie.pl", "jan@szkolenie.pl", "jan@szkolenie.com");
    }

    static Stream provideEmails() {
        return Stream.of(
                Arguments.of("jan.kowalski@szkolenie.pl", true),
                Arguments.of("jan@szkolenie.pl", true),
                Arguments.of("jan@szkolenie.com", true),
                Arguments.of("jan.kowalski.szkolenie.pl", false),
                Arguments.of("@szkolenie.pl", false),
                Arguments.of("jan.kowalski@", false)
        );
    }

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

    @Test
    void personShouldEarnSomeMoney() {
        person.earn(1000);

        assertEquals(1000, person.getMoney());
    }

    @Test
    void personShouldEarnMuchMoney() {
        person.earn(1000);
        person.earn(2000);
        person.earn(3000);

        assertEquals(6000, person.getMoney());
    }

    @Test
    void personShouldNotEarnMinusMoney() {
        Exception exception = assertThrows(MinusMoneyException.class, () -> {
            person.earn(-1000);
        });
        assertEquals("No minus money -> :( ", exception.getMessage());
    }

    @Test
    void personShouldEarnMoneyAferWork() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            person.work(100, 300);
        });

//        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
//            person.work(10000, 300);
//        });

        assertEquals(300, person.getMoney());
    }

    @Test
    void testSomethingWithAssume() {
        System.setProperty("ENV", "PROD");

        assumeTrue(System.getProperty("ENV").equals("PROD"));

        assertTrue(true);
    }

    @Test
    void emailShouldBeValid() {
        person.setEmail("jan.kowalski@szkolenie.pl");

        assertTrue(person.isEmailValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"jan.kowalski@szkolenie.pl", "jan@szkolenie.pl", "jan@szkolenie.com"})
    void emailsShouldBeValid(String email) {
        person.setEmail(email);

        assertTrue(person.isEmailValid());
    }

    @ParameterizedTest
    @ValueSource(strings = {"jan.kowalski.szkolenie.pl", "@szkolenie.pl", "jan.kowalski@"})
    void emailsShouldNotBeValid(String email) {
        person.setEmail(email);

        assertFalse(person.isEmailValid());
    }

    @ParameterizedTest
    @MethodSource(value = "provideEmails")
    void checkEmailCorrections(String email, boolean expectedValidaition) {
        person.setEmail(email);

        assertEquals(expectedValidaition, person.isEmailValid());
    }

    @ParameterizedTest
    @MethodSource(value = "provideValidEmail")
    void emailsShouldByValidByMethodSource(String email) {
        person.setEmail(email);

        assertTrue(person.isEmailValid());
    }


}
