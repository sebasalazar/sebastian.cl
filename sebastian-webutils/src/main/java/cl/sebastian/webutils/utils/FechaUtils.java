package cl.sebastian.webutils.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class FechaUtils {

    private static Logger logger = LoggerFactory.getLogger(FechaUtils.class);

    public static Date getFecha(int anio, int mes, int dia) {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            calendar.set(Calendar.YEAR, anio);
            calendar.set(Calendar.MONTH, mes - 1);
            calendar.set(Calendar.DATE, dia);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static boolean isHoraEnFecha(int hour, Date date) {
        boolean result = false;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            if (hour == calendar.get(Calendar.HOUR_OF_DAY)) {
                result = true;
            }

        } catch (Exception e) {
            result = false;
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return result;
    }

    public static Date getPrimeraHora() {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static Date getPrimeraHora(Date date) {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static Date getPrimeraHoraPrevia(Date date, int minusDays) {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -minusDays);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static Date getUltimaHora() {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static Date getUltimaHora(Date date) {
        Date fecha = new Date();
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = new Date();
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fecha;
    }

    public static boolean isDiaEnFecha(int day, Date date) {
        boolean result = false;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
                result = true;
            }

        } catch (Exception e) {
            result = false;
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return result;
    }

    public static int getUltimoDiaDelMes() {
        int day = 0;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());

            day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return day;
    }

    public static Date getUltimaSemana(Date date) {
        Date fdow = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            fdow = calendar.getTime();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fdow;
    }

    public static Date getDiaSiguiente(Date date, Integer next) {
        Date fdow = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, next);
            fdow = calendar.getTime();
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return fdow;
    }

    public static boolean isMismoDia(Date oneDay, Date otherDay) {
        return DateUtils.isSameDay(oneDay, otherDay);
    }

    public static String getFechaStrCL(Date date) {
        String result = "";
        try {
            Locale chileno = new Locale("es", "CL");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", chileno);
            result = formatter.format(date);
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return result;
    }

    public static String getFechaStr(Date date, Locale locale, int formatType) {
        String result = "";
        try {
            StringBuffer buffer = new StringBuffer();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            DateFormat df = DateFormat.getDateInstance(formatType, locale);

            /* Hack para tener año como YYYY y no como YY */
            FieldPosition yearPosition = new FieldPosition(DateFormat.YEAR_FIELD);
            StringBuffer format = df.format(calendar.getTime(), buffer, yearPosition);
            format.replace(yearPosition.getBeginIndex(), yearPosition.getEndIndex(), String.valueOf(calendar.get(Calendar.YEAR)));

            result = format.toString();

        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return result;
    }

    public static String getYear() {
        String year = "";
        try {
            Date now = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            year = StringUtils.trimToEmpty(calendar.get(Calendar.YEAR) + "");
        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug(e.toString());
        }
        return year;
    }
}