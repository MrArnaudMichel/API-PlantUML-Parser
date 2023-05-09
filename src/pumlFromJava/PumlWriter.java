package pumlFromJava;

import pumlFromJava.classes.*;
import pumlFromJava.classes.Object;

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

    private static void writeType(Type type, BufferedWriter writer) throws IOException {
        if (type.isPublic(type.getVisibility())){
            writer.write("+");
        }
        else if (type.isPrivate(type.getVisibility())){
            writer.write("-");
        }
        else if (type.isProtected(type.getVisibility())){
            writer.write("#");
        }
        else if (type.isAbstract(type.getVisibility())){
            writer.write("{abstract} ");
        }
        if (type.isStatic(type.getVisibility())){
            writer.write("{static} ");
        }
    }

    private static void drawAttribut(pumlFromJava.classes.Object o, BufferedWriter writer) throws IOException {
        for (Attributs attribut : o.getAttributes()) {
            TypeKind typeKind = attribut.getType().getKind();
            if (typeKind.isPrimitive()) {
                writeType(attribut, writer);
                writer.write(attribut.getName() + " : " + attribut.getType() + "\n");
            }
        }
    }

    private static void drawAttribut(Enumerations o, BufferedWriter writer) throws IOException {
        for (String attribut : o.getAttributes()) {
            writer.write(attribut + "\n");
        }
    }

    private static void drawMethode(Object o, BufferedWriter writer) throws IOException {
        for (Methode methode : o.getMethods()) {
            writeType(methode, writer);
            writer.write(" " + methode.getName() + "(");
            for (String param : methode.getParameters()) {
                writer.write(param + ", ");
            }
            writer.write(") : " + methode.getReturnType() + "\n");
            writer.write("\n");
        }
    }

    public static void fillPuml(PumlDiagram pumldiagram, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);


        for (Classe classe : pumldiagram.getClasses()) {
            writer.write("class " + classe.getNamePackage() + '.' + classe.getName() + "{\n");
            drawAttribut(classe, writer);
            //drawMethode(classe, writer);
            writer.write("}\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write("interface " + inter.getNamePackage() + '.' + inter.getName() + "<<interface>>{\n");
            drawAttribut(inter, writer);
            //drawMethode(inter, writer);
            writer.write("}\n");
        }
        for (Enumerations enumeration : pumldiagram.getEnumerations()) {
            writer.write("enum " + enumeration.getNamePackage() + '.' + enumeration.getName() + "<<enum>>{\n");
            drawAttribut(enumeration, writer);
            writer.write("}\n");
        }


        writeFin(writer);
    }
}
