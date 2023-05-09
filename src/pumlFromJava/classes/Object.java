package pumlFromJava.classes;

import java.util.ArrayList;

public interface Object {
    String getName();
    void setName(String name) ;

    ArrayList<Attributs> getAttributes();

    void setAttributes(ArrayList<Attributs> attributes);
    ArrayList<Methode> getMethods();

    void setMethods(ArrayList<Methode> methods);
}
