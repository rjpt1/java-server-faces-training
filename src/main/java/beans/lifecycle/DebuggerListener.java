/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.lifecycle;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Familia
 */
public class DebuggerListener implements javax.faces.event.PhaseListener {

    org.apache.logging.log4j.Logger log = LogManager.getRootLogger();

    @Override
    public void afterPhase(PhaseEvent pe) {
        if (log.isInfoEnabled()) {
            log.info("despues de la fase: " + pe.getPhaseId().toString());
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (log.isInfoEnabled()) {
            log.info("antes de la fase: " + pe.getPhaseId().toString());
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
