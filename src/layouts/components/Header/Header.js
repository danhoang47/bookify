import IconButton from "../IconButton";
import SearchBox from "../SearchBox";
import ProfileHeaderNav from "../ProfileHeaderNav";
import { Logo } from "@/components";
import { faBookmark, faBell } from "@fortawesome/free-solid-svg-icons";
import { Grid, Box } from "@mui/material";

const NotificationIconButton = IconButton;
const BookmarkIconButton = IconButton;

function Header() {
    return (
        <Box sx={{
          padding: '1em 0',
          position: 'fixed',
          zIndex: '2',
          left: '0',
          right: '0',
          top: '0',
          backgroundColor: 'white'
        }}>
            <Grid container alignItems={"center"}>
                <Grid item md={2}>
                    <Logo>
                        <h3>Bookify</h3>
                    </Logo>
                </Grid>
                <Grid item md={8}>
                    <SearchBox/>
                </Grid>
                <Grid item md={2}>
                    <Box sx={{
                      display: "flex",
                      justifyContent: "flex-end",
                      alignItems: "center"
                    }}>
                      <BookmarkIconButton icon={faBookmark} />
                      <NotificationIconButton icon={faBell} />
                      <ProfileHeaderNav />
                    </Box>
                </Grid>
            </Grid>
        </Box>
    );
}

export default Header;
