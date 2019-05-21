package ro.clovertech.backend.resources;

import ro.clovertech.backend.business.CategoryTO;
import ro.clovertech.backend.business.CaveatEmptorException;
import ro.clovertech.backend.business.IService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("categories")
@RequestScoped
public class CategoriesResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private IService service;

    @GET
    public Response getCategories() {
        List<CategoryTO> rootCategories = service.getCategories();
        return Response.ok(rootCategories).build();
    }

    @POST
    public Response createCategory(CategoryTO categoryTO) {
        try {
            long categoryId = service.createCategory(categoryTO);

            return Response.created(
                    UriBuilder
                            .fromResource(CategoriesResource.class)
                            .path(Long.toString(categoryId))
                            .build()
            ).build();

        } catch (CaveatEmptorException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage()).build();
        }

    }

}
