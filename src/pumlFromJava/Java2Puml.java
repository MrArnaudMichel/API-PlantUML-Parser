package pumlFromJava;

import jdk.jfr.Description;

import java.util.spi.ToolProvider;

@Description("" +
        "use: PumlDoclet;")
public class Java2Puml {

    public static void main(String[] args) {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());

/*
    javadoc -private -sourcepath ../../P21/p21/P21_Western/src -doclet pumlFromJava.FirstDoclet -docletpath out/production/SAE21/ western  // FirstDoclet is the default doclet
    javadoc -private -sourcepath ../../P21/p21/P21_Western/src -doclet pumlFromJava.PumlDoclet -docletpath out/production/SAE21/ western // PumlDoclet is the default doclet
    javadoc -private -sourcepath ../../P21/p21/P21_Western/src -doclet pumlFromJava.PumlDoclet -docletpath out/production/SAE21/ western --out file --d uml
    javadoc -private -docletpath out/production/SAE21/ -doclet pumlFromJava.PumlDoclet -sourcepath ../../P21/p21/P21_Western/src western --out file --d uml --dca
    javadoc -private -docletpath out/production/SAE21/ -doclet pumlFromJava.PumlDoclet -sourcepath src/ pumlFromJava --out file --d uml

    Options:
    "Standard options:\n" +
            "  --d <directory>             Specify where to place generated output files the directory by default is current directory\n" +
            "  --out <title>               Specify a document name the title by default is \"name\".\n" +
            "  --dca                       If the option is specified then the program produces a DCA.\n" +
            "  --str                       Considers the String type as primitive.\n" +
            "  --association               Deactivates associations.\n" +
            "  --constructor               Deactivates constructors.\n" +
            "  --method                    Deactivates methods.\n" +
            "  --field                     Deactivates attributes.\n" +
            "  --primitive             Deactivates primitive types.\n" +
            "  --UnPrimitive               Deactivates non-primitive types.\n" +
            "  --extends                   Deactivates extends.\n" +
            "  --implements                Deactivates implements.\n" +
            "  --help                      Print a synopsis of nonstandard options and exit.\n");
 */

        toolProvider.run(System.out, System.err, args);
    }
}
