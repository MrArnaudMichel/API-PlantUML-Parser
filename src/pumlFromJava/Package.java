package pumlFromJava;

import pumlFromJava.classes.Classe;
import pumlFromJava.classes.Enumerations;
import pumlFromJava.classes.Interface;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Objects;

public class Package {
    private String name;
    private ArrayList<Classe> classes = new ArrayList<Classe>();
    private ArrayList<Interface> interfaces = new ArrayList<Interface>();
    private ArrayList<Enumerations> enumerations = new ArrayList<Enumerations>();
    private ArrayList<Package> packages = new ArrayList<Package>();

    public Package() {
    }

    public Package(Element element) {
        setName(element.getSimpleName().toString());
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (Objects.equals(enclosedElement.getKind().toString(), "ENUM")) {
                this.addEnumeration(new Enumerations(enclosedElement));
            } else if (enclosedElement.getKind().isClass()) {
                this.addClasse(new Classe(enclosedElement));
            } else if (Objects.equals(enclosedElement.getKind().toString(), "INTERFACE")) {
                this.addInterface(new Interface(enclosedElement));
            } else if (Objects.equals(enclosedElement.getKind().toString(), "PACKAGE")) {
                this.addPackage(new Package(enclosedElement));
            }
        }
    }

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classe> classes) {
        this.classes = classes;
    }

    public void addClasse(Classe classe) {
        this.classes.add(classe);

    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(ArrayList<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(Interface interface1) {
        this.interfaces.add(interface1);
    }

    public ArrayList<Enumerations> getEnumerations() {
        return enumerations;
    }

    public void setEnumerations(ArrayList<Enumerations> enumerations) {
        this.enumerations = enumerations;
    }

    public void addEnumeration(Enumerations enumeration) {
        this.enumerations.add(enumeration);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public void addPackage(Package package1) {
        this.packages.add(package1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
