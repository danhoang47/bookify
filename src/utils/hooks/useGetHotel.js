import { useState } from "react";
import { useMutation, useQuery } from "@tanstack/react-query";
import {
  GetHotel,
  getHotelType,
  bookingRoom,
  GetHotels,
} from "@/services-new/hotel";
export default function useGetHotel() {
  const [hotels, setHotels] = useState([]);
  const [hotel, setHotel] = useState({});
  const [filter, setFilter] = useState();
  const hotelsQuery = useQuery({
    queryKey: ["gethotels"],
    queryFn: GetHotels,
    onSuccess: (data) => {
      // console.log(data.hotels);
      setHotels(data.hotels);
    },
  });
  const { data: hotelTypes } = useQuery({
    queryKey: ["hoteltype"],
    queryFn: getHotelType,
  });
  const { mutate: getHotelbyId, isSuccess } = useMutation({
    mutationKey: ["mutate"],
    mutationFn: (id) => GetHotel(id),
    onSuccess: (data) => {
      setHotel(data.hotel);
      // console.log(data);
    },
  });
  const { mutate: bookingHotel } = useMutation({
    mutationKey: ["booking hotel"],
    mutationFn: ({ selectDays, guests, hotelId, price }) =>
      bookingRoom(selectDays, guests, hotelId, price),
    enabled: isSuccess,
  });
  return {
    hotelsQuery,
    setFilter,
    hotel,
    hotels,
    setHotel,
    getHotelbyId,
    bookingHotel,
    hotelTypes,
  };
}
