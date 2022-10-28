import RegisterSection from "../Register/RegisterSection";
import getHotel from "@/services/hotel/getHotel";
import { useEffect, useState} from "react";
import { useParams } from "react-router-dom";

function Update() {
    const { hotelId } = useParams();
    const [hotelInfor, setHotelInfor] = useState();
    console.log(hotelId);

    useEffect(() => {
        getHotel(hotelId).then(data => {
            setHotelInfor(data);
        });
    //eslint-disable-next-line
    }, [])

    console.log(hotelInfor);
    return (  
        <div>
            
        </div>
    );
}

export default Update;