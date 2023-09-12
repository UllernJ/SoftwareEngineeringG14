import { Routes, Route } from 'react-router-dom'
import {Home} from "../../pages/Home";
import {RegisterUserPage} from "../../pages/RegisterUserPage";
import {RegisterOrganizationPage} from "../../pages/RegisterOrganizationPage";
import {ToursPage} from "../../pages/ToursPage";
import {Login} from "../Login";
import {OrganizationPage} from "../../pages/OrganizationPage";

export const Navigation = () => {

    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register/user" element={<RegisterUserPage/>} />
            <Route path="/register/organization" element={<RegisterOrganizationPage/>} />
            <Route path="/login" element={<Login />} />
            <Route path="/tours" element={<ToursPage />} />
            <Route path="/organization/:id" element={<OrganizationPage />} />
        </Routes>
    )
}