import React, {useState} from "react";
import {Organization, Tour} from "../types/global";
import {TourService} from "../service/TourService";
import {getOrganizationId} from "../service/OrganizationService";

export const CreateTourPage = () => {
    const [tour, setTour] = useState<Tour>();

    const [name, setName] = useState<string>("");
    const [description, setDescription] = useState<string>("");
    const [price, setPrice] = useState<number>(0);
    const [duration, setDuration] = useState<number>(0);
    const [maxGroupSize, setMaxGroupSize] = useState<number>(0);
    const [image, setImage] = useState<string>("");
    const [date, setDate] = useState<string>("");
    const [location, setLocation] = useState<string>("");

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const tour: CreateTourRequest = {
            name: name,
            description: description,
            price: price,
            durationHours: duration,
            maxCapacity: maxGroupSize,
            image: image,
            date: date,
            location: location,
            organization: {
                id: getOrganizationId(),
                name: "",
                description: "",
                address: "",
                phone: "",
                email: "",
                website: "",
            }
        }
        TourService.createTour(tour).then(res => {
            if (res.status === 200) {
                window.location.href = "/tours";
            }
        });
    }

    return (
        <div className="container mt-5">
            <h1>Edit Tour</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Tour Name:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={name}
                        onChange={e => setName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="description">Description:</label>
                    <textarea
                        className="form-control"
                        id="description"
                        name="description"
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                        required
                    >{tour?.description}</textarea>
                </div>
                <div className="form-group">
                    <label htmlFor="price">Price:</label>
                    <input
                        type="number"
                        className="form-control"
                        id="price"
                        name="price"
                        value={price}
                        onChange={e => setPrice(Number(e.target.value))}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="durationHours">Duration (Hours):</label>
                    <input
                        type="number"
                        className="form-control"
                        id="durationHours"
                        name="durationHours"
                        value={duration}
                        onChange={e => setDuration(Number(e.target.value))}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="image">Image URL:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="image"
                        name="image"
                        value={image}
                        onChange={e => setImage(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="maxCapacity">Max Capacity:</label>
                    <input
                        type="number"
                        className="form-control"
                        id="maxCapacity"
                        name="maxCapacity"
                        value={maxGroupSize}
                        onChange={e => setMaxGroupSize(Number(e.target.value))}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="date">Date:</label>
                    <input
                        type="date"
                        className="form-control"
                        id="date"
                        name="date"
                        value={date}
                        onChange={e => setDate(e.target.value)}
                        required
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="location">Location:</label>
                    <input
                        type="text"
                        className="form-control"
                        id="location"
                        name="location"
                        value={location}
                        onChange={e => setLocation(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Create Tour
                </button>
            </form>
        </div>

    )
}

export type CreateTourRequest = {
    name: string;
    description: string;
    price: number;
    durationHours: number;
    image: string;
    maxCapacity: number;
    date: string;
    location: string;
    organization: Organization;
}