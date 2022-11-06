import { Suspense } from 'react';
import { Outlet, useParams } from "react-router-dom";
import { Box } from "@mui/material";
import HotelManageHeader from "../components/HotelManageHeader";
import manageLayoutStyles from './HotelManageLayout.module.scss';
import Footer from "../components/Footer";

function HotelManageLayout() {
    const { id } = useParams();

    console.log(id);
    return (  
        <div id={manageLayoutStyles['hotel-manage-layout']}>
            <HotelManageHeader />
            <Box
                sx={{
                    position: "relative",
                    top: "72.78px",
                }}
            >
                <Suspense fallback={<div>Loading...</div>}>
                    <Outlet />
                </Suspense>
            </Box>
            <Footer />
        </div>
    );
}

export default HotelManageLayout;