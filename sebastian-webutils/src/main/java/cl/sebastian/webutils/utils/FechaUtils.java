package cl.sebastian.webutils.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class FechaUtils {

    private static final Logger logger = LoggerFactory.getLogger(FechaUtils.class);

    public static Date getFecha(int anio, int mes, int dia) {
        Date fecha = null;
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
            fecha = null;
            logger.error("Error al obtener fecha: {}", e.toString());
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
            logger.error("Error al validar hora en fecha: {}", e.toString());
        }
        return result;
    }

    public static Date getPrimeraHora() {
        Date fecha = null;
        try {
            fecha = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al obtener Primera hora del día: {}", e.toString());
        }
        return fecha;
    }

    public static Date getPrimeraHora(Date dia) {
        Date fecha = null;
        try {
            if (dia != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dia);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                fecha = calendar.getTime();
            }
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al obtener Primera hora del día: {}", e.toString());
        }
        return fecha;
    }

    public static Date getPrimeraHoraPrevia(Date dia, int diasPrevios) {
        Date fecha = null;
        try {
            if (dia != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dia);
                calendar.add(Calendar.DAY_OF_MONTH, -diasPrevios);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                fecha = calendar.getTime();
            }
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al obtener Primera hora de {} días previos: {}", diasPrevios, e.toString());
        }
        return fecha;
    }

    public static Date getUltimaHora() {
        Date fecha = null;
        try {
            fecha = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            fecha = calendar.getTime();
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al obtener última hora: {}", e.toString());
        }
        return fecha;
    }

    public static Date getUltimaHora(Date dia) {
        Date fecha = null;
        try {
            if (dia != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dia);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
                fecha = calendar.getTime();
            }
        } catch (Exception e) {
            fecha = null;
            logger.error("Error al obtener última hora: {}", e.toString());
        }
        return fecha;
    }

    public static boolean isDiaEnFecha(int dia, Date fecha) {
        boolean result = false;
        try {
            if (fecha != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(fecha);

                if (dia == calendar.get(Calendar.DAY_OF_MONTH)) {
                    result = true;
                }
            }
        } catch (Exception e) {
            result = false;
            logger.error("Error al validar día en fecha: {}", e.toString());
        }
        return result;
    }

    public static int getUltimoDiaDelMes() {
        int dia = 0;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(new Date());

            dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            logger.error("Error al obtener último día del mes: {}", e.toString());
        }
        return dia;
    }

    public static Date getUltimaSemana(Date dia) {
        Date ultSem = null;
        try {
            if (dia != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dia);
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                ultSem = calendar.getTime();
            }
        } catch (Exception e) {
            ultSem = null;
            logger.error("Error al obtener inicio de última semana: {}", e.toString());
        }
        return ultSem;
    }

    public static Date getDiaSiguiente(Date fecha, Integer diasPosteriores) {
        Date dia = null;
        try {
            if (fecha != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(fecha);
                calendar.add(Calendar.DAY_OF_MONTH, diasPosteriores);
                dia = calendar.getTime();
            }
        } catch (Exception e) {
            dia = null;
            logger.error("Error al obtener {} días siguiente: {}", diasPosteriores, e.toString());
        }
        return dia;
    }

    public static boolean isMismoDia(Date oneDay, Date otherDay) {
        return DateUtils.isSameDay(oneDay, otherDay);
    }

    public static String getFechaStrCL(Date fecha) {
        String result = "";
        try {
            if (fecha != null) {
                Locale chileno = new Locale("es", "CL");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", chileno);
                result = formatter.format(fecha);
            }
        } catch (Exception e) {
            result = "";
            logger.error("Error al obtener fecha en formato Chileno: {}", e.toString());
        }
        return result;
    }

    public static String getFechaStr(Date fecha, Locale idioma, int formato) {
        String result = "";
        try {
            StringBuffer buffer = new StringBuffer();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);

            DateFormat df = DateFormat.getDateInstance(formato, idioma);

            /* Hack para tener año como YYYY y no como YY */
            FieldPosition yearPosition = new FieldPosition(DateFormat.YEAR_FIELD);
            StringBuffer format = df.format(calendar.getTime(), buffer, yearPosition);
            format.replace(yearPosition.getBeginIndex(), yearPosition.getEndIndex(), String.valueOf(calendar.get(Calendar.YEAR)));

            result = format.toString();

        } catch (Exception e) {
            result = "";
            logger.error("Error al formatear desde idioma: {}", e.toString());
        }
        return result;
    }

    public static Integer getYear() {
        Integer year = null;
        try {
            Date now = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            year = calendar.get(Calendar.YEAR);
        } catch (Exception e) {
            year = null;
            logger.error("Error al Obtener año: {}", e.toString());
        }
        return year;
    }

    public static Integer getYear(Date fecha) {
        Integer year = null;
        try {
            if (fecha != null) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(fecha);
                year = calendar.get(Calendar.YEAR);
            }
        } catch (Exception e) {
            year = null;
            logger.error("Error al Obtener año: {}", e.toString());
        }
        return year;
    }
}
