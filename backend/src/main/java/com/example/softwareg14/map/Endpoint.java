package com.example.softwareg14.map;

public class Endpoint {
    private static final String apiPath = "/api/";
    public static final String USER_REGISTER =  apiPath + "user/register";
    public static final String USER_LOGIN = apiPath + "user/login";
    public static final String USER_BY_ID = apiPath + "user/{id}";
    private static final String UPDATE_USER = apiPath + "user/update";
    public static final String USER_DELETE = apiPath + "user/delete";
    public static final String ORGANIZATION_REGISTER = apiPath +  "organization/register";
    public static final String ORGANIZATION_LOGIN = apiPath + "organization/login";
    public static final String ORGANIZATIONS_ALL = apiPath + "organizations/all";
    public static final String ORGANIZATION_BY_ID = apiPath + "organization/{id}";
    public static final String ORGANIZATION_DELETE = apiPath + "organization/delete/{id}";
    public static final String ORGANIZATION_UPDATE = apiPath + "organization/update";
    public static final String TOURS_ALL = apiPath + "tour/all";
    public static final String CREATE_TOUR = apiPath + "tour/create";
    public static final String TOUR_REGISTER_ATTENDEE = apiPath + "tour/register";
    public static final String TOUR_UNREGISTER_ATTENDEE = apiPath + "tour/unregister";
    public static final String TOUR_BY_ID = apiPath + "tour/{id}";
    public static final String TOURS_BY_ORGANIZATION_ID = apiPath + "tours/organization/{id}";
    public static final String TOURS_BY_USER_ID = apiPath + "tours/user/{id}";

    public static final String ADD_USER_TO_TOUR = apiPath + "tour/add/user";

}