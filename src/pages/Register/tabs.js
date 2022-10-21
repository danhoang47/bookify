import {
    BasicInfomation, // tab 0
    ExtraInformation, 
    ImageSection,
    RoomInfomation,
    AmenityInformation,
} from "./components";

const tabs = [
    {
        id: 0,
        render: () => {
            return <BasicInfomation />
        } 
    },
    {
        id: 1,
        render: () => {
            return <AmenityInformation />
        } 
    },
    {
        id: 2,
        render: () => {
            return <RoomInfomation />
        } 
    },
    {
        id: 3,
        render: () => {
            return <ImageSection />
        } 
    },
    {
        id: 4,
        render: () => {
            return <ExtraInformation />
        } 
    },
]

export default tabs;