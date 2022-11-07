import Header from "./components/HeaderInfo";
import Body from "./components/Body";
import { HistoryContext, UserContext } from "@/utils/contexts";
import { useContext, useEffect, useMemo, useState } from "react";
import BookingHistoryStyle from "./BookingHistory.module.scss";
function BookingHistory() {
  const list = useMemo(
    () => [
      {
        id: "1",
        room: "123",
        hotel: "Del Luna",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "368",
        status: true,
        checkinDate: "16/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "2",
        room: "124",
        hotel: "Del Luna",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "368",
        status: false,
        checkinDate: "15/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "3",
        room: "229",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: false,
        checkinDate: "14/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "4",
        room: "226",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: true,
        checkinDate: "13/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "5",
        room: "225",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: false,
        checkinDate: "16/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "6",
        room: "224",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: true,
        checkinDate: "19/8/2022",
        checkoutDate: "20/8/2022",
      },
      {
        id: "7",
        room: "221",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: true,
        checkinDate: "10/8/2022",
        checkoutDate: "20/8/2022",
      },
    ],
    []
  );

  const { user } = useContext(UserContext);
  const [value, setValue] = useState([]);

  useEffect(() => {
    fetch(
      "http://localhost:8080/bookify/api/user/bookingHistory/" + user.user_id
    )
      .then((res) => res.json())
      .then((result) => setValue(result));
  }, []);

  return (
    <div className={BookingHistoryStyle["container"]}>
      <Header />
      <HistoryContext.Provider value={[value, setValue]}>
        <Body />
      </HistoryContext.Provider>
    </div>
  );
}
export default BookingHistory;
