package com.guilherme.dev.springblog.service.impl;

import com.guilherme.dev.springblog.model.Artigo;
import com.guilherme.dev.springblog.model.Autor;
import com.guilherme.dev.springblog.repository.ArtigoRepository;
import com.guilherme.dev.springblog.repository.AutorRepository;
import com.guilherme.dev.springblog.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;
    @Override
    public Autor criar(Autor autor) {
        return this.autorRepository.save(autor);
    }

    @Override
    public Autor obterPorCodigo(String codigo) {
        return this.autorRepository.findById(codigo).orElseThrow(()-> new IllegalArgumentException("Autor não existe"));
    }
}
