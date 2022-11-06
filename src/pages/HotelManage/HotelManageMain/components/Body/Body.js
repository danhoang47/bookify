import BodyStyle from "../../HotelManage.module.scss";
import { Link, useNavigate, useOutletContext } from "react-router-dom";
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

function Body() {
  const { dispatch } = useContext(ModalContext);
  const navigate = useNavigate();
  const hotelInfo = useOutletContext();

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
        handleClick: () => {
          if (hotelInfo) {
            navigate(`/hosting/update/${hotelInfo.hotelId}`)
          }
        }
      },
      {
        icon: faMoneyBill,
        title: "Tinh chỉnh giá phòng",
        handleClick: () => {
          if (hotelInfo) {
            navigate(`/hosting/update/${hotelInfo.hotelId}`)
          }
        }
      },
      {
        icon: faCalendarWeek,
        title: "Cập nhật trạng thái khả dụng",
        handleClick: () => {
          if (hotelInfo) {
            navigate(`/hosting/update/${hotelInfo.hotelId}`)
          }
        }
      },
    ],
    [hotelInfo]
  );

  return (
    <Grid container justifyContent={'center'}>
      <Grid item xs={10}>
        <div className={BodyStyle["body"]}>
          <div className={BodyStyle["body-header"]}>
            <h3>Lịch đặt phòng hôm nay</h3>
          </div>
          <BookingList width="30rem" bookList={hotelexample} />
          <div className={BodyStyle["change-setting"]}>
            <h3>Thay đổi và chỉnh sửa</h3>
            <Grid container className={BodyStyle["grid-display"]} spacing={2}>
              {options.map((setting, index) => (
                <Setting 
                  key={index}
                  setting={setting} 
                />
              ))}
            </Grid>
          </div>
        </div>
      </Grid>
    </Grid>
  );
}
export default Body;
