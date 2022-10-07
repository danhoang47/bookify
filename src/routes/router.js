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
  HotelManage,
} from "@/pages";
import DefaultLayout from "@/layouts/DefaultLayout";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<DefaultLayout />}>
        <Route path="" element={<Home />} />
        <Route path="profile" element={<Profile />}>
          <Route path="info" element={<PersonalInfo />} />
          <Route path="log&sec" element={<LoginandSecurity />} />
          <Route path="history" element={<BookingHistory />}>
            <Route path="" element={<Tabs />} />
            <Route path="today" element={<Tabs />} />
            <Route path="cancel" element={<Tabs />} />
            <Route path="booked" element={<Tabs />} />
          </Route>``
          {/* <Route path="hotelmanage" element={<HotelManage />}>
            {" "}
          </Route> */}
        </Route>
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
//src/components/Tab
//pages/History/components
//            import Tab
//             /All.jsx
//             /Accepted.jsx
//             /Canceled.jsx
// function AllTab({Tab, context}) {
//   const listData = []
//   const

//   return (
//     <Tab
//       context={listData}
//     >

//     </Tab>
//   )
// }

// <History>
//   const histories

//   useEffect(() => {
//     fetch()

//     setState()
//   })

//   <Header />
//   <div>
//     <nav></nav>
//     <Outlet context={histories}/>
//   </div>
// </History>
