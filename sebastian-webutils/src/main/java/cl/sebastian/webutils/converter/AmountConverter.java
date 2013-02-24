package cl.sebastian.webutils.converter;

import cl.sebastian.webutils.utils.FacesUtils;
import cl.sebastian.webutils.utils.LocaleUtils;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.log4j.Logger;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public class AmountConverter implements Converter {

    private static Logger logger = Logger.getLogger(AmountConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String monto = "";
        try {
            String lang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = LocaleUtils.getBrowserLocale(lang);
            logger.debug("LOCALE obtenido de UTILS: " + browserLocale);
            monto = NumberFormat.getInstance(browserLocale).format((BigDecimal) o);
            logger.debug("Monto convertido: " + monto);
        } catch (Exception e) {
            logger.warn("Error convirtiendo monto según LOCALE: " + e);
        }
        return monto;
    }
}
