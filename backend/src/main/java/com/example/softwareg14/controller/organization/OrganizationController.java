package com.example.softwareg14.controller.organization;

import com.example.softwareg14.controller.Endpoint;
import com.example.softwareg14.controller.Error;
import com.example.softwareg14.model.Organization;
import com.example.softwareg14.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @PostMapping(Endpoint.ORGANIZATION_REGISTER)
    public ResponseEntity<String> organization(@RequestBody OrganizationRequest organizationRequest) {
        List<Error> errors = organizationRequest.validate();
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            Organization organization = Organization.builder()
                    .name(organizationRequest.name)
                    .email(organizationRequest.email)
                    .password(organizationRequest.password)
                    .address(organizationRequest.address)
                    .phone(organizationRequest.phone)
                    .website(organizationRequest.website)
                    .description(organizationRequest.description)
                    .build();
            organizationService.createOrganization(organization);
            return new ResponseEntity<>("Organization registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register organization", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(Endpoint.ORGANIZATION_LOGIN)
    public ResponseEntity<Organization> login(@RequestBody OrganizationRequest organizationRequest) {
        try {
            Organization organization = organizationService
                    .getOrganizationByEmail(organizationRequest.email, organizationRequest.password);
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(Endpoint.ORGANIZATION_DELETE)
    public ResponseEntity<Organization> delete(@PathVariable("id") int id) {
        try {
            organizationService.deleteOrganizationById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Endpoint.ORGANIZATIONS_ALL)
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        try {
            List<Organization> organizations = organizationService.getAllOrganizations();
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(Endpoint.ORGANIZATION_UPDATE)
    public ResponseEntity<String> updateOrganization(@RequestBody Organization organization, @PathVariable("id") int id) {
        try {
            organizationService.updateOrganization(organization);
            return new ResponseEntity<>("Organization updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update organization", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
