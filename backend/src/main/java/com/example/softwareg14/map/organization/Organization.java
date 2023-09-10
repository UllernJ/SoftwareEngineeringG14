package com.example.softwareg14.map.organization;

import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Organization {

    private final OrganizationService organizationService;

    @Autowired
    public Organization(OrganizationService organizationService) {
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
}
