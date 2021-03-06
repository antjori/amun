package pt.devhub.siu.portal.google;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.devhub.siu.common.response.IResponse;
import pt.devhub.siu.common.response.ext.google.Item;
import pt.devhub.siu.common.response.impl.GoogleResponse;
import pt.devhub.siu.service.ServiceProcessor;
import pt.devhub.siu.service.resolver.ServiceEntity;
import pt.devhub.siu.service.resolver.ServiceEntityType;

/**
 * The controller for the Google page.
 */
@Named
@ViewScoped
public class GoogleServiceController implements Serializable {

	/**
	 * The serial version unique identifier.
	 */
	private static final long serialVersionUID = 4196891209387502056L;

	private IResponse googleResponse;

	// Injection of Google's service processor
	@Inject
	@ServiceEntity(ServiceEntityType.GOOGLE)
	private ServiceProcessor serviceProcessor;

	/**
	 * Default constructor for this class.
	 */
	public GoogleServiceController() {
	}

	/**
	 * Executes the call to Google's API Discovery service.
	 */
	@PostConstruct
	public void init() {
		setGoogleResponse(serviceProcessor.processRequest());
	}

	/**
	 * @param googleResponse
	 *            the googleResponse to set
	 */
	private void setGoogleResponse(final IResponse googleResponse) {
		this.googleResponse = googleResponse;
	}

	public List<Item> getGoogleApis() {
		return getGoogleResponse().getApiDiscovery().getItems();
	}

	/**
	 * Gets Google's response making the proper cast.
	 * 
	 * @return Google's response object
	 */
	private GoogleResponse getGoogleResponse() {
		return (GoogleResponse) googleResponse;
	}
}
