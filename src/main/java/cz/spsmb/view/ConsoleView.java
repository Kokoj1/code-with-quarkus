package cz.spsmb.view;


import cz.spsmb.dao.ConsoleRepository;

import cz.spsmb.dao.RatingRepository;
import cz.spsmb.dao.TypeRepository;
import cz.spsmb.dto.ConsoleDTO;
import cz.spsmb.model.Console;
import cz.spsmb.model.Type;
import cz.spsmb.rest.ConsoleResource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jdk.jfr.Category;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
@RequestScoped
public class ConsoleView {

    @Inject
    ConsoleRepository consoleRepository;
    @Inject
    RatingRepository ratingRepository;
    @Inject
    TypeRepository typeRepository;

    public ConsoleDTO getNewConsole(){return newConsole;}
    List<Console> consoleList;
    List<Type> types;
    ConsoleDTO newConsole;
    String brand;
    int year;

    @PostConstruct
    public void init() {
       Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String typeName = params.get("type");
        System.out.println(typeName);

        types = typeRepository.listAll();
        System.out.println(types);

        Optional<Type> type = typeRepository.listByName(typeName);
        if(type.isPresent()){
            consoleList = consoleRepository.findByTypeName(typeName);
            System.out.println(consoleList);
        }
        else{
            consoleList = consoleRepository.listAll();
        }
        newConsole = new ConsoleDTO();

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ConsoleRepository getConsoleRepository() {
        return consoleRepository;
    }

    public void setConsoleRepository(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    public RatingRepository getRatingRepository() {
        return ratingRepository;
    }

    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public TypeRepository getTypeRepository() {
        return typeRepository;
    }

    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public void setNewConsole(ConsoleDTO newConsole) {
        this.newConsole = newConsole;
    }

    public List<Console> getConsoleList() {
        return consoleList;
    }

    public void setConsoleList(List<Console> personList) {
        this.consoleList = consoleList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public double getAvgRatingForConsole(Console console) {
        return ratingRepository.getAvgRatingForConsole(console);
    }

    @Transactional
    public void saveConsole() {
        if (ConsoleResource.validateInput(newConsole)){
            Console console = new Console();
            console.setBrand(newConsole.getBrand());
            console.setYear(newConsole.getYear());
            Optional<Type> typeOptional = typeRepository.listByName(newConsole.getType());
            if (typeOptional.isPresent()) {
                console.setType(typeOptional.get());
            } else {
                Type type = new Type();
                type.setName(newConsole.getType());
                type.getConsoleList().add(console);
                typeRepository.persist(type);
                console.setType(type);
            }
            consoleRepository.persist(console);
        }

                init();
    }
}
