package id.gpi.popplus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePojo
{
	@SerializedName("a:FaultCode")
	@Expose
	private String aFaultCode;
	@SerializedName("a:FaultDescription")
	@Expose
	private String aFaultDescription;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("FamilyName")
	@Expose
	private String FamilyName;
	@SerializedName("GivenName")
	@Expose
	private String GivenName;
	@SerializedName("PersonalID")
	@Expose
	private Integer PersonalID;
	@SerializedName("RegisterRsp")
	@Expose
	private String RegisterRsp;
	@SerializedName("ForgetRsp")
	@Expose
	private String ForgetRsp;
	@SerializedName("ChangeRsp")
	@Expose
	private String ChangeRsp;
	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("redirect_url")
	@Expose
	private String redirect_url;
	@SerializedName("PosAccountID")
	@Expose
	private String PosAccountID;
	@SerializedName("Result")
	@Expose
	private String strResult = null;

	public String getaFaultCode() {
		return aFaultCode;
	}

	public void setaFaultCode(String aFaultCode) {
		this.aFaultCode = aFaultCode;
	}

	public String getaFaultDescription() {
		return aFaultDescription;
	}

	public void setaFaultDescription(String aFaultDescription) {
		this.aFaultDescription = aFaultDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	public String getGivenName() {
		return GivenName;
	}

	public void setGivenName(String givenName) {
		GivenName = givenName;
	}

	public Integer getPersonalID() {
		return PersonalID;
	}

	public void setPersonalID(Integer personalID) {
		PersonalID = personalID;
	}

	public String getRegisterRsp() {
		return RegisterRsp;
	}

	public void setRegisterRsp(String registerRsp) {
		RegisterRsp = registerRsp;
	}

	public String getForgetRsp() {
		return ForgetRsp;
	}

	public void setForgetRsp(String forgetRsp) {
		ForgetRsp = forgetRsp;
	}

	public String getChangeRsp() {
		return ChangeRsp;
	}

	public void setChangeRsp(String changeRsp) {
		ChangeRsp = changeRsp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getPosAccountID() {
		return PosAccountID;
	}

	public void setPosAccountID(String posAccountID) {
		PosAccountID = posAccountID;
	}

	public String getStrResult() {
		return strResult;
	}

	public void setStrResult(String strResult) {
		this.strResult = strResult;
	}
}
