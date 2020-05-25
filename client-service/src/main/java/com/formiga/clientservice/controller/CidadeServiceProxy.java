package com.formiga.clientservice.controller;


import com.formiga.clientservice.model.Cidade;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "cidade-service")
@RibbonClient(name = "cidade-service")
public interface CidadeServiceProxy {

    @RequestMapping(value = "/cidades/{id}", consumes = "application/json")
    public Cidade findById(@PathVariable Long id);

}
