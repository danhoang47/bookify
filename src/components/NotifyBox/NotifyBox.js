import DropdownBox from "../DropdownBox";
import NotifyItem from "../NotifyItem";
import { useMemo, useState } from "react";

function NotifyBox({ notifs, setNotifs }) {
    const handleClick = (readNotif) => {
        setNotifs((list) => {
            return list.reduce((prev, notif) => {
                if (notif.id !== readNotif.id) {
                    return prev
                } else {
                    return [...prev, {
                        ...readNotif,
                        isRead: true
                    }]
                }
            }, [])
        })
    }
    const [index, setIndex] = useState(0);

    const tabs = useMemo(() => ([
        {
            title: 'Tất cả',
            list: notifs,
            index: 0,
            role: 0,
        },
        {
            title: 'Chưa đọc',
            list: notifs.filter(({ isRead }) => !isRead),
            index: 1,
            role: 1,
        },
        {
            title: 'Đặt phòng',
            index: 2,
            list: notifs.filter(({ notifyType }) => notifyType === 3 || notifyType === 4),
            role: 1,
        }, 
        {
            title: 'Đơn đặt phòng',
            index: 3,
            list: notifs.filter(({ notifyType }) => notifyType === 0),
            role: 2
        }
    ]), [notifs])

    return (  
        <DropdownBox 
            heading={"Thông báo"}
            extraButtonTittle={"Đánh dấu đã đọc"}
            isScrollable={notifs.length > 8}
        >
            {
                tabs[index].list.map((notif) => (
                    <NotifyItem 
                        notif={notif}
                        key={notif.id}
                        handleClick={handleClick}
                    />
                ))
            }
        </DropdownBox>
    );
}

export default NotifyBox;