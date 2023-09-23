import {Tour} from "../types/global";
import {getUser} from "./UserService";

export const TourService = {
    getTours: async (): Promise<Tour[]> => {
        const url: string = 'http://localhost:8080/api/tour/all';
        const options: RequestInit = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return fetch(url, options)
            .then(response => response.json());
    },
    getToursByUserId: async (id: number): Promise<Tour[]> => {
        const url: string = 'http://localhost:8080/api/tour/all/user/' + id;
        const options: RequestInit = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return fetch(url, options)
            .then(response => response.json());
    },

    addAttendee: async (tourId: number): Promise<Response> => {
        const url: string = "http://localhost:8080/api/tour/add/user"
        const body = {
            tourId: tourId,
            userId: getUser().id
        }
        const options: RequestInit = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }
        return fetch(url, options)
            .then(response => response);
    },

    // removeAttendingUser: async (tourId: number): Promise<any> => {
    //     const url: string = "http://localhost:8080/api/tour/remove/user"
    //     const body = {
    //         tourId: tourId,
    //         userId: getUser().id
    //     }
    //     const options: RequestInit = {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(body)
    //     }
    //     return fetch(url, options)
    //         .then(response => response.json());
    // }

}