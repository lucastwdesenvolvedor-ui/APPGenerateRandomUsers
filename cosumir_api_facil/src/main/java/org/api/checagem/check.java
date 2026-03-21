package org.api.checagem;

public class check {
    public static boolean checkCountry(String pais){
        String psd[] = {"AU", "BR", "CA", "CH", "DE", "DK", "ES", "FI", "FR", "GB", "IE", "IN", "IR", "MX", "NL", "NO", "NZ", "RS", "TR", "UA", "US"};

        for(int i = 0; i < psd.length; i++){
            if(pais.equals(psd[i])){
                return true;
            }
        }

        if(pais.length() != 2){
            return false;
        }

        if (pais.matches("\\d+")) {
            return false;
        }

        return true;

    }

    public static boolean checkNumber(String numero) {
        if (numero == null || !numero.matches("\\d+")) {
            return false; // Não é número ou é nulo
        }
        try {
            int n = Integer.parseInt(numero);
            return n >= 1 && n <= 100; // Valida a faixa
        } catch (NumberFormatException e) {
            return false; // Se for um número grande demais
        }
    }
}
