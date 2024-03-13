package cz.spsmb.dao;


import cz.spsmb.model.Console;
import cz.spsmb.model.Rating;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RatingRepository implements PanacheRepository<Rating> {
    public double getAvgRatingForConsole(Console console){
        Double rating = Panache.getEntityManager().createQuery("SELECT avg(r.rating) FROM Rating r WHERE r.console = :console", Double.class).setParameter("console", console).getSingleResult();
        return rating == null?0d:rating;
    }
}
