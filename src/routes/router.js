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
  List,
} from "@/pages";
import DefaultLayout from "@/layouts/DefaultLayout";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      <Route path="/" element={<DefaultLayout />}>
        <Route path="" element={<Home />} />
        <Route path="profile" element={<Profile />} />
        <Route path="profile/info" element={<PersonalInfo />} />
        <Route path="profile/log&sec" element={<LoginandSecurity />} />
        <Route path="profile/history" element={<BookingHistory />}>
          <Route path="" element={<List />} />
          <Route
            path="today"
            element={<List date={"16/8/2022"} status={null} />}
          />
          <Route path="cancle" element={<List status={false} />} />
          <Route path="booked" element={<List status={true} />} />
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
