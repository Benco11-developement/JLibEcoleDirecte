package fr.benco11.jlibecoledirecte.lib.utils;

public class NumberUtils {
    public static double parseDouble(String a, RuntimeException toThrow) {
        if (a.isBlank()) return 0;
        try {
            return Double.parseDouble(a.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw toThrow;
        }
    }
}
