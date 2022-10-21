import basicInforStyles from './BasicInformation.module.scss';
import BasicInformationForm from "./BasicInformationForm";

function BasicInformation() {
    return (  
        <div id={basicInforStyles['basic-information']}>
            <div className={basicInforStyles['basic-infor-header']}>
                Cung cấp một vài thông tin cở bản về khách sạn của bạn.
            </div>
            <div className={basicInforStyles['basic-infor-body']}>
                <BasicInformationForm className={basicInforStyles['basic-infor-form']}/>
            </div>
        </div>
    );
}

export default BasicInformation;