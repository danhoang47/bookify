import { Logo } from "@/components";
import ProfileHeaderNav from "../ProfileHeaderNav";
import headerStyles from './HotelManageHeader.module.scss';
import { Link } from "react-router-dom";

const tabs = [
    {
        title: 'Khách sạn',
        to: ''
    },
    {
        title: 'Đặt phòng',
        to: 'booking'
    }, 
    {
        title: 'Chi tiết',
        to: 'detail'
    }
]

function HotelManageHeader() {


    return (  
        <div id={headerStyles['hotel-manage-header']}>
            <Logo />
            <div className={headerStyles['hotel-manage-tabs']}>
                {
                    tabs.map(({ title, to }, index) => (
                        <Link key={index} to={to}>
                            <p className={headerStyles['link-to']}>
                                {title}
                            </p>
                        </Link>
                    ))
                }
            </div>
        </div>
    );
}

export default HotelManageHeader;