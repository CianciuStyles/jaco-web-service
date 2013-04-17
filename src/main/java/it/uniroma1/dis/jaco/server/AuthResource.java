package it.uniroma1.dis.jaco.server;

import java.math.BigInteger;
import java.security.SecureRandom;

import it.uniroma1.dis.jaco.model.XMLClientID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/auth")
public class AuthResource {
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

	@GET
	@Produces(MediaType.TEXT_XML)
	public XMLClientID getXMLClientIDBrowser() {
		XMLClientID result = new XMLClientID(sig.nextSessionId());
		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public XMLClientID getXMLClientID() {
		XMLClientID result = new XMLClientID(sig.nextSessionId());
		return result;
	}

	/*
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public JSONClientID
	 * getJSONClientID() { JSONClientID result = new
	 * JSONClientID(sig.nextSessionId()); return result; }
	 */
}

final class SessionIdentifierGenerator {

	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

}