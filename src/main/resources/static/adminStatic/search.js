function search() {
    var id = document.getElementById("search_by_id");
    window.location.replace("admin/show/" + id.value);
}