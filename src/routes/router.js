import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import {
  Home,
  Profile,
  PersonalInfo,
  LoginandSecurity,
  BookingHistory,
  Introduction,
  HotelManageMain,
  HotelManageBooking,
  HotelManageDetail,
  Dashboard,
  Payment,
  CheckOut,
} from "@/pages";
import DefaultLayout from "@/layouts/DefaultLayout";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<DefaultLayout />}>
        <Route path="" element={<Home />} />
        <Route path="profile">
          <Route path="" element={<Profile />} />
          <Route path="info" element={<PersonalInfo />} />
          <Route path="loginandsecurity" element={<LoginandSecurity />} />
          <Route path="history" element={<BookingHistory />} />
          <Route path="payment" element={<Payment />} />
        </Route>
      </Route>
      <Route path="hotelmanage" element={<DefaultLayout />}>
        <Route path="" element={<HotelManageMain />} />
        <Route path="detail" element={<HotelManageDetail />} />
        <Route path="booking" element={<HotelManageBooking />} />
      </Route>

      <Route path="introduction" element={<DefaultLayout />}>
        <Route path="" element={<Introduction />} />
      </Route>
      <Route path="dashboard" element={<DefaultLayout />}>
        <Route path="" element={<Dashboard />} />
      </Route>
      <Route path="checkout" element={<DefaultLayout />}>
        <Route path="" element={<CheckOut />} />
      </Route>
      {/* <Route path="payment" element={<DefaultLayout />}>
        <Route path="" element={<Payment />} />
      </Route> */}
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
