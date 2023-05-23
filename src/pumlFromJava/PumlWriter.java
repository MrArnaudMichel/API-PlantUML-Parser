package pumlFromJava;

import pumlFromJava.classes.Classe;
import pumlFromJava.classes.Enumerations;
import pumlFromJava.classes.Interface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PumlWriter {
    /*
     * @PumlDoclet test
     * */

    private static void writeEntete(BufferedWriter writer) throws IOException {
        writer.write("@startuml\n" +
                "'https://plantuml.com/class-diagram\n" +
                "skinparam classAttributeIconSize 0\n" +
                "skinparam classFontStyle Bold\n" +
                "skinparam style strictuml\n" +
                "hide empty members\n\n");
    }

    private static void writeFin(BufferedWriter writer) throws IOException {
        writer.write("\n@enduml");
        writer.close();
    }


    public static void fillPuml(Package pumldiagram, String fileName, SaveOption saveOption) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);

        drawDC(pumldiagram, writer, saveOption);
        writeFin(writer);
    }

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
