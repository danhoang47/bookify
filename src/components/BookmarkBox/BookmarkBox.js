import DropdownBox from "../DropdownBox";
import BookmarkItem from "../BookmarkItem";

function BookmarkBox({ bookmarkedHotels, setBookmarkedHotels }) {
    const handleDeleted = (deletedHotelId) => {
        setBookmarkedHotels((list) => {
            return list.filter(({ hotelId }) => hotelId !== deletedHotelId);
        });
    };

    return (
        <DropdownBox
            heading={"Yêu thích"}
            extraButtonTittle={""}
            handleClick={handleDeleted}
            isScrollable={bookmarkedHotels.length > 8}
        >
            {bookmarkedHotels?.map((hotel) => (
                <BookmarkItem 
                    {...hotel} 
                    key={hotel.hotelId}
                    handleDeleted={handleDeleted}
                />
            ))}
        </DropdownBox>
    );
}

export default BookmarkBox;
