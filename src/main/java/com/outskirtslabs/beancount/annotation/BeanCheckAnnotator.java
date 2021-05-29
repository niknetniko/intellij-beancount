package com.outskirtslabs.beancount.annotation;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.util.ExecUtil;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * @author Niko Strijbol
 */
public class BeanCheckAnnotator extends ExternalAnnotator<String, List<AnnotationResult>> implements DumbAware {

    @Override
    public @Nullable String collectInformation(@NotNull PsiFile file) {
        return file.getVirtualFile().getPath();
    }

    @Override
    public @Nullable List<AnnotationResult> doAnnotate(String collectedInfo) {
        GeneralCommandLine commandLine = new GeneralCommandLine()
                .withParentEnvironmentType(GeneralCommandLine.ParentEnvironmentType.CONSOLE)
                .withExePath("bean-check")
                .withParameters(collectedInfo);

        try {
            var out = ExecUtil.execAndGetOutput(commandLine);
            return AnnotationResult.parse(out.getStderr());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void apply(@NotNull PsiFile file, List<AnnotationResult> annotationResults, @NotNull AnnotationHolder holder) {
        if (annotationResults == null) {
            return;
        }
        var doc = Objects.requireNonNull(file.getViewProvider().getDocument());
        for (var annotationResult : annotationResults) {
            if (annotationResult.getLine() < 0 || annotationResult.getLine() > doc.getLineCount()) {
                continue;
            }

            // Some checks have built-in support, so ignore those.
            if (annotationResult.getMessage().startsWith("Transaction does not balance:")) {
                continue;
            }

            var start = doc.getLineStartOffset(annotationResult.getLine());
            var end = doc.getLineEndOffset(annotationResult.getLine());
            holder.newAnnotation(HighlightSeverity.ERROR, annotationResult.getMessage())
                    .range(TextRange.create(start, end))
                    .create();
        }
    }
}
