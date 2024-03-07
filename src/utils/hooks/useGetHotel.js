import { useState } from "react";
import { useMutation, useQuery } from "@tanstack/react-query";
import {
  GetHotel,
  getHotelType,
  bookingRoom,
  GetHotels,
} from "@/services-new/hotel";
export default function useGetHotel() {
  const [hotel, setHotel] = useState({});
  const [selectDay, setSelectDay] = useState([]);
  const { data: hotelTypes } = useQuery({
    queryKey: ["hoteltype"],
    queryFn: getHotelType,
  });
  const { mutate: getHotelbyId, isSuccess } = useMutation({
    mutationKey: ["mutate"],
    mutationFn: (id) => GetHotel(id),
    onSuccess: (data) => {
      setHotel(data.hotel);
      setSelectDay(data.fullyBookedDates);
      // console.log(data);
    },
  });
  const { mutate: bookingHotel } = useMutation({
    mutationKey: ["booking hotel"],
    mutationFn: ({ selectDays, guests, hotelId, price }) =>
      bookingRoom(selectDays, guests, hotelId, price),
    enabled: isSuccess,
  });
  return { selectDay, hotel, setHotel, getHotelbyId, bookingHotel, hotelTypes };
}
