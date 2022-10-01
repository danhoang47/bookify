import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "./DefaultLayout.module.scss";
import { memo } from "react";
import { Outlet } from "react-router-dom";

function DefaultLayout() {
  console.log("layout rerender");

  return (
    <div className={styles["default-layout"]}>
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
}

export default memo(DefaultLayout);
