package pumlFromJava;

import pumlFromJava.classes.Classe;
import pumlFromJava.classes.Enumerations;
import pumlFromJava.classes.Interface;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe Package
 * <p>
 * Classe qui permet de créer un package
 * </p>
 * <br>
 * Elle possède les méthodes :
 *   <ul>
 *       <li>getName</li>
 *       <li>setName</li>
 *       <li>getClasses</li>
 *       <li>setClasses</li>
 *       <li>getInterfaces</li>
 *       <li>setInterfaces</li>
 *       <li>getEnumerations</li>
 *       <li>setEnumerations</li>
 *       <li>getPackages</li>
 *       <li>setPackages</li>
 *  </ul>
 *  <br>
 *  Elle possède les attributs :
 *  <ul>
 *      <li>name</li>
 *      <li>classes</li>
 *      <li>interfaces</li>
 *      <li>enumerations</li>
 *      <li>packages</li>
 * </ul>
 */
public class Package {
    private String name;

    /**
     * contient
     */
    private ArrayList<Classe> classes = new ArrayList<Classe>();

    /**
     * contient
     */
    private ArrayList<Interface> interfaces = new ArrayList<Interface>();

    /**
     * contient
     */
    private ArrayList<Enumerations> enumerations = new ArrayList<Enumerations>();

    /**
     * contient
     */
    private ArrayList<Package> packages = new ArrayList<Package>();

    /**
     * Constructeur de la classe Package
     */
    public Package() {
    }

    /**
     * Constructeur de la classe Package
     *
     * @param element Element
     */
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

    /**
     * Méthode getName qui permet de récupérer les classes du package
     *
     * @return ArrayList<Classe> classes
     */
    public ArrayList<Classe> getClasses() {
        return classes;
    }

    /**
     * Méthode setName qui permet de modifier les classes du package
     *
     * @param classes ArrayList<Classe>
     */
    public void setClasses(ArrayList<Classe> classes) {
        this.classes = classes;
    }

    /**
     * Méthode addClasse qui permet d'ajouter une classe au package
     *
     * @param classe Classe
     */
    public void addClasse(Classe classe) {
        this.classes.add(classe);

    }

    /**
     * Méthode getInterfaces qui permet de récupérer les interfaces du package
     *
     * @return ArrayList<Interface> interfaces
     */
    public ArrayList<Interface> getInterfaces() {
        return interfaces;
    }

    /**
     * Méthode setInterfaces qui permet de modifier les interfaces du package
     *
     * @param interfaces ArrayList<Interface>
     */
    public void setInterfaces(ArrayList<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * Méthode addInterface qui permet d'ajouter une interface au package
     *
     * @param interface1 Interface
     */
    public void addInterface(Interface interface1) {
        this.interfaces.add(interface1);
    }

    /**
     * Méthode getEnumerations qui permet de récupérer les énumérations du package
     *
     * @return ArrayList<Enumerations> enumerations
     */
    public ArrayList<Enumerations> getEnumerations() {
        return enumerations;
    }

    /**
     * Méthode setEnumerations qui permet de modifier les énumérations du package
     *
     * @param enumerations ArrayList<Enumerations>
     */
    public void setEnumerations(ArrayList<Enumerations> enumerations) {
        this.enumerations = enumerations;
    }

    /**
     * Méthode addEnumeration qui permet d'ajouter une énumération au package
     *
     * @param enumeration Enumerations
     */
    public void addEnumeration(Enumerations enumeration) {
        this.enumerations.add(enumeration);
    }

    /**
     * Méthode getPackages qui permet de récupérer les packages du package
     *
     * @return ArrayList<Package> packages
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    /**
     * Méthode setPackages qui permet de modifier les packages du package
     *
     * @param packages ArrayList<Package>
     */
    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    /**
     * Méthode addPackage qui permet d'ajouter un package au package
     *
     * @param package1 Package
     */
    public void addPackage(Package package1) {
        this.packages.add(package1);
    }

    /**
     * Méthode getName qui permet de récupérer le nom du package
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode setName qui permet de modifier le nom du package
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
}
