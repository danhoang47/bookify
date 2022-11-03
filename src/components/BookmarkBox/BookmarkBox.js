import DropdownBox from "../DropdownBox";
import BookmarkItem from "../BookmarkItem";

// testing purpose only
const hotel = {
    hotelId: "fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9",
    userId: "123",
    hotelTypeId: "91bb91b4-adcb-45ba-a0bf-1669a4d5878f",
    hotelName: "Khach san Vinpearl Nam Hoi An",
    backgroundImg:
        "http://localhost:8080/bookify/images/hotels/intercontinental-danang-city-3986758374-2x1-1657599743.jpg",
    isVerified: false,
    isAllowPet: false,
    isHasCamera: false,
    description: "This is a nice hotel ",
    country: "Vietnam",
    district: "Thanh pho Dong Hoi",
    city: "Tỉnh Quảng Bình",
    address: "36 Bach Dang",
    closing: "12:30",
    opening: "12:30",
    checkin: "10:30",
    checkout: "12:30",
    price: 0,
    rating: 0,
};

function BookmarkBox({ bookmarkedHotels }) {
    return (
        <DropdownBox heading={"Yêu thích"} extraButtonTittle={"Đánh dấu đã đọc"}>
            <BookmarkItem 
                {...hotel}
            />
        </DropdownBox>
    );
}

export default BookmarkBox;
