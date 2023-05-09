package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.classes.*;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class PumlDoclet implements Doclet {
    private CreateFic fileCreator = new CreateFic();

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
                    fileCreator.setOutFileName(arguments.get(0) + CreateFic.DEFAULT_NAME);;
                    return true;
                }
            },
            new Option("--d", true, "an option", "<string>") {
                @Override
                public boolean process(String option,
                                       List<String> arguments) {
                    fileCreator.setOutFilePath(arguments.get(0));
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
        String out;
        try {
            out = fileCreator.creationFichier();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Element element : environment.getSpecifiedElements())
        {
            dumpElement(element);
        }

        try {
            PumlWriter.fillPuml(pumlDiagram, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            pumlDiagram.addClasse(setClasse(element, attributs, methodes, element.getEnclosingElement().getSimpleName().toString()));
        } else if (Objects.equals(element.getKind().toString(), "INTERFACE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
            pumlDiagram.addInterface(setInterface(element, attributs, methodes, element.getEnclosingElement().getSimpleName().toString()));
        } else if (Objects.equals(element.getKind().toString(), "ENUM")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("ENUM_CONSTANT")) {
                    attributs.add(setAttribut(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
            pumlDiagram.addEnumeration(setEnumeration(element, attributs, element.getEnclosingElement().getSimpleName().toString()));
        } else if (Objects.equals(element.getKind().toString(), "ANNOTATION_TYPE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
        }
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
        attributs.setType(element.asType());
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

    private Classe setClasse(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes, String packageName){
        Classe classe = new Classe();
        classe.setNamePackage(packageName);
        classe.setName(element.getSimpleName().toString());
        classe.setAttributes(attributs);
        classe.setMethods(methodes);
        return classe;
    }

    private Interface setInterface(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes, String packageName){
        Interface interface_ = new Interface();
        interface_.setNamePackage(packageName);
        interface_.setName(element.getSimpleName().toString());
        interface_.setAttributes(attributs);
        interface_.setMethods(methodes);
        return interface_;
    }

    private Enumerations setEnumeration(Element element, ArrayList<Attributs> attributs, String packageName){
        Enumerations enumeration = new Enumerations();
        enumeration.setNamePackage(packageName);
        enumeration.setName(element.getSimpleName().toString());
        ArrayList<String> Arr = new ArrayList<>();
        for (Attributs attribut : attributs) {
            Arr.add(attribut.getName());
        }
        enumeration.setAttributes(Arr);
        return enumeration;
    }
}

