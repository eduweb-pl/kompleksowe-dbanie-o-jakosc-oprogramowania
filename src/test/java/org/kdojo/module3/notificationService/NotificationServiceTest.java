package org.kdojo.module3.notificationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private static EmailService spyEmailService;
    private static DatabaseService stubDatabaseService;
    private NotificationService notificationService;


    @BeforeEach
    void setUp() {
        spyEmailService = Mockito.mock(EmailService.class);
        stubDatabaseService = Mockito.mock(DatabaseService.class);
        List<String> dummyRecipients = List.of("alice@example.com", "bob@example.com");
        notificationService = new NotificationService(spyEmailService, stubDatabaseService, dummyRecipients, "admin@example.com");
    }

    @Test
    void shouldSendEmailToAdminAndRecipientsWhenNewRowExists() {
        // given: the database returns true when a new row exists
        Mockito.when(stubDatabaseService.hasNewRows()).thenReturn(true);

        // when: the notifyIfNewRow method is executed
        notificationService.notifyIfNewRow();

        // then: verify that the sendEmail method was called the appropriate number of times
        verify(spyEmailService, times(1)).sendEmail("admin@example.com", "New row added", "A new row has been added to the database.");
        verify(spyEmailService, times(1)).sendEmail("alice@example.com", "New row added", "Welcome alice");
        verify(spyEmailService, times(1)).sendEmail("bob@example.com", "New row added", "Welcome bob");
    }

    @Test
    void shouldNotSendEmailWhenNoNewRowExists() {
        // given: the database returns false when no new rows exist
        Mockito.when(stubDatabaseService.hasNewRows()).thenReturn(false);

        // when: the notifyIfNewRow method is executed
        notificationService.notifyIfNewRow();

        // then: verify that the sendEmail method was not called
        verify(spyEmailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void shouldHandleExceptionWhenCheckingForNewRows() {
        // given: the database throws an exception
        when(stubDatabaseService.hasNewRows()).thenThrow(new RuntimeException("Database error"));

        // when: the notifyIfNewRow method is executed
        // then: verify that an exception is thrown and no emails are sent
        assertThatThrownBy(notificationService::notifyIfNewRow)
                .isInstanceOf(DatabaseException.class)
                .hasMessage("Error while connecting to DB");
        verify(spyEmailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void shouldHandleExceptionWhenSendingEmail() {
        // given: the database states there are new rows
        EmailService mockEmailService = Mockito.mock(EmailService.class);
        List<String> dummyRecipients = List.of("alice@example.com", "bob@example.com");
        notificationService = new NotificationService(mockEmailService, stubDatabaseService, dummyRecipients, "admin@example.com");

        when(stubDatabaseService.hasNewRows()).thenReturn(true);
        doThrow(new RuntimeException("Email service error")).when(mockEmailService).sendEmail(eq("alice@example.com"), anyString(), anyString());

        // when: the notifyIfNewRow method is executed
        // then: verify that an exception is thrown
        assertThatThrownBy(notificationService::notifyIfNewRow)
                .isInstanceOf(EmailServiceException.class)
                .hasMessage("Error while sending email");
    }

    @Test
    void shouldHandleSlowEmailService() {
        // given: the database states there are new rows
        when(stubDatabaseService.hasNewRows()).thenReturn(true);
        EmailService mockEmailService = Mockito.mock(EmailService.class);
        notificationService = new NotificationService(mockEmailService, stubDatabaseService, List.of("alice@example.com"), "admin@example.com");

        doAnswer(invocation -> {
            Thread.sleep(2000); // Simulate delay
            return null;
        }).when(mockEmailService).sendEmail(anyString(), anyString(), anyString());

        // when: the notifyIfNewRow method is executed
        notificationService.notifyIfNewRow();

        // then: verify that the application still works correctly even with the delay
        verify(mockEmailService, times(1)).sendEmail(eq("admin@example.com"), anyString(), anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "alice@example.com, Welcome alice",
            "bob@example.com, Welcome bob",
            "charlie@example.com, Welcome charlie"
    })
    void shouldSendPersonalizedEmails(String recipient, String expectedMessage) {
        // given: the database states there are new rows
        when(stubDatabaseService.hasNewRows()).thenReturn(true);
        notificationService = new NotificationService(spyEmailService, stubDatabaseService, List.of(recipient), "admin@example.com");

        // when: the notifyIfNewRow method is executed
        notificationService.notifyIfNewRow();

        // then: verify that a personalized email was sent to the recipient
        verify(spyEmailService, times(1)).sendEmail(eq(recipient), eq("New row added"), eq(expectedMessage));
    }
}