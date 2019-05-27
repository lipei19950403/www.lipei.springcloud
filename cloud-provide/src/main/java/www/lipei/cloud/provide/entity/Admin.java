package www.lipei.cloud.provide.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
@JacksonXmlRootElement(localName = "User")
public class Admin {
	
	@JacksonXmlProperty(localName = "adminName")
	public String adminName;
	
	@JacksonXmlProperty(localName = "adminPwd")
	public String adminPwd;
	
	@JacksonXmlProperty(localName = "adminAge")
	public String adminAge;
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminAge() {
		return adminAge;
	}

	public void setAdminAge(String adminAge) {
		this.adminAge = adminAge;
	}
	
	
	
}
