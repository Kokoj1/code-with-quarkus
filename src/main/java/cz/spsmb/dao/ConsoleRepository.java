package cz.spsmb.dao;

import cz.spsmb.model.Console;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


import java.util.List;

    @ApplicationScoped
    public class ConsoleRepository implements PanacheRepository<Console> {

        /**public List<Console> listByName(String name){

            return find("name", name).list();
        }

        public Console listById(Long id){
            return findById(id);
        }
**/
        public List<Console> findByTypeName (String typeName){
            return Panache.getEntityManager().createQuery("SELECT j FROM Console j JOIN FETCH j.type c WHERE c.name = :typeName", Console.class).setParameter("typeName", typeName).getResultList();
        }
    }

