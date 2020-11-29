package co.th.employee.controllers;

import co.th.employee.services.GenerateToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import co.th.employee.resources.EmployeeDTO;
import co.th.employee.resources.TokenDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
	Map<String, EmployeeDTO> employees = new HashMap<>();
	TokenDTO tokenTmp = new TokenDTO();
	
    
	@PostMapping("/getAuthentication/")
    @ApiOperation(value="Find employees by id",notes="Provide an id to get employee information",response=EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public TokenDTO getAuthentication(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody EmployeeDTO employee
    ) {
		tokenTmp = new TokenDTO();
    	if(null!=employee && !StringUtils.trimToEmpty(employee.getEmpid()).equals("")){
    		tokenTmp.setEmpid(employee.getEmpid());
    		tokenTmp.setToken(GenerateToken.generateNewToken());
        }
        return tokenTmp;
    }

    @PostMapping("/getById")
    @ApiOperation(value="Find employees by id",notes="Provide an id to get employee information",response=EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public EmployeeDTO getById(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody EmployeeDTO employee, 
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody TokenDTO token
    ) throws Exception {
        if(!StringUtils.trimToEmpty(tokenTmp.getToken()).equals("") && tokenTmp.getToken().equals(StringUtils.trimToEmpty(token.getToken()))) {
        	return employees.get(employee.getEmpid());
        }
        else {
        	throw new ResponseStatusException(
        	          HttpStatus.UNAUTHORIZED, "You are not authorized to view the resource", null);
        }    	
    }

    @PostMapping("/getAll")
    @ApiOperation(value="Find all employees",notes="Get list of all employees",response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<EmployeeDTO> getAll(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody TokenDTO token
    ) {
    	if(!StringUtils.trimToEmpty(tokenTmp.getToken()).equals("") && tokenTmp.getToken().equals(StringUtils.trimToEmpty(token.getToken()))) {
    		return new ArrayList<EmployeeDTO>(employees.values());
        }
        else {
        	throw new ResponseStatusException(
        	          HttpStatus.UNAUTHORIZED, "You are not authorized to view the resource", null);
        } 
    }

    @PostMapping("/create")
    @ApiOperation(value="Create a new employee",notes="Create a new employee",response=List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created an employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public EmployeeDTO create(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody EmployeeDTO employee
    ) {
        if(StringUtils.trimToEmpty(tokenTmp.getToken()).equals("")) {
        	 employees.put(employee.getEmpid(), employee);
             return employee;
        }
        else {
        	throw new ResponseStatusException(
        	          HttpStatus.UNAUTHORIZED, "You are not authorized to create the new resource", null);
        } 
    }

    @PostMapping("/update")
    @ApiOperation(value="Update a employee",notes="Update a employee",response=EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated an employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public EmployeeDTO update(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody EmployeeDTO employee, 
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody TokenDTO token
    ) {
       if(!StringUtils.trimToEmpty(tokenTmp.getToken()).equals("") && tokenTmp.getToken().equals(StringUtils.trimToEmpty(token.getToken()))) {
        	employees.put(employee.getEmpid(), employee);
            return employee;
       }
       else {
       	throw new ResponseStatusException(
       	          HttpStatus.UNAUTHORIZED, "You are not authorized to update the resource", null);
       }
    }

    @PostMapping("/deleteById")
    @ApiOperation(value="Delete a employee based on employee id",notes="Delete a employee for a given id",response=EmployeeDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted an employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public EmployeeDTO deleteById(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody EmployeeDTO employee, 
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody TokenDTO token
    ) {
       if(!StringUtils.trimToEmpty(tokenTmp.getToken()).equals("") && tokenTmp.getToken().equals(StringUtils.trimToEmpty(token.getEmpid()))) {
        	return employees.remove(employee.getEmpid());
       }
       else {
       	throw new ResponseStatusException(
       	          HttpStatus.UNAUTHORIZED, "You are not authorized to delete the resource", null);
       }

    }

    @PostMapping("/deleteAll")
    @ApiOperation(value="Delete all employees",notes="Delete all employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted all employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void deleteAll(
    		@ApiParam(value="Send employee object to be created",required = true) @RequestBody TokenDTO token
    ) {
        if(!StringUtils.trimToEmpty(tokenTmp.getToken()).equals("") && tokenTmp.getToken().equals(StringUtils.trimToEmpty(token.getToken()))) {
        	employees.clear();
       }
       else {
       	throw new ResponseStatusException(
       	          HttpStatus.UNAUTHORIZED, "You are not authorized to delete the resource", null);
       }

    }


}
