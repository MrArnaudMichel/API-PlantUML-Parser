package pumlFromJava;

import java.util.spi.ToolProvider;

public class Java2Puml
{

    public static void main(String[] args)
    {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());

/*
    javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet>
      <package> ... <fichiers>
 */
        toolProvider.run(System.out, System.err, args);
    }
}
