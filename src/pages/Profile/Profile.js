import ProfileCard from "./components/card";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import { useNavigate } from "react-router-dom";
import {
  faIdCard,
  faShield,
  faMoneyBills,
  faSliders,
  faHistory,
} from "@fortawesome/free-solid-svg-icons";
import profileStyle from "./Profile.module.scss";
import { useMemo } from "react";

const account = {
  name: "Le Duc",
  email: "duc@gmail.com",
};

function Profile() {
  const navigate = useNavigate();

  const options = useMemo(
    () => [
      {
        icon: faIdCard,
        title: "Thông tin cá nhân",
        description: "Cung cấp thông tin cá nhân cần thiết của bạn",
        onClickHandler: (event) => {
          event.stopPropagation();
          navigate("/profile/info");
        },
      },
      {
        icon: faShield,
        title: "Đăng nhập và bảo mật",
        description: "Cập nhật mật khẩu và bảo mật tài khoản của bạn",
        onClickHandler: (event) => {
          event.stopPropagation();
          navigate("/profile/log&sec");
        },
      },
      {
        icon: faMoneyBills,
        title: "Thanh toán và chi trả",
        description:
          "Tìm hiểu lại các khoản thanh toán, chi trả, phiếu giảm giá, thẻ quà tặng",
      },
      {
        icon: faSliders,
        title: "Lựa chọn chung",
        description: "Cài đặt ngôn ngữ, loại tiền tệ mặc định của bạn",
      },
      {
        icon: faHistory,
        title: "Lịch sử đặt phòng",
        description: "Xem lịch sử các phòng bạn đã đặt hay hủy bỏ",
      },
    ],
    []
  );

  return (
    <div className={profileStyle["container"]}>
      <h1 className={profileStyle["account"]}>Tài Khoản</h1>
      <h4 className={profileStyle["commonInfo"]}>
        {account.name}, {account.email}{" "}
        <a href="https://www.nettruyenme.com/truyen-tranh/tro-choi-cua-chua-thuong/chap-1/902267">
          Thay đổi hồ sơ
        </a>
      </h4>
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={4}>
          {options.map((option, index) => {
            return (
              <Grid item xs={12} md={4} key={index}>
                <ProfileCard
                  title={option.title}
                  icon={option.icon}
                  description={option.description}
                  onClick={option.onClickHandler}
                />
              </Grid>
            );
          })}
        </Grid>
      </Box>
    </div>
  );
}

export default Profile;
