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
    public static String checkPass(String pass) {
        if (pass.length() < 8) {
            return "senha muito curta";
        } else {
            char c;
            int count = 0;

            for (int i = 0; i < pass.length(); i++) {
                c = pass.charAt(i);

                if (!Character.isLetterOrDigit(c)) {
                    return "insira uma senha valida sem simbolos";
                }

                if (Character.isDigit(c)) {
                    count++;
                }
            }

            if (count < 1) {
                return "a senha deve conter pelo menos um numero";
            }
        }

        return "";
    }
    public static String checkName(String name) {
        if (name.length() > 255) {
            return "nome muito longo";
        } else {
            char c;
            int count = 0;

            for (int i = 0; i < name.length(); i++) {
                c = name.charAt(i);

                if (!Character.isLetterOrDigit(c)) {
                    return "insira um nome valido sem simbolos";
                }

                if (Character.isDigit(c)) {
                    count++;
                }
            }

            if (count < 1) {
                return "o nome deve conter pelo menos um numero";
            }
        }

        return "";
    }
}
