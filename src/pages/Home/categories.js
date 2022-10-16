import {
    faUmbrellaBeach,
    faPanorama,
    faHouseChimney,
    faHotel,
    faWaterLadder,
    faMugHot,
    faBuilding
} from '@fortawesome/free-solid-svg-icons'

/**
 * Căn hộ / Nhà / Phục vụ bữa sáng / Thiết kế riêng / 
 */

const categories = [
    {
        filterType: 'hotel',
        name: 'Căn hộ',
        filterTypeId: '1',
        icon: faBuilding
    },
    {
        filterType: 'hotel',
        name: 'Nhà nhỏ',
        filterTypeId: '2',
        icon: faHouseChimney
    },
    {
        filterType: 'hotel',
        name: 'Phục vụ bữa sáng',
        filterTypeId: '3',
        icon: faMugHot
    },
    {
        filterType: 'hotel',
        name: 'Thiết kế riêng',
        filterTypeId: '4',
        icon: faHotel
    },
    {
        filterType: 'amenity',
        name: 'Bãi biển',
        filterTypeId: '1',
        icon: faUmbrellaBeach
    },
    {
        filterType: 'amenity',
        name: 'Khung cảnh đẹp',
        filterTypeId: '2',
        icon: faPanorama
    },
    {
        filterType: 'amenity',
        name: 'Hồ bơi',
        filterTypeId: '3',
        icon: faWaterLadder
    },
]

export default categories;