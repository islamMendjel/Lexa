package syntax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AnalyseSen {
    static testList headlist;
    static testList tempptr;
    static FileWriter regleFile; // File to store the rules and errors

    public static void insertList(String word, String type) {
        testList list = new testList();
        list.word = word;
        list.type = type;
        list.next = null;

        if (headlist == null) {
            headlist = list;
        } else {
            testList a = headlist;
            while (a.next != null) a = a.next;
            a.next = list;
        }
    }

    static void read() {
        try {
            // File paths for Linux and Windows
            FileReader fr = new FileReader("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/lexical/jetons.txt"); // For Linux
            //FileReader fr = new FileReader("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\lexical\\jetons.txt"); // For Windows
            Scanner sc = new Scanner(fr);
            String c = "";
            String t = "";

            while (sc.hasNext()) {
                c = sc.next();
                if (sc.hasNext()) { // ✅ **Fix: Ensure a second token exists**
                    t = sc.next(); // line 37 (Fixed)
                    insertList(c, t);
                } else {
                    System.err.println("Warning: Missing second token for " + c); // ✅ **Fix: Handle missing pairs**
                }
            }
            sc.close();
            tempptr = headlist;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void printList() {
        testList ptr = tempptr;

        while (ptr != null) {
            System.out.print(ptr.word + " ");
            System.out.println(ptr.type);
            ptr = ptr.next;
        }
    }

    static int erreur(String message) {
        String errorMessage = message + " (Token: " + tempptr.word + ", Type: " + tempptr.type + ")";
        System.out.println(errorMessage);
        try {
            regleFile.write("ERROR: " + errorMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1); // Halt the program on the first error
        return 0;
    }

    // Grammar Rule: <ProgrammeProgSI2025> → program <NomProg> ; <Corps> .
    static int ProgrammeProgSI2025() {
        String rule = "ProgrammeProgSI2025 → program <NomProg> ; <Corps> .";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("program") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next; // Move past "program"
            if (NomProg() == 1) { // Parse program name
                if (tempptr.word.equals(";") && tempptr.type.equals("Symbols")) {
                    tempptr = tempptr.next; // Move past ";"
                    if (Corps() == 1) { // Parse program body
                        if (tempptr.word.equals(".") && tempptr.type.equals("Symbols")) {
                            System.out.println("Programme reconnu");
                            try {
                                regleFile.write("Programme reconnu\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return 1; // Program successfully parsed
                        } else {
                            return erreur("Erreur: Point final manquant");
                        }
                    }
                } else {
                    return erreur("Erreur: Point-virgule manquant après le nom du programme");
                }
            }
        }
        return erreur("Erreur: Mot-clé 'program' attendu");
    }

    // Grammar Rule: <NomProg> → <Identificateur>
    static int NomProg() {
        String rule = "NomProg → <Identificateur>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.type.equals("Identificateur")) {
            tempptr = tempptr.next; // Move past the program name
            return 1;
        }
        return erreur("Erreur: Identificateur attendu pour le nom du programme");
    }

    // Grammar Rule: <Corps> → <PartieDéfinitionConstante><PartieDéfinitionVariable> <InstrComp> / <InstrComp>
    static int Corps() {
        String rule = "Corps → <PartieDéfinitionConstante><PartieDéfinitionVariable> <InstrComp> / <InstrComp>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (PartieDefinitionConstante() == 1) {
            if (PartieDefinitionVariable() == 1) {
                return InstrComp();
            }
        } else {
            if (PartieDefinitionVariable() == 1) {
                return InstrComp();
            } else {
                return InstrComp();
            }
        }
        return erreur("Erreur: Corps du programme mal formé");
    }

    // Grammar Rule: <PartieDéfinitionConstante> → const <DéfinitionConstante> ; / <vide>
    static int PartieDefinitionConstante() {
        String rule = "PartieDefinitionConstante → const <DéfinitionConstante> ; / <vide>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("const") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next; // Move past "const"
            if (DefinitionConstante() == 1) {
                if (tempptr.word.equals("var") || tempptr.word.equals("begin")) {
                    return 1;
                }
                if (tempptr.word.equals(";") && tempptr.type.equals("Symbols")) {
                    tempptr = tempptr.next; // Move past ";"
                    return 1;
                } else {
                    return erreur("Erreur: Point-virgule manquant après la définition de constante");
                }
            }
        }
        return 1; // <vide> case (no constant definitions)
    }

    // Grammar Rule: <DéfinitionConstante> → <Identificateur>=<ValeurNumérique> / <Identificateur>=<ValeurNumérique> ; <DéfinitionConstante> / <Identificateur>=<ConstanteChaine> / <Identificateur>=<ConstanteChaine> ; <DéfinitionConstante>
    static int DefinitionConstante() {
        String rule = "DefinitionConstante → <Identificateur>=<ValeurNumérique> / <Identificateur>=<ValeurNumérique> ; <DéfinitionConstante> / <Identificateur>=<ConstanteChaine> / <Identificateur>=<ConstanteChaine> ; <DéfinitionConstante>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!tempptr.type.equals("Identificateur")) {
            return 1;
        }

        if (tempptr.type.equals("Identificateur")) {
            tempptr = tempptr.next;
            if (isToken("=", "OperateurLogique")) {
                tempptr = tempptr.next;
                if (ValeurNumerique() == 1 || ConstanteChaine() == 1) {
                    if (tempptr.word.equals("var") || tempptr.word.equals("begin")) {
                        return 1;
                    }
                    if (tempptr.word.equals(";") && tempptr.type.equals("Symbols")) {
                        tempptr = tempptr.next;
                        return DefinitionConstante();
                    } else {
                        return erreur("Erreur: Point-virgule manquant après la définition de constante");
                    }
                } else {
                    return erreur("Erreur: Valeur numérique ou chaîne attendue");
                }
            } else {
                return erreur("Erreur: Signe égal '=' attendu");
            }
        }
        return erreur("Erreur: Identificateur attendu pour la définition de constante");
    }

    // Grammar Rule: <ValeurNumérique> → <ConstanteEntière> / <ConstanteRéelle>
    static int ValeurNumerique() {
        String rule = "ValeurNumerique → <ConstanteEntière> / <ConstanteRéelle>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.type.equals("ConstanteEntière") || tempptr.type.equals("ConstanteRéelle")) {
            tempptr = tempptr.next;
            return 1;
        }
        return erreur("Erreur: Valeur numérique attendue");
    }

    // Grammar Rule: <ConstanteChaine> → ’<SuitedeCaractère>’
    static int ConstanteChaine() {
        String rule = "ConstanteChaine → ’<SuitedeCaractère>’";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.type.equals("ConstanteChaine")) {
            tempptr = tempptr.next;
            return 1;
        }
        return erreur("Erreur: Constante chaîne attendue");
    }

    // Grammar Rule: <PartieDéfinitionVariable> → var <DéfinitionVariables> ; / <vide>
    static int PartieDefinitionVariable() {
        String rule = "PartieDefinitionVariable → var <DéfinitionVariables> ; / <vide>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("var") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next;
            if (DefinitionVariables() == 1) {
                if (tempptr.word.equals("begin")) {
                    return 1;
                }
                if (tempptr.word.equals(";") && tempptr.type.equals("Symbols")) {
                    tempptr = tempptr.next;
                    return 1;
                } else {
                    return erreur("Erreur: Point-virgule manquant après la définition de variables");
                }
            }
        }
        return 1; // <vide> case (no variable definitions)
    }

    // Grammar Rule: <DéfinitionVariables> → <DéfinitionVariable> ; <DéfinitionVariables> / <DéfinitionVariable>
    static int DefinitionVariables() {
        String rule = "DefinitionVariables → <DéfinitionVariable> ; <DéfinitionVariables> / <DéfinitionVariable>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("begin")) {
            return 1;
        }

        if (DefinitionVariable() == 1) {
            if (tempptr.word.equals("begin")) {
                return 1;
            }
            if (tempptr.word.equals(";")) {
                tempptr = tempptr.next;
                return DefinitionVariables();
            } else {
                return erreur("Erreur: Point-virgule manquant après la définition de variables");
            }
        }
        return erreur("Erreur: Définition de variables incorrecte");
    }

    // Grammar Rule: <DéfinitionVariable> → <GroupeVariable> : <IdentificateurType>
    static int DefinitionVariable() {
        String rule = "DefinitionVariable → <GroupeVariable> : <IdentificateurType>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (GroupeVariable() == 1) {
            if (tempptr.word.equals(":")) {
                tempptr = tempptr.next;
                return IdentificateurType();
            } else {
                return erreur("Erreur: Deux-points ':' attendu");
            }
        }
        return erreur("Erreur: Groupe de variables incorrect");
    }

    // Grammar Rule: <GroupeVariable> → <Identificateur>, <GroupeVariable> / <Identificateur>
    static int GroupeVariable() {
        if (Identificateur() == 1) {
            if (tempptr.word.equals(",")) {
                tempptr = tempptr.next;
                return GroupeVariable();
            }
            return 1;
        }
        return erreur("Erreur: Groupe de variables incorrect");
    }

    // Grammar Rule: <Identificateur> → <Lettre><SuitedeSymboles>
    static int Identificateur() {
        if (tempptr.type.equals("Identificateur")) {
            tempptr = tempptr.next;
            return 1;
        }
        return erreur("Erreur: Identificateur attendu");
    }

    // Grammar Rule: <IdentificateurType> → integer / reel / char / string / <enregistrement>
    static int IdentificateurType() {
        if (tempptr.word.equals("integer") || tempptr.word.equals("reel") || tempptr.word.equals("char") || tempptr.word.equals("string")) {
            tempptr = tempptr.next;
            return 1;
        } else if (Enregistrement() == 1) {
            return 1;
        }
        return erreur("Erreur: Type d'identificateur attendu");
    }

    // Grammar Rule: <enregistrement> → Record <DéfinitionVariables> end
    static int Enregistrement() {
        if (tempptr.word.equals("Record")) {
            tempptr = tempptr.next;
            if (DefinitionVariables() == 1) {
                if (tempptr.word.equals("end")) {
                    tempptr = tempptr.next;
                    return 1;
                }
            }
        }
        return erreur("Erreur: Enregistrement mal formé");
    }

    // Grammar Rule: <InstrComp> → begin <Instructions> end
    static int InstrComp() {
        String rule = "InstrComp → begin <Instructions> end";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isToken("begin", "Keywords")) {
            tempptr = tempptr.next;
            if (Instructions() == 1) {
                if (isToken("end", "Keywords")) {
                    tempptr = tempptr.next;
                    return 1;
                }
            }
        }
        return erreur("Erreur: Instruction composée mal formée");
    }

    // Grammar Rule: <Instructions> → <Instruction> ; <Instructions> / <Instruction> ;
    static int Instructions() {
        String rule = "Instructions → <Instruction> ; <Instructions> / <Instruction> ;";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Instruction() == 1) {
            if (isToken(";", "Symbols")) {
                tempptr = tempptr.next;
                return Instructions();
            }
            return 1;
        }
        return erreur("Erreur: Instructions mal formées");
    }

    static int Instruction() {
        if (tempptr.type.equals("Identificateur")) {
            return InstructionAffectation();
        }
        if (tempptr.word.equals("if") && tempptr.type.equals("Keywords")) {
            return InstructionIf();
        }
        if (tempptr.word.equals("Répéter") && tempptr.type.equals("Keywords")) {
            return InstructionRepeter();
        }
        return 1;
    }

    // Grammar Rule: <InstructionAffectation> → <Identificateur> := <Expression>
    static int InstructionAffectation() {
        String rule = "InstructionAffectation → <Identificateur> := <Expression>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Identificateur() == 1) {
            if (isToken(":=", "AssignmentOperator")) {
                tempptr = tempptr.next;
                return Expression();
            } else {
                return erreur("Erreur: Opérateur d'affectation ':=' attendu");
            }
        }
        return erreur("Erreur: Identificateur attendu pour l'affectation");
    }

    // Grammar Rule: <Expression> → <Expression><OperateurArithmétique><Expression> / (<Expression>) / <Identificateur> / <ValeurNumérique>
    static int Expression() {
        String rule = "Expression → <Expression><OperateurArithmétique><Expression> / (<Expression>) / <Identificateur> / <ValeurNumérique>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isToken("(", "Symbols")) {
            tempptr = tempptr.next;
            if (Expression() == 1) {
                if (isToken(")", "Symbols")) {
                    tempptr = tempptr.next;
                    return 1;
                }
            }
            return erreur("Erreur: Parenthèse fermante manquante");
        } else if (isIdentificateurOrValeurNumerique()) {
            tempptr = tempptr.next;
            if (isOperateurArithmétique()) {
                tempptr = tempptr.next;
                return Expression();
            }
            return 1;
        }
        return erreur("Erreur: Expression mal formée");
    }

    static boolean isIdentificateurOrValeurNumerique() {
        return tempptr.type.equals("Identificateur") || tempptr.type.equals("ConstanteEntière") || tempptr.type.equals("ConstanteRéelle");
    }

    static boolean isOperateurArithmétique() {
        return tempptr.type.equals("OperateurArithmétique");
    }

    static boolean isToken(String word, String type) {
        return tempptr.word.equals(word) && tempptr.type.equals(type);
    }

    // Grammar Rule: <InstructionIf> → if <Condition> then <Instructions> <InstructionConditionnelle>
    static int InstructionIf() {
        String rule = "InstructionIf → if <Condition> then <Instructions> <InstructionConditionnelle>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("if") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next;
            if (Condition() == 1) {
                if (tempptr.word.equals("then") && tempptr.type.equals("Keywords")) {
                    tempptr = tempptr.next;
                    if (Instructions() == 1) {
                        return InstructionConditionnelle();
                    }
                } else {
                    return erreur("Erreur: Mot-clé 'then' attendu");
                }
            }
        }
        return erreur("Erreur: Instruction if mal formée");
    }

    // Grammar Rule: <InstructionConditionnelle> → endif / else <Instructions> endif
    static int InstructionConditionnelle() {
        String rule = "InstructionConditionnelle → endif / else <Instructions> endif";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("endif") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next;
            return 1;
        }

        if (tempptr.word.equals("else") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next;
            if (Instructions() == 1) {
                if (tempptr.word.equals("endif") && tempptr.type.equals("Keywords")) {
                    tempptr = tempptr.next;
                    return 1;
                } else {
                    return erreur("Erreur: 'endif' attendu après 'else'");
                }
            }
        }
        return erreur("Erreur: Instruction conditionnelle mal formée");
    }

    // Grammar Rule: <InstructionRépéter> → Répéter <Instructions> Jusqu <Condition>
    static int InstructionRepeter() {
        String rule = "InstructionRepeter → Répéter <Instructions> Jusqu <Condition>";
        //System.out.println(rule);
        try {
            regleFile.write(rule + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tempptr.word.equals("Répéter") && tempptr.type.equals("Keywords")) {
            tempptr = tempptr.next;
            if (Instructions() == 1) {
                if (tempptr.word.equals("Jusqu") && tempptr.type.equals("Keywords")) {
                    tempptr = tempptr.next;
                    if (Condition() == 1) {
                        return 1;
                    } else {
                        return erreur("Erreur: Condition mal formée dans Répéter");
                    }
                } else {
                    return erreur("Erreur: Mot-clé 'Jusqu' attendu");
                }
            } else {
                return erreur("Erreur: Instructions mal formées dans Répéter");
            }
        }
        return erreur("Erreur: Mot-clé 'Répéter' attendu");
    }

    // Grammar Rule: <Condition> → <Expression><OperateurLogique><Expression> / (<Condition>)
    static int Condition() {
        if (Expression() == 1) {
            if (OperateurLogique() == 1) {
                return Expression();
            }
        } else if (tempptr.word.equals("(")) {
            tempptr = tempptr.next;
            if (Condition() == 1) {
                if (tempptr.word.equals(")")) {
                    tempptr = tempptr.next;
                    return 1;
                }
            }
        }
        return erreur("Erreur: Condition mal formée");
    }

    // Grammar Rule: <OperateurLogique> → > / >= / < / <= / = / <>
    static int OperateurLogique() {
        if (tempptr.word.equals(">") || tempptr.word.equals(">=") || tempptr.word.equals("<") || tempptr.word.equals("<=") || tempptr.word.equals("=") || tempptr.word.equals("<>")) {
            tempptr = tempptr.next;
            return 1;
        }
        return erreur("Erreur: Opérateur logique attendu");
    }

    public static void main(String[] args) {
        try {
            regleFile = new FileWriter("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/syntax/regle.txt"); // for linux
            //regleFile = new FileWriter("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\syntax\\regle.txt"); // for windows
            read();
            ProgrammeProgSI2025(); // L'axiome
            regleFile.close(); // Close the file after writing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}