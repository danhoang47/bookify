import { Grid, Box } from '@mui/material';
import hotelStyles from './Hotel.module.scss';
import { Album } from './components';
import { images } from './datas';

function Hotel() {
    const backgroundImage = 'photo/so-dien-thoai-le-tan-dat-phong-vinpearl-nam-hoi-an-1.jpg';

    return (  
        <div id={hotelStyles['hotel']}>
            <Grid container justifyContent={'center'}>
                <Grid item xs={10} >
                    <Album backgroundImage={backgroundImage} images={images}/>
                    <Box sx={{
                        marginTop: '2em'
                    }}>
                        <div className={hotelStyles['left']}>
                            {/* Hotel Information */}
                        </div>
                        <div className={hotelStyles['right']}>
                            {/* Booking Form */}
                        </div>
                    </Box>
                </Grid>
            </Grid>
        </div>
    );
}

export default Hotel;

