package pumlFromJava.classes;

/**
 * Interface Type
 * <p>
 *     Interface qui permet de créer une méthode
 *     d'une classe
 *     <br>
 *     Elle possède les méthodes :
 *     <ul>
 *         <li>isPublic</li>
 *         <li>isPrivate</li>
 *         <li>isProtected</li>
 *         <li>isAbstract</li>
 *         <li>isStatic</li>
 *         <li>getVisibility</li>
 *     </ul>
 */
public interface Type {

    /**
     *
     * @param parameters String
     * @return boolean
     */
    default boolean isPublic(String parameters) {
        return parameters.contains("public");
    }

    /**
     *
     * @param parameters String
     * @return boolean
     */
    default boolean isPrivate(String parameters) {
        return parameters.contains("private");
    }

    /**
     *
     * @param parameters String
     * @return boolean
     */
    default boolean isProtected(String parameters) {
        return parameters.contains("protected");
    }

    /**
     *
     * @param parameters String
     * @return boolean
     */
    default boolean isAbstract(String parameters) {
        return parameters.contains("abstract");
    }

    /**
     *
     * @param parameters String
     * @return boolean
     */
    default boolean isStatic(String parameters) {
        return parameters.contains("static");
    }

    /**
     *
     * @return String
     */
    default String getVisibility() {
        return "";
    }
}
