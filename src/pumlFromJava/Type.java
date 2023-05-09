package pumlFromJava;

public interface Type {

    default boolean isPublic(String parameters) {
        return parameters.contains("public");
    }

    default boolean isPrivate(String parameters) {
        return parameters.contains("private");
    }

    default boolean isProtected(String parameters) {
        return parameters.contains("protected");
    }

    default boolean isAbstract(String parameters) {
        return parameters.contains("abstract");
    }

    default boolean isStatic(String parameters) {
        return parameters.contains("static");
    }

    default String getVisibility() {
        return "";
    }
}
