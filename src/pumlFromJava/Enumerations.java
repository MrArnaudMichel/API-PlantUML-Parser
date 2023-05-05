package pumlFromJava;

import java.util.ArrayList;

public class Enumerations {
    private String name;
    private ArrayList<String> attributes = new ArrayList<String>();

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
}
