import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "./DefaultLayout.module.scss";
import { memo } from "react";
import { Outlet } from "react-router-dom";
import { Box } from '@mui/material';

function DefaultLayout() {
  console.log("layout rerender");

  return (
    <div className={styles["default-layout"]}>
      <Header />
      <Box sx={{
        position: 'relative',
        top: '72.78px',
      }}>
        <Outlet />
      </Box>
      {/* <Footer /> */}
    </div>
  );
}

export default memo(DefaultLayout);
