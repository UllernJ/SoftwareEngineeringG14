import {User} from "../types/global";

export const isUserLoggedIn = (): boolean => {
    return !!sessionStorage.getItem('user');
}
export const getUser = (): User => {
    return JSON.parse(sessionStorage.getItem('user') || '{}');
}