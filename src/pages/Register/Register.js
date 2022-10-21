import { Jumbotron, TabBar } from "./components";
import { Grid, Box } from "@mui/material";
import { RegisterContext } from "@/utils/contexts";
import registerStyles from "./Register.module.scss";
import { useState, useMemo } from "react";
import tabs from "./tabs";
import {
    basicHotelInforInitState,
} from './registerInitStates'

function Register() {
    // show BasicInformation first
    const [inputTabIndex, setInputTabIndex] = useState(0);
    const [basicHotelInfor, setBasicHotelInfo] = useState(
        basicHotelInforInitState
    );
    const registerContextValue = useMemo(() => ({
        basicHotelInfor, 
        setBasicHotelInfo
    }), [basicHotelInfor])

    return (
        <RegisterContext.Provider value={registerContextValue}>
            <div id={registerStyles["register"]}>
                <Grid container>
                    <Grid item xs={4} className={registerStyles["left"]}>
                        <Jumbotron />
                    </Grid>
                    <Grid item xs={8} className={registerStyles["right"]}>
                        <Box
                            sx={{
                                padding: "4em 0",
                            }}
                        >
                            {tabs[inputTabIndex].render()}
                        </Box>
                        <TabBar
                            inputTabIndex={inputTabIndex}
                            handleChangeTab={setInputTabIndex}
                            tabIdList={tabs.map(({ id }) => id)}
                        />
                    </Grid>
                </Grid>
            </div>
        </RegisterContext.Provider>
    );
}

export default Register;
