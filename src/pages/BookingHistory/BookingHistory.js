import Header from "./components/HeaderInfo";
import Body from "./components/Body";
import { HistoryContext } from "@/utils/contexts";
import { useMemo } from "react";
import BookingHistoryStyle from "./BookingHistory.module.scss";
function BookingHistory() {
  const list = useMemo(
    () => [
      {
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
        room: "224",
        hotel: "Delaware",
        address: "123 Đỗ Tấn - Hội An - Quảng Nam",
        adult: "2",
        price: "370",
        status: true,
        checkinDate: "16/8/2022",
        checkoutDate: "20/8/2022",
      },
    ],
    []
  );
  return (
    <div className={BookingHistoryStyle["container"]}>
      <Header />
      <HistoryContext.Provider value={list}>
        <Body />
      </HistoryContext.Provider>
    </div>
  );
}
export default BookingHistory;
