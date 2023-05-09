package pumlFromJava.classes;

import java.util.ArrayList;

public class Enumerations {
    private String name;
    private ArrayList<String> attributes = new ArrayList<String>();
    private String namePackage;

    public Enumerations(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }
}
