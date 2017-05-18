package com.example.nam.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "specializationApi",
        version = "v1",
        resource = "specialization",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.nam.example.com",
                ownerName = "backend.myapplication.nam.example.com",
                packagePath = ""
        )
)
public class SpecializationEndpoint {

    private static final Logger logger = Logger.getLogger(SpecializationEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Specialization.class);
    }

    /**
     * Returns the {@link Specialization} with the corresponding ID.
     *
     * @param idSpecialization the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Specialization} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "specialization/{idSpecialization}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Specialization get(@Named("idSpecialization") long idSpecialization) throws NotFoundException {
        logger.info("Getting Specialization with ID: " + idSpecialization);
        Specialization specialization = ofy().load().type(Specialization.class).id(idSpecialization).now();
        if (specialization == null) {
            throw new NotFoundException("Could not find Specialization with ID: " + idSpecialization);
        }
        return specialization;
    }

    /**
     * Inserts a new {@code Specialization}.
     */
    @ApiMethod(
            name = "insert",
            path = "specialization",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Specialization insert(Specialization specialization) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that specialization.idSpecialization has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(specialization).now();
        logger.info("Created Specialization with ID: " + specialization.getIdSpecialization());

        return ofy().load().entity(specialization).now();
    }

    /**
     * Updates an existing {@code Specialization}.
     *
     * @param idSpecialization the ID of the entity to be updated
     * @param specialization   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idSpecialization} does not correspond to an existing
     *                           {@code Specialization}
     */
    @ApiMethod(
            name = "update",
            path = "specialization/{idSpecialization}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Specialization update(@Named("idSpecialization") long idSpecialization, Specialization specialization) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idSpecialization);
        ofy().save().entity(specialization).now();
        logger.info("Updated Specialization: " + specialization);
        return ofy().load().entity(specialization).now();
    }

    /**
     * Deletes the specified {@code Specialization}.
     *
     * @param idSpecialization the ID of the entity to delete
     * @throws NotFoundException if the {@code idSpecialization} does not correspond to an existing
     *                           {@code Specialization}
     */
    @ApiMethod(
            name = "remove",
            path = "specialization/{idSpecialization}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idSpecialization") long idSpecialization) throws NotFoundException {
        checkExists(idSpecialization);
        ofy().delete().type(Specialization.class).id(idSpecialization).now();
        logger.info("Deleted Specialization with ID: " + idSpecialization);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "specialization",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Specialization> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Specialization> query = ofy().load().type(Specialization.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Specialization> queryIterator = query.iterator();
        List<Specialization> specializationList = new ArrayList<Specialization>(limit);
        while (queryIterator.hasNext()) {
            specializationList.add(queryIterator.next());
        }
        return CollectionResponse.<Specialization>builder().setItems(specializationList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idSpecialization) throws NotFoundException {
        try {
            ofy().load().type(Specialization.class).id(idSpecialization).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Specialization with ID: " + idSpecialization);
        }
    }
}