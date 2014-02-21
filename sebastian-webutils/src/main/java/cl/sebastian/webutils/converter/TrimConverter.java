package cl.sebastian.webutils.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class TrimConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return StringUtils.trim(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        if (o != null) {
            return StringUtils.trim(o.toString());
        } else {
            return null;
        }
    }
}