import {Tour} from "../types/global";

export const TourService = {
    getTours: async (): Promise<Tour[]> => {
        const url: string = 'http://localhost:8080/api/tours/all';
        const options: RequestInit = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return fetch(url, options)
            .then(response => response.json());
    }
}