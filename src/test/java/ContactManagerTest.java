import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public void setupAll() {
        System.out.println("should print before all tests");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact() {
        contactManager.addContact("John", "Doe", "0123456789");

        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .anyMatch(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("should not create contact when first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                contactManager.addContact(null, "Doe", "0213456789"));
    }

    @Test
    @DisplayName("should not create contact when last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                contactManager.addContact("John", null, "0213456789"));
    }

    @Test
    @DisplayName("should not create contact when phone number is null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () ->
                contactManager.addContact("John", "Doe", null));
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should execute after each test");
    }

    @AfterAll
    public void tearDownALL() {
        System.out.println("should be executed at the end of the Test");
    }

    @DisplayName("repeat contact creation test 5 times")
    @RepeatedTest(
            value = 5,
            name = "repeating contact creation test {currentRepetition} of {totalRepetitions}"
    )
    public void shouldTestContractCreationRepeatedly() {
        contactManager.addContact("John", "Doe", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat contact creation test 5 times")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456789", "+0123456789"})
    public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat contact creation test 5 times")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456789", "0123456789");
    }

    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvSource({"0123456789", "0123456789", "0123456789"})
    public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }
}