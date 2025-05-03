import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    // Define token types and their corresponding regex patterns
    private static final Map<String, Pattern> types = new HashMap<>();

    static {
        types.put("Identificateur", Pattern.compile("^[a-zA-Z\\p{L}][a-zA-Z0-9_\\p{L}]*$")); // Identifiers start with a letter
        types.put("ConstanteEntière", Pattern.compile("^\\d+$")); // Integer constants
        types.put("ConstanteRéelle", Pattern.compile("^\\d+(\\.\\d+)?(E[+-]?\\d+)?$", Pattern.CASE_INSENSITIVE)); // Real constants
        types.put("ConstanteChaine", Pattern.compile("^['\"].*?['\"]$")); // String constants
        types.put("OperateurArithmétique", Pattern.compile("^[+\\-*/]$")); // Arithmetic operators
        types.put("OperateurLogique", Pattern.compile("^(>=|<=|<>|>|<|=)$")); // Logical operators
        types.put("Keywords", Pattern.compile("^(program|const|var|begin|end|if|then|else|endif|Répéter|Jusqu)$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)); // Keywords
        types.put("IdentificateurType", Pattern.compile("^(integer|reel|char|string|record)$")); // Identifier types
        types.put("Symbols", Pattern.compile("^[;,:().]$")); // Symbols
        types.put("AssignmentOperator", Pattern.compile("^:=$")); // Assignment operator
    }

    // Determine the type of a token
    private static String getType(String word) {
        // Check if the word is a keyword
        if (types.get("Keywords").matcher(word).matches()) {
            return "Keywords";
        }

        // Check if the word is an identifier type
        if (types.get("IdentificateurType").matcher(word).matches()) {
            return "IdentificateurType";
        }

        // Check other token types
        for (Map.Entry<String, Pattern> entry : types.entrySet()) {
            if (entry.getValue().matcher(word).matches()) {
                return entry.getKey();
            }
        }
        return "Unknown"; // Handle unknown tokens
    }

    // Tokenize a line of code
    private static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        String tokenRegex = "(\\d+(\\.\\d+e[+-]?\\d+|e[+-]?\\d+)?|['\"].*?['\"]|>=|<=|<>|:=|[+\\-*/=<>();,:.]|\\b[\\p{L}a-zA-Z][\\p{L}a-zA-Z0-9_]*\\b)";
        Pattern pattern = Pattern.compile(tokenRegex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    public static void main(String[] args) {
        try {
            // Read the input file with UTF-8 encoding
            String data = new String(Files.readAllBytes(Paths.get("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/lexical/test.txt")), StandardCharsets.UTF_8); //for linux
            //String data = new String(Files.readAllBytes(Paths.get("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\lexical\\test.txt")), StandardCharsets.UTF_8); //for windows
            String[] lines = data.split("\\r?\\n"); // Split by lines

            // Process each line and tokenize
            StringBuilder results = new StringBuilder();
            for (String line : lines) {
                List<String> tokens = tokenize(line);
                for (String token : tokens) {
                    String type = getType(token);
                    if (type.equals("Unknown")) {
                        System.err.println("Warning: Unknown token '" + token + "' found in input.");
                    }
                    results.append(token).append(" ").append(type).append("\n");
                }
            }

            // Write the results to the output file
            Files.write(Paths.get("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/lexical/jetons.txt"), results.toString().getBytes()); //for linux
            //Files.write(Paths.get("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\lexical\\jetons.txt"), results.toString().getBytes()); //for windows
            System.out.println("jetons.txt has been created.");

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
}