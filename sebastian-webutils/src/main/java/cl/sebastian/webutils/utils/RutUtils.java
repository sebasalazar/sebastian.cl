package cl.sebastian.webutils.utils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina <sebasalazar@gmail.com>
 */
public abstract class RutUtils implements Serializable {

    public final static String DEFAULT_RUT_PATTERN = "##.###.###-X";
    private static final Pattern outRutRE = Pattern.compile("([0\\#\\.\\,]+)([^X^x^\\,^\\.^9]*)([Xx]*)");
    private static final Pattern inRutRE = Pattern.compile("([0-9\\.]+)\\-([a-zA-Z0-9]+)");
    private static final Logger logger = LoggerFactory.getLogger(RutUtils.class);

    public static Long parseRut(String value) {
        Long result = null;
        try {
            if (StringUtils.isNotBlank(value)) {
                // Borramos los caracteres esperables
                String rut = StringUtils.remove(value, ".");
                rut = StringUtils.remove(rut, ",");
                rut = StringUtils.remove(rut, "-");
                // Separamos el numero y el digito verificador
                if (StringUtils.isNotBlank(rut)) {
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
        } catch (Exception e) {
            result = null;
            logger.error("Error al parsear Rut: {}", e.toString());
        }
        return result;
    }

    public static String formatRut(Number o) throws Exception {
        return formatRut(o, DEFAULT_RUT_PATTERN);
    }

    public static String formatRut(Number o, String pattern) throws Exception {
        String formatedCheck = "";
        Number rut = (Number) o;
        if (rut != null) {
            String value = rut + "-" + getDV(rut.longValue());
            Matcher outMatcher = outRutRE.matcher(pattern);
            if (!(outMatcher.matches())) {
                logger.debug("Pattern incorrecto : " + pattern);
            }

            Matcher inMatcher = inRutRE.matcher(value);
            if (!(inMatcher.matches())) {
                throw new Exception("Formato de entrada incorrecto:"
                        + rut);
            }
            String checkFmt = outMatcher.group(1);
            String valueCheck = inMatcher.group(1);
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
            String separator = outMatcher.group(2);
            if (!StringUtils.isEmpty(separator)) {
                formatedCheck = formatedCheck + separator;
            }
            String dv = outMatcher.group(3);
            if (!StringUtils.isEmpty(dv)) {
                formatedCheck = formatedCheck + inMatcher.group(2);
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
        try {
            String formato = "";
            formato = StringUtils.remove(rut, ".");
            formato = StringUtils.remove(formato, ",");
            formato = StringUtils.remove(formato, "-");
            formato = StringUtils.upperCase(formato);

            String numeroStr = formato.substring(0, formato.length() - 1);
            Character digito = formato.charAt(formato.length() - 1);

            Integer numero = Integer.parseInt(numeroStr);
            if (digito == getDV(numero)) {
                resultado = true;
            }

        } catch (Exception e) {
            resultado = false;
            logger.error("Error al parsear rut: {}", e.toString());
        }

        return resultado;
    }
}
