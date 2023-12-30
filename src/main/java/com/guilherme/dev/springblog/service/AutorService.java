package com.guilherme.dev.springblog.service;

import com.guilherme.dev.springblog.model.Autor;

public interface AutorService {
    public Autor criar (Autor autor);
    public Autor obterPorCodigo(String codigo);
}
