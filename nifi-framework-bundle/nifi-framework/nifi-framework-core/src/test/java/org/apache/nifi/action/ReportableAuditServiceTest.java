package org.apache.nifi.action;

import org.apache.nifi.admin.service.AuditService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReportableAuditServiceTest {

    @Mock
    AuditService auditService;
    InMemoryAuditActionReporter auditActionReporter;
    private ReportableAuditService reportableAuditService;

    @BeforeEach
    void setUp() {
        auditActionReporter = new InMemoryAuditActionReporter();
        reportableAuditService = new ReportableAuditService(auditService, auditActionReporter);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldDecorateAuditService() {
        // given
        assertInstanceOf(AuditService.class, reportableAuditService);
    }

    @Test
    void shouldReportActions() {
        // given
        final List<Action> actions = new ArrayList<>();
        actions.add(new FlowChangeAction());

        // when
        reportableAuditService.addActions(actions);

        // then
        assertEquals(1, auditActionReporter.getReportedActions().size());
    }

    private static class InMemoryAuditActionReporter implements AuditActionReporter {
        List<Action> reportedActions = new ArrayList<>();
        @Override
        public void reportActions(Collection<Action> actions) {
            reportedActions.addAll(actions);
        }

        List<Action> getReportedActions() {
            return reportedActions;
        }

    }
}