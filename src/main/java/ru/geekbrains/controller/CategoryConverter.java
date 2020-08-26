package ru.geekbrains.controller;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@FacesConverter
@Named

public class CategoryConverter implements Converter<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryConverter.class);

    @Inject
    private  CategoryRepository categoryRepository; // здесь всегда null

    @Override
    public Category getAsObject(FacesContext context, UIComponent component, String value) {

         logger.info("get As Object " + value +categoryRepository);

        if (value == null || value.isEmpty()) {
            return null;
        }

        return categoryRepository.findById(Long.parseLong(value))
                .orElseThrow(() -> new ConverterException(new FacesMessage(String.format("%s is not correct id", value))));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Category value) {

        logger.info("get As String "+value);
        if (value == null) {
            return "";
        }

        return String.valueOf(value.getId());
    }
}
