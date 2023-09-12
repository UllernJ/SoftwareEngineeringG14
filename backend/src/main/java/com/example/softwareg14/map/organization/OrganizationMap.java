package com.example.softwareg14.map.organization;

import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationMap {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationMap(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.ORGANIZATION_REGISTER)
    public ResponseEntity<String> organization(@RequestBody OrganizationRequest organizationRequest) {
        if(!organizationRequest.validate()) {
            return new ResponseEntity("Invalid request", HttpStatus.BAD_REQUEST);
        }
        try {
            organizationService.createOrganization(organizationRequest.name, organizationRequest.address, organizationRequest.phone, organizationRequest.email, organizationRequest.website, organizationRequest.description, organizationRequest.password);
            return new ResponseEntity("Organization registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Failed to register organization", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(Endpoint.ORGANIZATIONS_ALL)
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        try {
            List<Organization> organizations = organizationService.getAllOrganizations();
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(Endpoint.ORGANIZATION_BY_ID)
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") int id) {
        try {
            Organization organization = organizationService.getOrganizationById(id);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
