package mtitek.swagger.sample;

import io.swagger.annotations.*;
import org.apache.http.HttpStatus;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("swagger/myEntity2")
@Api(value = "Swagger API: MyEntity")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MyEntitySwaggerAPI {
    @GET
    @Path("/query")
    @ApiOperation(httpMethod = HttpMethod.GET, value = "getMyEntity")
    @ApiResponses({ @ApiResponse(code = HttpStatus.SC_OK, message = "Http status 200 OK", response = MyEntity.class) })

    public Response getMyEntity() {
        MyEntity myEntity = new MyEntity();

        myEntity.setId("myEntity - id - 1");
        myEntity.setCode("myEntity - code - 1");
        myEntity.setDesc("myEntity - desc - 1");

        return Response.ok(myEntity).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "login: creates new connection to Bagri REST server; requires HTTPS protocol")
    public Response login(@ApiParam(name = "params", value = "set of login parameters in JSON format", example = "{\"schema\": \"default\", \"user\": \"guest\", \"password\": \"xxxxxxxx\"}") final LoginParams params) {
        logger.debug("login.enter; got params: {}", params);
        if (repos.getSchema(params.schemaName) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Unknown schema provided").build();
        } else {
            try {
                SchemaRepository repo = repos.connect(params.schemaName, params.userName, params.password);
                if (repo != null) {
                    NewCookie cookie = new NewCookie(bg_cookie, repo.getClientId());
                    server.reload(params.schemaName, false);
                    logger.trace("login.exit; returning client: {}", repo.getClientId());
                    return Response.ok("OK").cookie(cookie).build();
                } else {
                    return Response.status(Status.GONE).entity("Schema is not active").build();
                }
            } catch (// "Wrong credentials" ?
                    Exception // "Wrong credentials" ?
                            ex) {
                return Response.serverError().entity(ex.getMessage()).build();
            }
        }
    }

    @Path("{username}")
    @ApiOperation(value = "Updated user")
    public Response updateUser(
            @ApiParam(value = "description for query-parameter") @QueryParam("username") String username
    ) {
return null;
    }
}