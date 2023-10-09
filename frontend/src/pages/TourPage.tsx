import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {TourService} from "../service/TourService";
import {Tour} from "../types/global";

export const TourPage = () => {
    const { id } = useParams();
    const [tour, setTour] = useState<Tour>();

    useEffect(() => {
        TourService.getTourById(Number(id)).then(res => {
            setTour(res);
        })
    }, [id]);

    return (
        <div className="container mt-5">
            <div className="card">
                <div className="card-header bg-primary text-white">
                    <h1 className="mb-0">Tour Details</h1>
                </div>
                <div className="card-body">
                    {tour ? (
                        <>
                            <h2 className="card-title">{tour.name}</h2>
                            <div style={{ height: '250px', width: '50%', overflow: 'hidden' }}>
                                <img src={tour.image} className="card-img-top" alt={tour.name} />
                            </div>
                            <p className="card-text"><strong>Description: </strong>{tour.description}</p>
                            <address className="mb-3">
                                <strong>Address:</strong> {tour.organization.address}
                            </address>
                            <p>
                                <strong>Phone:</strong> {tour.organization.phone}
                            </p>
                            <p>
                                <strong>Email:</strong>{' '}
                                <a href={`mailto:${tour.organization.email}`}>{tour.organization.email}</a>
                            </p>
                            <p>
                                <strong>Website:</strong>{' '}
                                <a
                                    href={tour.organization.website}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                >
                                    {tour.organization.website}
                                </a>
                            </p>
                        </>
                    ) : (
                        <p>Loading tour data...</p>
                    )}
                </div>
            </div>
        </div>
    );
}