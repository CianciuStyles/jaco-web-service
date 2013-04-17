package it.uniroma1.dis.jaco.server;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import it.uniroma1.dis.jaco.model.Behavior;

@Path("/{client_id}/behaviors")
public class BehaviorsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	String baseFilePath = System.getProperty("catalina.base") + File.separator
			+ "JacoStorage";

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Behavior> getBehaviorsBrowser(
			@PathParam("client_id") String client_id) throws JAXBException {
		// String ipAddress = httpRequest.getRemoteAddr() + ":" +
		// httpRequest.getRemotePort();
		List<Behavior> list = new ArrayList<Behavior>();

		// System.out.println(baseFilePath + File.separator + client_id +
		// File.separator + "Behaviors");
		File behaviorsDirectory = new File(baseFilePath + File.separator
				+ client_id + File.separator + "Behaviors");
		File[] behaviors = behaviorsDirectory.listFiles();

		for (File behaviorFile : behaviors) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior result = (Behavior) jaxbUnmarshaller
					.unmarshal(behaviorFile);
			list.add(result);
		}

		return list;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Behavior> getBehaviors(@PathParam("client_id") String client_id)
			throws JAXBException {
		List<Behavior> list = new ArrayList<Behavior>();

		File behaviorsDirectory = new File(baseFilePath + File.separator
				+ client_id + File.separator + "Behaviors");
		File[] behaviors = behaviorsDirectory.listFiles();

		for (File behaviorFile : behaviors) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Behavior.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Behavior result = (Behavior) jaxbUnmarshaller
					.unmarshal(behaviorFile);
			list.add(result);
		}

		return list;
	}

	@DELETE
	public Response clearBehaviors(@PathParam("client_id") String client_id) {
		File behaviorsDirectory = new File(baseFilePath + File.separator
				+ client_id + File.separator + "Behaviors");
		File[] behaviors = behaviorsDirectory.listFiles();

		for (File behavior : behaviors)
			behavior.delete();

		return Response.ok().build();
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount(@PathParam("client_id") String client_id) {
		File behaviorsDirectory = new File(baseFilePath + File.separator
				+ client_id + File.separator + "Behaviors");
		File[] behaviorFiles = behaviorsDirectory.listFiles();
		return String.valueOf(behaviorFiles.length);
	}

	@POST
	@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	public Response createNewBehavior(JAXBElement<Behavior> behavior,
			@PathParam("client_id") String client_id) throws JAXBException,
			IOException, URISyntaxException {
		Behavior b = behavior.getValue();
		BehaviorResource br = new BehaviorResource(uriInfo, request, client_id,
				b.getName());
		return br.putBehavior(behavior);
	}

	@Path("{behaviorName}")
	public BehaviorResource getNonPlayerCharacter(
			@PathParam("client_id") String client_id,
			@PathParam("behaviorName") String name) {
		return new BehaviorResource(uriInfo, request, client_id, name);
	}
}
