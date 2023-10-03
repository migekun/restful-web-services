package com.manavas.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonControler {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Miguel Navas");
    }
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Miguel", "Navas"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Miguel Navas Params request");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam() {
        return new PersonV2(new Name("Miguel", "Navas"));
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeaders() {
        return new PersonV1("Miguel Navas");
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader() {
        return new PersonV2(new Name("Miguel", "Navas"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAccept() {
        return new PersonV1("Miguel Navas");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfAcceot() {
        return new PersonV2(new Name("Miguel", "Navas"));
    }

}
