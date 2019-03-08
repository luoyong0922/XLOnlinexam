package com.roy.utils;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
        String missingVariable = "undefined";
        try {
            String[] tmp = e.getMessageWithoutStackTop().split("\n");
            if (tmp.length > 1)
                tmp = tmp[1].split(" ");
            if (tmp.length > 1)
                missingVariable = tmp[1];

            writer.write("[出错了，请联系网站管理员：${" + missingVariable
                    + "}]");
        } catch (IOException ex) {
            throw new TemplateException(
                    "Failed to print error message. Cause: " + ex, environment);
        }
    }
}
