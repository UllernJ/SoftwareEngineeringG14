import {TourService} from "../service/TourService";
import {Tour} from "../types/global";
import {useEffect, useState} from "react";
import {getUser, isUserLoggedIn} from "../service/UserService";
import {Link} from "react-router-dom";
import {getOrgToken, isOrganizationLoggedIn} from "../service/OrganizationService";

export const ToursPage = () => {
    const [toursArr, setToursArr] = useState<Tour[]>([]);
    const [attendingTours, setAttendingTours] = useState<Tour[]>([]);
    const [hoveredTourId, setHoveredTourId] = useState<number | null>(null);
    const user = getUser();
    const org = getOrgToken();

    useEffect(() => {
        if (!isUserLoggedIn() && !isOrganizationLoggedIn()) {
            window.location.href = "/";
        }
        if (toursArr.length > 0) {
            return;
        }
        TourService.getTours().then(res => {
            setToursArr(res);
        });

        if (isUserLoggedIn()) {
            TourService.getToursByUserId(user.id).then(res => {
                setAttendingTours(res);
            });
        }
    }, [user.id, toursArr.length]);

    const isAttending = (tourId: number) => {
        return attendingTours.some(tour => tour.id === tourId);
    }

    const addAttendee = (tourId: number) => {
        TourService.addAttendee(tourId).then(res => {
            if (res.status === 200) {
                setToursArr([]);
            }
        });
    }

    const removeAttendee = (tourId: number) => {
        TourService.removeAttendingUser(tourId).then(res => {
            if (res.status === 200) {
                setToursArr([]);
            }
        });
    }

    const removeTour = (tourId: number) => {
        if (user.role === "ADMIN") {
            TourService.removeTour(tourId).then(res => {
                if (res.status === 200) {
                    setToursArr(toursArr.filter(tour => tour.id !== tourId));
                }
            });
        }
    }

    return (
        <>
            <div className="container mt-5">
                <div className="row justify-content-center">
                    {toursArr.map((tour: Tour) => (
                        <div className="col-md-4 mb-4" key={tour.id}>
                            <div className="card">
                                <div style={{height: '250px', overflow: 'hidden'}}>
                                    <img src={tour.image} className="card-img-top" alt={tour.name}/>
                                </div>
                                <div className="card-body">
                                    <Link to={"/tour/" + tour.id}><h5 className="card-title">{tour.name}</h5></Link>
                                    <p className="card-text">{tour.description}</p>
                                </div>
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">Price: {tour.price} euro</li>
                                    <li className="list-group-item">Duration: {tour.durationHours} hours</li>
                                    <li className="list-group-item">Participants: {tour.attendingUsers}/{tour.maxCapacity}</li>
                                    <li className="list-group-item">Date: {tour.date}</li>
                                    <li className="list-group-item">Hosted by: {tour.organization.name}</li>
                                </ul>
                                <div className="card-footer d-flex justify-content-between">
                                    {!isOrganizationLoggedIn() && (
                                        <>
                                            {isUserLoggedIn() && isAttending(tour.id) ? (
                                                <button
                                                    className={`btn btn-primary ${hoveredTourId === tour.id ? 'custom-hover' : ''}`}
                                                    onClick={() => removeAttendee(tour.id)}
                                                    onMouseEnter={() => setHoveredTourId(tour.id)} // Set the currently hovered tour
                                                    onMouseLeave={() => setHoveredTourId(null)} // Reset when mouse leaves
                                                >
                                                    {hoveredTourId === tour.id ? 'Remove' : 'Attending'}
                                                </button>
                                            ) : (
                                                <button
                                                    className="btn btn-primary"
                                                    onClick={() => addAttendee(tour.id)}
                                                >
                                                    Attend
                                                </button>
                                            )}
                                        </>
                                    )}
                                    {(org.id === tour.organization.id || user.role === "ADMIN") && (
                                        <Link to={"/tour/" + tour.id + "/edit"} className="btn btn-primary">Edit</Link>
                                    )}

                                    <Link to={"/organization/" + tour.organization.id}
                                          className="btn btn-primary">Organization</Link>
                                    {(org.id === tour.organization.id || user.role === "ADMIN") && (
                                        <button
                                            className="btn btn-danger"
                                            onClick={() => removeTour(tour.id)}
                                        >
                                            Delete
                                        </button>
                                    )}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};