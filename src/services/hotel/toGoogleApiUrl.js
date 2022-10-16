import googleApiKey from "./apiKey";

function toGoogleApiUrl(url) {
    return url + `&key=${googleApiKey}`;
}

export default toGoogleApiUrl;