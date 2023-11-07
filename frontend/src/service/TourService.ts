import {Tour} from "../types/global";
import {getUser} from "./UserService";
import {TourEditRequest} from "../pages/TourEditPage";
import {CreateTourRequest} from "../pages/CreateTourPage";

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

    removeAttendingUser: async (tourId: number): Promise<Response> => {
        const url: string = "http://localhost:8080/api/tour/remove/user"
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

    removeTour: async (tourId: number): Promise<Response> => {
        if (getUser().role !== "ADMIN") return new Response(
            JSON.stringify({message: "You are not authorized to delete this tour"}),
            {
                status: 401
            }
        );
        const url: string = "http://localhost:8080/api/tour/" + tourId;
        const options: RequestInit = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return fetch(url, options)
            .then(response => response);
    },

    getTourById: async (tourId: number): Promise<Tour> => {
        const url: string = 'http://localhost:8080/api/tour/' + tourId;
        const options: RequestInit = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return fetch(url, options)
            .then(response => response.json());
    },

    updateTour: async (tour: TourEditRequest): Promise<Response> => {
        const url: string = "http://localhost:8080/api/tour/" + tour.id;
        const options: RequestInit = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tour)
        }
        return fetch(url, options)
            .then(response => response);
    },

    createTour: async (tour: CreateTourRequest): Promise<Response> => {
        const url: string = "http://localhost:8080/api/tour";
        const options: RequestInit = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tour)
        }
        return fetch(url, options)
            .then(response => response);
    }

}