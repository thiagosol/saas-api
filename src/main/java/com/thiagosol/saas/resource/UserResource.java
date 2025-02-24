package com.thiagosol.saas.resource;

import com.thiagosol.saas.dto.UserDTO;
import com.thiagosol.saas.service.UserService;
//import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<UserDTO> listAll() {
        return userService.listAll();
    }

    /*@POST
    public UserDTO create(@Valid UserCreateDTO dto) {
        return userService.create(dto);
    }

    @PUT
    @Path("/{id}")
    public UserDTO update(@PathParam("id") Long id, @Valid UserUpdateDTO dto) {
        return userService.update(id, dto);
    }*/
}

