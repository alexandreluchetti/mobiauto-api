package br.com.mobiauto.mobiauto_server.core.shared;

import br.com.mobiauto.mobiauto_server.configuration.exception.InvalidDataException;

public abstract class CNPJ {

    public static boolean validation(String cnpj) {
        if (isValidCNPJ(cnpj)) return true;

        throw new InvalidDataException("CNPJ invalido!");
    }

    private static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            int sum = 0;
            int[] weight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weight1[i];
            }
            int firstDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);
            sum = 0;
            int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int i = 0; i < 13; i++) {
                sum += Character.getNumericValue(cnpj.charAt(i)) * weight2[i];
            }
            int secondDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);
            return cnpj.charAt(12) == Character.forDigit(firstDigit, 10) &&
                    cnpj.charAt(13) == Character.forDigit(secondDigit, 10);
        } catch (Exception e) {
            return false;
        }
    }
}
