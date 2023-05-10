package pumlFromJava.classes;

import pumlFromJava.PumlDiagram;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Objects;

public class SaveInfo {

    public static void addelement(Element element, PumlDiagram pumlDiagram){
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
        } else if (Objects.equals(element.getKind().toString(), "PACKAGE")) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                addelement(enclosedElement, pumlDiagram);
            }
        }
        //System.out.println(element.getKind() + " " + element.getSimpleName());
    }

    private static Attributs setAttribut(Element element){
        Attributs attributs = new Attributs();
        attributs.setName(element.getSimpleName().toString());
        attributs.setType(element.asType());
        attributs.setVisibility(element.getModifiers().toString());
        return attributs;
    }

    private static Methode setMethode(Element element){
        Methode methode = new Methode();
        methode.setName(element.getSimpleName().toString());
        methode.setReturnType(element.asType().toString());
        //methode.setParameters(element.getParameters().toString());
        methode.setVisibility(element.getModifiers().toString());
        return methode;
    }

    private static Classe setClasse(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes, String packageName){
        Classe classe = new Classe();
        classe.setNamePackage(packageName);
        classe.setName(element.getSimpleName().toString());
        classe.setAttributes(attributs);
        classe.setMethods(methodes);
        return classe;
    }

    private static Interface setInterface(Element element, ArrayList<Attributs> attributs, ArrayList<Methode> methodes, String packageName){
        Interface interface_ = new Interface();
        interface_.setNamePackage(packageName);
        interface_.setName(element.getSimpleName().toString());
        interface_.setAttributes(attributs);
        interface_.setMethods(methodes);
        return interface_;
    }

    private static Enumerations setEnumeration(Element element, ArrayList<Attributs> attributs, String packageName){
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
