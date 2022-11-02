const toastType = {
    SUCCESS: "success",
    FAILURE: "failure",
}

const getSucessToastMessage = payload => ({
    ...payload,
    type: toastType.SUCCESS
})

const getFailureToastMessage = payload => ({
    ...payload,
    type: toastType.SUCCESS
})

export default function toastMessageReducer(state, modal) {
    switch(modal.type) {
        case toastType.SUCCESS:
            return;
        case toastType.FAILURE:
            return;
        default:
            throw new Error("Invalid Toast Message Type");
    }
}

export {
    toastType,
    getFailureToastMessage,
    getSucessToastMessage
}