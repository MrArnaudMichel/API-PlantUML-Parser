package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.Doclet.Option;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class PumlDoclet implements Doclet {
    private static final String DEFAULT_NAME = "fichier.puml";
    private static final String DEFAULT_PATH = "./";

    private String outFileName;
    private String outFilePath;

    private PumlDiagram pumlDiagram = new PumlDiagram();
    @Override
    public void init(Locale locale, Reporter reporter) {  }

    @Override
    public String getName() {
        // For this doclet, the name of the doclet is just the
        // simple name of the class. The name may be used in
        // messages related to this doclet, such as in command-line
        // help when doclet-specific options are provided.
        return getClass().getSimpleName();
    }

    abstract static class Option implements Doclet.Option {
        private final String name;
        private final boolean hasArg;
        private final String description;
        private final String parameters;

        Option(String name, boolean hasArg,
               String description, String parameters) {
            this.name = name;
            this.hasArg = hasArg;
            this.description = description;
            this.parameters = parameters;
        }

        @Override
        public int getArgumentCount() {
            return hasArg ? 1 : 0;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public Kind getKind() {
            return Kind.STANDARD;
        }

        @Override
        public List<String> getNames() {
            return List.of(name);
        }

        @Override
        public String getParameters() {
            return hasArg ? parameters : "";
        }
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        return Set.of(
            new Option("--out", true, "an option", "<string>"){
                @Override
                public boolean process(String option, List<String> arguments) {
                    outFileName = arguments.get(0) + DEFAULT_NAME;
                    return true;
                }
            },
            new Option("--d", true, "an option", "<string>") {
                @Override
                public boolean process(String option,
                                       List<String> arguments) {
                    outFilePath = arguments.get(0);
                    return true;
                }
            }
        );
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        // This doclet supports all source versions.
        // More sophisticated doclets may use a more
        // specific version, to ensure that they do not
        // encounter more recent language features that
        // they may not be able to handle.
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        if (outFileName == null)
        {
            outFileName = DEFAULT_NAME;
        }
        else if (!outFileName.endsWith(".puml"))
        {
            outFileName += ".puml";
        }
        if (outFilePath == null)
        {
            outFilePath = DEFAULT_PATH;
        }
        else if (!outFilePath.endsWith("/"))
        {
            outFilePath += "/";
        }
        String out = outFilePath + outFileName;

        createFile(out);
        try {
            fillPuml(environment, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        for (Element element : environment.getSpecifiedElements())
        {
            dumpElement(element);
        }*/
        return true;
    }

    private void dumpElement(Element element)
    {
        for (Element enclosedElement : element.getEnclosedElements()) {
            addelement(enclosedElement);
        }
    }

    private void addelement(Element element){
        ArrayList<Attributs> attributs = new ArrayList<>();
        ArrayList<Methode> methodes = new ArrayList<>();
        if (Objects.equals(element.getKind().toString(), "CLASS")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("FIELD")) {
                    attributs.add(setAttribut(enclosedElement));
                }
                else if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
            pumlDiagram.addClasse(setClasse(element, attributs, methodes));
        } else if (Objects.equals(element.getKind().toString(), "INTERFACE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
            pumlDiagram.addInterface(setInterface(element, attributs, methodes));
        } else if (Objects.equals(element.getKind().toString(), "ENUM")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("ENUM_CONSTANT")) {
                    attributs.add(setAttribut(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
            pumlDiagram.addEnumeration(setEnumeration(element));
        } else if (Objects.equals(element.getKind().toString(), "ANNOTATION_TYPE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
        }
        /*
            pumlDiagram.addClasse(setClasse(element, attributs, methodes));
            for (Classe classe : pumlDiagram.getClasses()) {
                System.out.println(classe.getName());
                for (Attributs attribut : classe.getAttributes()) {
                    System.out.println(attribut.getName());
                }
                for (Methode methode : classe.getMethods()) {
                    System.out.println(methode.getName());
                }
            }*/
    }

    public void fillPuml(DocletEnvironment environment, String fileName) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write("@startuml\n" +
                "'https://plantuml.com/class-diagram\n" +
                "skinparam classAttributeIconSize 0\n" +
                "skinparam classFontStyle Bold\n" +
                "skinparam style strictuml\n" +
                "hide empty members\n\n");


        for(Element element : environment.getSpecifiedElements()) {
            for (Element member : element.getEnclosedElements()) {
                String text = "";

                if (member.getKind().toString().equalsIgnoreCase("class"))
                {
                    text = "class " + member.getSimpleName().toString() + "{}\n";
                }
                if (member.getKind().toString().equalsIgnoreCase("enum"))
                {
                    text = "enum " + member.getSimpleName().toString() + "<<enum>> {}\n";
                }
                if (member.getKind().toString().equalsIgnoreCase("interface"))
                {
                    text = "interface " + member.getSimpleName().toString() + "<<interface>> {}\n";
                }

                //System.out.println(member.toString() + " - " + member.getKind().toString());

                writer.write(text);

            }
        }

        writer.write("@enduml");
        writer.close();
    }

    public void createFile(String fileName)
    {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Attributs setAttribut(Element element){
        Attributs attributs = new Attributs();
        attributs.setName(element.getSimpleName().toString());
        attributs.setType(element.asType().toString());
        attributs.setVisibility(element.getModifiers().toString());
        return attributs;
    }

    private Methode setMethode(Element element){
        Methode methode = new Methode();
        methode.setName(element.getSimpleName().toString());
        methode.setReturnType(element.asType().toString());
        //methode.setParameters(element.getParameters().toString());
        methode.setVisibility(element.getModifiers().toString());
        return methode;
    }

    private Classe setClasse(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes){
        Classe classe = new Classe();
        classe.setName(element.getSimpleName().toString());
        classe.setAttributes(attributs);
        classe.setMethods(methodes);
        return classe;
    }

    private Interface setInterface(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes){
        Interface interface_ = new Interface();
        interface_.setName(element.getSimpleName().toString());
        interface_.setAttributes(attributs);
        interface_.setMethods(methodes);
        return interface_;
    }

    private Enumerations setEnumeration(Element element){
        Enumerations enumeration = new Enumerations();
        enumeration.setName(element.getSimpleName().toString());
        //enumeration.setAttributes(attributs);
        return enumeration;
    }
}

