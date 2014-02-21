package cl.sebastian.webutils.converter;

import cl.sebastian.webutils.utils.FechaUtils;
import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.LocaleUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class LocaleDateConverter implements Converter {

    private static final Logger logger = LoggerFactory.getLogger(LocaleDateConverter.class);
    public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Date fecha = null;
        try {
            /* INCOMPLETO. Completar cuando se utilice el getAsObject */
            String lang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = LocaleUtils.getBrowserLocale(lang);

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, browserLocale);
            fecha = df.parse(string);
            logger.debug("String:" + string);
            logger.debug("Date:" + fecha);
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al parsear string a fecha: {}", e.toString());
        }

        return fecha;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        logger.debug("As String");

        String resultado = StringUtils.EMPTY;
        String datePart = StringUtils.EMPTY;
        String timePart = StringUtils.EMPTY;

        try {

            /* Obtengo locale browser */
            String lang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = LocaleUtils.getBrowserLocale(lang);

            /* Obtengo parte horaria */
            Date date = (Date) o;
            SimpleDateFormat formato = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
            timePart = formato.format(o);
            datePart = FechaUtils.getFechaStr(date, browserLocale, DateFormat.SHORT);

            /* Obtengo las horas, minutos y segundos para decidir si formateo con ellos o no */
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            logger.debug(hour + ":" + minute + ":" + second);

            /* Decido si va con hora:minuto:segundo o no */
            if (hour + minute + second == 0) {
                resultado = datePart;
                logger.debug("Fecha corta: " + resultado);
            } else {
                resultado = datePart + " " + timePart;
                logger.debug("Fecha larga: " + resultado);
            }

        } catch (Exception e) {
            logger.error("Problemas para convertir fecha a string: {}", e.toString());
        }

        return resultado;
    }
}
