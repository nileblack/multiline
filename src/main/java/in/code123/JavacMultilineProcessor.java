package in.code123;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;


@SupportedAnnotationTypes({"in.code123.Multiline"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public final class JavacMultilineProcessor extends AbstractProcessor {
    private Elements elementUtils;

    public JavacMultilineProcessor() {
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
        System.out.println(".....");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("processing Multiline");
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Multiline.class);
        StringBuilder sb = new StringBuilder("package io.code123;\n")
                .append("@javax.annotation.processing.Generated(\"com.example.Generator\")\n")
                .append("public final class MultilineUtils {\n");
        for (Element element : elements) {
            String doc = elementUtils.getDocComment(element);
            if (doc != null) {
                sb.append("public static final String ").append(element.getSimpleName()).append("() {");
                sb.append(" return ");
                String[] lines = doc.split("\n");
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i];
                    sb.append("\"").append(line).append("\\n\"");
                    if (i < lines.length - 1) {
                        sb.append("+\n");
                    }
                }
                sb.append(";\n}\n");
            }
        }
        sb.append("}");
        try {
            JavaFileObject out_file = processingEnv.getFiler().createSourceFile("io.code123.MultilineUtils");
            try (Writer writer = out_file.openWriter()) {
                writer.append(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;
    }
}