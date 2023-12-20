function addImage() {
    var input = document.getElementById("inputImage");

    fetch("newImage", {
      method: "POST",
      body: JSON.stringify({
        src: input.value
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    }).then((response) => response.json())
        .then((json) => window.alert(json));

    input.value = "";
}

function removeImage(id) {
   fetch("/admin/removeImage", {
     method: "DELETE",
     body: JSON.stringify({
       id: id,
       src: "",
       idPlaceMark: 0
     }),
     headers: {
       "Content-type": "application/json; charset=UTF-8"
     }
   }).then((response) => response.json())
       .then((json) => {
         window.alert(json);
         location.reload();
       });
}