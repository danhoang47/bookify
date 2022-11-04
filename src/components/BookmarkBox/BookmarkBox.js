import DropdownBox from "../DropdownBox";
import BookmarkItem from "../BookmarkItem";
import { useState } from "react";

function BookmarkBox({ bookmarkedHotels, handleClick }) {
    const [hotelBookmarkedList, setHotelBookmarkedList] =
        useState(bookmarkedHotels);

    const handleDeleted = (deletedHotelId) => {
        setHotelBookmarkedList((list) => {
            return list.filter(({ hotelId }) => hotelId !== deletedHotelId);
        });
    };

    return (
        <DropdownBox
            heading={"Yêu thích"}
            extraButtonTittle={""}
            handleClick={handleClick}
            isScrollable={hotelBookmarkedList.length > 8}
        >
            {hotelBookmarkedList?.map((hotel) => (
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
