package pumlFromJava;

import java.util.spi.ToolProvider;

public class Java2Puml
{

    public static void main(String[] args)
    {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());

/*
    javadoc -private -sourcepath ../../P21/p21/P21_Western/src -doclet pumlFromJava.FirstDoclet -docletpath out/production/SAE21/ western
 */
        toolProvider.run(System.out, System.err, args);
    }
}
