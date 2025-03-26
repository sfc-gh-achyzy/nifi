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
    private AuditService auditService;
    private InMemoryFlowActionReporter flowActionReporter;
    private ReportableAuditService reportableAuditService;

    @BeforeEach
    void setUp() {
        flowActionReporter = new InMemoryFlowActionReporter();
        reportableAuditService = new ReportableAuditService(auditService, flowActionReporter, new NoOpActionConverter());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldDecorateAuditService() {
        assertInstanceOf(AuditService.class, reportableAuditService);
    }

    @Test
    void shouldReportActions() {
        final List<Action> actions = new ArrayList<>();
        actions.add(new FlowChangeAction());

        reportableAuditService.addActions(actions);

        assertEquals(1, flowActionReporter.getReportedActions().size());
    }

    private static class InMemoryFlowActionReporter implements FlowActionReporter {
        List<FlowAction> reportedActions = new ArrayList<>();
        public void reportFlowActions(Collection<FlowAction> actions) {
            reportedActions.addAll(actions);
        }

        List<FlowAction> getReportedActions() {
            return reportedActions;
        }

    }

}