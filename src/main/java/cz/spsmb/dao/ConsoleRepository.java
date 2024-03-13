package cz.spsmb.dao;

import cz.spsmb.model.Console;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


import java.util.List;

    @ApplicationScoped
    public class ConsoleRepository implements PanacheRepository<Console> {

        public List<Console> listByName(String name){

            return find("name", name).list();
        }

        public Console listById(Long id){
            return findById(id);
        }

    }

