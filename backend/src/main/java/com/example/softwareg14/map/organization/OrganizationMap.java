package com.example.softwareg14.map.organization;

import com.example.softwareg14.entity.Organization;
import com.example.softwareg14.entity.User;
import com.example.softwareg14.map.Endpoint;
import com.example.softwareg14.map.Error;
import com.example.softwareg14.map.user.UserRequest;
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
        List<Error> errors = organizationRequest.validate();
        if(!errors.isEmpty()) {
            return new ResponseEntity(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            organizationService.createOrganization(organizationRequest.name, organizationRequest.address, organizationRequest.phone, organizationRequest.website, organizationRequest.description, organizationRequest.password, organizationRequest.email);
            return new ResponseEntity("Organization registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Failed to register organization", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.ORGANIZATION_LOGIN)
    public ResponseEntity<Organization> login(@RequestBody OrganizationRequest organizationRequest) {
        try {
            if(organizationService.validateOrganization(organizationRequest.email, organizationRequest.password)) {
                Organization organization = organizationService.getOrganizationByEmail(organizationRequest.email);
                return new ResponseEntity<>(organization, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(Endpoint.ORGANIZATION_DELETE)
    public ResponseEntity<Organization> delete(@RequestBody OrganizationRequest organizationRequest) {
        try {
            if(organizationService.validateOrganization(organizationRequest.email, organizationRequest.password)) {
                Organization organization = organizationService.getOrganizationByEmail(organizationRequest.email);
                organizationService.deleteOrganizationById(organization.getId());
                return new ResponseEntity<>(organization, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
    @PostMapping(Endpoint.ORGANIZATION_UPDATE)
    public ResponseEntity<String> updateOrganization(@RequestBody Organization organization) {
        try {
            organizationService.updateOrganization(organization);
            return new ResponseEntity<>("Organization updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update organization", HttpStatus.INTERNAL_SERVER_ERROR);
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
