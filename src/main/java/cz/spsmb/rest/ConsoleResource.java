package cz.spsmb.rest;


import cz.spsmb.dao.ConsoleRepository;
import cz.spsmb.dao.TypeRepository;
import cz.spsmb.dto.ConsoleDTO;
import cz.spsmb.model.Console;
import cz.spsmb.model.Rating;
import cz.spsmb.model.Type;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/consoles")
public class ConsoleResource {

    @Inject
    ConsoleRepository consoleRepository;
    @Inject
    TypeRepository typeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Console> consoles = consoleRepository.listAll();
        return Response.ok().entity(consoles).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Console consoles = consoleRepository.findById(id);
        return Response.ok().entity(consoles).build();
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        consoleRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ConsoleDTO consoleDTO) {
        if (validateInput(consoleDTO)){
        Console console = new Console();
        console.setBrand(consoleDTO.getBrand());
        Optional<Type> typeOptional = typeRepository.listByName(consoleDTO.getType());
        if (typeOptional.isPresent()) {
            console.setType(typeOptional.get());
        } else {
            Type type = new Type();
            type.setName(consoleDTO.getType());
            type.getConsoleList().add(console);
            typeRepository.persist(type);
            console.setType(type);
        }
        consoleRepository.persist(console);
        return Response.ok().entity("OK").build();
    }

          return Response.status(400).entity("Invalid inputs").build();

}
    public static boolean validateInput(ConsoleDTO consoleDTO){
        return !(consoleDTO.getBrand().isEmpty() || consoleDTO.getType().isEmpty());
    }
}




