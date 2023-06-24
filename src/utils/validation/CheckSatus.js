import refreshJwt from "@/services-new/user/refershJwt";

export default async function CheckStatus(status) {
  const successRegex = /2\d\d/g;
  const unAuthorRegex = /4\d\d/g;
  const internalRegex = /5\d\d/g;
  if (status === 469) {
    return await refreshJwt();
  }
  if (successRegex.test(status)) {
    //refetch token here
    return true;
  } else if (unAuthorRegex.test(status) || internalRegex.test(status)) {
    return false;
  }
}
