import { Logo } from "@/components";
import ProfileHeaderNav from "../ProfileHeaderNav";
import headerStyles from "./HotelManageHeader.module.scss";
import { Link } from "react-router-dom";
import { Grid } from "@mui/material";

const tabs = [
    {
        title: "Khách sạn",
        to: "",
    },
    {
        title: "Đặt phòng",
        to: "booking",
    },
    {
        title: "Chi tiết",
        to: "detail",
    },
];

function HotelManageHeader() {
    return (
        <div id={headerStyles["hotel-manage-header"]}>
            <Grid container alignItems={"center"} justifyContent={"center"}>
                <Grid item md={2}>
                    <Logo />
                </Grid>
                <Grid item md={6}>
                    <div className={headerStyles["hotel-manage-tabs"]}>
                        {tabs.map(({ title, to }, index) => (
                            <Link key={index} to={to}>
                                <p className={headerStyles["link-to"]}>{title}</p>
                            </Link>
                        ))}
                    </div>
                </Grid>
                <Grid item md={2}>
                    <ProfileHeaderNav />
                </Grid>
            </Grid>
        </div>
    );
}

export default HotelManageHeader;
