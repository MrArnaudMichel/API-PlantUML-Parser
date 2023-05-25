package pumlFromJava;

import pumlFromJava.classes.Classe;
import pumlFromJava.classes.Enumerations;
import pumlFromJava.classes.Interface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe PumlWriter
 * <p>
 *     Classe qui permet d'écrire dans un fichier .puml
 *     <br>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>writeEntete</li>
 *         <li>writeFin</li>
 *         <li>fillPuml</li>
 *         <li>drawDC</li>
 *         <li>drawLink</li>
 *    </ul>
 */
public class PumlWriter {
    /*
     * @PumlDoclet test
     * */

    /**
     * Méthode qui permet d'écrire l'entête du fichier .puml
     * @param writer BufferedWriter
     * @throws IOException Exception
     */
    private static void writeEntete(BufferedWriter writer) throws IOException {
        writer.write("@startuml\n" +
                "'https://plantuml.com/class-diagram\n" +
                "skinparam classAttributeIconSize 0\n" +
                "skinparam classFontStyle Bold\n" +
                "skinparam style strictuml\n" +
                "hide empty members\n\n");
    }

    /**
     * Méthode qui permet d'écrire la fin du fichier .puml
     * @param writer BufferedWriter
     * @throws IOException Exception
     */
    private static void writeFin(BufferedWriter writer) throws IOException {
        writer.write("\n@enduml");
        writer.close();
    }


    /**
     * Méthode qui permet de remplir le fichier .puml
     * @param pumldiagram Package
     * @param fileName String
     * @param saveOption SaveOption
     * @throws IOException Exception
     */
    public static void fillPuml(Package pumldiagram, String fileName, SaveOption saveOption) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);

        drawDC(pumldiagram, writer, saveOption);
        writeFin(writer);
    }

    /**
     * Méthode qui permet de dessiner les classes, les interfaces et les énumérations
     * @param pumldiagram Package
     * @param writer BufferedWriter
     * @param saveOption SaveOption
     * @throws IOException Exception
     */
    private static void drawDC(Package pumldiagram, BufferedWriter writer, SaveOption saveOption) throws IOException {
        writer.write("package " + pumldiagram.getName() + " {\n");
        for (Classe classe : pumldiagram.getClasses()) {
            writer.write(classe.strDrawDiagram(saveOption));
            writer.write("\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write(inter.strDraw(saveOption));
            writer.write("\n");
        }
        for (Enumerations enumeration : pumldiagram.getEnumerations()) {
            writer.write(enumeration.strDraw(saveOption));
            writer.write("\n");
        }
        for (Package package1 : pumldiagram.getPackages()) {
            drawDC(package1, writer, saveOption);
        }
        drawLink(pumldiagram, writer, saveOption);
        writer.write("}\n");
    }

    /**
     * Méthode qui permet de dessiner les liens entre les classes, les interfaces et les énumérations
     * @param pumldiagram Package
     * @param writer BufferedWriter
     * @param saveOption SaveOption
     * @throws IOException Exception
     */
    private static void drawLink(Package pumldiagram, BufferedWriter writer, SaveOption saveOption) throws IOException {
        for (Classe classe : pumldiagram.getClasses()) {
            writer.write(classe.strRelation(saveOption));
            writer.write("\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write(inter.strRelation(saveOption));
            writer.write("\n");
        }
    }
}
