import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {TourService} from "../service/TourService";
import {Organization, Tour} from "../types/global";
import {getOrganizationId} from "../service/OrganizationService";

export const TourEditPage = () => {
    const { id } = useParams();
    const [tour, setTour] = useState<Tour>();

    const [name, setName] = useState<string>("");
    const [description, setDescription] = useState<string>("");
    const [price, setPrice] = useState<number>(0);
    const [duration, setDuration] = useState<number>(0);
    const [maxGroupSize, setMaxGroupSize] = useState<number>(0);
    const [image, setImage] = useState<string>("");
    const [date, setDate] = useState<string>("");
    const [location, setLocation] = useState<string>("");

    useEffect(() => {
        TourService.getTourById(Number(id)).then(res => {
            setTour(res);
            setName(res.name);
            setDescription(res.description);
            setPrice(res.price);
            setDuration(res.durationHours);
            setMaxGroupSize(res.maxCapacity);
            setImage(res.image);
            setDate(res.date);
            setLocation(res.location);
        });
    }, [id]);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const tour: TourEditRequest = {
            id: Number(id),
            name: name,
            description: description,
            price: price,
            durationHours: duration,
            maxCapacity: maxGroupSize,
            image: image,
            date: date,
            location: location,
        }
        TourService.updateTour(tour).then(res => {
            console.log(res);
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
                    Save Changes
                </button>
            </form>
        </div>

    )
}

export type TourEditRequest = {
    id: number;
    name: string;
    description: string;
    price: number;
    durationHours: number;
    image: string;
    maxCapacity: number;
    date: string;
    location: string;
}