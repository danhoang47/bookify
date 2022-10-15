import { Grid, Box } from "@mui/material";
import { BannerCarousel } from "./components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faUmbrellaBeach,
    faPanorama,
    faHouseChimney,
    faHotel,
    faWaterLadder
} from '@fortawesome/free-solid-svg-icons'
import homeStyles from './Home.module.scss';

// testing purpose only
const trendingHotels = [
    {
        backgroundImage: 'photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg',
        name: 'Hotel 1'
    },
    {
        backgroundImage: 'photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg',
        name: 'Hotel 2'
    },
    {
        backgroundImage: 'photo/Hotel-Gardens-The-10-Most-Beautiful-Around-the-World-1.jpg',
        name: 'Hotel 3'
    },
]

function Home() {

    return (
        <Grid container spacing={0}>
            <Grid item xs={12}>
                <Box sx={{
                    borderRadius: '1.6em',
                    overflow: 'hidden',
                }}>
                    <BannerCarousel trendingHotels={trendingHotels}/>
                </Box>
            </Grid>
            <Grid item xs={12}>
                <div className={homeStyles['filter-section']}>
                    
                </div>
            </Grid>
        </Grid>
    );
}

export default Home;
