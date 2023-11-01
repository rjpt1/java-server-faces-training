/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.backing;

import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Familia
 */
@Named
@RequestScoped
public class VacanterForm {
    @Inject
    private Candidato candidato;
    
    Logger log = LogManager.getRootLogger();

    public VacanterForm() {
        log.info("creando objeto VacanterForm ");
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    
    public String enviar(){
        if (this.candidato.getNombre().equals("Juan")) {
            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "gracias pero Juan Perez ya trabaja con nosotros";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null; // mensaje global
                facesContext.addMessage(msg, facesMessage);
                return "index";
            }
            log.info("entrando a exito");
            return "exito";
        }
        else {
            log.info("entrando a fallo");
            return "fallo";
        }
    }
}
