package cl.sebastian.webutils.converter;

import cl.sebastian.webutils.utils.FacesUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class SiNoConverter implements Converter {

    private static final String SINO_MSG_ERROR = "Validación incorrecta";

    public static FacesMessage creaMensajeSiNoInvalido(Object o) {
        FacesMessage fm = new FacesMessage();
        fm.setDetail(SINO_MSG_ERROR);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        fm.setSummary(SINO_MSG_ERROR + " " + o);
        return fm;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        String formatedCheck = StringUtils.EMPTY;
        Boolean sino = (Boolean) o;

        if (sino != null) {
            if (sino == true) {
                formatedCheck = FacesUtils.getMessage("si");
            } else {
                formatedCheck = FacesUtils.getMessage("no");
            }
        }
        return formatedCheck;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Boolean result = null;
        if (value != null) {
            String no = FacesUtils.getMessage("no");
            if (no.equals(value)) {
                result = false;
            } else {
                result = true;
            }
        }

        if (result == null) {
            FacesUtils.error(uic, SINO_MSG_ERROR);
            throw new ConverterException(creaMensajeSiNoInvalido(value));
        }
        return result;
    }
}
