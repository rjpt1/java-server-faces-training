/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.backing;

import beans.helper.ColoniaHelper;
import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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

    @Inject
    private ColoniaHelper coloniaHelper;

    private boolean comentarioEnviado;

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

    public String enviar() {
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
        } else {
            log.info("entrando a fallo");
            return "fallo";
        }
    }

    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        int nuevoCodigoPostal = ((Long) valueChangeEvent.getNewValue()).intValue();

        UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");
        int coloniaId = this.coloniaHelper.getColoniaIdPorCP(nuevoCodigoPostal);
        coloniaIdInputText.setValue(coloniaId);
        coloniaIdInputText.setSubmittedValue(coloniaId);

        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        String nuevaCiudad = "Mexico";
        ciudadInputText.setValue(nuevaCiudad);
        ciudadInputText.setSubmittedValue(nuevaCiudad);

        facesContext.renderResponse();
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

    public void ocultarComentario(ActionEvent actionEvent) {
        this.comentarioEnviado = !this.comentarioEnviado;
    }

    public ColoniaHelper getColoniaHelper() {
        return coloniaHelper;
    }

    public void setColoniaHelper(ColoniaHelper coloniaHelper) {
        this.coloniaHelper = coloniaHelper;
    }
}
