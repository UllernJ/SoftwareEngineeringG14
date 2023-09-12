import {TourService} from "../service/TourService";
import {Tour} from "../types/global";
import {useEffect, useState} from "react";
import {getUser} from "../service/UserService";

export const ToursPage = () => {
    const [toursArr, setToursArr] = useState<Tour[]>([]);
    const user = getUser();

    useEffect(() => {
        TourService.getTours().then(res => {
            setToursArr(res);
        });
    }, []);

    const addAttendee = (tourId: number) => {
        console.log(tourId)
        console.log(user.id)
    }

    return (
        <>
            <div className="container mt-5">
                <div className="row justify-content-center">
                    {toursArr.map((tour: Tour) => (
                        <div className="col-md-4 mb-4" key={tour.id}>
                            <div className="card">
                                <div style={{ height: '250px', overflow: 'hidden' }}>
                                <img src={tour.image} className="card-img-top" alt={tour.name} />
                                </div>
                                <div className="card-body">
                                    <h5 className="card-title">{tour.name}</h5>
                                    <p className="card-text">{tour.description}</p>
                                </div>
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">Price: {tour.price} euro</li>
                                    <li className="list-group-item">Duration: {tour.durationHours} hours</li>
                                    <li className="list-group-item">Participants: {tour.usersAttending.length}/{tour.maxCapacity}</li>
                                    <li className="list-group-item">Date: {new Date(tour.date).toLocaleDateString()}</li>
                                </ul>
                                <div className="card-footer">
                                    <button className="btn btn-primary" onClick={() => addAttendee(tour.id)}>Attend</button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};