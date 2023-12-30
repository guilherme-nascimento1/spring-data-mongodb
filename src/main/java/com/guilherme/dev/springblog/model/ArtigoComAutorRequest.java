package com.guilherme.dev.springblog.model;

import lombok.Data;

@Data
public class ArtigoComAutorRequest {
    private Artigo artigo;
    private Autor autor;
}
