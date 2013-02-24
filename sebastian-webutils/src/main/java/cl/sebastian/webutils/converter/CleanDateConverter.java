package cl.sebastian.webutils.converter;

import cl.sebastian.webutils.utils.FechaUtils;
import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.LocaleUtils;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 ********************************************************************
 * Converter según
 * locale que fuerza a mostrar sólo la fecha, sin HH:mm:ss.
 */
public class CleanDateConverter implements Converter {

    private static Logger logger = Logger.getLogger(CleanDateConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        Date date = null;
        try {

            /* Obtengo locale browser */
            String lang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = LocaleUtils.getBrowserLocale(lang);

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, browserLocale);
            date = df.parse(string);
        } catch (Exception e) {
            logger.error("Error al parsear string a fecha");
            logger.error(e.getMessage());
        }

        return date;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        String resultado = StringUtils.EMPTY;

        try {

            /* Obtengo locale browser */
            String lang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = LocaleUtils.getBrowserLocale(lang);

            /* Convierto (tipo SHORT) según LOCALE */
            Date date = (Date) o;
            resultado = FechaUtils.getFechaStr(date, browserLocale, DateFormat.SHORT);

        } catch (Exception e) {
            logger.error("Problemas para convertir fecha a string");
            logger.error(e.getMessage());
        }

        return resultado;
    }
}
