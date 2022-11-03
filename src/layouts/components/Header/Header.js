import IconButton from "../IconButton";
import SearchBox from "../SearchBox";
import ProfileHeaderNav from "../ProfileHeaderNav";
import { UserContext } from "@/utils/contexts";
import { BookmarkBox, Logo } from "@/components";
import { faBookmark, faBell } from "@fortawesome/free-regular-svg-icons";
import { Grid, Box } from "@mui/material";
import headerStyles from './Header.module.scss';
import { useContext } from "react";

const NotificationIconButton = IconButton;
const BookmarkIconButton = IconButton;

function Header({ location = '' }) {
    const { user } = useContext(UserContext);


    return (
        <div className={headerStyles['header']} >
            <Grid container alignItems={"center"} justifyContent={'center'}>
                <Grid item md={2}>
                    <Logo>
                        <h3>Bookify</h3>
                    </Logo>
                </Grid>
                <Grid item md={location.includes('/hotel') ? 6 : 8}>
                    <SearchBox/>
                </Grid>
                <Grid item md={2}>
                    <Box sx={{
                      display: "flex",
                      justifyContent: "flex-end",
                      alignItems: "center"
                    }}>
                      <BookmarkIconButton icon={faBookmark} >
                        <BookmarkBox />
                      </BookmarkIconButton>
                      <NotificationIconButton icon={faBell} />
                      <ProfileHeaderNav />
                    </Box>
                </Grid>
            </Grid>
        </div>
    );
}

export default Header;
