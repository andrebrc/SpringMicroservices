package com.formiga.cidadeservice.utils;


import com.formiga.cidadeservice.model.UF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToUFConverter implements Converter<String, UF> {

    private final Logger log = LoggerFactory.getLogger(StringToUFConverter.class);
    @Override
    public UF convert(String source) {
        try {
            return UF.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UFNotFoundxception("Erro ao converter a UF: " + source + ", Error: " + e.getMessage());
        }
    }
}
