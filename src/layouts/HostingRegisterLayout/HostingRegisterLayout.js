import { useEffect } from "react";
import { Outlet, useHref } from "react-router-dom";
import HostingRegisterHeader from "../components/HostingRegisterHeader";

function HostingRegisterLayout() {
    const href = useHref();

    return (  
        <>
            <HostingRegisterHeader location={href}/>
            <Outlet />
        </>
    );
}

export default HostingRegisterLayout;