async function newPassowrdUpdate(userId, newPassword) {
  const url = "http://localhost:8080/bookify/api/user/changePassword/" + userId;
  const accountForm = new FormData();
  accountForm.append("newPassword", newPassword);
  const options = {
    method: "POST",
    body: accountForm,
  };

  return await fetch(url, options).then((response) => response.json());
}

export default newPassowrdUpdate;
