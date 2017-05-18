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
        name = "jobDescriptionApi",
        version = "v1",
        resource = "jobDescription",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.nam.example.com",
                ownerName = "backend.myapplication.nam.example.com",
                packagePath = ""
        )
)
public class JobDescriptionEndpoint {

    private static final Logger logger = Logger.getLogger(JobDescriptionEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(JobDescription.class);
    }

    /**
     * Returns the {@link JobDescription} with the corresponding ID.
     *
     * @param idJobDescription the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code JobDescription} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "jobDescription/{idJobDescription}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public JobDescription get(@Named("idJobDescription") long idJobDescription) throws NotFoundException {
        logger.info("Getting JobDescription with ID: " + idJobDescription);
        JobDescription jobDescription = ofy().load().type(JobDescription.class).id(idJobDescription).now();
        if (jobDescription == null) {
            throw new NotFoundException("Could not find JobDescription with ID: " + idJobDescription);
        }
        return jobDescription;
    }

    /**
     * Inserts a new {@code JobDescription}.
     */
    @ApiMethod(
            name = "insert",
            path = "jobDescription",
            httpMethod = ApiMethod.HttpMethod.POST)
    public JobDescription insert(JobDescription jobDescription) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that jobDescription.idJobDescription has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(jobDescription).now();
        logger.info("Created JobDescription with ID: " + jobDescription.getIdJobDescription());

        return ofy().load().entity(jobDescription).now();
    }

    /**
     * Updates an existing {@code JobDescription}.
     *
     * @param idJobDescription the ID of the entity to be updated
     * @param jobDescription   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idJobDescription} does not correspond to an existing
     *                           {@code JobDescription}
     */
    @ApiMethod(
            name = "update",
            path = "jobDescription/{idJobDescription}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public JobDescription update(@Named("idJobDescription") long idJobDescription, JobDescription jobDescription) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idJobDescription);
        ofy().save().entity(jobDescription).now();
        logger.info("Updated JobDescription: " + jobDescription);
        return ofy().load().entity(jobDescription).now();
    }

    /**
     * Deletes the specified {@code JobDescription}.
     *
     * @param idJobDescription the ID of the entity to delete
     * @throws NotFoundException if the {@code idJobDescription} does not correspond to an existing
     *                           {@code JobDescription}
     */
    @ApiMethod(
            name = "remove",
            path = "jobDescription/{idJobDescription}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idJobDescription") long idJobDescription) throws NotFoundException {
        checkExists(idJobDescription);
        ofy().delete().type(JobDescription.class).id(idJobDescription).now();
        logger.info("Deleted JobDescription with ID: " + idJobDescription);
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
            path = "jobDescription",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<JobDescription> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<JobDescription> query = ofy().load().type(JobDescription.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<JobDescription> queryIterator = query.iterator();
        List<JobDescription> jobDescriptionList = new ArrayList<JobDescription>(limit);
        while (queryIterator.hasNext()) {
            jobDescriptionList.add(queryIterator.next());
        }
        return CollectionResponse.<JobDescription>builder().setItems(jobDescriptionList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idJobDescription) throws NotFoundException {
        try {
            ofy().load().type(JobDescription.class).id(idJobDescription).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find JobDescription with ID: " + idJobDescription);
        }
    }
}