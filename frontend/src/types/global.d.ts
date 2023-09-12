export type User = {
    id: number;
    name: string;
    username: string;
    email: string;
}

export type Organization = {
    id: number;
    name: string;
    description: string;
    address: string;
    phone: string;
    email: string;
    website: string;
}

export type Tour = {
    id: number;
    name: string;
    description: string;
    price: number;
    durationHours: number;
    image: string;
    organization: Organization;
    maxCapacity: number;
    date: Date;
}