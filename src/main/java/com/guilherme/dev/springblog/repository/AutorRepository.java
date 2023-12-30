package com.guilherme.dev.springblog.repository;

import com.guilherme.dev.springblog.model.Autor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutorRepository extends MongoRepository<Autor, String> {

}
