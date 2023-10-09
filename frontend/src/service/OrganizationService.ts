import {Organization} from "../types/global";

export const getOrganization = async (id: number): Promise<Organization> => {
    const API_URL = "http://localhost:8080/api/organization/" + id;
    const response = await fetch(API_URL);
    const organization = await response.json();
    return organization;
}

export const isOrganizationLoggedIn = (): boolean => {
    return !!sessionStorage.getItem('organization');
}

export const getOrganizationId = (): number => {
    return JSON.parse(sessionStorage.getItem('organization')!).id;
}


