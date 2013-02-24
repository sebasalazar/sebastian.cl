package cl.sebastian.webutils.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.regexp.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class RutUtils implements Serializable {

    public final static String DEFAULT_RUT_PATTERN = "##.###.###-X";
    private static RE outRutRE = new RE("([0\\#\\.\\,]+)([^X^x^\\,^\\.^9]*)([Xx]*)");
    private static RE inRutRE = new RE("([0-9\\.]+)\\-([a-zA-Z0-9]+)");
    private static Logger logger = LoggerFactory.getLogger(RutUtils.class);

    public static Long parseRut(String value) {
        Long result = null;
        if (value != null) {
            // Borramos los caracteres esperables
            String rut = StringUtils.remove(value, ".");
            rut = StringUtils.remove(rut, ",");
            rut = StringUtils.remove(rut, "-");
            // Separamos el numero y el digito verificador
            if (!StringUtils.isEmpty(rut) && rut.length() > 1 && rut.length() < 11) {
                String numRut = rut.substring(0, rut.length() - 1);
                String dv = rut.substring(rut.length() - 1).toUpperCase();
                if (NumberUtils.isDigits(numRut)) {
                    long longRut = Long.parseLong(numRut);
                    if (dv.charAt(0) == getDV(longRut)) {
                        result = new Long(longRut);
                    }
                }
            }
        }
        return result;
    }

    public static String formatRut(Number o) throws Exception {
        return formatRut(o, DEFAULT_RUT_PATTERN);
    }

    public static String formatRut(Number o, String pattern) throws Exception {
        String formatedCheck = StringUtils.EMPTY;
        Number rut = (Number) o;
        if (rut != null) {
            String value = rut + "-" + getDV(rut.longValue());
            if (!(outRutRE.match(pattern))) {
                logger.debug("Pattern incorrecto : " + pattern);
            }
            if (!(inRutRE.match(value))) {
                throw new Exception("Formato de entrada incorrecto:"
                        + rut);
            }
            String checkFmt = outRutRE.getParen(1);
            String valueCheck = inRutRE.getParen(1);
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            int indexGroupChar = checkFmt.indexOf(".") >= 0 ? checkFmt.indexOf(".") : checkFmt.indexOf(",");
            if (indexGroupChar >= 0) {
                dfs.setGroupingSeparator(checkFmt.charAt(indexGroupChar));
            }
            DecimalFormat df = null;
            if (indexGroupChar >= 0) {
                df = new DecimalFormat(checkFmt.replace(
                        checkFmt.charAt(indexGroupChar), ','), dfs);
            } else {
                df = new DecimalFormat(checkFmt, dfs);
            }
            df.setGroupingUsed(indexGroupChar >= 0);
            formatedCheck = df.format(Long.parseLong(valueCheck));
            String separator = outRutRE.getParen(2);
            if (!StringUtils.isEmpty(separator)) {
                formatedCheck = formatedCheck + separator;
            }
            String dv = outRutRE.getParen(3);
            if (!StringUtils.isEmpty(dv)) {
                formatedCheck = formatedCheck + inRutRE.getParen(2);
            }
        }
        return formatedCheck;
    }

    public static char getDV(long rut) {
        long M = 0, S = 1, T = rut;
        for (; T != 0; T /= 10) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        return (char) (S != 0 ? S + 47 : 75);
    }

    public static boolean isRut(String rut) {
        boolean resultado = false;
        String formato = "";

        try {
            formato = StringUtils.remove(rut, ".");
            formato = StringUtils.remove(formato, ",");
            formato = StringUtils.remove(formato, "-");
            formato = formato.toUpperCase();

            String numeroStr = formato.substring(0, formato.length() - 1);
            Character digito = formato.charAt(formato.length() - 1);

            Integer numero = Integer.parseInt(numeroStr);
            if (digito == getDV(numero)) {
                resultado = true;
            }

        } catch (Exception e) {
            logger.error(e.toString());
            logger.debug("Error al parsear rut", e);
        }

        return resultado;
    }

    public static String formatRutConnect(String rutStr) {
        String resultado = null;

        try {
            String formato = "";
            formato = StringUtils.remove(rutStr, ".");
            formato = StringUtils.remove(formato, ",");
            formato = StringUtils.remove(formato, "-");
            formato = formato.toUpperCase();

            String numeroStr = formato.substring(0, formato.length() - 1);
            Character digito = formato.charAt(formato.length() - 1);

            Integer numero = Integer.parseInt(numeroStr);
            resultado = formatRut(numero);
        } catch (Exception e) {
            resultado = null;
            logger.error(e.toString());
            logger.debug("Error al formatear", e);
        }

        return resultado;
    }

    public static String formatRutConverter(String rutStr) {
        String resultado = null;

        try {
            String formato = "";
            formato = StringUtils.remove(rutStr, ".");
            formato = StringUtils.remove(formato, ",");
            formato = StringUtils.remove(formato, "-");
            formato = formato.toUpperCase();

            String numeroStr = formato.substring(0, formato.length() - 1);
            Character digito = formato.charAt(formato.length() - 1);
            Integer numero = Integer.parseInt(numeroStr);
            char dV = getDV(numero);
            if (digito.equals(dV)) {
                resultado = formatRut(numero);
            } else {
                String messageError = FacesUtils.getMessage("rutInvalido");
                throw new Exception(messageError);
            }
        } catch (Exception e) {
            resultado = null;
            logger.error(e.toString());
            logger.debug("Error al formatear", e);
        }

        return resultado;
    }
}
