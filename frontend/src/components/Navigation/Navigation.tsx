import { Routes, Route } from 'react-router-dom'
import {Home} from "../../pages/Home";
import {RegisterUserPage} from "../../pages/RegisterUserPage";
import {RegisterOrganizationPage} from "../../pages/RegisterOrganizationPage";
import {ToursPage} from "../../pages/ToursPage";
import {Login} from "../Login";
import {OrganizationPage} from "../../pages/OrganizationPage";
import {TourPage} from "../../pages/TourPage";
import {TourEditPage} from "../../pages/TourEditPage";

export const Navigation = () => {

    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register/user" element={<RegisterUserPage/>} />
            <Route path="/register/organization" element={<RegisterOrganizationPage/>} />
            <Route path="/login" element={<Login />} />
            <Route path="/tours" element={<ToursPage />} />
            <Route path="/organization/:id" element={<OrganizationPage />} />
            <Route path="/tour/:id" element={<TourPage />} />
            <Route path="/tour/:id/edit" element={<TourEditPage />} />
        </Routes>
    )
}