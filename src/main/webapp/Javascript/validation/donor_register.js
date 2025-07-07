/**
 *
 */

function validateForm() {
  let name = document.getElementById("name").value.trim();
  let contact = document.getElementById("contact").value.trim();
  if (name === "") {
    alert("please entr a full name");
    return false;
  }
  let city = document.getElementById("city").value.trim();
  if (city === "") {
    alert("please enter city  name");
    return false;
  }
  if (contact.length != 10) {
    alert("please enter 10 digit no");
    return false;
  }
  return true;
}
