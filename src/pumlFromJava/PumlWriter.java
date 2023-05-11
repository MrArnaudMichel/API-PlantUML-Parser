package pumlFromJava;

import pumlFromJava.classes.*;
import pumlFromJava.classes.Instance;

import javax.lang.model.type.TypeKind;
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


    public static void fillPuml(PumlDiagram pumldiagram, String fileName, String choixDC) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);

        drawDC(pumldiagram, writer, choixDC);
        writeFin(writer);
    }

    private static void drawDC(PumlDiagram pumldiagram, BufferedWriter writer, String choixDC) throws IOException {
        for (Classe classe : pumldiagram.getClasses()) {
            writer.write(classe.strDraw(choixDC));
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write(inter.strDraw(choixDC));
        }
        for (Enumerations enumeration : pumldiagram.getEnumerations()) {
            writer.write(enumeration.strDraw());
        }
    }
}
