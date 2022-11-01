import getHotel from "@/services/hotel/getHotel";
import { useEffect, useState, lazy, Suspense } from "react";
import { useParams } from "react-router-dom";

const RegisterSection = lazy(() => import("../Register/RegisterSection"));
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
            {hotelInfor && (
                <Suspense fallback={<div>Loading...</div>}>
                    <RegisterSection
                        hotelId={hotelId}
                        basicHotelInforInitState={hotelInfor.basicHotelInfor}
                        roomInfoInitState={hotelInfor.roomInfor}
                        extraInforInitState={hotelInfor.extraInfor}
                        viewImagesInitState={hotelInfor.viewImages}
                        backgroundImageInitState={hotelInfor.backgroundImg}
                        roomImagesInitState={hotelInfor.roomImages}
                        amenitiesInitState={hotelInfor.hotelAmenities}
                        displayAmenitiesInitState={hotelInfor.hotelAmenities}
                    />
                </Suspense>
            )}
        </div>
    );
}

export default Update;
