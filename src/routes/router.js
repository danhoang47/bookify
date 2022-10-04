import {
    createBrowserRouter,
    createRoutesFromElements,
    Route,
} from "react-router-dom";
import { Home, Profile, PersonalInfo, LoginandSecurity } from "@/pages";
import { DefaultLayout, ProfileLayout } from "@/layouts";

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route>
            <Route path="/" element={<DefaultLayout />}>
                <Route path="" element={<Home />} />
            </Route>
            <Route path="profile" element={<ProfileLayout />}>
                <Route path="" element={<Profile />} />
                <Route path="info" element={<PersonalInfo />} />
                <Route path="loginandsecurity" element={<LoginandSecurity />} />
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
