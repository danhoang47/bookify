import { Outlet } from "react-router-dom";
import profileLayoutStyles from "./ProfileLayout.module.scss";
import Header from "../components/Header";
import Footer from "../components/Footer";

function ProfileLayout() {
    return (
        <div className={profileLayoutStyles["container"]}>
            <Header />
            <Outlet />
            <Footer />
        </div>
    );
}

export default ProfileLayout;
