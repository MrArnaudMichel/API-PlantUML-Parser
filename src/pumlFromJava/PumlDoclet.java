package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.classes.*;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.*;
import java.io.IOException;


public class PumlDoclet implements Doclet {
    private final CreateFile fileCreator = new CreateFile();

    private final PumlDiagram pumlDiagram = new PumlDiagram();
    private String choixDc = "DCC";
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
            new Option("--out", true, "Choix du nom de fichier", "<string>") {
                @Override
                public boolean process(String option, List<String> arguments) {
                    fileCreator.setOutFileName(arguments.get(0));
                    ;
                    return true;
                }
            },
            new Option("--d", true, "Choix du chemin", "<string>") {
                @Override
                public boolean process(String option,
                                       List<String> arguments) {
                    fileCreator.setOutFilePath(arguments.get(0));
                    return true;
                }
            },
            new Option("--DCA", false, "Si l'option est précisée alors le programme produit un DCA", "") {
                @Override
                public boolean process(String option, List<String> arguments) {
                    choixDc = "DCA";
                    return true;
                }
            },
            new Option("--help", false, "Afficher l'aide", ""){
                @Override
                public boolean process(String option, List<String> arguments) {
                    System.out.println(
                            "Usage: javadoc [options] [packagenames] [sourcefiles] [@files]\n" +
                            "Standard options:\n" +
                            "  --d <directory>             Specify where to place generated output files the directory by default is current directory\n" +
                            "  --out <title>               Specify a document name the title by default is \"name\".\n");
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
    public boolean run(DocletEnvironment environment)  {
        String out = null;
        try {
            out = fileCreator.creationFichier();
        } catch (IOException ignored) {
            throw new RuntimeException(ignored);
        }

        for (Element element : environment.getIncludedElements())
        {
            dumpElement(element);
        }

        try {
            PumlWriter.fillPuml(pumlDiagram, out, choixDc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void dumpElement(Element element)
    {
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (Objects.equals(enclosedElement.getKind().toString(), "ENUM")) {
                pumlDiagram.addEnumeration(new Enumerations(enclosedElement));
            } else if (enclosedElement.getKind().isClass()) {
                pumlDiagram.addClasse(new Classe(enclosedElement));
            } else if (Objects.equals(enclosedElement.getKind().toString(), "INTERFACE")) {
                pumlDiagram.addInterface(new Interface(enclosedElement));
            } // Si l'objet est un package, on relance la fonction récurssivement
            /*else if (Objects.equals(enclosedElement.getKind().toString(), "PACKAGE") && element.getSimpleName()) {
                dumpElement(enclosedElement);
            }*/
        }
    }
}

