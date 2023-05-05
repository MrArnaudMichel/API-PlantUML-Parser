package pumlFromJava;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.*;

public class PumlDoclet implements Doclet {
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

    @Override
    public Set<? extends Doclet.Option> getSupportedOptions() {
        // This doclet does not support any options.
        return Collections.emptySet();
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
        // This method is called to perform the work of the doclet.
        // In this case, it just prints out the names of the
        // elements specified on the command line.
        System.out.println(this.getName());
        System.out.println(environment.getSpecifiedElements());
        System.out.println(environment.getIncludedElements());
        for (Element element : environment.getSpecifiedElements())
        {
            dumpElement(element);
        }
        return true;
    }

    private void dumpElement(Element element)
    {
        for (Element enclosedElement : element.getEnclosedElements()) {
            drawElement(enclosedElement);
        }
    }

    private void drawElement(Element element){
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
        } else if (Objects.equals(element.getKind().toString(), "INTERFACE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
        } else if (Objects.equals(element.getKind().toString(), "ENUM")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind().toString().equals("ENUM_CONSTANT")) {
                    attributs.add(setAttribut(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("METHOD")) {
                    methodes.add(setMethode(enclosedElement));
                } else if (enclosedElement.getKind().toString().equals("CONSTRUCTOR")) {

                }
            }
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

