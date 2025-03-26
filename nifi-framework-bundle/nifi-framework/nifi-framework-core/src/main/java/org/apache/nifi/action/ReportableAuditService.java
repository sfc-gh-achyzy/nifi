package org.apache.nifi.action;

import org.apache.nifi.admin.service.AuditService;
import org.apache.nifi.history.History;
import org.apache.nifi.history.HistoryQuery;
import org.apache.nifi.history.PreviousValue;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportableAuditService implements AuditService {

    private final AuditService auditService;
    private final FlowActionReporter flowActionReporter;
    private final ActionConverter actionConverter;

    public ReportableAuditService(AuditService auditService, FlowActionReporter flowActionReporter, ActionConverter actionConverter) {
        this.auditService = auditService;
        this.flowActionReporter = flowActionReporter;
        this.actionConverter = actionConverter;
    }

    @Override
    public void addActions(Collection<Action> actions) {
        auditService.addActions(actions);
        flowActionReporter.reportFlowActions(
                actions.stream()
                        .map(actionConverter::convert)
                        .toList()
        );
    }

    @Override
    public Map<String, List<PreviousValue>> getPreviousValues(String componentId) {
        return auditService.getPreviousValues(componentId);
    }

    @Override
    public void deletePreviousValues(String propertyName, String componentId) {
        auditService.deletePreviousValues(propertyName, componentId);
    }

    @Override
    public History getActions(HistoryQuery actionQuery) {
        return auditService.getActions(actionQuery);
    }

    @Override
    public History getActions(int firstActionId, int maxActions) {
        return auditService.getActions(firstActionId, maxActions);
    }

    @Override
    public Action getAction(Integer actionId) {
        return auditService.getAction(actionId);
    }

    @Override
    public void purgeActions(Date end, Action purgeAction) {
        auditService.purgeActions(end, purgeAction);
    }
}
