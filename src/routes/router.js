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
      </Route>
      <Route path="profile" element={<DefaultLayout />}>
        <Route path="" element={<Profile />} />
        <Route path="info" element={<PersonalInfo />} />
        <Route path="payment" element={<Payment />} />
        <Route path="loginandsecurity" element={<LoginandSecurity />} />
        <Route path="history" element={<BookingHistory />} />
      </Route>
      <Route path="introduction" element={<DefaultLayout />}>
        <Route path="" element={<Introduction />} />
      </Route>
      <Route path="hotelmanage" element={<DefaultLayout />}>
        <Route path="" element={<HotelManageMain />} />
        <Route path="detail" element={<HotelManageDetail />} />
        <Route path="booking" element={<HotelManageBooking />} />
      </Route>
      <Route path="dashboard" element={<DefaultLayout />}>
        <Route path="all" element={<Dashboard />} />
      </Route>
      <Route path="checkout" element={<DefaultLayout />}>
        <Route path="" element={<CheckOut />} />
      </Route>
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
