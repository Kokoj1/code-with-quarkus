package cz.spsmb.dao;

import cz.spsmb.model.Type;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class TypeRepository implements PanacheRepository<Type> {
    public Optional<Type> listByName(String name){
        return find("name", name).singleResultOptional();
    }

}
