package pumlFromJava.classes;

import java.util.ArrayList;

public class Instance {
    private String name;

    private ArrayList<Methode> methods = new ArrayList<Methode>();
    private ArrayList<String> extendsClasses = new ArrayList<String>();
    private ArrayList<String> implementsInterfaces = new ArrayList<String>();
    private String namePackage;

    public Instance(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Methode> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Methode> methods) {
        this.methods = methods;
    }

    public ArrayList<String> getExtendsClasses() {
        return extendsClasses;
    }

    public void setExtendsClasses(ArrayList<String> extendsClasses) {
        this.extendsClasses = extendsClasses;
    }

    public ArrayList<String> getImplementsInterfaces() {
        return implementsInterfaces;
    }

    public void setImplementsInterfaces(ArrayList<String> implementsInterfaces) {
        this.implementsInterfaces = implementsInterfaces;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}