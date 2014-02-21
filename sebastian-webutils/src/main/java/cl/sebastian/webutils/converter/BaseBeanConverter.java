package cl.sebastian.webutils.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class BaseBeanConverter implements Converter {

    private static final Logger logger = LoggerFactory.getLogger(BaseBeanConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Object result = null;

        try {
            byte[] serObj = null;
            serObj = Hex.decodeHex(string.toCharArray());
            result = SerializationUtils.deserialize(serObj);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al convertir string : " + string + " ex: "
                    + ExceptionUtils.getStackTrace(e));
        }

        return result;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String result = "";
        try {
            byte[] serObj = SerializationUtils.serialize((Serializable) o);
            result = new String(Hex.encodeHex(serObj));
        } catch (Exception ex) {
            logger.error(ex.toString());
            logger.debug("Error al convertir objeto : " + o + " ex: "
                    + ExceptionUtils.getStackTrace(ex));
        }
        return result;
    }
}
