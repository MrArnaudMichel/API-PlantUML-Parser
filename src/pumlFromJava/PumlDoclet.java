package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.classes.Classe;
import pumlFromJava.classes.Enumerations;
import pumlFromJava.classes.Interface;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;


public class PumlDoclet implements Doclet {
    private final CreateFile fileCreator = new CreateFile();
    private final SaveOption saveOption = new SaveOption();
    private final Package pumlDiagram = new Package();

    @Override
    public void init(Locale locale, Reporter reporter) {
    }

    @Override
    public String getName() {
        // For this doclet, the name of the doclet is just the
        // simple name of the class. The name may be used in
        // messages related to this doclet, such as in command-line
        // help when doclet-specific options are provided.
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        return Set.of(
                new Option("--out", true, "Specify a document name the title by default is \"file\"", "<string>") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        fileCreator.setOutFileName(arguments.get(0));
                        return true;
                    }
                },
                new Option("--d", true, "Specify where to place generated output files the directory by default is current directory", "<string>") {
                    @Override
                    public boolean process(String option,
                                           List<String> arguments) {
                        fileCreator.setOutFilePath(arguments.get(0));
                        return true;
                    }
                },
                new Option("--dca", false, "If the option is specified then the program produces a DCA", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setTypeDiagram("DCA");
                        return true;
                    }
                },
                new Option("--str", false, "Considers the String type as primitive", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setStrPrimitive(false);
                        return true;
                    }
                },
                new Option("--association", false, "Deactivates associations", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setAssociation(false);
                        return true;
                    }
                },
                new Option("--constructor", false, "Deactivates constructors", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setConstructor(false);
                        return true;
                    }
                },
                new Option("--method", false, "Deactivates methods", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setMethod(false);
                        return true;
                    }
                },
                new Option("--field", false, "Deactivates attributes", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setField(false);
                        return true;
                    }
                },
                new Option("--drawPrimitive", false, "Deactivates primitive types", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setDrawPrimitive(false);
                        return true;
                    }
                },
                new Option("--UnPrimitive", false, "Deactivates non-primitive types", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setDrawUnPrimitive(false);
                        return true;
                    }
                },
                new Option("--extends", false, "Deactivates extends", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setDrawExtends(false);
                        return true;
                    }
                },
                new Option("--implements", false, "Deactivates implements", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        saveOption.setDrawImplements(false);
                        return true;
                    }
                },
                new Option("--help", false, "Print a synopsis of nonstandard options and exit", "") {
                    @Override
                    public boolean process(String option, List<String> arguments) {
                        System.out.println(
                                "Usage: javadoc [options] [packagenames] [sourcefiles] [@files]\n" +
                                        "Standard options:\n" +
                                        "  --d <directory>             Specify where to place generated output files the directory by default is current directory\n" +
                                        "  --out <title>               Specify a document name the title by default is \"file\".\n" +
                                        "  --dca                       If the option is specified then the program produces a DCA.\n" +
                                        "  --str                       Considers the String type as primitive.\n" +
                                        "  --association               Deactivates associations.\n" +
                                        "  --constructor               Deactivates constructors.\n" +
                                        "  --method                    Deactivates methods.\n" +
                                        "  --field                     Deactivates attributes.\n" +
                                        "  --drawPrimitive             Deactivates primitive types.\n" +
                                        "  --UnPrimitive               Deactivates non-primitive types.\n" +
                                        "  --extends                   Deactivates extends.\n" +
                                        "  --implements                Deactivates implements.\n" +
                                        "  --help                      Print a synopsis of nonstandard options and exit.\n");
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
        String out = null;
        try {
            out = fileCreator.creationFichier();
        } catch (IOException ignored) {
            throw new RuntimeException(ignored);
        }
        boolean first = true;
        for (Element element : environment.getIncludedElements()) {
            if (first) {
                pumlDiagram.setName(element.getEnclosedElements().toString().split("\\.")[0].split(",")[0]);
                first = false;
            }
            dumpElement(element);
        }

        try {
            PumlWriter.fillPuml(pumlDiagram, out, saveOption);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void dumpElement(Element element) {
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (Objects.equals(enclosedElement.getKind().toString(), "ENUM")) {
                pumlDiagram.addEnumeration(new Enumerations(enclosedElement));
            } else if (enclosedElement.getKind().isClass()) {
                pumlDiagram.addClasse(new Classe(enclosedElement));
            } else if (Objects.equals(enclosedElement.getKind().toString(), "INTERFACE")) {
                pumlDiagram.addInterface(new Interface(enclosedElement));
            } else if (Objects.equals(enclosedElement.getKind().toString(), "PACKAGE") && !Objects.equals(enclosedElement.getSimpleName().toString(), pumlDiagram.getName())) {
                pumlDiagram.addPackage(new Package(enclosedElement));
            }
        }
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
}

