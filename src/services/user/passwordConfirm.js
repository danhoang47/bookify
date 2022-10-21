async function compareCurrentPassword(userId, currentPassword) {
  const url =
    "http://localhost:8080/testUpload/rest/user_detail/compareCurrentPassword/" +
    userId;
  const accountForm = new FormData();
  accountForm.append("currentPassword", currentPassword);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => response.json());
}

export default compareCurrentPassword;
