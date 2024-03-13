package cz.spsmb.view;


import cz.spsmb.dao.ConsoleRepository;

import cz.spsmb.model.Console;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;
@Named
@RequestScoped
public class ConsoleView {

    @Inject
    ConsoleRepository consoleRepository;


    List<Console> consoleList;
    String brand;
    int year;

    @PostConstruct
    public void init() {
        consoleList = consoleRepository.listAll();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Console> getPersonList() {
        return consoleList;
    }

    public void setPersonList(List<Console> personList) {
        this.consoleList = consoleList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Transactional
    public void savePerson() {
        Console console = new Console(brand, year);
        consoleRepository.persist(console);
        consoleList.add(console);
        System.out.println("Saved " + console);
    }
}
