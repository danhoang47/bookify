import RegisterSection from "../Register/RegisterSection";
import getHotel from "@/services/hotel/getHotel";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function Update() {
    const { hotelId } = useParams();
    const [hotelInfor, setHotelInfor] = useState(null);

    useEffect(() => {
        getHotel(hotelId).then((data) => {
            setHotelInfor(data);
        });
        //eslint-disable-next-line
    }, []);

    return (
        <div>
            {   hotelInfor && (
                <RegisterSection 
                    basicHotelInforInitState={hotelInfor.basicHotelInfor}
                    roomInfoInitState={hotelInfor.roomInfor}
                    extraInforInitState={hotelInfor.extraInfor}
                    viewImagesInitState={hotelInfor.viewImages}
                    backgroundImageInitState={hotelInfor.backgroundImage}
                    roomImagesInitState={hotelInfor.roomImages}
                    amenitiesInitState={hotelInfor.hotelAmenities}
                    displayAmenitiesInitState={hotelInfor.hotelAmenities}
                />)}
        </div>
    );
}

export default Update;
