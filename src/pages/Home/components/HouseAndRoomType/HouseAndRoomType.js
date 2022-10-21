import styles from './HouseAndRoomType.module.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function HouseAndRoomType({ houseAndRoomTypes, currentType, handlePicked}) {
    return (  
        <div id={styles['house-room-type']}>
            <h4 className={styles['house-room-heading']}>
                Loại Nhà\Phòng
            </h4>
            <div className={styles['house-room-picker']}>
                {
                    houseAndRoomTypes.map(({id, type, icon}) => (
                        <div 
                            key={id}
                            className={[
                                styles['type-item'],
                                currentType === id ? styles['active'] : ''
                            ].join(' ')}
                            onClick={(event) => {
                                event.stopPropagation();
                                if (currentType !== null && currentType === id) {
                                    handlePicked(null);
                                }
                                else {
                                    handlePicked(id);
                                }
                            }}
                        > 
                            <FontAwesomeIcon icon={icon} />
                            <p className={styles['type-title']}>{type}</p>
                        </div>
                    ))
                }
            </div>
        </div>
    );
}

export default HouseAndRoomType;