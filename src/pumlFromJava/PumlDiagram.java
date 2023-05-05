package pumlFromJava;

import java.util.ArrayList;
import java.util.Enumeration;

public class PumlDiagram {
    private ArrayList<Classe> classes=new ArrayList<Classe>();
    private ArrayList<Interface> interfaces  = new ArrayList<Interface>();
    private ArrayList<Enumerations> enumerations = new ArrayList<Enumerations>();

    public PumlDiagram(){}

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
}
