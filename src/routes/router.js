import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Routes,
} from "react-router-dom";
import {
  Home,
  Profile,
  PersonalInfo,
  LoginandSecurity,
  BookingHistory,
  Tabs,
  Introduction,
  HotelManage,
  HotelManageDetail,
  Dashboard,
  Payment,
} from "@/pages";
import DefaultLayout from "@/layouts/DefaultLayout";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<DefaultLayout />}>
        <Route path="" element={<Home />} />
<<<<<<< HEAD
        <Route path="profile">
          <Route path="" element={<Profile />} />
          <Route path="info" element={<PersonalInfo />} />
          <Route path="loginandsecurity" element={<LoginandSecurity />} />
          <Route path="history" element={<BookingHistory />}>
            <Route path="" element={<Tabs />} />
            <Route path="today" element={<Tabs />} />
            <Route path="cancel" element={<Tabs />} />
            <Route path="booked" element={<Tabs />} />
          </Route>
        </Route>

        <Route path="hotelmanage">
          <Route path="" element={<HotelManage />} />
          <Route path="detail" element={<HotelManageDetail />} />
        </Route>
=======
      </Route>
      <Route path="profile" element={<DefaultLayout />}>
        <Route path="" element={<Profile />} />
        <Route path="info" element={<PersonalInfo />} />
        <Route path="payment" element={<Payment />} />
>>>>>>> a25a1b69d065e5ec31b6e2f7825f4c405f11e042
      </Route>
      <Route path="introduction" element={<DefaultLayout />}>
        <Route path="" element={<Introduction />} />
      </Route>
<<<<<<< HEAD
=======
      <Route path="hotelmanage" element={<DefaultLayout />}>
        <Route path="" element={<HotelManage />} />
        <Route path="detail" element={<HotelManageDetail />} />
      </Route>
      <Route path="dashboard" element={<DefaultLayout />}>
        <Route path="all" element={<Dashboard />} />
      </Route>
>>>>>>> a25a1b69d065e5ec31b6e2f7825f4c405f11e042
    </Route>
  )
);

export default router;

// / -> <Home />
//         /profile => <Profile />
// /hosting/register -> <Register />
// /hosting -> <Hosting />
//         /today -> <Today />
//         /insight -> <Insight />
//             /reviews -> <Reviews />
//             /earnings -> <Earnings />
//             /views -> <Views />
//             /reservation -> <Reservation />
//             /transactions -> <Transactions />
// /dashboard -> <Dashboard />
//         /listing -> <Listing />
//             /all
//             /pending
//             /onstream
