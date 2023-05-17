package pumlFromJava.classes;

import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

public class Instance {
    private String name;

    private ArrayList<Methode> methods = new ArrayList<Methode>();
    private String extendsClasse;
    private List<? extends TypeMirror> implementsInterface = new ArrayList<>();
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

    public String getExtendsClasse() {
        return extendsClasse;
    }

    public void setExtendsClasse(String extendsClasse) {
        this.extendsClasse = extendsClasse;
    }

    public List<? extends TypeMirror> getImplementsInterface() {
        return implementsInterface;
    }

    public void setImplementsInterface(List<? extends TypeMirror> implementsInterface) {
        this.implementsInterface = implementsInterface;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}