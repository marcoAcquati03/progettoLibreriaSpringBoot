package com.example.progettoSpringBoot.Dao;

import com.example.progettoSpringBoot.Model.UserLibro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface UserLibroDao extends CrudRepository<UserLibro, Long>{

    UserLibro findById(long id);

    List<UserLibro> findByUserId(long id);
}
