package fr.avalonlab.warp10.utils;

import fr.avalonlab.warp10.exceptions.MissingMandatoryDataException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class WFormat {

    public static String formatOptionalLatLon(Double lat, Double lon) {
        String latLon = "";
        if (lat != null && lon != null) {
            latLon = lat + ":" + lon;
        }
        return latLon;
    }

    public static String formatOptionalLongValue(Long possibleNullValue) {
        if (possibleNullValue == null) {
            return "";
        }
        return possibleNullValue.toString();
    }

    public static String formatLabels(Map<String, String> labels) {
        if (labels != null && !labels.isEmpty()) {
            return labels.entrySet().stream().map(entry -> entry.getKey() + '=' + entry.getValue()).collect(Collectors.joining(","));
        }
        throw new MissingMandatoryDataException("labels");
    }

    public static String formatMandatoryFieldName(String name) {
        if (name == null) {
            throw new MissingMandatoryDataException("name");
        }
        return name;
    }

    public static String formatToInputData(String value) {
        String escaped = null;
        try {
            escaped = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // Not Possible
        }
        ;
        String res = "'" + escaped + "'";
        return res;
    }

    public static String formatToInputData(double value) {
        String res = Double.toString(value);
        if (!res.contains(".")) {
            res += ".0";
        }
        return res;
    }

    public static String formatToInputData(long value) {
        String res = Double.toString(value);
        if (!res.contains(".")) {
            res += ".0";
        }
        return res;
    }

    public static String formatToInputData(boolean value) {
        String res = value ? "T" : "F";
        return res;
    }
}
