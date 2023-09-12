import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getOrganization} from "../service/OrganizationService";
import {Organization} from "../types/global";

export const OrganizationPage = () => {
    const [organization, setOrganization] = useState<Organization>();

    const { id } = useParams();
    useEffect(() => {
        getOrganization(Number(id)).then(res => {
            setOrganization(res);
        })
    }, [id]);

    return (
        <div className="container mt-5">
            <div className="card">
                <div className="card-header bg-primary text-white">
                    <h1 className="mb-0">Organization Details</h1>
                </div>
                <div className="card-body">
                    {organization ? (
                        <>
                            <h2 className="card-title">{organization.name}</h2>
                            <p className="card-text">{organization.description}</p>
                            <address className="mb-3">
                                <strong>Address:</strong> {organization.address}
                            </address>
                            <p>
                                <strong>Phone:</strong> {organization.phone}
                            </p>
                            <p>
                                <strong>Email:</strong>{' '}
                                <a href={`mailto:${organization.email}`}>
                                    {organization.email}
                                </a>
                            </p>
                            <p>
                                <strong>Website:</strong>{' '}
                                <a
                                    href={organization.website}
                                    target="_blank"
                                    rel="noopener noreferrer">
                                    {organization.website}
                                </a>
                            </p>
                        </>
                    ) : (
                        <p>Loading organization data...</p>
                    )}
                </div>
            </div>
        </div>
    );
}