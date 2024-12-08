package com.nhom7.socialNetworkApp.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/web.jsp")

                .addDecoratorPath("/admin/*", "/admin.jsp")

                .addDecoratorPath("/mod/*", "/mod.jsp")


                .addExcludedPath("/login/*")
                .addExcludedPath("/api/v1/*")
        ;
    }
}