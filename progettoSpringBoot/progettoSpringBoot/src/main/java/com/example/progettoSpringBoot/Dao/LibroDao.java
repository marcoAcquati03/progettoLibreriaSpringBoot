package com.example.progettoSpringBoot.Dao;

import com.example.progettoSpringBoot.Model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroDao extends CrudRepository<Libro, Long>{
    Libro findById(long id);
}
