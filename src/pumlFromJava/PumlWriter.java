package pumlFromJava;

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

    public static void writeType(Type type, BufferedWriter writer) throws IOException {
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

    public static void fillPuml(PumlDiagram pumldiagram, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writeEntete(writer);


        for (Classe classe : pumldiagram.getClasses()) {
            writer.write("class " + classe.getName() + "{\n");
            for (Attributs attribut : classe.getAttributes()) {
                writeType(attribut, writer);
                writer.write(" " + attribut.getName() + " : " + attribut.getType() + "\n");
            }
            for (Methode methode : classe.getMethods()) {
                if (methode.isPublic(methode.getVisibility())){
                    writer.write("+");
                }
                else if (methode.isPrivate(methode.getVisibility())){
                    writer.write("-");
                }
                else if (methode.isProtected(methode.getVisibility())){
                    writer.write("#");
                }
                else if (methode.isAbstract(methode.getVisibility())){
                    writer.write("{abstract} ");
                }
                if (methode.isStatic(methode.getVisibility())){
                    writer.write("{static} ");
                }

                writer.write(" " + methode.getName() + "(");
                for (String param : methode.getParameters()) {
                    writer.write(param + ", ");
                }
                writer.write(") : " + methode.getReturnType() + "\n");
                writer.write("\n");

            }
            writer.write("}\n");
        }
        for (Interface inter : pumldiagram.getInterfaces()) {
            writer.write("interface " + inter.getName() + "<<interface>>{\n");
            for (Attributs attribut : inter.getAttributes()) {
                if (attribut.isPublic(attribut.getVisibility())){
                    writer.write("+");
                }
                else if (attribut.isPrivate(attribut.getVisibility())){
                    writer.write("-");
                }
                else if (attribut.isProtected(attribut.getVisibility())){
                    writer.write("#");
                }
                else if (attribut.isAbstract(attribut.getVisibility())){
                    writer.write("{abstract} ");
                }
                if (attribut.isStatic(attribut.getVisibility())){
                    writer.write("{static} ");
                }
                writer.write(" " + attribut.getName() + " : " + attribut.getType() + "\n");
            }
            for (Methode methode : inter.getMethods()) {
                if (methode.isPublic(methode.getVisibility())){
                    writer.write("+");
                }
                else if (methode.isPrivate(methode.getVisibility())){
                    writer.write("-");
                }
                else if (methode.isProtected(methode.getVisibility())){
                    writer.write("#");
                }
                else if (methode.isAbstract(methode.getVisibility())){
                    writer.write("{abstract} ");
                }
                if (methode.isStatic(methode.getVisibility())){
                    writer.write("{static} ");
                }

                writer.write(" " + methode.getName() + "(");
                for (String param : methode.getParameters()) {
                    writer.write(param + ", ");
                }
                writer.write(") : " + methode.getReturnType() + "\n");
                writer.write("\n");

            }
            writer.write("}\n");
        }
        for (Enumerations enumeration : pumldiagram.getEnumerations()) {
            writer.write("enum " + enumeration.getName() + "<<Enum>>{\n");
            for (String attribut : enumeration.getAttributes()) {
                writer.write(attribut + "\n");
            }
            writer.write("}\n");
        }


        writeFin(writer);
    }
}
