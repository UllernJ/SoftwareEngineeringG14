import {TourService} from "../service/TourService";
import {Tour} from "../types/global";
import {useEffect, useState} from "react";

export const ToursPage = () => {
    const [toursArr, setToursArr] = useState<Tour[]>([]);

    useEffect(() => {
        TourService.getTours().then(res => {
            setToursArr(res);
            console.log(res)
        });
    }, []);

    return (
        <>
            <div className="container mt-5">
                <div className="row justify-content-center">
                    {toursArr.map((tour: Tour) => (
                        <div key={tour.id} className="col-md-4 mb-4">
                            <div className="card">
                                <img src={tour.image} className="card-img-top" alt={tour.name} />
                                <div className="card-body">
                                    <h5 className="card-title">{tour.name}</h5>
                                    <p className="card-text">{tour.description}</p>
                                    <ul className="list-group list-group-flush">
                                        <li className="list-group-item">Price: {tour.price} euro</li>
                                        <li className="list-group-item">Duration: {tour.durationHours} hours</li>
                                        <li className="list-group-item">Max Capacity: {tour.maxCapacity}</li>
                                        <li className="list-group-item">Date: {new Date(tour.date).toLocaleDateString()}</li>
                                    </ul>
                                </div>
                                <div className="card-footer">
                                    <a href={tour.organization.website} className="btn btn-primary">Visit Organization</a>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};