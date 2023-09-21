import {Organization, User} from "../types/global";

export const userLogin = async (username: string, password: string): Promise<User> => {
    const API_URL = "http://localhost:8080/api/user/login";
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    });
    return await response.json();
}

export const organizationLogin = async (email: string, password: string): Promise<Organization> => {
    const API_URL = "http://localhost:8080/api/organization/login";
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, password})
    });
    return await response.json();
}