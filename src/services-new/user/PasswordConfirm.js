import { CheckStatus } from "@/utils/validation";
async function compareCurrentPassword(currentPassword) {
  const url = "http://localhost:3001/user/compareCurrentPassword/";
  const accountForm = new FormData();
  accountForm.append("currentPassword", currentPassword);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => {
    if (CheckStatus(response.status)) return response.json();
    return CheckStatus(response.status);
  });
}
export default compareCurrentPassword;
