export const userLogin = async (username: string, password: string): Promise<Response> => {
    const API_URL = "http://localhost:8080/api/user/login";
    return await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username, password})
    });
}

export const organizationLogin = async (email: string, password: string): Promise<Response> => {
    const API_URL = "http://localhost:8080/api/organization/login";
    return await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, password})
    });
}