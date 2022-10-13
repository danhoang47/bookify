import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import {
  Home,
  Profile,
  PersonalInfo,
  Introduction,
  HotelManage,
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
      </Route>
      <Route path="introduction" element={<DefaultLayout />}>
        <Route path="" element={<Introduction />} />
      </Route>
      <Route path="hotelmanage" element={<DefaultLayout />}>
        <Route path="" element={<HotelManage />} />
        <Route path="detail" element={<HotelManageDetail />} />
      </Route>
      <Route path="dashboard" element={<DefaultLayout />}>
        <Route path="" element={<Dashboard />} />
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
// /host/register -> <Register />
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
