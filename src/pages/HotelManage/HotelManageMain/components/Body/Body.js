import BodyStyle from "../../HotelManage.module.scss";
import { Link } from "react-router-dom";
import Grid from "@mui/material/Grid";
import { useState, useMemo } from "react";
import { ModalContext } from "@/utils/contexts";
import { useContext } from "react";
import Setting from "./Setting";
import BookingList from "./BookingList";
import {
  faPencil,
  faMoneyBill,
  faCalendarWeek,
} from "@fortawesome/free-solid-svg-icons";
import {
  getHotelSettingModal,
  getSignInModal,
} from "@/utils/reducers/modalReducer";

function Body({ hotel }) {
  const { dispatch } = useContext(ModalContext);

  const hotelexample = {
    name: "Khách sạn Vin Pearl Nam Hội An",
    tabs: [
      {
        username: "Vibha",
        checkindate: "6 June",
        checkoutdate: "11 June",
        roomtype: "Phòng đơn",
        adults: 3,
        children: 2,
        status: 1,
        avatar: "https://variety.com/wp-content/uploads/2021/04/Avatar.jpg",
      },
      {
        username: "Vibha",
        checkindate: "7 June",
        checkoutdate: "12 June",
        roomtype: "Phòng đôi",
        adults: 3,
        children: 2,
        status: 2,
        avatar: "https://variety.com/wp-content/uploads/2021/04/Avatar.jpg",
      },
      {
        username: "Vibha",
        checkindate: "6 June",
        checkoutdate: "12 June",
        roomtype: "Phòng đôi",
        adults: 3,
        children: 2,
        status: 3,
        avatar: "https://variety.com/wp-content/uploads/2021/04/Avatar.jpg",
      },
    ],
  };
  const options = useMemo(
    () => [
      {
        icon: faPencil,
        title: "Thay đổi cài đặt khách sạn",
        modal: getHotelSettingModal({ isOpen: true }),
      },
      {
        icon: faMoneyBill,
        title: "Tinh chỉnh giá phòng",
        modal: getSignInModal({ isOpen: true }),
      },
      {
        icon: faCalendarWeek,
        title: "Cập nhật trạng thái khả dụng",
        modal: getSignInModal({ isOpen: true }),
      },
    ],
    []
  );

  return (
    <div className={BodyStyle["body"]}>
      <div className={BodyStyle["body-header"]}>
        <h2>Lịch đặt phòng hôm nay</h2>
        <Link to="booking">
          Tất cả các đơn <span>({hotelexample.tabs.length})</span>
        </Link>
      </div>
      <BookingList width="30rem" bookList={hotelexample} />
      <div className={BodyStyle["change-setting"]}>
        <h2>Thay đổi và chỉnh sửa</h2>
        <Grid container className={BodyStyle["grid-display"]} columnSpacing={8}>
          {options.map((set, index) => (
            <Setting 
              key={index}
              setting={set} 
            />
          ))}
        </Grid>
      </div>
    </div>
  );
}
export default Body;
