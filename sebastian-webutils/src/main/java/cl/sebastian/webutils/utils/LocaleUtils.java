package cl.sebastian.webutils.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class LocaleUtils {

    private static final Logger logger = LoggerFactory.getLogger(LocaleUtils.class);

    public static Locale getBrowserLocale(String lang) {

        Locale locale = new Locale("es", "CL");

        try {

            String stringLocale = "";

            if (lang != null) {

                StringTokenizer st = new StringTokenizer(lang, ",");
                if (st.hasMoreTokens()) {
                    stringLocale = st.nextToken();
                }

                String[] localeParts = StringUtils.split(stringLocale, "-");

                if (localeParts.length == 2) {
                    locale = new Locale(localeParts[0], localeParts[1].toUpperCase());
                } else if (localeParts.length == 1) {
                    locale = new Locale(localeParts[0]);
                }

            }
        } catch (Exception e) {
            locale = new Locale("es", "CL");
            logger.error("Error al convertir monto según LOCALE: {}", e.toString());
        }

        return locale;
    }

    public static String getLang() {
        String lang = "es";
        try {
            String browserLang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = getBrowserLocale(browserLang);
            lang = StringUtils.lowerCase(browserLocale.getLanguage()).trim();

            // @TODO Mejorar esta lógica si se aceptan más idiomas.
            if ("es".equals(lang)) {
                lang = "es";
            } else {
                lang = "en";
            }
        } catch (Exception e) {
            lang = "es";
            logger.error(e.toString());
            logger.debug("Error al obtener idioma", e);
        }
        return lang;
    }

    public static String getLang(String browserLang) {
        String lang = "es";
        try {
            Locale browserLocale = getBrowserLocale(browserLang);
            lang = browserLocale.getLanguage();
        } catch (Exception e) {
            lang = "es";
            logger.error(e.toString());
        }
        return lang;
    }

    public static String getMoneyFormatted(BigDecimal money) {
        String localeMoney = "";
        try {
            String browserLang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = getBrowserLocale(browserLang);

            NumberFormat format = NumberFormat.getCurrencyInstance(browserLocale);

            String formatMoney = format.format(money.doubleValue());
            String symbol = format.getCurrency().getSymbol();

            localeMoney = symbol + " " + formatMoney;
        } catch (Exception e) {
            localeMoney = "";
            logger.error(e.toString());
        }
        return localeMoney;
    }

    public static String getMoney(BigDecimal money) {
        String localeMoney = "";
        try {
            String browserLang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = getBrowserLocale(browserLang);

            NumberFormat format = NumberFormat.getInstance(browserLocale);

            localeMoney = format.format(money.doubleValue());
        } catch (Exception e) {
            localeMoney = "";
            logger.error("Error al obtener Moneda", e.toString());
        }
        return localeMoney;
    }

    public static String getDate(Date date) {
        String localeDay = "";
        try {
            String browserLang = FacesUtils.getRequest().getHeader("Accept-Language");
            Locale browserLocale = getBrowserLocale(browserLang);

            localeDay = FechaUtils.getFechaStr(date, browserLocale, DateFormat.SHORT);
        } catch (Exception e) {
            localeDay = "";
            logger.error("Error al obtener Fecha: {}", e.toString());
        }
        return localeDay;
    }
}
