package id.gpi.popplus.model;

public class CredentialsData
{
	private ResponsePojo responsePojo;

	public ResponsePojo getResponsePojo() {
		return responsePojo;
	}

	public void setResponsePojo(ResponsePojo responsePojo) {
		this.responsePojo = responsePojo;
	}

	private static CredentialsData CredentialsInstance = new CredentialsData();
	public synchronized static CredentialsData getInstance()
	{
		return CredentialsInstance;
	}

	private CredentialsData()
	{
	}

	public static void initCredentialsData()
	{
		CredentialsInstance = new CredentialsData();
	}
}
