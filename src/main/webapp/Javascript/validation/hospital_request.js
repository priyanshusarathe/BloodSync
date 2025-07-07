/**
 * hospital request validation
 */
function validation() {
  let hospitalname = document.getElementById("hospital").value.trim();
  let Bloodunit = document.getElementById("Blood").value.trim();

  if (hospitalname === "") {
    alert("please enter a correct hospital name");
    return false;
  }
  Bloodunit = Number(Bloodunit);
  if (Bloodunit <= 0) {
    alert("please enter a valid blood unit");
    return false;
  }
  return true;
}
