package com.outskirtslabs.beancount.annotation;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Niko Strijbol
 */
public class AnnotationResult {
    
    private final int line;
    private final String message;

    public AnnotationResult(int line, String message) {
        this.line = line;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public String getMessage() {
        return message;
    }
    
    private static final Pattern pattern = Pattern.compile("(.*):(\\d+):(.*)");
    
    public static List<AnnotationResult> parse(String text) {
        var result = new ArrayList<AnnotationResult>();
        if (StringUtils.isEmpty(text.trim())) {
            return result;
        }
        var blocks = text.split("\n\n\n");
        for (var block : blocks) {
            var line = block.split("\n");
            var interesting = line[0];
            Matcher matcher = pattern.matcher(interesting);
            if (!matcher.matches()) {
                System.err.println("Non match for line " + interesting);
                continue;
            }
            var nr = matcher.group(2);
            var message = matcher.group(3).trim();
            if (message.startsWith("Account") && message.endsWith("does not exist:")) {
                continue;
            }
            if (message.startsWith("Invalid reference to unknown account")) {
                continue;
            }
            result.add(new AnnotationResult(Integer.parseInt(nr) - 1, message));
        }
        return result;
    }
}
