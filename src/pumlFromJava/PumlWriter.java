package pumlFromJava;

import pumlFromJava.classes.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PumlWriter {

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


    public static void fillPuml(Package pumldiagram, String fileName, String choixDC) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);

        drawDC(pumldiagram, writer, choixDC);
        writeFin(writer);
    }

    private static void drawDC(Package pumldiagram, BufferedWriter writer, String choixDC) throws IOException {
        for (Classe classe : pumldiagram.getClasses()) {
            writer.write(classe.strDrawDiagram(choixDC));
            writer.write("\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write(inter.strDraw(choixDC));
            writer.write("\n");
        }
        for (Enumerations enumeration : pumldiagram.getEnumerations()) {
            writer.write(enumeration.strDraw());
            writer.write("\n");
        }
        drawLink(pumldiagram, writer, choixDC);
    }

    private static void drawLink(Package pumldiagram, BufferedWriter writer, String choixDC) throws IOException {
        for (Classe classe : pumldiagram.getClasses()) {
            writer.write(classe.strRelation(choixDC));
            writer.write("\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write(inter.strRelation());
            writer.write("\n");
        }
    }
}
