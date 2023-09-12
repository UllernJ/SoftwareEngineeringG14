import {Organization} from "../types/global";

export const getOrganization = async (id: number): Promise<Organization> => {
    const API_URL = "http://localhost:8080/api/organization/" + id;
    const response = await fetch(API_URL);
    const organization = await response.json();
    return organization;
}

