package co.th.employee.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Token foe authen")
public class TokenDTO {

	@ApiModelProperty(notes="Employee id")
	private String empid;
    @ApiModelProperty(notes="Token")
    private String token;
    
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}
