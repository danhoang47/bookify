import { lazy, Suspense, useState } from "react";
import basicInforStyles from "./BasicInformation.module.scss";
const BasicInformationForm = lazy(() => import("./BasicInformationForm"));

function BasicInformation({ setNextTabValid }) {

    return (
        <div id={basicInforStyles["basic-information"]}>
            <h3 className={basicInforStyles["basic-infor-header"]}>
                Cung cấp một vài thông tin cơ bản về khách sạn của bạn.
            </h3>
            <div className={basicInforStyles["basic-infor-body"]}>
                <Suspense fallback={<div>Loading...</div>}>
                    <BasicInformationForm
                        className={basicInforStyles["basic-infor-form"]}
                        setNextTabValid={setNextTabValid}
                    />
                </Suspense>
            </div>
        </div>
    );
}

export default BasicInformation;