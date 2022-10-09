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
          <Route path="log&sec" element={<LoginandSecurity />} />
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
      </Route>
      <Route path="introduction" element={<DefaultLayout />}>
        <Route path="" element={<Introduction />} />
      </Route>
    </Route>
  )
);

export default router;

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
