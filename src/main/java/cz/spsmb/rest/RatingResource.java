package cz.spsmb.rest;

import cz.spsmb.dao.ConsoleRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import cz.spsmb.dao.ConsoleRepository;
import cz.spsmb.dao.RatingRepository;
import cz.spsmb.dto.RatingDTO;
import cz.spsmb.model.Console;
import cz.spsmb.model.Rating;

import java.util.Optional;

@Path("/rating")
public class RatingResource {
    @Inject
    ConsoleRepository consoleRepository;
    @Inject
    RatingRepository ratingRepository;

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response setRating(@PathParam("id") long consoleId, RatingDTO ratingDTO) {
        if (!isValidInput(ratingDTO) || consoleId <= 0) {
            return Response.status(400).entity("Invalid rating or consoleId value.").build();
        }
        Optional<Console> consoleOptional = consoleRepository.findByIdOptional(consoleId);
        if (consoleOptional.isPresent()) {
            Console console = consoleOptional.get();
            Rating rating = new cz.spsmb.model.Rating();
            rating.setRating(ratingDTO.getRating());
            rating.setConsole(console);
            console.getRatingList().add(rating);
            ratingRepository.persist(rating);
            consoleRepository.persist(console);
            return Response.ok().entity("ok").build();
        }
        return Response.status(404).entity(String.format("Console with id %s not found.", consoleId)).build();
    }

    private boolean isValidInput(RatingDTO ratingDTO) {
        return (ratingDTO.getRating() > 0 && ratingDTO.getRating() <= 5);
    }
    }