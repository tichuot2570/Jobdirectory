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
        name = "locationApi",
        version = "v1",
        resource = "location",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.nam.example.com",
                ownerName = "backend.myapplication.nam.example.com",
                packagePath = ""
        )
)
public class LocationEndpoint {

    private static final Logger logger = Logger.getLogger(LocationEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Location.class);
    }

    /**
     * Returns the {@link Location} with the corresponding ID.
     *
     * @param idLocation the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Location} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "location/{idLocation}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Location get(@Named("idLocation") long idLocation) throws NotFoundException {
        logger.info("Getting Location with ID: " + idLocation);
        Location location = ofy().load().type(Location.class).id(idLocation).now();
        if (location == null) {
            throw new NotFoundException("Could not find Location with ID: " + idLocation);
        }
        return location;
    }

    /**
     * Inserts a new {@code Location}.
     */
    @ApiMethod(
            name = "insert",
            path = "location",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Location insert(Location location) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that location.idLocation has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(location).now();
        logger.info("Created Location with ID: " + location.getIdLocation());

        return ofy().load().entity(location).now();
    }

    /**
     * Updates an existing {@code Location}.
     *
     * @param idLocation the ID of the entity to be updated
     * @param location   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idLocation} does not correspond to an existing
     *                           {@code Location}
     */
    @ApiMethod(
            name = "update",
            path = "location/{idLocation}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Location update(@Named("idLocation") long idLocation, Location location) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idLocation);
        ofy().save().entity(location).now();
        logger.info("Updated Location: " + location);
        return ofy().load().entity(location).now();
    }

    /**
     * Deletes the specified {@code Location}.
     *
     * @param idLocation the ID of the entity to delete
     * @throws NotFoundException if the {@code idLocation} does not correspond to an existing
     *                           {@code Location}
     */
    @ApiMethod(
            name = "remove",
            path = "location/{idLocation}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idLocation") long idLocation) throws NotFoundException {
        checkExists(idLocation);
        ofy().delete().type(Location.class).id(idLocation).now();
        logger.info("Deleted Location with ID: " + idLocation);
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
            path = "location",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Location> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Location> query = ofy().load().type(Location.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Location> queryIterator = query.iterator();
        List<Location> locationList = new ArrayList<Location>(limit);
        while (queryIterator.hasNext()) {
            locationList.add(queryIterator.next());
        }
        return CollectionResponse.<Location>builder().setItems(locationList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idLocation) throws NotFoundException {
        try {
            ofy().load().type(Location.class).id(idLocation).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Location with ID: " + idLocation);
        }
    }
}