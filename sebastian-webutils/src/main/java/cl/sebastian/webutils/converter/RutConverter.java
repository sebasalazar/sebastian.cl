package cl.sebastian.webutils.converter;

import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.RutUtils;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class RutConverter implements Converter, Serializable {

    public final static String DEFAULT_RUT_PATTERN = "##.###.###-X";
    private static Logger logger = LoggerFactory.getLogger(RutConverter.class);
    private boolean ignorarRUTVacio = true;

    public RutConverter() {
    }

    public RutConverter(boolean ignorar) {
        this.ignorarRUTVacio = ignorar;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String result = "";
        try {
            String rutStr = (String) o;
            if (!StringUtils.isEmpty(rutStr)) {
                result = RutUtils.formatRutConverter(rutStr);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            String messageError = FacesUtils.getMessage("rutInvalido");
            throw new ConverterException(messageError);
        }
        return result;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        String result = "";
        if (StringUtils.isEmpty(value) && ignorarRUTVacio) {
            result = "";
        } else {
            result = RutUtils.formatRutConverter(value);
            if (StringUtils.isEmpty(result)) {
                String messageError = FacesUtils.getMessage("rutInvalido");
                throw new ConverterException(messageError);
            }
        }
        return result;
    }
}
